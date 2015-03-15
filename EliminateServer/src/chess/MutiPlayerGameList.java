/*
 * 
 */
package chess;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class MutiPlayerGameList.
 */
public class MutiPlayerGameList {

	/** The game list. */
	public static Map<Long, MutiPlayerGame> gameList = new HashMap<Long, MutiPlayerGame>();

	/**
	 * Adds the game.
	 * 
	 * @param roomID
	 *            the room id
	 * @param game
	 *            the game
	 */
	public static void addGame(Long roomID, MutiPlayerGame game) {
		gameList.put(roomID, game);
	}

	/**
	 * Gets the game.
	 * 
	 * @param roomID
	 *            the room id
	 * @return the game
	 */
	public static MutiPlayerGame getGame(Long roomID) {
		return gameList.get(roomID);
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
	 * Instantiates a new muti player game list.
	 */
	public MutiPlayerGameList() {

	}
}
