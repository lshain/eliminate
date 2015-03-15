/*
 * 
 */
package eliminate.controller.input;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import login.LoginMsg;
import rank.RankMsg;
import room.Room;
import room.RoomMsg;
import singlePlayer.SinglePlayerMsg;
import user.User;
import user.UserMsg;
import chess.ChessMsg;
import chess.MutiPlayerGameMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.room.RoomList;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.dialogs.FriendAskForAddDialog;
import eliminate.view.dialogs.FriendAskForInviteDialog;
import eliminate.view.dialogs.FriendInfoDialog;
import eliminate.view.dialogs.FriendInviteDialog;
import eliminate.view.dialogs.FriendManageDialog;
import eliminate.view.dialogs.NetworkDialog;
import eliminate.view.dialogs.RankingDialog;
import eliminate.view.fight.FightGame;
import eliminate.view.login.LoginPanel;
import eliminate.view.mutiplayer.CorperatePanel;
import eliminate.view.room.AgainstRoomWaitPanel;
import eliminate.view.room.RoomPanel;
import eliminate.view.room.RoomWaitPanel;
import eliminate.view.single.ChooseItemPanel;
import eliminate.view.single.GamePanel;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class HandleInput.
 */
public class HandleInput implements Runnable {

	/** The game type. */
	private int gameType = 0;

	/** The input. */
	ObjectInputStream input;

	/** The in game. */
	static boolean inGame = false;

	/** The rooms. */
	static RoomList rooms;

	/** The room. */
	static Room room;

	/** The user. */
	static User user;

	/**
	 * Change to choose item panel.
	 */
	public static void changeToChooseItemPanel() {
		inGame = false;
		AudioPlayer.playMain(true);
		chooseItemPanel.reSet();
		MainFrame.setContentPanel(chooseItemPanel);
	}

