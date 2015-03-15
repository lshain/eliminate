/*
 * 
 */
package singlePlayer;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class SinglePlayerMsg.
 */
@SuppressWarnings("serial")
public class SinglePlayerMsg implements Serializable {

	/** The Constant GAME_START. */
	public static final int GAME_START = 1;

	/** The Constant GAME_END. */
	public static final int GAME_END = 2;

	/** The items. */
	public ArrayList<Integer> items;

	/** The type. */
	private int type = 0;

	/** The score. */
	private int score = 0;

	/** The combo. */
	private int combo = 0;

	/** The gold. */
	private int gold = 0;

	/**
	 * Instantiates a new single player msg.
	 * 
	 * @param items
	 *            the items
	 */
	public SinglePlayerMsg(ArrayList<Integer> items) {
		this.items = items;
		this.type = GAME_START;
	}

	/**
	 * Instantiates a new single player msg.
	 * 
	 * @param score
	 *            the score
	 * @param combo
	 *            the combo
	 * @param gold
	 *            the gold
	 */
	public SinglePlayerMsg(int score, int combo, int gold) {
		this.score = score;
		this.combo = combo;
		this.gold = gold;
		this.type = GAME_END;
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
	 * Gets the gold.
	 * 
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public ArrayList<Integer> getItems() {
		return items;
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
