package chess;

import java.util.Observable;
import java.util.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class GameTimer.
 */
public class GameTimer extends Observable implements Observer {

	/** The Constant SCORE_CHANGE. */
	public static final int SCORE_CHANGE = 1;

	/** The Constant TOTAL_TIME_CHANGE. */
	public static final int TOTAL_TIME_CHANGE = 2;

	/** The Constant INVICIBLE_STATE_CHANGE. */
	public static final int INVICIBLE_STATE_CHANGE = 3;

	/** The Constant MAXIMUMCOMBO. */
	public static final int MAXIMUMCOMBO = 4;

	/** The Constant HINT. */
	public static final int HINT = 5;
	
	public static final int FROZEN_TIME_END = 6;

	/** The Constant TOTAL_TIME. */
	private static final int TOTAL_TIME = 60;// 一局游戏的总时间

	/** The Constant COMBO_TIME. */
	private static final int COMBO_TIME = 1;// 两次连击间隔时间

	/** The Constant HINT_TIME. */
	private static final int HINT_TIME = 3;// 提示时间

	private static final int FROZEN_TIME = 2;
	
	/** The Constant FIRST_INVINCIBLE_TIME. */
	private static final int FIRST_INVINCIBLE_TIME = 5;// 第一次无敌时间

	/** The Constant NEXT_INVINCIBLE_TIME. */
	private static final int NEXT_INVINCIBLE_TIME = 3;// 无敌时间内的延长无敌时间

	/** The plus combo time. */
	private int plusComboTime = 0;

	/** The maximum combo. */
	private int maximumCombo = 0;// 最大连击

	/** The current combo. */
	private int currentCombo = 0;// 当前连击

	/** The score. */
	private int score = 0;// 分数

	/** The multiple. */
	private int multiple = 1;// 获得分数时的倍数

	/** The combo time. */
	private CountTime comboTime = null; // 连击时间间隔计时器

	/** The invincible time. */
	private CountTime invincibleTime = null;// 无敌时间计时器

	/** The rest time. */
	private CountTime restTime = null;// 游戏剩余时间

	/** The hint time. */
	private CountTime hintTime = null;
	
	private CountTime frozenTime = null;

	/**
	 * Instantiates a new game timer.
	 */
	public GameTimer() {
		init();
	}

	/**
	 * Adds the combo time.
	 */
	public void addComboTime() {
		if (plusComboTime == 2) {
			return;
		}
		plusComboTime++;
	}

	/**
	 * Adds the game time.
	 */
	public void addGameTime() {
		restTime.setTime(10);
	}

	/**
	 * Cancel.
	 */
	public void cancel() {
		if (comboTime != null)
			comboTime.putEnd();
		;
		if (invincibleTime != null)
			invincibleTime.putEnd();
		if (restTime != null)
			restTime.putEnd();
		if (hintTime != null) {
			restTime.putEnd();
		}
		if(frozenTime != null){
			frozenTime.putEnd();
		}
	}

	/**
	 * Cancel hint.
	 */
	public void cancelHint() {
		if (hintTime != null)
			hintTime.putEnd();
	}
	
	
	public void cancelFrozenTime(){
		if (frozenTime != null)
			frozenTime.putEnd();
	}
	/**
	 * Combo.
	 * 
	 * @param disappearNum
	 *            the disappear num
	 * @param combo
	 *            the combo
	 */
	public void combo(int disappearNum, boolean combo) {
		if (!combo) {
			computeScore(disappearNum, multiple);
			return;
		}
		if (comboTime != null) {
			if (comboTime.getRestTime() == -1) {
				currentCombo = 1;
			} else {
				currentCombo++;
				if (maximumCombo < currentCombo) {
					maximumCombo = currentCombo;
					inform(MAXIMUMCOMBO);
				}
				comboTime.putEnd();
			}
			if (currentCombo % 4 == 0) {
				if (invincibleTime == null) {
					invincibleTime = new CountTime(1, FIRST_INVINCIBLE_TIME,
							CountTime.INVINCIBLE);
					invincibleTime.start();
					invincibleTime.addObserver(this);
					multiple = 2;
				} else if (invincibleTime.getRestTime() >= 0) {
					invincibleTime.setTime(NEXT_INVINCIBLE_TIME);
					multiple = multiple * 2;
				} else {
					invincibleTime.putEnd();
					invincibleTime = new CountTime(1, FIRST_INVINCIBLE_TIME,
							CountTime.INVINCIBLE);
					invincibleTime.start();
					invincibleTime.addObserver(this);
					multiple = 2;
				}

				inform(INVICIBLE_STATE_CHANGE);
			}
			// 假设一直只有3个消除
			computeScore(disappearNum, multiple);
		}
		if (comboTime == null) {
			currentCombo = 1;
			maximumCombo = 1;
			computeScore(disappearNum, multiple);
		}
		comboTime = new CountTime(1, COMBO_TIME + plusComboTime,
				CountTime.COMBO);
		comboTime.start();
		comboTime.addObserver(this);
	}

