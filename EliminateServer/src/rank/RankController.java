/*
 * 
 */
package rank;

import login.OnlineUser;
import serverDB.DatabaseOperation;

// TODO: Auto-generated Javadoc
/**
 * The Class RankController.
 */
public class RankController {

	/** The db. */
	static DatabaseOperation db = new DatabaseOperation();

	/**
	 * Gets the daily score.
	 * 
	 * @param userID
	 *            the user id
	 * @return the daily score
	 */
	public static void getDailyScore(String userID) {
		OnlineUser.getSockets(userID).writeObject(
				new RankMsg(RankMsg.DAILYASCORE, db.getAverage(userID)));
	}

	/**
	 * Gets the daily times.
	 * 
	 * @param userID
	 *            the user id
	 * @return the daily times
	 */
	public static void getDailyTimes(String userID) {
		OnlineUser.getSockets(userID).writeObject(
				new RankMsg(RankMsg.DAILYTIMES, db.getAverage(userID)));
	}

	/**
	 * Gets the score rank.
	 * 
	 * @param userID
	 *            the user id
	 * @return the score rank
	 */
	public static void getScoreRank(String userID) {
		OnlineUser.getSockets(userID).writeObject(
				new RankMsg(RankMsg.GAME_DETAIL, db
						.getSingleRanking(userID)));
	}

	/**
	 * Gets the team rank.
	 * 
	 * @param userID
	 *            the user id
	 * @return the team rank
	 */
	public static void getTeamRank(String userID) {
		OnlineUser.getSockets(userID).writeObject(
				new RankMsg(db.getTeamRanking()));
	}
}
