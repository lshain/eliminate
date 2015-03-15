/*
 * 
 */
package chess;

import java.awt.Point;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ChessMsg.
 */
@SuppressWarnings("serial")
public class ChessMsg implements Serializable {

	/** The Constant SWAP_CHESS. */
	public static final int SWAP_CHESS = 1;

	/** The Constant CLICK_CHESS. */
	public static final int CLICK_CHESS = 2;

	/** The Constant CHESS_STATE. */
	public static final int CHESS_STATE = 3;

	/** The stand. */
	public int stand;

	/** The start_pos. */
	private Point start_pos;

	/** The end_pos. */
	private Point end_pos;

	/** The hide hint. */
	private boolean hideHint = false;

	/** The chess_state. */
	private int[][][] chess_state;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new chess msg.
	 * 
	 * @param chess_state
	 *            the chess_state
	 * @param stand
	 *            the stand
	 */
	public ChessMsg(int[][][] chess_state, int stand) {
		this.chess_state = chess_state;
		this.type = CHESS_STATE;
		this.stand = stand;
	}

	/**
	 * Instantiates a new chess msg.
	 * 
	 * @param start_pos
	 *            the start_pos
	 * @param stand
	 *            the stand
	 */
	public ChessMsg(Point start_pos, int stand) {
		this.type = CLICK_CHESS;
		this.start_pos = start_pos;
		this.stand = stand;
	}

	/**
	 * Instantiates a new chess msg.
	 * 
	 * @param start_pos
	 *            the start_pos
	 * @param end_pos
	 *            the end_pos
	 * @param stand
	 *            the stand
	 */
	public ChessMsg(Point start_pos, Point end_pos, int stand) {
		this.type = SWAP_CHESS;
		this.start_pos = start_pos;
		this.end_pos = end_pos;
		this.stand = stand;
	}

	/**
	 * Gets the chess state.
	 * 
	 * @return the chess state
	 */
	public int[][][] getChessState() {
		return chess_state;
	}

	/**
	 * Gets the end pos.
	 * 
	 * @return the end pos
	 */
	public Point getEndPos() {
		return end_pos;
	}

	/**
	 * Gets the hint.
	 * 
	 * @return the hint
	 */
	public boolean getHint() {
		return hideHint;
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
	 * Gets the start pos.
	 * 
	 * @return the start pos
	 */
	public Point getStartPos() {
		return start_pos;
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
	 * Hide hint.
	 * 
	 * @return the chess msg
	 */
	public ChessMsg hideHint() {
		hideHint = true;
		return this;
	}

}
