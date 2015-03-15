package user;

import java.io.Serializable;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User extends Observable implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant SINGLE_PLAYER_GAME. */
	public static final int SINGLE_PLAYER_GAME = 1;

	/** The Constant COOPERATE_GAME. */
	public static final int COOPERATE_GAME = 2;

	/** The Constant FIGHT_GAME. */
	public static final int FIGHT_GAME = 3;

	/** The user id. */
	private String userId = "";

	/** The password. */
	private String password = "";

	/** The gold. */
	private int gold = 0;

	/** The score. */
	private int score = 0;

	/** The combo. */
	private int combo = 0;

	/** The average. */
	private int average = 0;

	/** The total. */
	private int total = 0;// 总游戏局数

	/** The multi total. */
	private int multiTotal = 0;// 多人游戏总局数

	// private int stand;

	/** The game prepared. */
	private boolean gamePrepared = false;

	/** The game type. */
	private int gameType = 0;

	/**
	 * Instantiates a new user.
	 * 
	 * @param userId
	 *            the user id
	 * @param password
	 *            the password
	 */
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	/**
	 * Adds the gold.
	 * 
	 * @param gold
	 *            the gold
	 */
	public void addGold(int gold) {
		this.gold += gold;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object user) {
		if (user instanceof User) {
			return this.getUserId() == ((User) user).getUserId();
		}
		return false;
	}

	/**
	 * Gets the average.
	 * 
	 * @return the average
	 */
	public int getAverage() {
		return average;
	}

	/**
	 * Gets the combo.
	 * 
	 * @return the combo
	 */
	public int getCombo() {
		return combo;
	}

	/**
	 * Gets the game prepared.
	 * 
	 * @return the game prepared
	 */
	public boolean getGamePrepared() {
		return gamePrepared;
	}

	/**
	 * Gets the game type.
	 * 
	 * @return the game type
	 */
	public int getGameType() {
		return gameType;
	}

	/**
	 * Gets the gold.
	 * 
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Gets the multi total.
	 * 
	 * @return the multi total
	 */
	public int getMultiTotal() {
		return multiTotal;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
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
	 * Gets the total.
	 * 
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	// 获取数据
	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	// public void setStand(int stand){
	// this.stand = stand;
	// }
	//
	// public int getStand(){
	// return stand;
	// }
	//
	/**
	 * Inform.
	 */
	public void inform() {

	}

	/**
	 * Sets the average.
	 * 
	 * @param average
	 *            the new average
	 */
	public void setAverage(int average) {
		this.average = average;
	}

	/**
	 * Sets the combo.
	 * 
	 * @param combo
	 *            the new combo
	 */
	public void setCombo(int combo) {
		this.combo = combo;
	}

	/**
	 * Sets the game prepared.
	 * 
	 * @param gamePrepared
	 *            the new game prepared
	 */
	public void setGamePrepared(boolean gamePrepared) {
		this.gamePrepared = gamePrepared;
	}

	/**
	 * Sets the game type.
	 * 
	 * @param gameType
	 *            the new game type
	 */
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	// 设置数据
	/**
	 * Sets the gold.
	 * 
	 * @param gold
	 *            the new gold
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * Sets the multi total.
	 * 
	 * @param multiTotal
	 *            the new multi total
	 */
	public void setMultiTotal(int multiTotal) {
		this.multiTotal = multiTotal;
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
	 * Sets the total.
	 * 
	 * @param total
	 *            the new total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}