	// num为消除的个数，mul为分数的倍数
	/**
	 * Compute score.
	 * 
	 * @param num
	 *            the num
	 * @param mul
	 *            the mul
	 */
	public void computeScore(int num, int mul) {
		if (num == 0) {
			return;
		}
		if (num == 3) {
			score += 100 * mul;
		} else if (num == 4) {
			score += 200 * mul;
		} else {
			score += 500 * mul;
		}
		inform(SCORE_CHANGE);
	}

	/**
	 * Delete.
	 */
	public void delete() {
		if (restTime != null) {
			restTime.putEnd();
		}
		if (comboTime != null) {
			comboTime.putEnd();
		}
		if (invincibleTime != null) {
			invincibleTime.putEnd();
		}
	}

	/**
	 * Gets the combo.
	 * 
	 * @return the combo
	 */
	public int getCombo() {
		return this.currentCombo;
	}

	/**
	 * Gets the invicible.
	 * 
	 * @return the invicible
	 */
	public boolean getInvicible() {
		return invincibleTime.getRestTime() > 0;
	}

	/**
	 * Gets the max combo.
	 * 
	 * @return the max combo
	 */
	public int getMaxCombo() {
		return maximumCombo;
	}

	/**
	 * Gets the rest time.
	 * 
	 * @return the rest time
	 */
	public int getRestTime() {
		int temp = restTime.getRestTime();
		if (temp == -1)
			return 0;
		return restTime.getRestTime();
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
	 * Inform.
	 * 
	 * @param flag
	 *            the flag
	 */
	public void inform(int flag) {
		setChanged();
		notifyObservers(flag);
	}

	/**
	 * Inits the.
	 */
	public void init() {
		restTime = new CountTime(1, TOTAL_TIME, CountTime.TOTAL);
		restTime.addObserver(this);
		currentCombo = 0;
		maximumCombo = 0;
		score = 0;
	}

	/**
	 * Inits the hint time.
	 * 
	 * @param isUseItem
	 *            the is use item
	 */
	public void initHintTime(boolean isUseItem) {
		if (isUseItem) {
			hintTime = new CountTime(1, HINT_TIME - 1, CountTime.HINT);
			hintTime.start();
			hintTime.addObserver(this);
		} else {
			hintTime = new CountTime(1, HINT_TIME, CountTime.HINT);
			hintTime.addObserver(this);
			hintTime.start();
		}
	}
	
	public void initFrozenTime(){
		if(frozenTime != null){
			frozenTime.putEnd();
		}
		frozenTime = new CountTime(1, FROZEN_TIME, CountTime.FROZEN);
		frozenTime.start();
		frozenTime.addObserver(this);
	}

	/**
	 * Re start.
	 */
	public void reStart() {
		init();
		start();
	}

	public void deleteComboTool() {
		plusComboTime = 0;
	}

	/**
	 * Resume.
	 */
	public void resume() {
		if (comboTime != null)
			comboTime.start();
		if (invincibleTime != null)
			invincibleTime.start();
		restTime.start();
	}

	/**
	 * Start.
	 */
	public void start() {
		restTime.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		if (comboTime != null)
			comboTime.stop();
		if (invincibleTime != null)
			invincibleTime.stop();
		restTime.stop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		int flag = (Integer) arg;
		if (flag == CountTime.COMBO) {
			currentCombo = 0;
		} else if (flag == CountTime.HINT) {
			// 提示
			inform(HINT);
		} else if (flag == CountTime.INVINCIBLE) {
			multiple = 1;
			inform(INVICIBLE_STATE_CHANGE);
		} else if (flag == CountTime.TOTAL) {
			if (restTime.getRestTime() == -1) {
				stop();
				return;
			}
			inform(TOTAL_TIME_CHANGE);
		} else if(flag == CountTime.FROZEN){
			inform(FROZEN_TIME_END);
		}
	}
}
