/*
 * 
 */
package eliminate.model.chess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class ChessModel.
 */
public class ChessModel extends Observable {

	// 棋子数量
	/** The Constant NUM_CHESS. */
	public static final int NUM_CHESS = 15;

	// 棋子的种类
	/** The Constant NO_CHESS. */
	public static final int NO_CHESS = 0; // 无棋子

	// 常规棋子
	/** The Constant NUM_NORMAL. */
	public static final int NUM_NORMAL = 6;

	/** The Constant CHESS_1. */
	public static final int CHESS_1 = 1;

	/** The Constant CHESS_2. */
	public static final int CHESS_2 = 2;

	/** The Constant CHESS_3. */
	public static final int CHESS_3 = 3;

	/** The Constant CHESS_4. */
	public static final int CHESS_4 = 4;
	
	/** The Constant CHESS_5. */
	public static final int CHESS_5 = 5;

	/** The Constant CHESS_6. */
	public static final int CHESS_6 = 6;

	// 爆炸效果
	/** The Constant NUM_BLOW. */
	public static final int NUM_BLOW = 7;

	/** The Constant BLOW_1. */
	public static final int BLOW_1 = 7;

	/** The Constant BLOW_2. */
	public static final int BLOW_2 = 8;

	/** The Constant BLOW_3. */
	public static final int BLOW_3 = 9;

	/** The Constant BLOW_4. */
	public static final int BLOW_4 = 10;

	/** The Constant BLOW_5. */
	public static final int BLOW_5 = 11;

	/** The Constant BLOW_6. */
	public static final int BLOW_6 = 12;

	/** The Constant BLOW_7. */
	public static final int BLOW_7 = 13;

	// 道具
	/** The Constant PROP_B. */
	public static final int PROP_B = 14;

	/** The Constant PROP_A. */
	public static final int PROP_A = 15;

	/** The chessboard. */
	private Chessboard chessboard = new Chessboard();

	// 棋子的状态， 其中chess_state[x][y][0]表示x, y 处棋子的种类
	// chess_state[x][y][1]表示x方向偏移量 x > 0 向右偏移， x < 0 向左偏移
	// chess_state[x][y][2]表示y方向偏移量 y > 0 向上偏移， y < 0 向左偏移
	/** The chess_state. */
	private int[][][] chess_state;

	/** The chess_state_width. */
	private int chess_state_width;

	/** The chess_state_height. */
	private int chess_state_height;

	/** The hint. */
	ArrayList<Point> hint = new ArrayList<Point>();
	
	private int colorNum = 0;
	Set<Integer> temp = new HashSet<Integer>();

	/**
	 * Instantiates a new chess model.
	 */
	public ChessModel() {
		init();
	}

