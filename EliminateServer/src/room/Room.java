package room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import login.OnlineUser;
import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class Room.
 */
@SuppressWarnings("serial")
public class Room extends Observable implements Serializable {

	/** The Constant STAND_ONE. */
	public static final int STAND_ONE = 7;

	/** The Constant STAND_TWO. */
	public static final int STAND_TWO = 8;

	/** The Constant COOPERATE. */
	public static final int COOPERATE = 1;

	/** The Constant FIGHT_AGAINST. */
	public static final int FIGHT_AGAINST = 2;

	/** The Constant MAX_PLAYER_NUM. */
	public static final int MAX_PLAYER_NUM = 4;

	/** The Constant IN_GAME. */
	public static final int IN_GAME = 5;

	/** The Constant OUT_GAME. */
	public static final int OUT_GAME = 6;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		ArrayList<User> i = new ArrayList<User>();
		i.add(new User("wuzhao", "hello"));
		System.out.println(i.remove(new User("wuzhao", "hello")));
	}

	/** The type. */
	private int type = 1;

	/** The max user num. */
	private int maxUserNum = 4;

	/** The id. */
	private long id = 0;

	/** The score. */
	private int score = 0;

	/** The name. */
	private String name;

	/** The game state. */
	private int gameState = 6;

	/** The room owner. */
	private String roomOwner = "";

	/** The user list. */
	private Map<String, Integer> userList = new HashMap<String, Integer>();

	/**
	 * Instantiates a new room.
	 * 
	 * @param type
	 *            the type
	 * @param maxUserNum
	 *            the max user num
	 * @param name
	 *            the name
	 */
	public Room(int type, int maxUserNum, String name) {
		this.type = type;
		if (maxUserNum > 0 && maxUserNum <= MAX_PLAYER_NUM) {
			this.maxUserNum = maxUserNum;
		}
		this.name = name;
	}

	/**
	 * Instantiates a new room.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 */
	public Room(int type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * Adds the user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean addUser(User user) {
		if (userList.size() < maxUserNum
				&& (!userList.containsKey(user.getUserId()))) {
			if (type == Room.COOPERATE) {
				userList.put(user.getUserId(), Room.STAND_ONE);
			} else if (userList.size() % 2 == 0) {
				userList.put(user.getUserId(), Room.STAND_ONE);
			} else {
				userList.put(user.getUserId(), Room.STAND_TWO);
			}
			if (userList.size() == 1) {
				this.roomOwner = user.getUserId();
			} else {
				RoomList.updateRooms();
			}
			return true;
		}
		return false;
	}

	/**
	 * Change team.
	 * 
	 * @param userID
	 *            the user id
	 */
	public void changeTeam(String userID) {
		if (userList.containsKey(userID)) {
			int stand = userList.get(userID);
			userList.put(userID, STAND_ONE + STAND_TWO - stand);
		}
	}

	/**
	 * Delete user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean deleteUser(User user) {
		Object temp = userList.remove(user.getUserId());
		if (user.getUserId().equals(this.roomOwner) && userList.size() > 0) {
			this.roomOwner = userList.keySet().iterator().next();
		}
		if (userList.size() == 0) {
			RoomList.removeRoom(this);
			System.out.println("Room>deleteUser:" + "removeRoom");
		} else if (temp != null) {
			RoomList.updateRooms();
		}
		return temp != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Room) {
			return this.getID() == ((Room) o).getID();
		}
		return false;
	}

	/**
	 * Gets the current user num.
	 * 
	 * @return the current user num
	 */
	public int getCurrentUserNum() {
		return userList.size();

	}

	/**
	 * Gets the game state.
	 * 
	 * @return the game state
	 */
	public int getGameState() {
		return gameState;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public long getID() {
		return id;
	}

	/**
	 * Gets the max user num.
	 * 
	 * @return the max user num
	 */
	public int getMaxUserNum() {
		return maxUserNum;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the owner.
	 * 
	 * @return the owner
	 */
	public String getOwner() {
		return this.roomOwner;
	}

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Gets the table data.
	 * 
	 * @return the table data
	 */
	public String[][] getTableData() {
		if (type == Room.COOPERATE) {
			String[][] temp = new String[userList.size()][2];
			int i = 1;
			for (String user : userList.keySet()) {
				if (user.equals(roomOwner)) {
					temp[0][0] = "房主";
					temp[0][1] = roomOwner;
				} else {
					temp[i][0] = "玩家";
					temp[i][1] = user;
					i++;
				}
			}
			return temp;
		} else {
			String[][] temp = new String[userList.size()][3];
			int i = 1;
			for (String user : userList.keySet()) {
				if (user.equals(roomOwner)) {
					temp[0][0] = "房主";
					temp[0][1] = Integer.toString(userList.get(user));
					temp[0][2] = roomOwner;
				} else {
					temp[i][0] = "玩家";
					temp[i][1] = Integer.toString(userList.get(user));
					temp[i][2] = user;
					i++;
				}
			}
			return temp;
		}
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
	 * Gets the user list.
	 * 
	 * @return the user list
	 */
	public Set<String> getUserList() {
		return userList.keySet();
	}

	/**
	 * Gets the user list.
	 * 
	 * @param stand
	 *            the stand
	 * @return the user list
	 */
	public ArrayList<String> getUserList(int stand) {
		ArrayList<String> users = new ArrayList<String>();
		for (String s : userList.keySet()) {
			if (userList.get(s) == stand) {
				users.add(s);
			}
		}
		return users;
	}

	/**
	 * Gets the user stand.
	 * 
	 * @param userID
	 *            the user id
	 * @return the user stand
	 */
	public int getUserStand(String userID) {
		return userList.get(userID);
	}

	/**
	 * Checks for user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean hasUser(User user) {
		return userList.keySet().contains(user);
	}

	/**
	 * Sets the game state.
	 * 
	 * @param gameState
	 *            the new game state
	 */
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setID(long id) {
		this.id = id;
	}

	/**
	 * Sets the max user num.
	 * 
	 * @param maxUserNum
	 *            the max user num
	 * @return true, if successful
	 */
	public boolean setMaxUserNum(int maxUserNum) {
		if (maxUserNum > 0 && maxUserNum <= MAX_PLAYER_NUM) {
			this.maxUserNum = maxUserNum;
			return true;
		}
		return false;
	}

	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Start game.
	 * 
	 * @return true, if successful
	 */
	public boolean startGame() {
		for (String userID : userList.keySet()) {
			if (!OnlineUser.getSockets(userID).getUser().getGamePrepared()
					&& !userID.equals(roomOwner)) {
				return false;
			}
		}
		for (String userID : userList.keySet()) {
			OnlineUser.getSockets(userID).getUser().setGamePrepared(false);
		}
		gameState = IN_GAME;
		return true;
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 */
	public void updateUser(User user) {
		deleteUser(user);
		addUser(user);
	}
}
