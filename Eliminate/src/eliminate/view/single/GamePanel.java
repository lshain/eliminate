/*
 * 
 */
package eliminate.view.single;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import singlePlayer.SinglePlayerMsg;
import chess.GameTimer;
import chess.MutiPlayerGameMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.controller.login.LoginController;
import eliminate.model.chess.ChessModel;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.InvinciblePanel;
import eliminate.view.MyButton;
import eliminate.view.dialogs.AboutDialog;
import eliminate.view.dialogs.HelpDialog;
import eliminate.view.dialogs.SettingDialog;
import eliminate.view.info.GameInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class GamePanel.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, Observer {

	/**
	 * The Class ChessMotionAdapter.
	 */
	class ChessMotionAdapter extends MouseAdapter {

		/** The pressed. */
		boolean pressed = false;

		/** The pos_x. */
		int pos_x;

		/** The pos_y. */
		int pos_y;

		/** The pos_start_x. */
		int pos_start_x;

		/** The pos_start_y. */
		int pos_start_y;

		/** The start_x. */
		int start_x;

		/** The start_y. */
		int start_y;

		/** The end_x. */
		int end_x;

		/** The end_y. */
		int end_y;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			start_x = e.getX() / chessModel.getWidth() + 1;
			start_y = e.getY() / chessModel.getHeight() + 1;
			int value = chessModel.getValue(start_x, start_y, 0);
			if (value > ChessModel.PROP_A) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						if (threadRunning) {
							return;
						}
						threadRunning = true;
						int num = chessModel.deleteA(start_x, start_y);
						timer.combo(num, true);
						if (chess_board.hasHint) {
							chess_board.setHint(false);
						}
						timer.cancelHint();
						if (num > 5) {
							animationPanel.changeTo(AnimationPanel.DISAPPEAR_6);
						}

						if (timer.getCombo() > 7) {
							animationPanel.changeTo(AnimationPanel.COMBO_8);
						} else if (timer.getCombo() > 3) {
							animationPanel.changeTo(AnimationPanel.COMBO_4);
						}
						repaint();
						try {
							Thread.sleep(PAUSE);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						reDelete();
						threadRunning = false;
					}
				}).start();
			} else if (value == ChessModel.PROP_B) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						if (threadRunning) {
							return;
						}
						threadRunning = true;
						int num = chessModel.deleteB(start_x, start_y);
						timer.combo(num, true);
						timer.cancelHint();
						if (num > 5) {
							animationPanel.changeTo(AnimationPanel.DISAPPEAR_6);
						}

						if (timer.getCombo() > 7) {
							animationPanel.changeTo(AnimationPanel.COMBO_8);
						} else if (timer.getCombo() > 3) {
							animationPanel.changeTo(AnimationPanel.COMBO_4);
						}
						if (chess_board.hasHint) {
							chess_board.setHint(false);
						}
						repaint();
						try {
							Thread.sleep(PAUSE);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						reDelete();
						threadRunning = false;
					}
				}).start();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			pressed = true;
			pos_start_x = e.getX();
			pos_start_y = e.getY();
			start_x = e.getX() / chessModel.getWidth() + 1;
			start_y = e.getY() / chessModel.getHeight() + 1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			pressed = false;

			// if (chessModel.getValue(start_x, start_y, 1) != 0) {
			//
			// }

			end_x = e.getX() / chessModel.getWidth() + 1;
			end_y = e.getY() / chessModel.getHeight() + 1;
			if (end_x >= chessModel.getHorizontalNum()
					|| end_y >= chessModel.getVerticalNum()) {
				return;
			}
			end_x -= start_x;
			end_y -= start_y;
			if (Math.abs(end_x) >= Math.abs(end_y)) {
				if (end_x < 0 && start_x > 0) {
					end_x = start_x - 1;
				} else if (end_x > 0 && start_x < chessModel.getHorizontalNum()) {
					end_x = start_x + 1;
				} else {
					return;
				}
				end_y = start_y;
			} else {
				if (end_y < 0 && start_y > 0) {
					end_y = start_y - 1;
				} else if (end_y > 0 && start_y < chessModel.getVerticalNum()) {
					end_y = start_y + 1;
				} else {
					return;
				}
				end_x = start_x;
			}
			handle(start_x, start_y, end_x, end_y);
		}
	}

	public static void setDirectDropDown(boolean dropDown) {
		directDropDown = dropDown;
	}

	/** The background_pic. */
	private String background_pic = "media/image/singlePlayer/single.jpg";

	// 退出按键
	/** The exit. */
	private String exit = "media/image/all/exit2.png";

	/** The exit_down. */
	private String exit_down = "media/image/all/exit2_hover.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/all/exit2_hover.png";

	/** The exit_bt. */
	JButton exit_bt;

	// 设置按键
	/** The settings. */
	private String settings = "media/image/all/settings.png";

	/** The settings_down. */
	private String settings_down = "media/image/all/settings_hover.png";

	/** The settings_bt. */
	JButton settings_bt;

	// 帮助按键
	/** The help. */
	private String help = "media/image/all/help.png";

	/** The help_down. */
	private String help_down = "media/image/all/help_hover.png";

	/** The help_bt. */
	JButton help_bt;

	// 关于按键
	/** The about. */
	private String about = "media/image/all/about.png";

	/** The about_down. */
	private String about_down = "media/image/all/about_hover.png";

	/** The about_bt. */
	JButton about_bt;

	/** The chess_board. */
	private ChessView chess_board;

	/** The info panel. */
	private GameInfo infoPanel;

	/** The invinci panel. */
	private InvinciblePanel invinciPanel;

	/** The animation panel. */
	private AnimationPanel animationPanel;

	/** The timer. */
	GameTimer timer;

	/** The chess model. */
	ChessModel chessModel;

	// private int timeToolNum = 0;
	/** The bomb tool num. */
	private int bombToolNum = 0;

	// private int superToolNum = 0;
	// private int hintToolNum = 0;
	/** The has hint tool. */
	private boolean hasHintTool = false;

	/** The score tool num. */
	private int scoreToolNum = 0;

	/** The pause. */
	private static int PAUSE = 20;

	/** The PAUS e1. */
	private static int PAUSE1 = 20;

	/** The in game. */
	private boolean inGame = false;

	/** The adapter. */
	private ChessMotionAdapter adapter = new ChessMotionAdapter();

	/** The thread running. */
	private boolean threadRunning = false;

	/** The hints. */
	private ArrayList<Point> hints;

	private static boolean directDropDown = false;

	/**
	 * Instantiates a new game panel.
	 */
	public GamePanel() {
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AudioPlayer.playButtonOne();
		if (e.getSource() == exit_bt) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					exit();
					HandleInput.changeToChooseItemPanel();
				}
			}).start();
			MainFrame.showLoadingDialog();
		} else if (e.getSource() == settings_bt) {
			new SettingDialog(MainFrame.getOwner(), true, true);
		} else if (e.getSource() == help_bt) {
			new HelpDialog(MainFrame.getOwner(), true);
		} else if (e.getSource() == about_bt) {
			new AboutDialog(MainFrame.getOwner(), true);
		}
	}

	/**
	 * Delete.
	 */
	public void delete() {
		if (chessModel.canDelete()) {
			if (chess_board.hasHint) {
				chess_board.setHint(false);
			}
			timer.cancelHint();
			int num = chessModel.delete(directDropDown);
			System.out.println(num + "个");
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
				animationPanel.changeTo(AnimationPanel.DISAPPEAR_6);
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
			if (timer.getCombo() > 7) {
				animationPanel.changeTo(AnimationPanel.COMBO_8);
			} else if (timer.getCombo() > 3) {
				animationPanel.changeTo(AnimationPanel.COMBO_4);
			}
			reDelete();
		} else {
			return;
		}
	}

	/**
	 * End game.
	 */
	public void endGame() {
		if (inGame) {
			inGame = !inGame;
		} else {
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (threadRunning) {
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				threadRunning = true;
				timer.cancelHint();
				hasHintTool = false;
				chess_board.setHint(false);
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
				LoginController.writeObject(new SinglePlayerMsg(score, timer
						.getMaxCombo(), score / 250));
				MainFrame.showMsgDialog("您最终的的分为" + score + "， 获得金币数量" + score
						/ 250, "单人游戏");
				threadRunning = false;
				HandleInput.changeToChooseItemPanel();
				AudioPlayer.gamingBackgoundOneStop();
				AudioPlayer.playMain(true);
				HandleInput.setInGame(false);
			}
		}).start();

	}

	/**
	 * Exit.
	 */
	public void exit() {
		bombToolNum = 0;
		// private int superToolNum = 0;
		// private int hintToolNum = 0;
		hasHintTool = false;
		scoreToolNum = 0;
		timer.cancel();
	}

	/**
	 * Handle.
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
	public void handle(final int start_x, final int start_y, final int end_x,
			final int end_y) {
		if (threadRunning) {
			return;
		}
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				threadRunning = true;
				swap(start_x, start_y, end_x, end_y);
				chessModel.swapColor(start_x, start_y, end_x, end_y);
				if (chessModel.canDelete(start_x, start_y)
						|| chessModel.canDelete(end_x, end_y)) {
					delete();
				} else {
					swap(start_x, start_y, end_x, end_y);
					chessModel.swapColor(start_x, start_y, end_x, end_y);
				}
				threadRunning = false;
			}
		});
		thread.start();
	}

	/**
	 * Inits the.
	 */
	private void init() {

		try {
			setLayout(null);
			// 退出按键
			exit_bt = MyButton.makeButton(exit, exit_down, exit_hover);
			add(exit_bt);
			exit_bt.setBounds(15, 541, 70, 70);
			exit_bt.addActionListener(this);

			// 设置按键
			settings_bt = MyButton.makeButton(settings, settings_down,
					settings_down);
			add(settings_bt);
			settings_bt.setBounds(102, 541, 70, 70);
			settings_bt.addActionListener(this);

			// 帮助按键
			help_bt = MyButton.makeButton(help, help_down, help_down);
			add(help_bt);
			help_bt.setBounds(189, 541, 70, 70);
			help_bt.addActionListener(this);

			// 关于按键
			about_bt = MyButton.makeButton(about, about_down, about_down);
			add(about_bt);
			about_bt.setBounds(278, 541, 70, 70);
			about_bt.addActionListener(this);

			chess_board = new ChessView();
			chess_board.setBounds(380, 43, 486, 513);
			add(chess_board);

			infoPanel = new GameInfo();
			infoPanel.setBounds(0, 0, 287, 575);
			add(infoPanel);

			invinciPanel = new InvinciblePanel();
			invinciPanel.setVisible(false);
			add(invinciPanel);
			invinciPanel.setBounds(-120, 10, 1053, 592);

			animationPanel = new AnimationPanel();
			add(animationPanel);
			animationPanel.setBounds(0, 0, 1103, 618);
			animationPanel.changeTo(AnimationPanel.NORMAL);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Move.
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
	public void move(final int start_x, final int start_y, final int end_x,
			final int end_y) {
		for (int i = 0; i < 3; i++) {
			chessModel.setValue(start_x, start_y, 1, (end_x - start_x)
					* chessModel.getWidth() / 3 * (i + 1));
			chessModel.setValue(start_x, start_y, 2, (end_y - start_y)
					* chessModel.getHeight() / 3 * (i + 1));
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chessModel.inform();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(background_pic).getImage(), 0, 0,
				this.getWidth(), this.getHeight(), this);
	}

	/**
	 * Re delete.
	 */
	private void reDelete() {
		int num = 0;
		while (chessModel.isChangeble()) {
			boolean temp = directDropDown;
			while (chessModel.hasBlack()) {
				for (int i = 0; i < 3; i++) {
					chessModel.dropDown(temp);
					chessModel.inform();
					try {
						Thread.sleep(PAUSE1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				num = chessModel.delete(temp);
				timer.combo(num, false);
			}
			num = chessModel.delete(temp);
			timer.combo(num, false);
			chessModel.inform();
			try {
				Thread.sleep(PAUSE1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hints = chessModel.getHint();
		while (hints.size() == 0) {
			chessModel.changeModel(new ChessModel());
			hints = chessModel.getHint();
		}
		timer.initHintTime(hasHintTool);
	}

	/**
	 * Sets the invicible.
	 * 
	 * @param invicible
	 *            the new invicible
	 */
	public void setInvicible(boolean invicible) {
		invinciPanel.setVisible(invicible);
	}

	/**
	 * Sets the items.
	 * 
	 * @param items
	 *            the new items
	 */
	public void setItems(ArrayList<Integer> items) {
		for (int i : items) {
			switch (i) {
			case MutiPlayerGameMsg.BOMB_TOOL:
				bombToolNum++;
				break;
			case MutiPlayerGameMsg.TIME_TOOL:
				timer.addGameTime();
				break;
			case MutiPlayerGameMsg.SCORE_TOOL:
				scoreToolNum++;
				break;
			case MutiPlayerGameMsg.SUPER_TOOL:
				timer.addComboTime();
				break;
			case MutiPlayerGameMsg.HINT_TOOL:
				hasHintTool = true;
				break;
			}
		}
	}

	/**
	 * Sets the max score.
	 * 
	 * @param score
	 *            the new max score
	 */
	public void setMaxScore(int score) {
		infoPanel.setMaxScore(score);
	}

	/**
	 * Start.
	 */
	public void start() {
		timer = new GameTimer();
		timer.addObserver(infoPanel);
		timer.addObserver(this);
		chessModel = new ChessModel();
		hints = chessModel.getHint();
		while (hints.size() == 0) {
			chessModel = new ChessModel();
			hints = chessModel.getHint();
		}
		chessModel.addObserver(chess_board);
		chess_board.addMouseListener(adapter);
		chess_board.setHint(false);
		chessModel.inform();
		chess_board.addMouseMotionListener(adapter);
		timer.start();
		timer.initHintTime(hasHintTool);
		inGame = true;
	}

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
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chessModel.inform();
		}
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
					setInvicible(true);
				} else {
					setInvicible(false);
				}
			} else if (flag == GameTimer.HINT) {
				if (!threadRunning)
					chess_board.setHint(hints);
			} else if (flag == GameTimer.TOTAL_TIME_CHANGE) {
				int temp = timer.getRestTime();
				if (temp == 5) {
					animationPanel.changeTo(AnimationPanel.TIME_OVER);
				}
				if (temp == 0) {
					chess_board.removeMouseListener(adapter);
					chess_board.removeMouseMotionListener(adapter);
					endGame();
				}
			}
		}
	}

}