	/**
	 * Bomb.
	 * 
	 * @return the int
	 */
	public int bomb() {
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 4; k < 7; k++) {
				chess_state[i][k][0] = NUM_NORMAL + 1;
			}
		}
		return 27;
	}

	/**
	 * Can delete.
	 * 
	 * @return true, if successful
	 */
	public boolean canDelete() {
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				if (isInLine(i, k)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Can delete.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if successful
	 */
	public boolean canDelete(int x, int y) {
		return isInLine(x, y);
	}

	/**
	 * Change model.
	 * 
	 * @param model
	 *            the model
	 */
	public void changeModel(ChessModel model) {
		this.chess_state = model.getState();
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Delete.
	 * 
	 * @return the int
	 */
	public int delete(boolean dropDown) {
		int num = 0;
		if (hasBlack()) {
			if (dropDown) {
				for (int x = 1; x < chess_state_width; x++) {
					for (int y = chess_state_height - 1; y > 0; y--) {
						if (chess_state[x][y][0] == 0) {
							for (; y > 0; y--) {
								swapColor(x, y, x, y - 1);
							}
							chess_state[x][y][0] = (int) (Math.random() * NUM_NORMAL) + 1;
							chess_state[x][y][1] = 0;
							chess_state[x][y][2] = 0;
							break;
						}
					}
				}
			} else {
				for (int y = 1; y < chess_state_height; y++) {
					for (int x = chess_state_width - 1; x > 0; x--) {
						if (chess_state[x][y][0] == 0) {
							for (; x > 0; x--) {
								swapColor(x, y, x - 1, y);
							}
							chess_state[x][y][0] = (int) (Math.random() * NUM_NORMAL) + 1;
							chess_state[x][y][1] = 0;
							chess_state[x][y][2] = 0;
							break;
						}
					}
				}
			}
			return 0;
		} else if (isChangeble()) {
			for (int x = 1; x < chess_state_width; x++) {
				for (int y = chess_state_height - 1; y > 0; y--) {
					if (chess_state[x][y][0] > NUM_NORMAL
							&& chess_state[x][y][0] < NUM_NORMAL + NUM_BLOW) {
						chess_state[x][y][0]++;
						;
					} else if (chess_state[x][y][0] == NUM_NORMAL + NUM_BLOW) {
						chess_state[x][y][0] = 0;
					}
				}
			}
			return 0;
		}
		temp.clear();
		for (int x = 1; x < chess_state_width; x++) {
			for (int y = chess_state_height - 1; y > 0; y--) {
				if (isInLine(x, y)) {
					temp.add(chess_state[x][y][0]);
					num += 1;
					chess_state[x][y][3] = 1;
					if (chess_state[x][y][0] > PROP_A) {
						temp.add(40);
						num += deleteAround(x, y);
					}
				}
			}
		}
		colorNum = temp.size();
		for (int x = 1; x < chess_state_width; x++) {
			for (int y = chess_state_height - 1; y > 0; y--) {
				if (chess_state[x][y][3] == 1) {
					chess_state[x][y][0] = NUM_NORMAL + 1;
					chess_state[x][y][3] = 0;
				}
			}
		}
		return num;
	}

	/**
	 * Delete a.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the int
	 */
	public int deleteA(int x, int y) {
		int num = deleteAround(x, y);
		for (int i = 1; i < chess_state_width; i++) {
			for (int j = 1; j < chess_state_height; j++) {
				if (chess_state[i][j][3] == 1) {
					chess_state[i][j][0] = NUM_NORMAL + 1;
					chess_state[i][j][3] = 0;
				}
			}
		}
		return num;
	}

	/**
	 * Delete ab.
	 * 
	 * @return the int
	 */
	public int deleteAB() {
		int num = 0;
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 0; k < chess_state_height; k++) {
				if (k == 0) {
					if (chess_state[i][k][0] >= PROP_B) {
						chess_state[i][k][0] = (int) (Math.random() * NUM_NORMAL) + 1;
					}
				} else {
					if (chess_state[i][k][0] == PROP_B) {
						num += deleteB(i, k);
					} else if (chess_state[i][k][0] > PROP_A) {
						num += deleteA(i, k);
					}
				}
			}
		}
		return num;
	}

	/**
	 * Delete around.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the int
	 */
	private int deleteAround(int x, int y) {
		int num = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (x + i > 0 && x + i < chess_state_width && y + j > 0
						&& y + j < chess_state_height) {
					if (chess_state[x + i][y + j][3] != 1) {
						chess_state[x + i][y + j][3] = 1;
						num++;
						if(chess_state[x + i][y + j][0] > PROP_A){
							num += deleteAround(x + i, y + j);
						}
						
						if(chess_state[x + i][y + j][0] == PROP_B){
							num += deletePropB(x + i, y + j);
						}
					}
				}
			}
		}
		return num;
	}

	/**
	 * Delete b.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the int
	 */
	public int deleteB(int x, int y) {
		int num = deletePropB(x, y);
		for (int i = 1; i < chess_state_width; i++) {
			for (int j = 1; j < chess_state_height; j++) {
				if (chess_state[i][j][3] == 1) {
					chess_state[i][j][0] = NUM_NORMAL + 1;
					chess_state[i][j][3] = 0;
				}
			}
		}
		return num;
	}
	
	public int deletePropB(int x, int y){
		int num = 0;
		for (int i = 1; i < chess_state_width; i++) {
			if(chess_state[i][y][3] != 1){
				chess_state[i][y][3] = 1;
				num++;
				if(chess_state[i][y][0] > PROP_A){
					num += deleteAround(i, y );
				}
				
				if(chess_state[i][y][0] == PROP_B){
					num += deletePropB(i, y);
				}
			}
		}
		for (int i = 1; i < chess_state_height; i++) {
			if(chess_state[x][i][3] != 1){
				chess_state[x][i][3] = 1;
				num++;
				if(chess_state[x][i][0] > PROP_A){
					num += deleteAround(x, i );
				}
				
				if(chess_state[x][i][0] == PROP_B){
					num += deletePropB(x, i);
				}
			}
		}
		return num;
	}

	/**
	 * Drop down.
	 */
	public void dropDown(boolean dropDown) {
		if (dropDown) {
			for (int x = 1; x < chess_state_width; x++) {
				for (int y = chess_state_height - 1; y >= 0; y--) {
					if (chess_state[x][y][0] == 0) {
						for (; y >= 0; y--) {
							chess_state[x][y][2] += chessboard.getHeight() / 3;
						}
						break;
					}
				}
			}
		} else {
			for (int y = 1; y < chess_state_height; y++) {
				for (int x = chess_state_width - 1; x >= 0; x--) {
					if (chess_state[x][y][0] == 0) {
						for (; x >= 0; x--) {
							chess_state[x][y][1] += chessboard.getWidth() / 3;
						}
						break;
					}
				}
			}
		}
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return chessboard.getHeight();
	}

	/**
	 * Gets the hint.
	 * 
	 * @return the hint
	 */
	public ArrayList<Point> getHint() {
		hint.clear();

		for (int i = 1; i < chess_state_width - 1; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				swap(i, k, i + 1, k);
				if (isInLineY(i, k)) {
					hint.add(new Point(i + 1, k));
					for (int j = k + 1; j < chess_state_height; j++) {
						if (chess_state[i][j][0] == chess_state[i][k][0]) {
							hint.add(new Point(i, j));
						} else {
							break;
						}
					}

					for (int j = k - 1; j > 0; j--) {
						if (chess_state[i][j][0] == chess_state[i][k][0]) {
							hint.add(new Point(i, j));
						} else {
							break;
						}
					}
					swap(i, k, i + 1, k);
					return hint;
				}

				if (isInLineX(i, k)) {
					hint.add(new Point(i + 1, k));
					for (int j = i - 1; j > 0; j--) {
						if (chess_state[j][k][0] == chess_state[i][k][0]) {
							hint.add(new Point(j, k));
						} else {
							break;
						}
					}
					swap(i, k, i + 1, k);
					return hint;
				}

				if (isInLineY(i + 1, k)) {
					hint.add(new Point(i, k));
					for (int j = k + 1; j < chess_state_height; j++) {
						if (chess_state[i + 1][j][0] == chess_state[i + 1][k][0]) {
							hint.add(new Point(i + 1, j));
						} else {
							break;
						}
					}

					for (int j = k - 1; j > 0; j--) {
						if (chess_state[i + 1][j][0] == chess_state[i + 1][k][0]) {
							hint.add(new Point(i + 1, j));
						} else {
							break;
						}
					}
					swap(i, k, i + 1, k);
					return hint;
				}
				if (isInLineX(i + 1, k)) {
					hint.add(new Point(i, k));
					for (int j = i + 2; j < chess_state_width; j++) {
						if (chess_state[j][k][0] == chess_state[i + 1][k][0]) {
							hint.add(new Point(j, k));
						} else {
							break;
						}
					}
					swap(i, k, i + 1, k);
					return hint;
				}

				swap(i, k, i + 1, k);
			}
		}

		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height - 1; k++) {
				swap(i, k, i, k + 1);
				if (isInLineX(i, k)) {
					hint.add(new Point(i, k + 1));
					for (int j = i + 1; j < chess_state_width; j++) {
						if (chess_state[j][k][0] == chess_state[i][k][0]) {
							hint.add(new Point(j, k));
						} else {
							break;
						}
					}

					for (int j = i - 1; j > 0; j--) {
						if (chess_state[j][k][0] == chess_state[i][k][0]) {
							hint.add(new Point(j, k));
						} else {
							break;
						}
					}
					swap(i, k, i, k + 1);
					return hint;
				}

				if (isInLineY(i, k)) {
					hint.add(new Point(i, k + 1));
					for (int j = k - 1; j > 0; j--) {
						if (chess_state[i][j][0] == chess_state[i][k][0]) {
							hint.add(new Point(i, j));
						} else {
							break;
						}
					}
					swap(i, k, i, k + 1);
					return hint;
				}

				if (isInLineX(i, k + 1)) {
					hint.add(new Point(i, k));
					for (int j = i + 1; j < chess_state_width; j++) {
						if (chess_state[j][k + 1][0] == chess_state[i][k + 1][0]) {
							hint.add(new Point(j, k + 1));
						} else {
							break;
						}
					}

					for (int j = i - 1; j > 0; j--) {
						if (chess_state[j][k + 1][0] == chess_state[i][k + 1][0]) {
							hint.add(new Point(j, k + 1));
						} else {
							break;
						}
					}
					swap(i, k, i, k + 1);
					return hint;
				}
				if (isInLineY(i + 1, k + 1)) {
					hint.add(new Point(i, k));
					for (int j = k + 2; j < chess_state_height; j++) {
						if (chess_state[i][j][0] == chess_state[i][k + 1][0]) {
							hint.add(new Point(i, j));
						} else {
							break;
						}
					}
					swap(i, k, i, k + 1);
					return hint;
				}

				swap(i, k, i, k + 1);
			}
		}

		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				if (chess_state[i][k][0] > PROP_B) {
					hint.add(new Point(i, k));
				}
			}
		}
		return hint;
	}

	/**
	 * Gets the horizontal num.
	 * 
	 * @return the horizontal num
	 */
	public int getHorizontalNum() {
		return chess_state_width;
	}

	/**
	 * Gets the rid.
	 * 
	 * @return the rid
	 */
	private void getRid() {
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				if (isInLine(i, k)) {
					while (isInLine(i, k)) {
						chess_state[i][k][0] = (int) (Math.random() * NUM_NORMAL) + 1;
					}
				}
			}
		}
	}

	/**
	 * Gets the rid x.
	 * 
	 * @return the rid x
	 */
	public Point getRidXY() {
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				if (chess_state[i][k][0] == NUM_NORMAL + 1) {
					return new Point(i, k);
				}
			}
		}
		return new Point(0, 0);
	}

	/**
	 * Gets the state.
	 * 
	 * @return the state
	 */
	public int[][][] getState() {
		return chess_state;
	}

	/**
	 * Gets the value.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @return the value
	 */
	public int getValue(int x, int y, int z) {
		return chess_state[x][y][z];
	}

	/**
	 * Gets the vertical num.
	 * 
	 * @return the vertical num
	 */
	public int getVerticalNum() {
		return chess_state_height;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return chessboard.getWidth();
	}

	/**
	 * Checks for black.
	 * 
	 * @return true, if successful
	 */
	public boolean hasBlack() {
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				if (chess_state[i][k][0] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Inform.
	 */
	public void inform() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		chess_state_width = chessboard.getHorizontalNum() + 1;
		chess_state_height = chessboard.getVerticalNum() + 1;
		chess_state = new int[chess_state_width][chess_state_height][4];
		for (int i = 0; i < chess_state_width; i++) {
			for (int k = 0; k < chess_state_height; k++) {
				chess_state[i][k][0] = (int) (Math.random() * NUM_NORMAL) + 1;
				chess_state[i][k][1] = 0;
				chess_state[i][k][2] = 0;
				chess_state[i][k][3] = 0;
			}
		}
		getRid();
		notifyObservers(this);
	}

	/**
	 * Checks if is changeble.
	 * 
	 * @return true, if is changeble
	 */
	public boolean isChangeble() {
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				if (chess_state[i][k][0] < 1
						|| (chess_state[i][k][0] > NUM_NORMAL && chess_state[i][k][0] < PROP_B)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if is in line.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is in line
	 */
	public boolean isInLine(int x, int y) {
		return isInLineX(x, y) || isInLineY(x, y);
	}

	/**
	 * Checks if is in line x.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is in line x
	 */
	private boolean isInLineX(int x, int y) {
		int num_same = 1;
		int tempx = x - 1;
		boolean isPropA = false;
		if (chess_state[x][y][0] > PROP_A) {
			chess_state[x][y][0] -= PROP_A;
			isPropA = true;
		}
		if (chess_state[x][y][0] < 1 || chess_state[x][y][0] > NUM_NORMAL) {
			return false;
		}
		while (tempx > 0) {
			if (chess_state[tempx][y][0] == chess_state[x][y][0]
					|| chess_state[tempx][y][0] - PROP_A == chess_state[x][y][0]) {
				num_same++;
			} else {
				break;
			}
			tempx--;
		}

		tempx = x + 1;
		while (tempx < chess_state_width) {
			if (chess_state[tempx][y][0] == chess_state[x][y][0]
					|| chess_state[tempx][y][0] - PROP_A == chess_state[x][y][0]) {
				num_same++;
			} else {
				break;
			}
			tempx++;
		}

		if (isPropA)
			chess_state[x][y][0] += PROP_A;

		return num_same >= 3;
	}

	/**
	 * Checks if is in line y.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is in line y
	 */
	private boolean isInLineY(int x, int y) {
		int num_same = 1;
		int tempy = y - 1;

		boolean isPropA = false;
		if (chess_state[x][y][0] > PROP_A) {
			chess_state[x][y][0] -= PROP_A;
			isPropA = true;
		}

		if (chess_state[x][y][0] < 1 || chess_state[x][y][0] > NUM_NORMAL) {
			return false;
		}

		while (tempy > 0) {
			if (chess_state[x][tempy][0] == chess_state[x][y][0]
					|| chess_state[x][tempy][0] - PROP_A == chess_state[x][y][0]) {
				num_same++;
			} else {
				break;
			}
			tempy--;
		}

		tempy = y + 1;
		while (tempy < chess_state_height) {
			if (chess_state[x][tempy][0] == chess_state[x][y][0]
					|| chess_state[x][tempy][0] - PROP_A == chess_state[x][y][0]) {
				num_same++;
			} else {
				break;
			}
			tempy++;
		}

		if (isPropA)
			chess_state[x][y][0] += PROP_A;
		return num_same >= 3;
	}

	/**
	 * Produce a.
	 */
	public void produceA() {
		Point pos = getRidXY();
		int chessType = 0;
		for(Integer i : temp){
			if(i > 0 && i <= NUM_NORMAL){
				chessType = i;
				break;
			}
		}
		if(chessType == 0){
			chessType = ((int) (Math.random() * NUM_NORMAL)) + 1;
		}
		int type = chessType + PROP_A;
		chess_state[pos.x][pos.y][0] = type;
	}

	/**
	 * Produce b.
	 */
	public void produceB() {
		Point temp = getRidXY();
		chess_state[temp.x][temp.y][0] = PROP_B;
	}

	/**
	 * Sets the data.
	 * 
	 * @param chess
	 *            the new data
	 */
	public void setData(int[][][] chess) {
		for (int i = 0; i < chess_state_height; i++) {
			for (int k = 0; k < chess_state_height; k++) {
				for (int l = 0; l < 3; l++) {
					chess_state[i][k][l] = chess[i][k][l];
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Sets the height.
	 * 
	 * @param height
	 *            the new height
	 */
	public void setHeight(int height) {
		chessboard.setHeight(height);
	}

	/**
	 * Sets the horizontal num.
	 * 
	 * @param horizontal_num
	 *            the new horizontal num
	 */
	public void setHorizontalNum(int horizontal_num) {
		chessboard.setHorizontalNum(horizontal_num);
	}

	/**
	 * Sets the value.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param value
	 *            the value
	 */
	public void setValue(int x, int y, int z, int value) {
		chess_state[x][y][z] = value;
	}

	/**
	 * Sets the vertical num.
	 * 
	 * @param vertical_num
	 *            the new vertical num
	 */
	public void setVerticalNum(int vertical_num) {
		chessboard.setVerticalNum(vertical_num);
	}
	
	public int getColorNum() {
		return colorNum;
	}

	/**
	 * Sets the width.
	 * 
	 * @param width
	 *            the new width
	 */
	public void setWidth(int width) {
		chessboard.setWidth(width);
	}

	/**
	 * Swap.
	 * 
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 */
	public void swap(int x1, int y1, int x2, int y2) {
		int temp = getValue(x1, y1, 0);
		setValue(x1, y1, 0, getValue(x2, y2, 0));
		setValue(x2, y2, 0, temp);
	}
	
	
	public boolean getFiveInline() {
		int num = 0;
		for (int i = 1; i < chess_state_width; i++) {
			for (int k = 1; k < chess_state_height; k++) {
				if (chess_state[i][k][0] == NUM_NORMAL + 1) {
					if ((i + 1 < chess_state_width) && 
							(chess_state[i + 1][k][0] == NUM_NORMAL + 1)) {
						
						i = i + 2;
						num = 2;
						for (; i < chess_state_width; i++) {
							if (chess_state[i][k][0] == NUM_NORMAL + 1) {
								num++;
							} else{
								break;
							}
						}
						return num == 5;
					}
					else if ((k + 1 < chess_state_height) && 
							(chess_state[i][k + 1][0] == NUM_NORMAL + 1)) {
						
						k = k + 2;
						num = 2;
						for (; k < chess_state_height; k++) {
							if (chess_state[i][k][0] == NUM_NORMAL + 1) {
								num++;
							} else{
								break;
							}
						}
						return num == 5;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Swap color.
	 * 
	 * @param x1
	 *            the x1
	 * @param y1
	 *            the y1
	 * @param x2
	 *            the x2
	 * @param y2
	 *            the y2
	 */
	public void swapColor(int x1, int y1, int x2, int y2) {
		int temp = getValue(x1, y1, 0);
		setValue(x1, y1, 0, getValue(x2, y2, 0));
		setValue(x2, y2, 0, temp);
		chess_state[x1][y1][1] = 0;
		chess_state[x1][y1][2] = 0;
		chess_state[x2][y2][1] = 0;
		chess_state[x2][y2][2] = 0;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Swap color.
	 * 
	 * @param chessMove
	 *            the chess move
	 */
	public void swapColor(int[] chessMove) {
		swapColor(chessMove[0], chessMove[1], chessMove[2], chessMove[3]);
	}
}
