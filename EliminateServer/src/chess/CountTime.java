package chess;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

// TODO: Auto-generated Javadoc
/**
 * The Class CountTime.
 */
public class CountTime extends Observable {

	/**
	 * The Class LocalTimerTask.
	 */
	class LocalTimerTask extends TimerTask {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			if (!isRunning) {
				return;
			}
			if (time > 0) {
				// if(flag==INVINCIBLE) {
				// System.out.println("INVINCIBLE : " + time);
				// }
				// if(flag==COMBO) {
				// System.out.println("COMBO : " + time);
				// }
				// if(flag==TOTAL) {
				// System.out.println("REST TIME : " + time);
				// }
				time--;
				if (flag == TOTAL)
					inform();
			} else {
				// if(flag==INVINCIBLE) {
				// System.out.println("INVINCIBLE : Time is up.");
				// }
				// if(flag==COMBO){
				// System.out.println("COMBO : Time is up.");
				// }
				// if(flag==TOTAL) {
				// System.out.println("Time is up.");
				// }
				localTimer.cancel();
				time--;
				inform();
			}
		}
	}

	/** The Constant COMBO. */
	public static final int COMBO = 0;// ��������ʱ���

	/** The Constant INVINCIBLE. */
	public static final int INVINCIBLE = 1;// �޵�ʱ�䵹��ʱ���

	/** The Constant TOTAL. */
	public static final int TOTAL = 2;// ��Ϸʱ�䵹��ʱ���

	/** The Constant HINT. */
	public static final int HINT = 3;// ��ʾʱ�䵹��ʱ���
	
	public static final int FROZEN = 4;

	/** The time. */
	private int time = 1;

	/** The local timer. */
	private Timer localTimer;

	/** The flag. */
	private int flag = 0;

	/** The is running. */
	private boolean isRunning = false;

	// tΪ����ʱ
	/**
	 * Instantiates a new count time.
	 * 
	 * @param seconds
	 *            the seconds
	 * @param t
	 *            the t
	 * @param type
	 *            the type
	 */
	public CountTime(int seconds, int t, int type) {
		time = t;
		flag = type;
		localTimer = new Timer();
		localTimer.schedule(new LocalTimerTask(), 0, seconds * 1000);
	}

	/**
	 * Gets the rest time.
	 * 
	 * @return the rest time
	 */
	public int getRestTime() {
		return time;
	}

	// ֪ͨ�۲��߸���ʱ��
	/**
	 * Inform.
	 */
	public void inform() {
		setChanged();
		notifyObservers(flag);
	}

	/**
	 * Put end.
	 */
	public void putEnd() {
		localTimer.cancel();
	}

	// ����t��ʱ��(�޵�ʱ���޵У�
	/**
	 * Sets the time.
	 * 
	 * @param t
	 *            the new time
	 */
	public void setTime(int t) {
		time += t;
	}

	/**
	 * Start.
	 */
	public void start() {
		isRunning = true;
	}

	/**
	 * Stop.
	 */
	public void stop() {
		isRunning = false;
	}
}
