/*
 * 
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;

import login.LoginThread;
import login.OnlineUser;
import rank.RankController;
import rank.RankMsg;
import room.Room;
import room.RoomControl;
import room.RoomList;
import room.RoomMsg;
import singlePlayer.GameController;
import singlePlayer.SinglePlayerMsg;
import user.User;
import user.UserMsg;
import chess.ChessMsg;
import chess.FightGameList;
import chess.MutiPlayerGameList;
import chess.MutiPlayerGameMsg;
import friend.FriendControl;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class HandleInput.
 */
public class HandleInput implements Runnable {

	/** The user. */
	private User user;

	/** The input. */
	private ObjectInputStream input;

	/** The thread. */
	Thread thread = null;

	/** The stand. */
	int stand;

	/** The game type. */
	int gameType;

	/** The room id. */
	long roomID;

	/**
	 * Instantiates a new handle input.
	 * 
	 * @param user
	 *            the user
	 * @param input
	 *            the input
	 */
	public HandleInput(User user, ObjectInputStream input) {
		this.user = user;
		this.input = input;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (thread != null) {
			try {
				Object obj = input.readObject();
				System.out.println(obj);
				if (obj instanceof RoomMsg) {
					switch (((RoomMsg) obj).getType()) {
					case RoomMsg.CREATE_ROOM:
						RoomControl.createRoom(user, ((RoomMsg) obj).getRoom());
						break;
					case RoomMsg.LEAVE_ROOM:
						RoomControl.leaveRoom(user, ((RoomMsg) obj).getRoom());
						OnlineUser.getSockets(user.getUserId()).writeObject(
								new RoomMsg(null, RoomMsg.LEAVE_ROOM));
						break;
					case RoomMsg.ENTER_ROOM:
						RoomControl.enterRoom(user, ((RoomMsg) obj).getRoom());
						break;
					case RoomMsg.ROOM_CHAT:
						RoomControl.roomChat(user, (RoomMsg) obj);
						break;
					case RoomMsg.START_GAME:
						RoomControl.startGame(user, (RoomMsg) obj);
						roomID = OnlineUser.getSockets(user.getUserId())
								.getRoomID();
						stand = RoomList.getRoom(roomID).getUserStand(
								user.getUserId());
						gameType = OnlineUser.getSockets(user.getUserId())
								.getUser().getGameType();
						break;
					case RoomMsg.PREPARE_GAME:
						OnlineUser.getSockets(user.getUserId()).getUser()
								.setGamePrepared(true);
						OnlineUser.getSockets(user.getUserId())
								.writeObject(obj);
						roomID = OnlineUser.getSockets(user.getUserId())
								.getRoomID();
						stand = RoomList.getRoom(roomID).getUserStand(
								user.getUserId());
						gameType = OnlineUser.getSockets(user.getUserId())
								.getUser().getGameType();
						break;
					case RoomMsg.CANCEL_PREPARED:
						OnlineUser.getSockets(user.getUserId()).getUser()
								.setGamePrepared(false);
						OnlineUser.getSockets(user.getUserId())
								.writeObject(obj);
						break;
					case RoomMsg.ROOM_LIST:
						OnlineUser.getSockets(user.getUserId()).writeObject(
								new RoomMsg(RoomList.getRoomList1(), RoomList
										.getRoomList2()));
						break;
					case RoomMsg.MULTIPLAYER:
						OnlineUser.getSockets(user.getUserId())
								.writeObject(obj);
						break;
					}

				} else if (obj instanceof ChessMsg) {
					ChessMsg msg = (ChessMsg) obj;
					switch (msg.getType()) {
					case ChessMsg.SWAP_CHESS:
						if (gameType == User.COOPERATE_GAME) {
							MutiPlayerGameList.getGame(roomID).swapChess(
									msg.getStartPos(), msg.getEndPos());
						} else if (gameType == User.FIGHT_GAME) {
							FightGameList.getGame(roomID, stand).swapChess(
									msg.getStartPos(), msg.getEndPos());
						}
						break;
					case ChessMsg.CLICK_CHESS:
						if (gameType == User.COOPERATE_GAME) {
							MutiPlayerGameList.getGame(roomID).delete(
									msg.getStartPos().x, msg.getStartPos().y);
						} else if (gameType == User.FIGHT_GAME) {
							FightGameList.getGame(roomID, stand).delete(
									msg.getStartPos().x, msg.getStartPos().y);
						}
						break;
					}
				} else if (obj instanceof MutiPlayerGameMsg) {
					MutiPlayerGameMsg msg = (MutiPlayerGameMsg) obj;
					switch (msg.getType()) {
					case MutiPlayerGameMsg.BUY_ITEM:
						if (gameType == User.COOPERATE_GAME) {
							MutiPlayerGameList.getGame(
									OnlineUser.getSockets(user.getUserId())
											.getRoomID()).buyItem(
									msg.getItems(), user.getUserId());
						} else if (gameType == User.FIGHT_GAME) {
							FightGameList.getGame(roomID, stand).buyItem(
									msg.getItems(), user.getUserId());
						}
						break;
					case MutiPlayerGameMsg.KICK_PLAYER:
						if (user.getUserId().equals(
								RoomList.getRoom(
										OnlineUser.getSockets(user.getUserId())
												.getRoomID()).getOwner())) {
							RoomControl.leaveRoom(new User(msg.getPlayerID(),
									null), RoomList.getRoom(OnlineUser
									.getSockets(user.getUserId()).getRoomID()));
							try {
								OnlineUser.getSockets(msg.getPlayerID())
										.writeObject(msg);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						break;
					case MutiPlayerGameMsg.CHANGE_TEAM:
						RoomControl.changeTeam(msg);
						break;
					}
				} else if (obj instanceof SinglePlayerMsg) {
					SinglePlayerMsg msg = (SinglePlayerMsg) obj;
					if (msg.getType() == SinglePlayerMsg.GAME_START) {
						GameController.StartGame(msg.getItems(),
								user.getUserId());
					} else if (msg.getType() == SinglePlayerMsg.GAME_END) {
						GameController.endGame(user, msg.getScore(),
								msg.getCombo(), msg.getGold());
					}
				} else if (obj instanceof RankMsg) {
					RankMsg msg = (RankMsg) obj;
					if (msg.getType() == RankMsg.GAME_DETAIL) {
						System.out.println("fdsfsdfsd");
						RankController.getScoreRank(user.getUserId());
					} else if (msg.getType() == RankMsg.DAILYTIMES) {
						RankController.getDailyTimes(user.getUserId());
						;
					} else if (msg.getType() == RankMsg.DAILYASCORE) {
						RankController.getDailyScore(user.getUserId());
					} else if (msg.getType() == RankMsg.MULTIPLAYER) {
						RankController.getTeamRank(user.getUserId());
					}
				} else if (obj instanceof FriendMsg) {
					FriendMsg msg = (FriendMsg) obj;
					switch (msg.getType()) {
					case FriendMsg.ASK_FOR_ADD_FRIEND:
						FriendControl.askForAddFriend(user, msg.getFriendId());
						break;
					case FriendMsg.REFUSE_ADD_FRIEND:
						FriendControl.refuseAddFriend(user, msg.getFriendId());
						break;
					case FriendMsg.APPROVE_ADD_FRIEND:
						FriendControl.approveAddFriend(user, msg.getFriendId());
						break;
					case FriendMsg.DELETE_FRIEND:
						FriendControl.deleteFriend(user, msg.getFriendId());
						break;
					case FriendMsg.USER_INFO:
						FriendControl.searchUserInfo(user, msg.getFriendId());
						break;
					case FriendMsg.ALL_FRIEND:
						FriendControl.getAllFriend(user);
						break;
					case FriendMsg.ONLINE_FRIEND:
						FriendControl.getOnlineFriend(user);
						break;
					case FriendMsg.INVITE_FRIEND:
						FriendControl.InviteFriend(user.getUserId(),
								msg.getFriendId());
						break;
					case FriendMsg.INVITE_AGREE:
						Room room = RoomList.getRoom(OnlineUser.getSockets(
								msg.getFriendId()).getRoomID());
						RoomControl.enterRoom(user, room);
						break;
					case FriendMsg.INVITE_REFUSE:
						OnlineUser.getSockets(msg.getFriendId()).writeObject(
								new FriendMsg(user.getUserId(),
										FriendMsg.INVITE_REFUSE));
						;
						break;
					case FriendMsg.INVITE_IN_GAME:
						OnlineUser.getSockets(msg.getFriendId()).writeObject(
								new FriendMsg(user.getUserId(),
										FriendMsg.INVITE_IN_GAME));
						break;

					}
				} else if (obj instanceof UserMsg) {
					UserMsg msg = (UserMsg)obj;
					if(msg.getType() == UserMsg.CHANGE_PASSWORD){
						LoginThread.changePassword(user.getUserId(), msg.getPassword());
						OnlineUser.getSockets(user.getUserId()).writeObject(
								msg);
					}
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("delete User!");
				OnlineUser.deleteUser(user);
				break;
			}
		}
	}

	/**
	 * Start.
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
		}
		thread.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		thread = null;
	}

}
