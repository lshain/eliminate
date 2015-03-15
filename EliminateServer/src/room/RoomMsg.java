/*
 * 
 */
package room;

import java.io.Serializable;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomMsg.
 */
@SuppressWarnings("serial")
public class RoomMsg implements Serializable {

	/** The Constant CREATE_ROOM. */
	public static final int CREATE_ROOM = 1;

	/** The Constant ENTER_ROOM. */
	public static final int ENTER_ROOM = 2;

	/** The Constant LEAVE_ROOM. */
	public static final int LEAVE_ROOM = 3;

	/** The Constant CREATE_SUCESS. */
	public static final int CREATE_SUCESS = 4;

	/** The Constant LEAVE_SUCESS. */
	public static final int LEAVE_SUCESS = 5;

	/** The Constant ENTER_SUCESS. */
	public static final int ENTER_SUCESS = 6;

	/** The Constant ROOM_IN_GAME. */
	public static final int ROOM_IN_GAME = 16;

	/** The Constant IS_FULL. */
	public static final int IS_FULL = 7;

	/** The Constant UPDATE_ROOM_INFO. */
	public static final int UPDATE_ROOM_INFO = 8;

	/** The Constant PREPARE_GAME. */
	public static final int PREPARE_GAME = 9;

	/** The Constant START_GAME. */
	public static final int START_GAME = 10;

	/** The Constant CANCEL_PREPARED. */
	public static final int CANCEL_PREPARED = 11;

	/** The Constant SOMEONE_NOT_PREPARED. */
	public static final int SOMEONE_NOT_PREPARED = 12;

	/** The Constant ROOM_LIST. */
	public static final int ROOM_LIST = 13;

	/** The Constant ROOM_CHAT. */
	public static final int ROOM_CHAT = 14;

	/** The Constant MULTIPLAYER. */
	public static final int MULTIPLAYER = 15;

	/** The chat. */
	private String chat = "";

	/** The room. */
	private Room room;

	/** The type. */
	private int type = 1;

	/** The room list1. */
	private Map<Long, Room> roomList1 = null;

	/** The room list2. */
	private Map<Long, Room> roomList2 = null;

	/**
	 * Instantiates a new room msg.
	 * 
	 * @param roomList1
	 *            the room list1
	 * @param roomList2
	 *            the room list2
	 */
	public RoomMsg(Map<Long, Room> roomList1, Map<Long, Room> roomList2) {
		type = ROOM_LIST;
		this.roomList1 = roomList1;
		this.roomList2 = roomList2;
	}

	/**
	 * Instantiates a new room msg.
	 * 
	 * @param room
	 *            the room
	 * @param type
	 *            the type
	 */
	public RoomMsg(Room room, int type) {
		this.type = type;
		this.room = room;
	}

	/**
	 * Gets the chat content.
	 * 
	 * @return the chat content
	 */
	public String getChatContent() {
		return chat;
	}

	/**
	 * Gets the room.
	 * 
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Gets the room list1.
	 * 
	 * @return the room list1
	 */
	public Map<Long, Room> getRoomList1() {
		return roomList1;
	}

	/**
	 * Gets the room list2.
	 * 
	 * @return the room list2
	 */
	public Map<Long, Room> getRoomList2() {
		return roomList2;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the chat content.
	 * 
	 * @param chat
	 *            the new chat content
	 */
	public void setChatContent(String chat) {
		this.chat = chat;
	}
}
