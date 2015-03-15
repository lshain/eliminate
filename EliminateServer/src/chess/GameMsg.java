/*
 * 
 */
package chess;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GameMsg.
 */
@SuppressWarnings("serial")
public class GameMsg implements Serializable {

	/** The Constant GAME_START. */
	public static final int GAME_START = 1;

	/** The Constant GAME_END. */
	public static final int GAME_END = 2;

	/** The Constant GAME_MSG. */
	public static final int GAME_MSG = 3;

	/** The Constant GAME_STOP. */
	public static final int GAME_STOP = 4;

	/** The rest time. */
	private int restTime;

	/** The score. */
	private int score;

	/** The max combo num. */
	private int maxComboNum;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new game msg.
	 * 
	 * @param type
	 *            the type
	 */
	public GameMsg(int type) {
		this.type = type;
	}

	/**
	 * Instantiates a new game msg.
	 * 
	 * @param restTime
	 *            the rest time
	 * @param score
	 *            the score
	 * @param maxComboNum
	 *            the max combo num
	 */
	public GameMsg(int restTime, int score, int maxComboNum) {
		this.type = GAME_MSG;
		this.restTime = restTime;
		this.score = score;
		this.maxComboNum = maxComboNum;
	}

	/**
	 * Gets the max combo num.
	 * 
	 * @return the max combo num
	 */
	public int getMaxComboNum() {
		return maxComboNum;
	}

	/**
	 * Gets the rest time.
	 * 
	 * @return the rest time
	 */
	public int getRestTime() {
		return restTime;
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}
}
