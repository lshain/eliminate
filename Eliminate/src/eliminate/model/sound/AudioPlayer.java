/*
 * 
 */
package eliminate.model.sound;

// TODO: Auto-generated Javadoc
/**
 * The Class AudioPlayer.
 */
public class AudioPlayer {

	/** The button1. */
	static private Audio button1 = new Audio();

	/** The combo_disappear. */
	static private Audio combo_disappear = new Audio();

	/** The combo_one. */
	static private Audio combo_one = new Audio();

	/** The combo_four. */
	static private Audio combo_four = new Audio();

	/** The combo_three. */
	static private Audio combo_three = new Audio();

	/** The combo_two. */
	static private Audio combo_two = new Audio();

	/** The combo_five. */
	static private Audio combo_five = new Audio();

	/** The combo_six. */
	static private Audio combo_six = new Audio();

	/** The fail. */
	static private Audio fail = new Audio();

	/** The lose. */
	static private Audio lose = new Audio();

	/** The login. */
	static private Audio login = new Audio();

	/** The win. */
	static private Audio win = new Audio();

	/** The main. */
	static private Audio main = new Audio();

	/** The ready go. */
	static private Audio readyGo = new Audio();

	/** The gaming_background1. */
	static private Audio gaming_background1 = new Audio();

	/** The gaming_background2. */
	static private Audio gaming_background2 = new Audio();

	/** The gaming_background3. */
	static private Audio gaming_background3 = new Audio();

	/**
	 * Button one set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void buttonOneSetVolume(float d) {
		button1.setVolume(d);
	}

	/**
	 * Combo disappear set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void comboDisappearSetVolume(float d) {
		combo_disappear.setVolume(d);
	}

	/**
	 * Combo five set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void comboFiveSetVolume(float d) {
		combo_five.setVolume(d);
	}

	/**
	 * Combo four set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void comboFourSetVolume(float d) {
		combo_four.setVolume(d);
	}

	/**
	 * Combo one set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void comboOneSetVolume(float d) {
		combo_one.setVolume(d);
	}

	/**
	 * Combo six set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void comboSixSetVolume(float d) {
		combo_six.setVolume(d);
	}

	/**
	 * Combo three set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void comboThreeSetVolume(float d) {
		combo_three.setVolume(d);
	}

	/**
	 * Combo two set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void comboTwoSetVolume(float d) {
		combo_two.setVolume(d);
	}

	/**
	 * Fail set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void failSetVolume(float d) {
		fail.setVolume(d);
	}

	/**
	 * Fail stop.
	 */
	public static void failStop() {
		fail.stop();
	}

	/**
	 * Gaming backgound one set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void gamingBackgoundOneSetVolume(float d) {
		gaming_background1.setVolume(d);
	}

	/**
	 * Gaming backgound one stop.
	 */
	public static void gamingBackgoundOneStop() {
		gaming_background1.stop();
	}

	/**
	 * Gaming backgound three set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void gamingBackgoundThreeSetVolume(float d) {
		gaming_background3.setVolume(d);
	}

	/**
	 * Gaming backgound three stop.
	 */
	public static void gamingBackgoundThreeStop() {
		gaming_background3.stop();
	}

	/**
	 * Gaming backgound two set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void gamingBackgoundTwoSetVolume(float d) {
		gaming_background2.setVolume(d);
	}

	/**
	 * Gaming backgound two stop.
	 */
	public static void gamingBackgoundTwoStop() {
		gaming_background2.stop();
	}

	/**
	 * Login set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void loginSetVolume(float d) {
		login.setVolume(d);
	}

	/**
	 * Login stop.
	 */
	public static void loginStop() {
		login.stop();
	}

	/**
	 * Lose set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void loseSetVolume(float d) {
		lose.setVolume(d);
	}

	/**
	 * Lose stop.
	 */
	public static void loseStop() {
		lose.stop();
	}

	/**
	 * Main set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void mainSetVolume(float d) {
		main.setVolume(d);
	}

	/**
	 * Main stop.
	 */
	public static void mainStop() {
		main.stop();
	}

