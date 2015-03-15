/*
 * 
 */
package room;

import java.util.Date;

import login.OnlineUser;
import login.UserInfo;
import user.User;
import chess.FightGame;
import chess.FightGameList;
import chess.MutiPlayerGame;
import chess.MutiPlayerGameList;
import chess.MutiPlayerGameMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomControl.
 */
public class RoomControl {

	/**
	 * Change team.
	 * 
	 * @param msg
	 *            the msg
	 */
	public static void changeTeam(MutiPlayerGameMsg msg) {
		UserInfo userInfo = OnlineUser.getSockets(msg.getPlayerID());
		if (userInfo != null) {
			Long roomID = userInfo.getRoomID();
			RoomList.getRoom(roomID).changeTeam(msg.getPlayerID());
			for (String temp : RoomList.getRoom(roomID).getUserList()) {
				OnlineUser.getSockets(temp).writeObject(
						new RoomMsg(RoomList.getRoom(roomID),
								RoomMsg.UPDATE_ROOM_INFO));
			}
		}
	}

	/**
	 * Creates the room.
	 * 
	 * @param user
	 *            the user
	 * @param room
	 *            the room
	 */
	public static void createRoom(User user, Room room) {
		leaveRoom(user, RoomList.getRoom(OnlineUser
				.getSockets(user.getUserId()).getRoomID()));
		room.setID((new Date()).getTime());
		room.addUser(user);
		RoomList.addRoom(room);
		if (room.getType() == Room.COOPERATE) {
			MutiPlayerGameList.addGame(room.getID(),
					new MutiPlayerGame(room.getID(), Room.STAND_ONE));
			OnlineUser.getSockets(user.getUserId()).getUser()
					.setGameType(User.COOPERATE_GAME);
		} else if (room.getType() == Room.FIGHT_AGAINST) {
			FightGameList.addGame(room.getID(), new FightGame(room.getID()));
			OnlineUser.getSockets(user.getUserId()).getUser()
					.setGameType(User.FIGHT_GAME);
		}
		OnlineUser.getSockets(user.getUserId()).setRoomID(room.getID());
		OnlineUser.getSockets(user.getUserId()).writeObject(
				new RoomMsg(room, RoomMsg.CREATE_SUCESS));
	}

	/**
	 * Enter room.
	 * 
	 * @param user
	 *            the user
	 * @param room
	 *            the room
	 */
	public static void enterRoom(User user, Room room) {
		if (room == null) {
			return;
		}
		leaveRoom(user, RoomList.getRoom(OnlineUser
				.getSockets(user.getUserId()).getRoomID()));
		if (room.getType() == Room.COOPERATE) {
			OnlineUser.getSockets(user.getUserId()).getUser()
					.setGameType(User.COOPERATE_GAME);
		} else if (room.getType() == Room.FIGHT_AGAINST) {
			OnlineUser.getSockets(user.getUserId()).getUser()
					.setGameType(User.FIGHT_GAME);
		}
		if (RoomList.getRoom(room.getID()).getGameState() == Room.IN_GAME) {
			OnlineUser.getSockets(user.getUserId()).writeObject(
					new RoomMsg(RoomList.getRoom(room.getID()),
							RoomMsg.ROOM_IN_GAME));
			return;
		}
		if (RoomList.getRoom(room.getID()).addUser(
				OnlineUser.getSockets(user.getUserId()).getUser())) {
			System.out.println(user.getUserId() + "Enter Room: " + room.getID()
					+ "Sucessed");
			OnlineUser.getSockets(user.getUserId()).setRoomID(room.getID());
			OnlineUser.getSockets(user.getUserId()).writeObject(
					new RoomMsg(RoomList.getRoom(room.getID()),
							RoomMsg.ENTER_SUCESS));
			for (String temp : RoomList.getRoom(room.getID()).getUserList()) {
				OnlineUser.getSockets(temp).writeObject(
						new RoomMsg(RoomList.getRoom(room.getID()),
								RoomMsg.UPDATE_ROOM_INFO));
			}

		} else {
			System.out.println(user.getUserId() + "Enter Room: "
					+ room.getID() + "Failed");
			OnlineUser.getSockets(user.getUserId()).writeObject(
					new RoomMsg(RoomList.getRoom(room.getID()),
							RoomMsg.IS_FULL));
		}
	}

	/**
	 * Leave room.
	 * 
	 * @param user
	 *            the user
	 * @param room
	 *            the room
	 */
	public static void leaveRoom(User user, Room room) {
		if (room == null || RoomList.getRoom(room.getID()) == null) {
			return;
		}
		RoomList.getRoom(room.getID()).deleteUser(user);
		OnlineUser.getSockets(user.getUserId()).setRoomID(0);
		OnlineUser.getSockets(user.getUserId()).getUser().setGameType(0);
		if (RoomList.getRoom(room.getID()) == null) {
			return;
		}
		for (String temp : RoomList.getRoom(room.getID()).getUserList()) {
			OnlineUser.getSockets(temp).writeObject(
					new RoomMsg(RoomList.getRoom(room.getID()),
							RoomMsg.UPDATE_ROOM_INFO));
		}
	}

	/**
	 * Room chat.
	 * 
	 * @param user
	 *            the user
	 * @param msg
	 *            the msg
	 */
	public static void roomChat(User user, RoomMsg msg) {
		for (String temp : RoomList.getRoom(msg.getRoom().getID())
				.getUserList()) {
			OnlineUser.getSockets(temp).writeObject(msg);
		}
	}

	/**
	 * Start game.
	 * 
	 * @param user
	 *            the user
	 * @param msg
	 *            the msg
	 */
	public static void startGame(User user, RoomMsg msg) {
		Room room = RoomList.getRoom(msg.getRoom().getID());
		if (room != null && room.getOwner().equals(user.getUserId())) {
			if (RoomList.getRoom(msg.getRoom().getID()).startGame()) {
				if (room.getType() == Room.COOPERATE) {
					MutiPlayerGameList.getGame(room.getID()).reStart();
				} else {
					FightGameList.getGame(room.getID()).reStart();
				}
				RoomList.getRoom(msg.getRoom().getID()).setGameState(
						Room.IN_GAME);
				for (String temp : RoomList.getRoom(msg.getRoom().getID())
						.getUserList()) {
					OnlineUser.getSockets(temp).writeObject(msg);
				}

				System.out.println("RoomControl: GameStart!");
				if (room.getType() == Room.COOPERATE) {
					MutiPlayerGameList.getGame(room.getID()).start();
				} else {
					FightGameList.getGame(room.getID()).start();
				}
			} else {
				OnlineUser.getSockets(user.getUserId()).writeObject(
						new RoomMsg(msg.getRoom(),
								RoomMsg.SOMEONE_NOT_PREPARED));
			}
		}
	}

}
