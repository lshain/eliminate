/*
 * 
 */
package chess;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import login.LoginThread;
import login.OnlineUser;
import room.Room;
import room.RoomList;
import user.User;
import user.UserMsg;
import chessModel.ChessModel;

// TODO: Auto-generated Javadoc
/**
 * The Class MutiPlayerGame.
 */
public class MutiPlayerGame extends Game implements Observer {

	/** The Constant TIME_TOOL_PRICE. */
	private static final int TIME_TOOL_PRICE = 35;

	/** The Constant BOMB_TOOL_PRICE. */
	private static final int BOMB_TOOL_PRICE = 35;

	/** The Constant SUPER_TOOL_PRICE. */
	private static final int SUPER_TOOL_PRICE = 35;

	/** The Constant HINT_TOOL_PRICE. */
	private static final int HINT_TOOL_PRICE = 35;

	/** The Constant SCORE_TOOL_PRICE. */
	private static final int SCORE_TOOL_PRICE = 35;
	

	/**
	 * Instantiates a new muti player game.
	 * 
	 * @param roomID
	 *            the room id
	 * @param stand
	 *            the stand
	 */
	public MutiPlayerGame(long roomID, int stand) {
		this.roomID = roomID;
		this.stand = stand;
	}

	/**
	 * Buy item.
	 * 
	 * @param items
	 *            the items
	 * @param userID
	 *            the user id
	 */
	public void buyItem(ArrayList<Integer> items, String userID) {
		if (timer.getRestTime() < 20) {
			return;
		}
		int gold = OnlineUser.getSockets(userID).getUser().getGold();
		for (int i : items) {
			switch (i) {
			case MutiPlayerGameMsg.BOMB_TOOL:
				if (gold >= BOMB_TOOL_PRICE) {
					bombToolNum++;
					gold -= BOMB_TOOL_PRICE;
				}
				break;
			case MutiPlayerGameMsg.TIME_TOOL:
				if (gold > TIME_TOOL_PRICE) {
					timer.addGameTime();
					gold -= TIME_TOOL_PRICE;
				}
				break;
			case MutiPlayerGameMsg.SCORE_TOOL:
				if (gold > SCORE_TOOL_PRICE) {
					scoreToolNum++;
					gold -= SCORE_TOOL_PRICE;
				}
				break;
			case MutiPlayerGameMsg.SUPER_TOOL:
				if (gold > SUPER_TOOL_PRICE) {
					comboToolNum++;
					timer.addComboTime();
					gold -= SUPER_TOOL_PRICE;
				}
				break;
			case MutiPlayerGameMsg.HINT_TOOL:
				if (gold > HINT_TOOL_PRICE) {
					hintTool = true;
					gold -= BOMB_TOOL_PRICE;
				}
				break;
			}
			if (gold < 0) {
				break;
			}
		}

		OnlineUser.getSockets(userID).getUser().setGold(gold);
		LoginThread.saveUser(OnlineUser.getSockets(userID).getUser());
	}
	
	public void deleteComboTool(){
		timer.deleteComboTool();
	}
	
	public void addComboTool(){
		for(int i = 0; i < comboToolNum; i++){
			timer.addComboTime();
		}
	}
	
	public void frozen(){
		frozen_type = (int) (Math.random() * ChessModel.NUM_NORMAL) + 1;
		MutiPlayerGameMsg msg = new MutiPlayerGameMsg(MutiPlayerGameMsg.FROZEN, stand);
		msg.setFrozenType(frozen_type);
		sendToPlayers(msg);
		timer.initFrozenTime();
	}
	
	public void deFrozen(){
		frozen_type = -1;
		MutiPlayerGameMsg msg = new MutiPlayerGameMsg(MutiPlayerGameMsg.FROZEN, stand);
		msg.setFrozenType(frozen_type);
		sendToPlayers(msg);
	}