	// 按键声音1
	/**
	 * Play button one.
	 */
	public static void playButtonOne() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				button1.play("media/music/button1.wav", false);
			}
		}).start();
	}

	// 连击消失声音
	/**
	 * Play combo disappear.
	 */
	public static void playComboDisappear() {
		combo_disappear.play("media/music/combo_disappear.wav", false);
	}

	// 连击五次声音
	/**
	 * Play combo five.
	 */
	public static void playComboFive() {
		combo_five.play("media/music/combo_five.wav", false);
	}

	// 连击四次声音
	/**
	 * Play combo four.
	 */
	public static void playComboFour() {
		combo_four.play("media/music/combo_four.wav", false);
	}

	// 连击一次声音
	/**
	 * Play combo one.
	 */
	public static void playComboOne() {
		combo_one.play("media/music/combo_one.wav", false);
	}

	// 连击六次及以上声音
	/**
	 * Play combo six.
	 */
	public static void playComboSix() {
		combo_six.play("media/music/combo_six.wav", false);
	}

	// 连击三次声音
	/**
	 * Play combo three.
	 */
	public static void playComboThree() {
		combo_three.play("media/music/combo_three.wav", false);
	}

	// 连击两次声音
	/**
	 * Play combo two.
	 */
	public static void playComboTwo() {
		combo_two.play("media/music/combo_two.wav", false);
	}

	// 失败声音
	/**
	 * Play fail.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playFail(final boolean isLoop) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				fail.play("media/music/fail.wav", isLoop);
			}
		}).start();
	}

	// 游戏时的背景音乐1
	/**
	 * Play gaming backgound one.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playGamingBackgoundOne(final boolean isLoop) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				stopAll();
				gaming_background1.play("media/music/gaming_background1.wav",
						isLoop);
			}
		}).start();
	}

	// 游戏时的背景音乐3
	/**
	 * Play gaming backgound three.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playGamingBackgoundThree(final boolean isLoop) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				stopAll();
				gaming_background3.play("media/music/gaming_background3.wav",
						isLoop);
			}
		}).start();
	}

	// 游戏时的背景音乐2
	/**
	 * Play gaming backgound two.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playGamingBackgoundTwo(final boolean isLoop) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				stopAll();
				gaming_background2.play("media/music/gaming_background2.wav",
						isLoop);
			}
		}).start();

	}

	// 登录界面声音
	/**
	 * Play login.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playLogin(final boolean isLoop) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				stopAll();
				login.play("media/music/login.wav", isLoop);
			}
		}).start();

	}

	// 失败声音
	/**
	 * Play lose.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playLose(boolean isLoop) {
		lose.play("media/music/lose.wav", isLoop);
	}

	// 主界面声音
	/**
	 * Play main.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playMain(final boolean isLoop) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				stopAll();
				main.play("media/music/main.wav", isLoop);
			}
		}).start();
	}

	// readyGo声音
	/**
	 * Play ready go.
	 */
	public static void playReadyGo() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				readyGo.play("media/music/ReadyGo.wav", false);
			}
		});
	}

	// 胜利声音
	/**
	 * Play win.
	 * 
	 * @param isLoop
	 *            the is loop
	 */
	public static void playWin(final boolean isLoop) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				win.play("media/music/win.wav", isLoop);
			}
		}).start();
	}

	/**
	 * Ready go set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void readyGoSetVolume(float d) {
		readyGo.setVolume(d);
	}

	/**
	 * Sets the effects.
	 * 
	 * @param db
	 *            the new effects
	 */
	public static void setEffects(float db) {
		button1.setVolume(db);
		combo_one.setVolume(db);
		combo_two.setVolume(db);
		combo_three.setVolume(db);
		combo_four.setVolume(db);
		combo_five.setVolume(db);
		combo_six.setVolume(db);
		readyGo.setVolume(db);
		lose.setVolume(db);
		fail.setVolume(db);
		win.setVolume(db);
	}

	/**
	 * Sets the music volume.
	 * 
	 * @param db
	 *            the new music volume
	 */
	public static void setMusicVolume(float db) {
		main.setVolume(db);
		gaming_background1.setVolume(db);
		gaming_background2.setVolume(db);
		gaming_background3.setVolume(db);
		login.setVolume(db);
	}

	/**
	 * Stop all.
	 */
	public static void stopAll() {
		main.stop();
		gaming_background1.stop();
		gaming_background2.stop();
		gaming_background3.stop();
		login.stop();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Win set volume.
	 * 
	 * @param d
	 *            the d
	 */
	public static void winSetVolume(float d) {
		win.setVolume(d);
	}

	/**
	 * Win stop.
	 */
	public static void winStop() {
		win.stop();
	}
}
