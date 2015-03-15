/*
 * 
 */
package chess;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class FightGameList.
 */
public class FightGameList {

	/** The game list. */
	public static Map<Long, FightGame> gameList = new HashMap<Long, FightGame>();

	/**
	 * Adds the game.
	 * 
	 * @param roomID
	 *            the room id
	 * @param game
	 *            the game
	 */
	public static void addGame(Long roomID, FightGame game) {
		gameList.put(roomID, game);
	}

	/**
	 * Gets the game.
	 * 
	 * @param roomID
	 *            the room id
	 * @return the game
	 */
	public static FightGame getGame(Long roomID) {
		return gameList.get(roomID);
	}

	/**
	 * Gets the game.
	 * 
	 * @param roomID
	 *            the room id
	 * @param stand
	 *            the stand
	 * @return the game
	 */
	public static MutiPlayerGame getGame(Long roomID, int stand) {
		return gameList.get(roomID).getGame(stand);
	}

	/**
	 * Removes the game.
	 * 
	 * @param roomID
	 *            the room id
	 */
	public static void removeGame(Long roomID) {
		gameList.remove(roomID);
	}

	/**
	 * Instantiates a new fight game list.
	 */
	public FightGameList() {

	}
}