	public static void changeToRoomPanel() {
		new Thread(new Runnable(){
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			inGame = false;
			MainFrame.setContentPanel(roomPanel);
		}
		
		}).start();
	}

	/**
	 * Gets the room.
	 * 
	 * @return the room
	 */
	public static Room getRoom() {
		return room;
	}

	/**
	 * Gets the rooms.
	 * 
	 * @return the rooms
	 */
	public static RoomList getRooms() {
		return rooms;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public static User getUser() {
		return user;
	}

	/**
	 * Sets the in game.
	 * 
	 * @param ingame
	 *            the new in game
	 */
	public static void setInGame(boolean ingame) {
		inGame = ingame;
	}

	/**
	 * Show rank dialog.
	 */
	public static void showRankDialog() {
		rankDialog.setUserInfo(user);
		rankDialog.show();
	}

	/** The stand. */
	int stand;

	/** The temp. */
	int temp = 0;

	/** The my score. */
	int myScore = 0;

	/** The against score. */
	int againstScore = 0;

	/** The mygold. */
	int mygold = 0;

	/** The room wait panel. */
	RoomWaitPanel roomWaitPanel = new RoomWaitPanel();

	/** The against room wait panel. */
	AgainstRoomWaitPanel againstRoomWaitPanel = new AgainstRoomWaitPanel();

	/** The corperate panel. */
	CorperatePanel corperatePanel = new CorperatePanel();

	/** The room panel. */
	static RoomPanel roomPanel = new RoomPanel();

	/** The fight panel. */
	FightGame fightPanel = new FightGame();

	/** The single game. */
	GamePanel singleGame = new GamePanel();

	/** The choose item panel. */
	static ChooseItemPanel chooseItemPanel = new ChooseItemPanel();

	/** The friend manage dialog. */
	FriendManageDialog friendManageDialog;

	/** The rank dialog. */
	static RankingDialog rankDialog;

	/** The thread. */
	Thread thread;

	/**
	 * Instantiates a new handle input.
	 * 
	 * @param input
	 *            the input
	 * @param user
	 *            the user
	 */
	public HandleInput(ObjectInputStream input, User user) {
		this.input = input;
		rooms = new RoomList();
		HandleInput.user = user;
		roomWaitPanel.initMoney(user.getGold());
		againstRoomWaitPanel.initMoney(user.getGold());
		chooseItemPanel.updateMoney(user.getGold());
		rooms.addObserver(roomPanel);
		rankDialog = new RankingDialog(MainFrame.getOwner(), true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		LoginController.writeObject(new RoomMsg(null, RoomMsg.ROOM_LIST));
		while (thread != null) {
			try {
				Object obj = input.readObject();
				System.out.println("HandleInput: " + obj);
				if (obj instanceof RoomMsg) {
					RoomMsg msg = (RoomMsg) obj;
					if (msg.getType() == RoomMsg.ROOM_LIST) {
						System.out.println("HandleInput:" + msg.getRoomList2());
						rooms.setRoomList(msg.getRoomList1(),
								msg.getRoomList2());
					}
					if (msg.getType() == RoomMsg.CREATE_SUCESS) {
						room = msg.getRoom();
						roomWaitPanel.reset();
						againstRoomWaitPanel.reset();
						fightPanel.clearText();
						corperatePanel.clearText();
						if (room.getType() == Room.COOPERATE) {
							roomWaitPanel.initPlayer(room.getTableData());
							MainFrame.setContentPanel(roomWaitPanel);
							gameType = User.COOPERATE_GAME;
						} else if (room.getType() == Room.FIGHT_AGAINST) {
							againstRoomWaitPanel
									.initPlayer(room.getTableData());
							MainFrame.setContentPanel(againstRoomWaitPanel);
							gameType = User.FIGHT_GAME;
						}
						System.out.println(room.getUserList());
						// LoginController.chatThreadStart();
					} else if (msg.getType() == RoomMsg.ENTER_SUCESS) {
						room = msg.getRoom();
						roomWaitPanel.reset();
						againstRoomWaitPanel.reset();
						fightPanel.clearText();
						corperatePanel.clearText();
						if (room.getType() == Room.COOPERATE) {
							roomWaitPanel.initPlayer(room.getTableData());
							MainFrame.setContentPanel(roomWaitPanel);
							gameType = User.COOPERATE_GAME;
						} else if (room.getType() == Room.FIGHT_AGAINST) {
							againstRoomWaitPanel
									.initPlayer(room.getTableData());
							MainFrame.setContentPanel(againstRoomWaitPanel);
							gameType = User.FIGHT_GAME;
						}
						// LoginController.chatThreadStart();
					} else if (msg.getType() == RoomMsg.UPDATE_ROOM_INFO) {
						room = msg.getRoom();
						if (room.getType() == Room.COOPERATE) {
							roomWaitPanel.initPlayer(room.getTableData());
						} else if (room.getType() == Room.FIGHT_AGAINST) {
							againstRoomWaitPanel
									.initPlayer(room.getTableData());
						}
					} else if (msg.getType() == RoomMsg.ROOM_CHAT) {
						if (room.getType() == Room.COOPERATE) {
							roomWaitPanel.addChatContent(msg.getChatContent()
									+ "\n");
							corperatePanel.addChatContent(msg.getChatContent()
									+ "\n");
						} else if (room.getType() == Room.FIGHT_AGAINST) {
							againstRoomWaitPanel.addChatContent(msg
									.getChatContent() + "\n");
							fightPanel.addChatContent(msg.getChatContent()
									+ "\n");
						}
					} else if (msg.getType() == RoomMsg.START_GAME) {
						if (room.getType() == Room.COOPERATE) {
							corperatePanel.setPlayers(room);
							roomWaitPanel.buyItems();
							corperatePanel.setHasHint(false);
							MainFrame.setContentPanel(corperatePanel);
							corperatePanel.showReadyGo();
							AudioPlayer.playReadyGo();
						} else {
							MainFrame.setContentPanel(fightPanel);
							fightPanel.setHasHint(false);
							againstRoomWaitPanel.buyItems();
							fightPanel.showReadyGo();
						}
						inGame = true;
						stand = room.getUserStand(user.getUserId());
					} else if (msg.getType() == RoomMsg.PREPARE_GAME) {
						if (room.getType() == Room.COOPERATE) {
							roomWaitPanel.hideReadyButton();
						} else if (room.getType() == Room.FIGHT_AGAINST) {
							againstRoomWaitPanel.hideReadyButton();
						}
					} else if (msg.getType() == RoomMsg.CANCEL_PREPARED) {
						if (room.getType() == Room.COOPERATE) {
							roomWaitPanel.showReadyButton();
						} else if (room.getType() == Room.FIGHT_AGAINST) {
							againstRoomWaitPanel.showReadyButton();
						}
					} else if (msg.getType() == RoomMsg.LEAVE_ROOM) {
						roomWaitPanel.reset();
						againstRoomWaitPanel.reset();
						MainFrame.setContentPanel(roomPanel);
						// LoginController.voiceChatStop();
					} else if (msg.getType() == RoomMsg.MULTIPLAYER) {
						MainFrame.setContentPanel(roomPanel);
					} else if (msg.getType() == RoomMsg.ROOM_IN_GAME) {
						MainFrame.hideLoadingDialog();
						MainFrame.showMsgDialog("该房间正在游戏中，请稍候再试!", "进入房间");
					} else if (msg.getType() == RoomMsg.IS_FULL) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MainFrame.hideLoadingDialog();
						MainFrame.showMsgDialog("该房间人数已满!", "进入房间");
					} else if (msg.getType() == RoomMsg.SOMEONE_NOT_PREPARED) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MainFrame.hideLoadingDialog();
						MainFrame.showMsgDialog("所有人准备好在开始哦!", "开始游戏");
					}
				} else if (obj instanceof ChessMsg) {
					ChessMsg msg = (ChessMsg) obj;
					if (gameType == User.COOPERATE_GAME) {
						if (msg.getHint()) {
							corperatePanel.setHasHint(false);
						}
						corperatePanel.changeChessState(msg.getChessState());
					} else if (gameType == User.FIGHT_GAME) {
						if (msg.getStand() == stand) {
							if (msg.getHint()) {
								fightPanel.setHasHint(false);
							}
							fightPanel.changeBigChessState(msg.getChessState());
						} else if (msg.getStand() == Room.STAND_ONE
								+ Room.STAND_TWO - stand) {
							fightPanel.changeSmallChessState(msg
									.getChessState());
						}
					}
				} else if (obj instanceof LoginMsg) {
					LoginMsg msg = (LoginMsg) obj;
					if (msg.getType() == LoginMsg.PUSH_OUT_LINE) {
						LoginController.closeSocket();
						MainFrame.showMsgDialog("您的账号在其他地方登陆！", "警告");
						MainFrame.setContentPanel(new LoginPanel());
					}
				} else if (obj instanceof MutiPlayerGameMsg) {
					MutiPlayerGameMsg msg = (MutiPlayerGameMsg) obj;
					if (msg.getType() == MutiPlayerGameMsg.GAME_END) {
						if (gameType == User.COOPERATE_GAME) {
							MainFrame.showMsgDialog(
									"游戏结束！您们最终的分为" + msg.getScore() + "\n获得"
											+ msg.getGod() + "金币的奖励！", "多人游戏");
							roomWaitPanel.reset();
							MainFrame.setContentPanel(roomWaitPanel);
							corperatePanel.setInvincible(false);
						} else if (gameType == User.FIGHT_GAME) {
							if (msg.getStand() == stand) {
								myScore = msg.getScore();
								mygold = msg.getGod();
								fightPanel.setBigInvincible(false);
							} else {
								againstScore = msg.getScore();
								fightPanel.setSmallInvincible(false);
							}
							temp = (temp + 1) % 2;
							if (temp == 0) {
								if (myScore > againstScore) {
									AudioPlayer.playWin(false);
									MainFrame.showMsgDialog("恭喜你,您的小组获得胜利,获得"
											+ mygold + "金币的奖励", "对战游戏");
								} else {
									AudioPlayer.playFail(false);
									MainFrame.showMsgDialog("失败！\n获得" + mygold
											+ "金币的奖励", "对战游戏");
								}
								againstRoomWaitPanel.reset();
								MainFrame.setContentPanel(againstRoomWaitPanel);
							}
						}
						inGame = false;
					} else if (msg.getType() == MutiPlayerGameMsg.INVINCIBLE_TIME_START) {
						if (gameType == User.COOPERATE_GAME) {
							corperatePanel.setInvincible(true);
						} else if (gameType == User.FIGHT_GAME) {
							if (stand == msg.getStand()) {
								fightPanel.setBigInvincible(true);
							} else {
								fightPanel.setSmallInvincible(true);
								;
							}
						}
					} else if (msg.getType() == MutiPlayerGameMsg.INVINCIBLE_TIME_END) {
						if (gameType == User.COOPERATE_GAME) {
							corperatePanel.setInvincible(false);
						} else if (gameType == User.FIGHT_GAME) {
							if (stand == msg.getStand()) {
								fightPanel.setBigInvincible(false);
							} else {
								fightPanel.setSmallInvincible(false);
								;
							}
						}
					} else if (msg.getType() == MutiPlayerGameMsg.UPDATE_GAME_INFO) {
						if (gameType == User.COOPERATE_GAME) {
							corperatePanel.setScores(Integer.toString(msg
									.getScore()));
							corperatePanel.setRestTime(Integer.toString(msg
									.getRestTime()));
						} else if (gameType == User.FIGHT_GAME) {
							if (stand == msg.getStand()) {
								fightPanel.setMyScore(msg.getScore());
								fightPanel.setMyTime(msg.getRestTime());
							} else {
								fightPanel.setOtherScore(msg.getScore());
								fightPanel.setOtherTime(msg.getRestTime());
							}
						}
					} else if (msg.getType() == MutiPlayerGameMsg.UPDATE_GAME_SCORE) {
						if (gameType == User.COOPERATE_GAME) {
							corperatePanel.setScores(Integer.toString(msg
									.getScore()));
						} else if (gameType == User.FIGHT_GAME) {
							if (stand == msg.getStand()) {
								fightPanel.setMyScore(msg.getScore());
							} else {
								fightPanel.setOtherScore(msg.getScore());
							}
						}
					} else if (msg.getType() == MutiPlayerGameMsg.UPDATE_GAME_TIME) {
						if (gameType == User.COOPERATE_GAME) {
							corperatePanel.setRestTime(Integer.toString(msg
									.getRestTime()));
						} else if (gameType == User.FIGHT_GAME) {
							if (stand == msg.getStand()) {
								fightPanel.setMyTime(msg.getRestTime());
							} else {
								fightPanel.setOtherTime(msg.getRestTime());
							}
						}
					} else if (msg.getType() == MutiPlayerGameMsg.KICK_PLAYER) {
						MainFrame.showMsgDialog("您被请离了房间！", "多人游戏");
						roomWaitPanel.reset();
						againstRoomWaitPanel.reset();
						MainFrame.setContentPanel(roomPanel);
					} else if (msg.getType() == MutiPlayerGameMsg.SHOW_HINT) {
						if (gameType == User.COOPERATE_GAME) {
							corperatePanel.setHints(msg.getHint());
						} else if (gameType == User.FIGHT_GAME) {
							if (msg.getStand() == stand) {
								fightPanel.setHints(msg.getHint());
							}
						}
					} else if (msg.getType() == MutiPlayerGameMsg.FROZEN) {
						if (gameType == User.FIGHT_GAME) {
							if (stand == msg.getStand()) {
								fightPanel.frozenBig(msg.getFrozenType());
							} else {
								fightPanel.frozenSmall(msg.getFrozenType());;
							}
						}
					}
				} else if (obj instanceof SinglePlayerMsg) {
					SinglePlayerMsg msg = (SinglePlayerMsg) obj;
					if (msg.getType() == SinglePlayerMsg.GAME_START) {
						singleGame = new GamePanel();
						MainFrame.setContentPanel(singleGame);
						singleGame.setMaxScore(user.getScore());
						singleGame.start();
						singleGame.setItems(msg.getItems());
						AudioPlayer.playGamingBackgoundOne(true);
						inGame = true;
					}
				} else if (obj instanceof UserMsg) {
					UserMsg msg = (UserMsg) obj;
					if (msg.getType() == UserMsg.UPDATE_USER_INFO) {
						user = msg.getUser();
						roomWaitPanel.initMoney(user.getGold());
						againstRoomWaitPanel.initMoney(user.getGold());
						chooseItemPanel.updateMoney(user.getGold());
					} else if(msg.getType() == UserMsg.CHANGE_PASSWORD){
						MainFrame.showMsgDialog("更改密码成功，请牢记您的新密码！", "更改密码");
					}
				} else if (obj instanceof RankMsg) {
					RankMsg msg = (RankMsg) obj;
					if (msg.getType() == RankMsg.GAME_DETAIL) {
						rankDialog.showSingleScore(msg.getDetailData());
					} else if (msg.getType() == RankMsg.DAILYTIMES) {
						rankDialog.showDialyTime(msg.getDailyTimes());
					} else if (msg.getType() == RankMsg.DAILYASCORE) {
						rankDialog.showDialyScore(msg.getDailyScore());
					} else if (msg.getType() == RankMsg.MULTIPLAYER) {
						rankDialog.showTeamRank(msg.getTeamRank());
					}
				} // 好友
				else if (obj instanceof FriendMsg) {
					FriendMsg msg = (FriendMsg) obj;
					if (msg.getType() == FriendMsg.ASK_FOR_ADD_FRIEND) {
						if (!inGame) {
							FriendAskForAddDialog f = new FriendAskForAddDialog(
									MainFrame.getOwner(), true);
							f.visible(msg.getFriendId());
						} else {
							LoginController.writeObject(new FriendMsg(msg
									.getFriendId(), FriendMsg.FAIL_ADD_FRIEND));
						}
					} else if (msg.getType() == FriendMsg.USER_INFO) {
						User u = msg.getUser();
						boolean isFriend = msg.getIsFriend();
						FriendInfoDialog f = new FriendInfoDialog(
								MainFrame.getOwner(), true, isFriend);
						f.visible(u);
					} else if (msg.getType() == FriendMsg.ALL_FRIEND) {
						ArrayList<String> online = msg.getOnlineFriends();
						ArrayList<String> offline = msg.getOfflineFriends();
						friendManageDialog = new FriendManageDialog(
								MainFrame.getOwner(), true);
						friendManageDialog.visible(online, offline);
					} else if (msg.getType() == FriendMsg.FAIL_ADD_FRIEND) {
						MainFrame.showMsgDialog("该用户不存在或不在线", "添加好友失败");
					} else if (msg.getType() == FriendMsg.APPROVE_ADD_FRIEND) {
						MainFrame.showMsgDialog(
								msg.getFriendId() + "同意了您的好友请求", "添加好友成功");
					} else if (msg.getType() == FriendMsg.REFUSE_ADD_FRIEND) {
						MainFrame.showMsgDialog(
								msg.getFriendId() + "拒绝了您的好友请求", "添加好友失败");
					} else if (msg.getType() == FriendMsg.DELETE_FRIEND) {
						System.out.println("Delete!");
						ArrayList<String> online = msg.getOnlineFriends();
						ArrayList<String> offline = msg.getOfflineFriends();
						friendManageDialog = new FriendManageDialog(
								MainFrame.getOwner(), true);
						friendManageDialog.visible(online, offline);
					} else if (msg.getType() == FriendMsg.ONLINE_FRIEND) {
						ArrayList<String> online = msg.getOnlineFriends();
						FriendInviteDialog fid = new FriendInviteDialog(
								MainFrame.getOwner(), true);
						fid.visible(online);
					} else if (msg.getType() == FriendMsg.INVITE_FRIEND) {
						if (inGame) {
							LoginController.writeObject(new FriendMsg(msg
									.getFriendId(), FriendMsg.INVITE_IN_GAME));
						} else {
							FriendAskForInviteDialog f = new FriendAskForInviteDialog(
									MainFrame.getOwner(), true);
							f.visible(msg.getFriendId());
						}
					} else if (msg.getType() == FriendMsg.INVITE_REFUSE) {
						if (!inGame) {
							MainFrame.showMsgDialog("您的好友" + msg.getFriendId()
									+ "拒绝了您的邀请！", "邀请好友");
						}
					} else if (msg.getType() == FriendMsg.INVITE_IN_GAME) {
						if (!inGame) {
							MainFrame.showMsgDialog("您的好友" + msg.getFriendId()
									+ "正在游戏中，请稍候在再试！", "邀请好友");
						}
					} else if (msg.getType() == FriendMsg.INVITE_NOT_ONLINE) {
						if (!inGame) {
							MainFrame.showMsgDialog("您的好友" + msg.getFriendId()
									+ "已下线，请稍候在再试！", "邀请好友");
						}
					}
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				new Thread(new Runnable() {
					@Override
					public void run() {
						MainFrame.setContentPanel(new LoginPanel());
					}
				}).start();
				if (thread != null) {
					new NetworkDialog(MainFrame.getOwner(), true);
				}
				break;
			}

		}
	}

	/**
	 * Start.
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
		}
		thread.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		thread = null;
	}

}
