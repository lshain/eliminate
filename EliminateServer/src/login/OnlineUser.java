/*
 * 
 */
package login;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import room.RoomList;
import room.RoomMsg;
import user.User;
import controller.HandleInput;

// TODO: Auto-generated Javadoc
/**
 * The Class OnlineUser.
 */
public class OnlineUser extends Observable {

	/** The user map. */
	private static Map<String, UserInfo> userMap = new HashMap<String, UserInfo>();

	/**
	 * Adds the user.
	 * 
	 * @param socket
	 *            the socket
	 * @param user
	 *            the user
	 * @param output
	 *            the output
	 * @param input
	 *            the input
	 * @return true, if successful
	 */
	public static boolean addUser(Socket socket, User user,
			ObjectOutputStream output, ObjectInputStream input) {
		if (userMap.containsKey(user.getUserId())) {
			deleteUser(user);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		userMap.put(user.getUserId(), new UserInfo(user, socket, output,
				new HandleInput(user, input)));
		System.out.println("游戏在线人数:" + userMap.size());
		return true;
	}

	/**
	 * Delete user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public static boolean deleteUser(User user) {
		if (userMap.get(user.getUserId()) == null) {
			return true;
		}
		userMap.get(user.getUserId()).closeSocket();
		long roomID = userMap.get(user.getUserId()).getRoomID();
		userMap.remove(user.getUserId()).getUser();
		if (roomID != 0) {
			System.out.println(roomID);
			System.out.println("delete Room!");
			RoomList.getRoom(roomID).deleteUser(user);
		}
		if (RoomList.getRoom(roomID) != null) {
			for (String temp : RoomList.getRoom(roomID).getUserList()) {
				getSockets(temp).writeObject(
						new RoomMsg(RoomList.getRoom(roomID),
								RoomMsg.UPDATE_ROOM_INFO));
			}
		}
		System.out.println("游戏在线人数:" + userMap.size());
		return true;
	}

	/**
	 * Gets the map.
	 * 
	 * @return the map
	 */
	public static Map<String, UserInfo> getMap() {
		return userMap;
	}

	/**
	 * Gets the sockets.
	 * 
	 * @param userID
	 *            the user id
	 * @return the sockets
	 */
	public static UserInfo getSockets(String userID) {
		return userMap.get(userID);
	}

	/**
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public static Set<String> getUsers() {
		return userMap.keySet();
	}

	/**
	 * Checks if is online.
	 * 
	 * @param userID
	 *            the user id
	 * @return true, if is online
	 */
	public static boolean isOnline(String userID) {
		return userMap.containsKey(userID);
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public static boolean updateUser(User user) {
		if (userMap.containsKey(user)) {
			userMap.get(user.getUserId()).setUser(user);
		}
		return false;
	}

	/**
	 * Instantiates a new online user.
	 */
	public OnlineUser() {

	}

	/**
	 * Inform.
	 */
	public void inform() {
		setChanged();
		inform();
	}
}
