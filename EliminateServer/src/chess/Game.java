package chess;

import java.awt.Point;
import java.util.ArrayList;

import room.Room;
import user.User;
import chessModel.ChessModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public abstract class Game {

	/** The bomb tool num. */
	protected int bombToolNum = 0;

	/** The hint tool. */
	protected boolean hintTool = false;

	/** The score tool num. */
	protected int scoreToolNum = 0;
	
	protected int comboToolNum = 0;

	/** The Constant PAUSE. */
	protected static final long PAUSE = 20;

	/** The chess model. */
	ChessModel chessModel;

	/** The timer. */
	GameTimer timer = new GameTimer();

	/** The is thread running. */
	boolean isThreadRunning = false;

	/** The in game. */
	boolean inGame = false;

	/** The hints. */
	protected ArrayList<Point> hints;

	/** The stand. */
	int stand;
	
	int frozen_type = -1;
	
	protected int type;

	/** The room id. */
	long roomID;
	
	int temp = 0;

	/**
	 * Instantiates a new game.
	 */
	public Game() {
		this.chessModel = new ChessModel();
	}

	/**
	 * Delete.
	 */
	public void delete() {
		temp = 1;
		if (chessModel.canDelete()) {
			int num = chessModel.delete();
			if (chessModel.getColorNum() == 1 && (num == 4 || num == 6)) {
				System.out.println("ProduceA:");
				chessModel.produceA();
			} else if(chessModel.getColorNum() == 1 && num == 5){
				if(chessModel.getFiveInline()){
					System.out.println("ProduceB");
					chessModel.produceB();
				} else{
					System.out.println("ProduceA:");
					chessModel.produceA();
				}
				
			} else if (chessModel.getColorNum() == 1 && num > 6) {
				System.out.println("ProduceB");
				chessModel.produceB();
			} else if(chessModel.getColorNum() == 2 && num == 7){
				System.out.println("ProduceA:");
				chessModel.produceA();
			} else if(chessModel.getColorNum() == 2 && num == 9){
				System.out.println("ProduceA:");
				chessModel.produceA();
				System.out.println("ProduceB");
				chessModel.produceB();
			}
			timer.combo(num, true);
			sendToPlayers(new ChessMsg(chessModel.getState(), stand).hideHint());
			reDelete();
		} else {
			return;
		}
	}

	/**
	 * Delete.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void delete(final int x, final int y) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (!inGame || isThreadRunning) {
					System.out.println("isThreadRunning" + isThreadRunning + "temp:" + temp);
					return;
				}

				isThreadRunning = true;
				if (chessModel.getValue(x, y, 0) > ChessModel.PROP_A) {
					int num = chessModel.deleteA(x, y);
					timer.combo(num, true);
					sendToPlayers(new ChessMsg(chessModel.getState(), stand)
							.hideHint());
					timer.cancelHint();
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					reDelete();
				} else if (chessModel.getValue(x, y, 0) == ChessModel.PROP_B) {
					int num = chessModel.deleteB(x, y);
					if(type == User.FIGHT_GAME){
						FightGameList.getGame(roomID).getGame(Room.STAND_ONE + 
								Room.STAND_TWO - stand).frozen();
					}
					timer.combo(num, true);
					sendToPlayers(new ChessMsg(chessModel.getState(), stand)
							.hideHint());
					timer.cancelHint();
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					reDelete();
				}
				isThreadRunning = false;
			}

		}).start();
	}

	/**
	 * Re delete.
	 */
	protected void reDelete() {
		temp = 3;
		int num = 0;
		while (chessModel.isChangeble()) {
			temp  = 4;
			System.out.println("ISCHANGEABLE");
			while (chessModel.hasBlack()) {
				for (int i = 0; i < 3; i++) {
					chessModel.dropDown();
					sendToPlayers(new ChessMsg(chessModel.getState(), stand));
					try {
						Thread.sleep(PAUSE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				chessModel.delete();
				sendToPlayers(new ChessMsg(chessModel.getState(), stand));
			}
			num = chessModel.delete();
			sendToPlayers(new ChessMsg(chessModel.getState(), stand));
			timer.combo(num, false);
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		hints = chessModel.getHint();
		while (hints.size() == 0) {
			chessModel.changeModel(new ChessModel());
			sendToPlayers(new ChessMsg(chessModel.getState(), stand));
			hints = chessModel.getHint();
		}
		timer.initHintTime(hintTool);
	}

	/**
	 * Re start.
	 */
	public void reStart() {
		chessModel = new ChessModel();
	}

	/**
	 * Send to players.
	 * 
	 * @param obj
	 *            the obj
	 */
	public abstract void sendToPlayers(Object obj);

	/**
	 * Swap.
	 * 
	 * @param start_x
	 *            the start_x
	 * @param start_y
	 *            the start_y
	 * @param end_x
	 *            the end_x
	 * @param end_y
	 *            the end_y
	 */
	public void swap(final int start_x, final int start_y, final int end_x,
			final int end_y) {
		for (int i = 0; i < 3; i++) {
			chessModel.setValue(start_x, start_y, 1, (end_x - start_x)
					* chessModel.getWidth() / 3 * (i + 1));
			chessModel.setValue(start_x, start_y, 2, (end_y - start_y)
					* chessModel.getHeight() / 3 * (i + 1));

			chessModel.setValue(end_x, end_y, 1,
					(start_x - end_x) * chessModel.getWidth() / 3 * (i + 1));
			chessModel.setValue(end_x, end_y, 2,
					(start_y - end_y) * chessModel.getHeight() / 3 * (i + 1));
			sendToPlayers(new ChessMsg(chessModel.getState(), stand));
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Swap chess.
	 * 
	 * @param start_pos
	 *            the start_pos
	 * @param end_pos
	 *            the end_pos
	 */
	public void swapChess(final Point start_pos, final Point end_pos) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(chessModel.getValue(start_pos.x, start_pos.y, 0) == frozen_type ||
						chessModel.getValue(end_pos.x, end_pos.y, 0) == frozen_type){
					return;
				}
				if (!inGame || isThreadRunning) {
					System.out.println("isThreadRunning" + isThreadRunning + "temp:" + temp);
					return;
				}
				isThreadRunning = true;
				swap(start_pos.x, start_pos.y, end_pos.x, end_pos.y);
				chessModel.swapColor(start_pos.x, start_pos.y, end_pos.x,
						end_pos.y);
				sendToPlayers(new ChessMsg(chessModel.getState(), stand));
				if (chessModel.canDelete(start_pos.x, start_pos.y)
						|| chessModel.canDelete(end_pos.x, end_pos.y)) {
					try{
						timer.cancelHint();
					} catch(Exception e){
						e.printStackTrace();
					}
					delete();
				} else {
					swap(start_pos.x, start_pos.y, end_pos.x, end_pos.y);
					chessModel.swapColor(start_pos.x, start_pos.y, end_pos.x,
							end_pos.y);
					sendToPlayers(new ChessMsg(chessModel.getState(), stand));
				}
				temp = 0;
				isThreadRunning = false;
			}
		}).start();
	}
}
