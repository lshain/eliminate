package room;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import login.OnlineUser;
import chess.FightGameList;
import chess.MutiPlayerGameList;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomList.
 */
public class RoomList {

	/** The room list1. */
	private static Map<Long, Room> roomList1 = new HashMap<Long, Room>();

	/** The room list2. */
	private static Map<Long, Room> roomList2 = new HashMap<Long, Room>();

	/**
	 * Adds the room.
	 * 
	 * @param room
	 *            the room
	 */
	public static void addRoom(Room room) {
		if (room != null) {
			if (room.getType() == Room.COOPERATE) {
				roomList1.put(room.getID(), room);
			} else if (room.getType() == Room.FIGHT_AGAINST) {
				roomList2.put(room.getID(), room);
			}
			updateRooms();
		}
	}

	/**
	 * Gets the room.
	 * 
	 * @param id
	 *            the id
	 * @return the room
	 */
	public static Room getRoom(long id) {
		Room room = roomList1.get(id);
		if (room == null) {
			room = roomList2.get(id);
		}
		return room;
	}

	/**
	 * Gets the room list1.
	 * 
	 * @return the room list1
	 */
	public static Map<Long, Room> getRoomList1() {
		return roomList1;
	}

	/**
	 * Gets the room list2.
	 * 
	 * @return the room list2
	 */
	public static Map<Long, Room> getRoomList2() {
		return roomList2;
	}

	/**
	 * Removes the room.
	 * 
	 * @param room
	 *            the room
	 */
	public static void removeRoom(Room room) {
		if (room.getType() == Room.COOPERATE) {
			roomList1.remove(room.getID());
			MutiPlayerGameList.getGame(room.getID()).stop();
			MutiPlayerGameList.removeGame(room.getID());
		} else if (room.getType() == Room.FIGHT_AGAINST) {
			roomList2.remove(room.getID());
			FightGameList.getGame(room.getID()).stop();
			FightGameList.removeGame(room.getID());
		}

		System.out.println("RoomList > removeRoom:" + room.getID());
		updateRooms();
	}

	/**
	 * Update rooms.
	 */
	public static void updateRooms() {
		Set<String> users = OnlineUser.getUsers();
		RoomMsg roomMsg = new RoomMsg(roomList1, roomList2);
		for (String userID : users) {
			System.out.println(userID + ": " + roomMsg.getRoomList1()
					+ "--" + roomMsg.getRoomList2());
			OnlineUser.getSockets(userID).writeObject(roomMsg);
		}
	}
}
