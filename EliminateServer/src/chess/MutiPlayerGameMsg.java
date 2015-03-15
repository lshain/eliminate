/*
 * 
 */
package chess;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class MutiPlayerGameMsg.
 */
@SuppressWarnings("serial")
public class MutiPlayerGameMsg implements Serializable {

	/** The Constant BUY_ITEM. */
	public static final int BUY_ITEM = 1;

	// ÎÞµÐÊ±¼ä
	/** The Constant INVINCIBLE_TIME_START. */
	public static final int INVINCIBLE_TIME_START = 2;

	/** The Constant INVINCIBLE_TIME_END. */
	public static final int INVINCIBLE_TIME_END = 3;

	//
	/** The Constant GAME_END. */
	public static final int GAME_END = 5;

	//
	/** The Constant UPDATE_GAME_TIME. */
	public static final int UPDATE_GAME_TIME = 6;

	/** The Constant UPDATE_GAME_SCORE. */
	public static final int UPDATE_GAME_SCORE = 7;

	/** The Constant UPDATE_GAME_INFO. */
	public static final int UPDATE_GAME_INFO = 8;

	/** The Constant HINT_TOOL. */
	public static final int HINT_TOOL = 9;

	/** The Constant BOMB_TOOL. */
	public static final int BOMB_TOOL = 10;

	/** The Constant TIME_TOOL. */
	public static final int TIME_TOOL = 11;

	/** The Constant SCORE_TOOL. */
	public static final int SCORE_TOOL = 12;

	/** The Constant SUPER_TOOL. */
	public static final int SUPER_TOOL = 13;

	/** The Constant KICK_PLAYER. */
	public static final int KICK_PLAYER = 14;

	/** The Constant SHOW_HINT. */
	public static final int SHOW_HINT = 15;

	/** The Constant HIND_HINT. */
	public static final int HIND_HINT = 16;

	/** The Constant CHANGE_TEAM. */
	public static final int CHANGE_TEAM = 17;
	
	public static final int FROZEN = 18;

	/** The player id. */
	private String playerID;

	/** The type. */
	private int type = 0;

	/** The rest time. */
	private int restTime = 0;

	/** The score. */
	private int score = 0;

	/** The stand. */
	private int stand = 0;

	/** The gold. */
	private int gold = 0;
	
	private int frozenType = -1;

	/** The hide hints. */

	/** The item list. */
	private ArrayList<Integer> itemList = new ArrayList<Integer>();

	/** The hints. */
	private ArrayList<Point> hints = new ArrayList<Point>();

	/**
	 * Instantiates a new muti player game msg.
	 * 
	 * @param itemList
	 *            the item list
	 * @param stand
	 *            the stand
	 */
	public MutiPlayerGameMsg(ArrayList<Integer> itemList, int stand) {
		this.itemList.addAll(itemList);
		type = BUY_ITEM;
		this.stand = stand;
	}

	/**
	 * Instantiates a new muti player game msg.
	 * 
	 * @param type
	 *            the type
	 * @param stand
	 *            the stand
	 */
	public MutiPlayerGameMsg(int type, int stand) {
		this.type = type;
		this.stand = stand;
	}

	/**
	 * Instantiates a new muti player game msg.
	 * 
	 * @param playerID
	 *            the player id
	 * @param stand
	 *            the stand
	 */
	public MutiPlayerGameMsg(String playerID, int stand) {
		this.playerID = playerID;
		this.type = KICK_PLAYER;
		this.stand = stand;
	}

	/**
	 * Gets the god.
	 * 
	 * @return the god
	 */
	public int getGod() {
		return gold;
	}

	/**
	 * Gets the hint.
	 * 
	 * @return the hint
	 */
	public ArrayList<Point> getHint() {
		return hints;
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public ArrayList<Integer> getItems() {
		return itemList;
	}

	/**
	 * Gets the player id.
	 * 
	 * @return the player id
	 */
	public String getPlayerID() {
		return playerID;
	}

	/**
	 * Gets the rest time.
	 * 
	 * @return the rest time
	 */
	public int getRestTime() {
		return this.restTime;
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
	 * Gets the stand.
	 * 
	 * @return the stand
	 */
	public int getStand() {
		return stand;
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
	 * Sets the gold.
	 * 
	 * @param gold
	 *            the new gold
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * Sets the hints.
	 * 
	 * @param hints
	 *            the new hints
	 */
	public void setHints(ArrayList<Point> hints) {
		this.hints = hints;
	}

	/**
	 * Sets the player id.
	 * 
	 * @param playerID
	 *            the new player id
	 */
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	/**
	 * Sets the rest time.
	 * 
	 * @param restTime
	 *            the new rest time
	 */
	public void setRestTime(int restTime) {
		this.restTime = restTime;
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
	
	public void setFrozenType(int frozenType){
		this.frozenType = frozenType;
	}
	
	public int getFrozenType(){
		return frozenType;
	}
}
