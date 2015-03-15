package chess;

import room.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class FightGame.
 */
public class FightGame {

	/** The games. */
	MutiPlayerGame[] games = new MutiPlayerGame[2];

	/** The room id. */
	private long roomID;

	/**
	 * Instantiates a new fight game.
	 * 
	 * @param roomID
	 *            the room id
	 */
	public FightGame(long roomID) {
		this.roomID = roomID;
		games[0] = new MutiPlayerGame(roomID, Room.STAND_ONE);
		games[0].setTypeFight();
		games[1] = new MutiPlayerGame(roomID, Room.STAND_TWO);
		games[1].setTypeFight();
	}

	/**
	 * Gets the game.
	 * 
	 * @param stand
	 *            the stand
	 * @return the game
	 */
	public MutiPlayerGame getGame(int stand) {
		if (stand == Room.STAND_ONE) {
			return games[0];
		} else if (stand == Room.STAND_TWO) {
			return games[1];
		}
		return null;
	}

	/**
	 * Gets the room id.
	 * 
	 * @return the room id
	 */
	public long getRoomID() {
		return roomID;
	}

	/**
	 * Re start.
	 */
	public void reStart() {
		games[0].reStart();
		games[1].reStart();
	}

	/**
	 * Start.
	 */
	public void start() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				games[0].start();
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				games[1].start();
			}
		}).start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		games[0].stop();
		games[1].stop();
	}
}
