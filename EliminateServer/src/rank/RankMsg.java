/*
 * 
 */
package rank;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RankMsg.
 */
@SuppressWarnings("serial")
public class RankMsg implements Serializable {

	/** The Constant GAME_DETAIL. */
	public static final int GAME_DETAIL = 1;

	/** The Constant DAILYASCORE. */
	public static final int DAILYASCORE = 2;

	/** The Constant DAILYTIMES. */
	public static final int DAILYTIMES = 3;

	/** The Constant MULTIPLAYER. */
	public static final int MULTIPLAYER = 4;

	/** The game detail data. */
	private int[][] gameDetailData;

	/** The average. */
	private int[][] average;

	/** The team rank. */
	private String[][] teamRank;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new rank msg.
	 * 
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 */
	public RankMsg(int type, int[][] data) {
		if (type == GAME_DETAIL) {
			gameDetailData = data;
			this.type = GAME_DETAIL;
		} else {
			this.average = data;
			this.type = type;
		}
	}

	/**
	 * Instantiates a new rank msg.
	 * 
	 * @param teamRank
	 *            the team rank
	 */
	public RankMsg(String[][] teamRank) {
		this.teamRank = teamRank;
		this.type = MULTIPLAYER;
	}

	/**
	 * Gets the daily score.
	 * 
	 * @return the daily score
	 */
	public int[][] getDailyScore() {
		return average;
	}

	/**
	 * Gets the daily times.
	 * 
	 * @return the daily times
	 */
	public int[][] getDailyTimes() {
		int[][] times = new int[average.length][4];
		for (int i = 0; i < average.length; i++) {
			times[i][0] = average[i][0];
			times[i][1] = average[i][1];
			times[i][2] = average[i][2];
			times[i][3] = average[i][4];
			System.out.println(times[i][0] + "  " + times[i][1] + "  "
					+ times[i][2]);
		}
		return times;
	}

	/**
	 * Gets the detail data.
	 * 
	 * @return the detail data
	 */
	public String[][] getDetailData() {
		String[][] temp = new String[gameDetailData.length][3];
		for (int i = 0; i < gameDetailData.length; i++) {
			temp[i][0] = gameDetailData[i][0] + "Äê" + gameDetailData[i][1]
					+ "ÔÂ" + gameDetailData[i][2] + "ÈÕ";
			temp[i][1] = Integer.toString(gameDetailData[i][3]);
			temp[i][2] = Integer.toString(gameDetailData[i][4]);
		}
		return temp;
	}

	/**
	 * Gets the team rank.
	 * 
	 * @return the team rank
	 */
	public String[][] getTeamRank() {
		String[][] temp = new String[teamRank.length][3];
		for (int i = 0; i < teamRank.length; i++) {
			temp[i][0] = Integer.toString(i + 1);
			temp[i][1] = teamRank[i][0];
			temp[i][2] = teamRank[i][1];
		}
		return temp;
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
