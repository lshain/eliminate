package chessModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Chessboard.
 */
public class Chessboard {

	/** The horizontal_num. */
	private int horizontal_num = 9; // 横向棋子的数量

	/** The vertical_num. */
	private int vertical_num = 9; // 纵向棋子的数量

	/** The width. */
	private int width = 54; // 横向棋子的长度

	/** The height. */
	private int height = 57; // 纵向棋子的长度

	/**
	 * Instantiates a new chessboard.
	 */
	public Chessboard() {

	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the horizontal num.
	 * 
	 * @return the horizontal num
	 */
	public int getHorizontalNum() {
		return horizontal_num;
	}

	/**
	 * Gets the vertical num.
	 * 
	 * @return the vertical num
	 */
	public int getVerticalNum() {
		return vertical_num;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the height.
	 * 
	 * @param height
	 *            the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets the horizontal num.
	 * 
	 * @param horizontal_num
	 *            the new horizontal num
	 */
	public void setHorizontalNum(int horizontal_num) {
		this.horizontal_num = horizontal_num;
	}

	/**
	 * Sets the vertical num.
	 * 
	 * @param vertical_num
	 *            the new vertical num
	 */
	public void setVerticalNum(int vertical_num) {
		this.vertical_num = vertical_num;
	}

	/**
	 * Sets the width.
	 * 
	 * @param width
	 *            the new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
}