	/**
	 * End game.
	 */
	public void endGame() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				timer.cancelHint();
				while (isThreadRunning) {
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				isThreadRunning = true;
				int num = chessModel.deleteAB();
				timer.combo(num, false);
				reDelete();
				while (bombToolNum > 0) {
					bombToolNum--;
					num = chessModel.bomb();
					timer.combo(num, false);
					reDelete();
				}
				int score = timer.getScore();
				while (scoreToolNum > 0) {
					score = (int) (score * 1.1);
					scoreToolNum--;
				}

				hintTool = false;
				MutiPlayerGameMsg msg = new MutiPlayerGameMsg(
						MutiPlayerGameMsg.GAME_END, stand);
				RoomList.getRoom(roomID).setGameState(Room.OUT_GAME);
				msg.setScore(score);
				int gold = score / 250;
				msg.setGold(gold);
				sendToPlayers(msg);
				ArrayList<String> users = RoomList.getRoom(roomID).getUserList(
						stand);
				LoginThread.updateTeamRanking(users, score);
				for (String user : users) {
					OnlineUser.getSockets(user).getUser().addGold(gold);
					LoginThread.saveUser(OnlineUser.getSockets(user).getUser());
					OnlineUser.getSockets(user).writeObject(
							new UserMsg(OnlineUser.getSockets(user)
									.getUser(), UserMsg.UPDATE_USER_INFO));
				}
				isThreadRunning = false;
				inGame = false;
				comboToolNum = 0;
			}
		}).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see chess.Game#sendToPlayers(java.lang.Object)
	 */
	@Override
	public void sendToPlayers(Object obj) {
		if (RoomList.getRoom(roomID) == null) {
			return;
		}
		for (String user : RoomList.getRoom(roomID).getUserList()) {
			OnlineUser.getSockets(user).writeObject(obj);
		}
	}

	/**
	 * Start.
	 */
	public void start() {
		inGame = true;
		reStart();
		hints = chessModel.getHint();
		while (hints.size() == 0) {
			chessModel = new ChessModel();
			hints = chessModel.getHint();
		}
		sendToPlayers(new ChessMsg(chessModel.getState(), stand));
		MutiPlayerGameMsg msg = new MutiPlayerGameMsg(
				MutiPlayerGameMsg.UPDATE_GAME_INFO, stand);
		msg.setRestTime(60);
		msg.setScore(0);
		sendToPlayers(msg);
		timer = new GameTimer();
		timer.addObserver(this);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.start();
		timer.initHintTime(hintTool);
	}

	/**
	 * Stop.
	 */
	public void stop() {
		timer.delete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obj, Object msg) {
		if (obj instanceof GameTimer) {
			Integer flag = (Integer) msg;
			if (flag == GameTimer.INVICIBLE_STATE_CHANGE) {
				if (timer.getInvicible()) {
					sendToPlayers(new MutiPlayerGameMsg(
							MutiPlayerGameMsg.INVINCIBLE_TIME_START, stand));
					if(type == User.FIGHT_GAME){
						FightGameList.getGame(roomID).getGame(Room.STAND_ONE + 
								Room.STAND_TWO - stand).deleteComboTool();
					}
				} else {
					sendToPlayers(new MutiPlayerGameMsg(
							MutiPlayerGameMsg.INVINCIBLE_TIME_END, stand));
					if(type == User.FIGHT_GAME){
						FightGameList.getGame(roomID).getGame(Room.STAND_ONE + 
								Room.STAND_TWO - stand).addComboTool();;
					}
				}
			} else if (flag == GameTimer.SCORE_CHANGE) {
				MutiPlayerGameMsg msg2 = new MutiPlayerGameMsg(
						MutiPlayerGameMsg.UPDATE_GAME_SCORE, stand);
				msg2.setScore(timer.getScore());
				sendToPlayers(msg2);
			} else if (flag == GameTimer.TOTAL_TIME_CHANGE) {
				int temp = timer.getRestTime();
				MutiPlayerGameMsg msg2 = new MutiPlayerGameMsg(
						MutiPlayerGameMsg.UPDATE_GAME_TIME, stand);
				msg2.setRestTime(temp);
				sendToPlayers(msg2);
				if (temp == 0) {
					inGame = false;
					endGame();
				}
			} else if (flag == GameTimer.HINT) {
				if (!isThreadRunning && timer.getRestTime() > 0) {
					MutiPlayerGameMsg msg2 = new MutiPlayerGameMsg(
							MutiPlayerGameMsg.SHOW_HINT, stand);
					msg2.setHints(hints);
					sendToPlayers(msg2);
				}
			} else if(flag == GameTimer.FROZEN_TIME_END){
				deFrozen();
			}
		}
	}
	
	public void setTypeFight(){
		type = User.FIGHT_GAME;
	}
}
