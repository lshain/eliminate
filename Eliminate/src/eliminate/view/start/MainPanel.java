/*
 * 
 */
package eliminate.view.start;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rank.RankMsg;
import room.RoomMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.dialogs.AboutDialog;
import eliminate.view.dialogs.ExitDialog;
import eliminate.view.dialogs.HelpDialog;
import eliminate.view.dialogs.SettingDialog;

// TODO: Auto-generated Javadoc
/**
 * The Class MainPanel.
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel implements ActionListener {

	/** The test. */
	static JFrame test;

	/** The background_pic. */
	private String background_pic = "media/image/startFrame/main.jpg";

	// 单人游戏按键图片
	/** The single_player_bt. */
	JButton single_player_bt;

	/** The single_player. */
	private String single_player = "media/image/startFrame/bt/single_player.png";

	/** The single_player_down. */
	private String single_player_down = "media/image/startFrame/bt/single_player_hover.png";

	// 多人游戏按键
	/** The multi_player. */
	private String multi_player = "media/image/startFrame/bt/multi_player.png";

	/** The multi_player_down. */
	private String multi_player_down = "media/image/startFrame/bt/multi_player_hover.png";

	/** The multi_player_bt. */
	JButton multi_player_bt;

	// 排行榜按键
	/** The rank_list. */
	private String rank_list = "media/image/startFrame/bt/rank_list.png";

	/** The rank_list_down. */
	private String rank_list_down = "media/image/startFrame/bt/rank_list_hover.png";

	/** The rank_list_bt. */
	JButton rank_list_bt;

	// 退出按键
	/** The exit. */
	private String exit = "media/image/all/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/all/exit_hover.png";

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

	// 动画效果
	/** The rabbit. */
	private String rabbit = "media/image/startFrame/rabbit.png";

	/** The rabbit_pic. */
	JLabel rabbit_pic;

	/** The star1. */
	public String star1 = "media/image/startFrame/star1.png";

	/** The star1_ pic. */
	JLabel star1_Pic;
	/** The star2. */
	public String star2 = "media/image/startFrame/star2.png";

	/** The star2_ pic. */
	JLabel star2_Pic;

	/** The star3. */
	public String star3 = "media/image/startFrame/star3.png";

	/** The star3_ pic. */
	JLabel star3_Pic;

	/**
	 * Instantiates a new main panel.
	 */
	public MainPanel() {
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
		if (e.getSource() == single_player_bt) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					HandleInput.changeToChooseItemPanel();
				}
			}).start();
			;
			MainFrame.showLoadingDialog();
		} else if (e.getSource() == exit_bt) {
			new ExitDialog(MainFrame.getOwner(), true);
		} else if (e.getSource() == multi_player_bt) {
			LoginController.writeObject(new RoomMsg(null, RoomMsg.MULTIPLAYER));
			MainFrame.showLoadingDialog();
		} else if (e.getSource() == rank_list_bt) {
			LoginController.writeObject(new RankMsg(RankMsg.GAME_DETAIL, null));
			HandleInput.showRankDialog();
		} else if (e.getSource() == settings_bt) {
			new SettingDialog(MainFrame.getOwner(), true, false);
		} else if (e.getSource() == help_bt) {
			new HelpDialog(MainFrame.getOwner(), true);
		} else if (e.getSource() == about_bt) {
			new AboutDialog(MainFrame.getOwner(), true);
		}
	}

	/**
	 * Inits the.
	 */
	private void init() {
		try {
			// 单人游戏按键
			single_player_bt = MyButton.makeButton(single_player,
					single_player_down, single_player_down);
			add(single_player_bt);
			single_player_bt.setBounds(681, 68, 384, 143);
			single_player_bt.addActionListener(this);

			// 多人游戏按键
			multi_player_bt = MyButton.makeButton(multi_player,
					multi_player_down, multi_player_down);
			add(multi_player_bt);
			multi_player_bt.setBounds(710, 234, 394, 148);
			multi_player_bt.addActionListener(this);

			// 成就排行按键
			rank_list_bt = MyButton.makeButton(rank_list, rank_list_down,
					rank_list_down);
			add(rank_list_bt);
			rank_list_bt.setBounds(733, 393, 333, 135);
			rank_list_bt.addActionListener(this);

			single_player_bt.setBounds(644, 64,383,147);
			multi_player_bt.setBounds(644, 229,383,147);
			rank_list_bt.setBounds(648, 393,383,147);

			// 退出按键
			exit_bt = MyButton.makeButton(exit, exit_down, exit_down);
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

			star1_Pic = new JLabel(new ImageIcon(star1));
			star1_Pic.setBounds(191, 70, 235, 235);
			add(star1_Pic);

			star2_Pic = new JLabel(new ImageIcon(star2));
			star2_Pic.setBounds(100, 313, 140, 140);
			add(star2_Pic);

			star3_Pic = new JLabel(new ImageIcon(star3));
			star3_Pic.setBounds(405, 465, 76, 76);
			add(star3_Pic);

			rabbit_pic = new JLabel(new ImageIcon(rabbit));
			// rabbit_pic.setBounds(285,263,270,139);
			// rabbit_pic.setBounds(285,-135,270,139);
			add(rabbit_pic);

			// this.addMouseListener(new MouseAdapter(){
			// @Override
			// public void mouseClicked(MouseEvent e) {
			// System.out.println(e.getPoint());
			// }
			// });
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.setLayout(null);
		// addMouseMotionListener(new WindowDraggable());

		new Thread(new Runnable() {
			private int rabbit_data[] = { -135, -134, -130, -121, -105, -80,
					-44, 5, 69, 150, 260, 260, 224, 199, 183, 174, 170, 169,
					169, 170, 174, 183, 199, 224, 260, 260, 251, 247, 246, 246,
					247, 251, 260 };

			public int[] getRoute(int x, int Wave) {
				int[] temp = new int[600];
				int last = x;
				int now = 0;
				for (int i = 0; i < 60; i++) {
					now = x - Wave + (int) (2 * Wave * Math.random());
					// System.out.print(now + " ");
					// System.out.print(now - last + " ");
					for (int j = 0; j < 10; j++) {
						temp[10 * i + j] = (j * now + (10 - j) * last) / 10;
						// System.out.print( (j* now + (5-j) * last)/5 + " " +
						// now + " "+ last + " " + j + " ");
						// System.out.print(temp[10 * i + j] + " ");
					}
					last = now;

				}

				return temp;
			}

			@Override
			public void run() {
				AudioPlayer.playMain(true);
				for (int i = 0; i < 300; i++) {
					if (i < rabbit_data.length) {
						rabbit_pic.setBounds(285, rabbit_data[i], 270, 139);
						try {
							Thread.sleep(0);
						} catch (Exception e) {
						}
						rabbit_pic.repaint();
					}
					if (i % 5 == 0) {
						star1_Pic.setBounds(getRoute(191, 35)[i],
								getRoute(70, 35)[i], 235, 235);
						star2_Pic.setBounds(getRoute(100, 20)[i],
								getRoute(313, 20)[i], 140, 140);
						star3_Pic.setBounds(getRoute(405, 15)[i],
								getRoute(465, 15)[i], 76, 76);
					}
					try {
						Thread.sleep(20);
					} catch (Exception e) {
					}
					star1_Pic.repaint();
					star1_Pic.repaint();
					star1_Pic.repaint();
				}
			}
		}).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(background_pic).getImage(), 0, 0,
				this.getWidth(), this.getHeight(), this);
	}
}
