/*
 * 
 */
package eliminate.view.single;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import singlePlayer.SinglePlayerMsg;
import chess.MutiPlayerGameMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.dialogs.AboutDialog;
import eliminate.view.dialogs.HelpDialog;
import eliminate.view.dialogs.SettingDialog;
import eliminate.view.start.MainPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ChooseItemPanel.
 */
@SuppressWarnings("serial")
public class ChooseItemPanel extends JPanel implements ActionListener,
		MouseListener {

	/** The items. */
	private ArrayList<Integer> items = new ArrayList<Integer>();

	// 背景图片
	/** The background_pic. */
	private String background_pic = "media/image/singlePlayer/chooseItemBackground.png.jpg";

	// 现有金币
	/** The money. */
	private JLabel money;

	/** The now_money. */
	private int now_money;

	// 退出按键
	/** The exit. */
	private String exit = "media/image/roomWait/bt/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/roomWait/bt/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/roomWait/bt/exit_hover.png";

	/** The exit_bt. */
	private JButton exit_bt;

	// 选用道具
	/** The choose. */
	private String choose = "media/image/roomWait/bt/choose.png";

	// 选用道具的光圈
	/** The choose_super. */
	private JButton choose_hint, choose_bomb, choose_time, choose_score,
			choose_super;

	// 是否选用道具
	/** The is_choose_super. */
	private boolean is_choose_hint, is_choose_bomb, is_choose_time,
			is_choose_score, is_choose_super;

	// 道具所需金币
	/** The super_money. */
	private JLabel hint_money, bomb_money, time_money, score_money,
			super_money;

	/** The money_super. */
	private int money_hint, money_bomb, money_time, money_score, money_super;

	// 确认要使用道具的勾
	/** The confirm. */
	private String confirm = "media/image/roomWait/bt/confirm.png";

	// 设置提示道具价格
	// private void setHintMoney(int i) {
	// hint_bt.setText(Integer.toString(i));
	// hint_bt.repaint();
	// }
	//
	// // 设置提示道具价格
	// private void setBombMoney(int i) {
	// bomb_bt.setText(Integer.toString(i));
	// bomb_bt.repaint();
	// }
	//
	// // 设置时间道具价格
	// private void setTimeMoney(int i) {
	// time_bt.setText(Integer.toString(i));
	// time_bt.repaint();
	// }
	//
	// // 设置分数道具价格
	// private void setScoreMoney(int i) {
	// score_bt.setText(Integer.toString(i));
	// score_bt.repaint();
	// }
	//
	// // 设置无敌时间道具价格
	// private void setSuperMoney(int i) {
	// super_bt.setText(Integer.toString(i));
	// super_bt.repaint();
	// }

	/** The confirm_super. */
	private JButton confirm_hint, confirm_bomb, confirm_time, confirm_score,
			confirm_super;

	// 提示
	/** The hint. */
	private String hint = "media/image/roomWait/hint.png";

	/** The hint_disable. */
	private String hint_disable = "media/image/roomWait/hint_disable.png";

	/** The hint_bt. */
	private JButton hint_bt;

	// 炸弹
	/** The bomb. */
	private String bomb = "media/image/roomWait/bomb.png";

	/** The bomb_disable. */
	private String bomb_disable = "media/image/roomWait/bomb_disable.png";

	/** The bomb_bt. */
	private JButton bomb_bt;

	// 增加时间
	/** The time. */
	private String time = "media/image/roomWait/time.png";

	/** The time_disable. */
	private String time_disable = "media/image/roomWait/time_disable.png";

	/** The time_bt. */
	private JButton time_bt;

	// 分数提升
	/** The score. */
	private String score = "media/image/roomWait/score.png";

	/** The score_disable. */
	private String score_disable = "media/image/roomWait/score_disable.png";

	/** The score_bt. */
	private JButton score_bt;

	// 无敌时间增长
	/** The super_up. */
	private String super_up = "media/image/roomWait/super.png";

	/** The super_disable. */
	private String super_disable = "media/image/roomWait/super_disable.png";

	/** The super_bt. */
	private JButton super_bt;

	// 开始按钮
	/** The start. */
	private String start = "media/image/roomWait/bt/start.png";

	/** The start_down. */
	private String start_down = "media/image/roomWait/bt/start_down.png";

	/** The start_hover. */
	private String start_hover = "media/image/roomWait/bt/start_hover.png";

	/** The start_bt. */
	private JButton start_bt;

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

	// 字体
	/** The font. */
	private static Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	/**
	 * Instantiates a new choose item panel.
	 */
	public ChooseItemPanel() {
		// 各个道具的金钱
		money_hint = 35;
		money_bomb = 35;
		money_time = 35;
		money_score = 35;
		money_super = 35;
		// 现有金钱
		now_money = 90;

		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(20f));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
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
		// 退出
		if (e.getSource() == exit_bt) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					MainFrame.setContentPanel(new MainPanel());
				}
			}).start();
			MainFrame.showLoadingDialog();
		}
		// 开始
		else if (e.getSource() == start_bt) {
			LoginController.writeObject(new SinglePlayerMsg(items));
			MainFrame.showLoadingDialog();
		} else if (e.getSource() == settings_bt) {
			new SettingDialog(MainFrame.getOwner(), true, true);
		} else if (e.getSource() == help_bt) {
			new HelpDialog(MainFrame.getOwner(), true);
		} else if (e.getSource() == about_bt) {
			new AboutDialog(MainFrame.getOwner(), true);
		}
	}

	// 判断是否能够购买道具,并设置图标可否点击
	/**
	 * Can click.
	 * 
	 * @param order
	 *            the order
	 */
	private void canClick(String order) {
		// 判断hint道具可否购买
		if (!canOffer(now_money, money_hint) && !order.equals("hint")
				&& !is_choose_hint) {
			hint_bt.setEnabled(false);
		} else {
			hint_bt.setEnabled(true);
		}
		// 判断bomb道具可否购买
		if (!canOffer(now_money, money_bomb) && !order.equals("bomb")
				&& !is_choose_bomb) {
			bomb_bt.setEnabled(false);
		} else {
			bomb_bt.setEnabled(true);
		}
		// 判断time道具可否购买
		if (!canOffer(now_money, money_time) && !order.equals("time")
				&& !is_choose_time) {
			time_bt.setEnabled(false);
		} else {
			time_bt.setEnabled(true);
		}
		// 判断score道具可否购买
		if (!canOffer(now_money, money_score) && !order.equals("score")
				&& !is_choose_score) {
			score_bt.setEnabled(false);
		} else {
			score_bt.setEnabled(true);
		}
		// 判断super道具可否购买
		if (!canOffer(now_money, money_super) && !order.equals("super")
				&& !is_choose_super) {
			super_bt.setEnabled(false);
		} else {
			super_bt.setEnabled(true);
		}
	}

	// 判断是否能够购买道具
	/**
	 * Can offer.
	 * 
	 * @param now
	 *            the now
	 * @param need
	 *            the need
	 * @return true, if successful
	 */
	private boolean canOffer(int now, int need) {
		if (now >= need) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inits the.
	 */
	private void init() {
		setLayout(null);

		// *****************显示现有金币*********
		money = new JLabel("90");
		add(money);
		money.setBounds(877, 50, 90, 30);
		money.setHorizontalAlignment(SwingConstants.CENTER);
		money.setVerticalAlignment(SwingConstants.CENTER);
		money.setFont(font);
		money.setForeground(Color.WHITE);

		try {

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

			// ************************************提示部分****************************
			// 提示
			hint_bt = MyButton.makeButton(hint, hint, hint, hint_disable);
			add(hint_bt);
			hint_bt.setBounds(422, 108, 152, 115);
			// 441,109,114,114
			hint_bt.addMouseListener(this);
			is_choose_hint = false;
			hint_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));

			// 提示外层光圈
			choose_hint = MyButton.makeButton(choose, choose, choose);
			add(choose_hint);
			choose_hint.setBounds(386, 54, 225, 224);
			choose_hint.setVisible(false);

			// 提示价格
			hint_money = new JLabel(Integer.toString(money_hint));
			add(hint_money);
			hint_money.setBounds(470, 242, 40, 30);
			hint_money.setHorizontalAlignment(SwingConstants.CENTER);
			hint_money.setVerticalAlignment(SwingConstants.CENTER);
			hint_money.setFont(font);
			hint_money.setForeground(Color.BLACK);

			// 确认使用提示道具
			confirm_hint = MyButton.makeButton(confirm, confirm, confirm);
			add(confirm_hint);
			confirm_hint.setBounds(410, 223, 60, 60);
			confirm_hint.setVisible(false);

			// ************************************炸弹部分****************************
			// 炸弹
			bomb_bt = MyButton.makeButton(bomb, bomb, bomb, bomb_disable);
			add(bomb_bt);
			bomb_bt.setBounds(622, 104, 152, 115);
			bomb_bt.addMouseListener(this);
			is_choose_bomb = false;
			bomb_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));

			// 炸弹外层光圈
			choose_bomb = MyButton.makeButton(choose, choose, choose);
			add(choose_bomb);
			choose_bomb.setBounds(594, 54, 225, 224);
			choose_bomb.setVisible(false);

			// 炸弹价格
			bomb_money = new JLabel(Integer.toString(money_bomb));
			add(bomb_money);
			bomb_money.setBounds(678, 242, 40, 30);
			bomb_money.setHorizontalAlignment(SwingConstants.CENTER);
			bomb_money.setVerticalAlignment(SwingConstants.CENTER);
			bomb_money.setFont(font);
			bomb_money.setForeground(Color.BLACK);

			// 确认使用炸弹道具
			confirm_bomb = MyButton.makeButton(confirm, confirm, confirm);
			add(confirm_bomb);
			confirm_bomb.setBounds(618, 223, 60, 60);
			confirm_bomb.setVisible(false);

			// ************************************时间部分****************************
			// 时间
			time_bt = MyButton.makeButton(time, time, time, time_disable);
			add(time_bt);
			time_bt.setBounds(829, 104, 152, 115);
			time_bt.addMouseListener(this);
			is_choose_time = false;
			time_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));

			// 增长时间光圈
			choose_time = MyButton.makeButton(choose, choose, choose);
			add(choose_time);
			choose_time.setBounds(792, 54, 225, 224);
			choose_time.setVisible(false);

			// 增长时间价格
			time_money = new JLabel(Integer.toString(money_time));
			add(time_money);
			time_money.setBounds(876, 242, 40, 30);
			time_money.setHorizontalAlignment(SwingConstants.CENTER);
			time_money.setVerticalAlignment(SwingConstants.CENTER);
			time_money.setFont(font);
			time_money.setForeground(Color.BLACK);

			// 确认使用时间道具
			confirm_time = MyButton.makeButton(confirm, confirm, confirm);
			add(confirm_time);
			confirm_time.setBounds(816, 223, 60, 60);
			confirm_time.setVisible(false);

			// ************************************分数部分****************************
			// 分数增加
			score_bt = MyButton.makeButton(score, score, score, score_disable);
			add(score_bt);
			score_bt.setBounds(434, 324, 152, 115);
			score_bt.addMouseListener(this);
			is_choose_score = false;
			score_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));

			// 分数外层光圈
			choose_score = MyButton.makeButton(choose, choose, choose);
			add(choose_score);
			choose_score.setBounds(386, 258, 225, 224);
			choose_score.setVisible(false);

			// 分数价格
			score_money = new JLabel(Integer.toString(money_score));
			add(score_money);
			score_money.setBounds(470, 449, 40, 30);
			score_money.setHorizontalAlignment(SwingConstants.CENTER);
			score_money.setVerticalAlignment(SwingConstants.CENTER);
			score_money.setFont(font);
			score_money.setForeground(Color.BLACK);

			// 确认使用分数道具
			confirm_score = MyButton.makeButton(confirm, confirm, confirm);
			add(confirm_score);
			confirm_score.setBounds(410, 427, 60, 60);
			confirm_score.setVisible(false);

			// ************************************无敌时间部分****************************
			// 无敌时间增长
			super_bt = MyButton.makeButton(super_up, super_up, super_up,
					super_disable);
			add(super_bt);
			super_bt.setBounds(630, 314, 152, 115);
			super_bt.addMouseListener(this);
			is_choose_super = false;
			super_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));

			// 无敌时间外层光圈
			choose_super = MyButton.makeButton(choose, choose, choose);
			add(choose_super);
			choose_super.setBounds(594, 260, 225, 224);
			choose_super.setVisible(false);

			// 无敌时间价格
			super_money = new JLabel(Integer.toString(money_super));
			add(super_money);
			super_money.setBounds(678, 449, 40, 30);
			super_money.setHorizontalAlignment(SwingConstants.CENTER);
			super_money.setVerticalAlignment(SwingConstants.CENTER);
			super_money.setFont(font);
			super_money.setForeground(Color.BLACK);

			// 确认使用无敌时间道具
			confirm_super = MyButton.makeButton(confirm, confirm, confirm);
			add(confirm_super);
			confirm_super.setBounds(618, 427, 60, 60);
			confirm_super.setVisible(false);

			start_bt = MyButton.makeButton(start, start_down, start_hover);
			add(start_bt);
			start_bt.setBounds(873, 506, 174, 68);
			start_bt.addActionListener(this);

			// 判断是否能够购买道具
			canClick("");

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		AudioPlayer.playButtonOne();
		// 提示
		if (e.getSource() == hint_bt && hint_bt.isEnabled()) {
			if (is_choose_hint == false) {
				choose_hint.setVisible(true);
				confirm_hint.setVisible(true);
				is_choose_hint = true;
				items.add(MutiPlayerGameMsg.HINT_TOOL);
				// 修改现有金钱
				if (canOffer(now_money, money_hint)) {
					now_money = now_money - money_hint;
					setMoney(now_money);
					canClick("hint");
				}

			} else {
				choose_hint.setVisible(false);
				confirm_hint.setVisible(false);
				is_choose_hint = false;
				// 修改现有金钱
				now_money = now_money + money_hint;
				setMoney(now_money);
				canClick("hint");
				items.remove(new Integer(MutiPlayerGameMsg.HINT_TOOL));
			}
		}
		// 炸弹
		else if (e.getSource() == bomb_bt && bomb_bt.isEnabled()) {
			if (is_choose_bomb == false) {
				choose_bomb.setVisible(true);
				confirm_bomb.setVisible(true);
				is_choose_bomb = true;
				items.add(MutiPlayerGameMsg.BOMB_TOOL);
				// 修改现有金钱
				if (canOffer(now_money, money_bomb)) {
					now_money = now_money - money_bomb;
					setMoney(now_money);
					canClick("bomb");
				}
			} else {
				choose_bomb.setVisible(false);
				confirm_bomb.setVisible(false);
				is_choose_bomb = false;
				// 修改现有金钱
				items.remove(new Integer(MutiPlayerGameMsg.BOMB_TOOL));
				now_money = now_money + money_bomb;
				setMoney(now_money);
				canClick("bomb");
			}
		} else if (e.getSource() == time_bt && time_bt.isEnabled()) {
			if (is_choose_time == false) {
				choose_time.setVisible(true);
				confirm_time.setVisible(true);
				is_choose_time = true;
				// 修改现有金钱
				items.add(MutiPlayerGameMsg.TIME_TOOL);
				if (canOffer(now_money, money_time)) {
					now_money = now_money - money_time;
					setMoney(now_money);
					canClick("time");
				}
			} else {
				choose_time.setVisible(false);
				confirm_time.setVisible(false);
				is_choose_time = false;
				// 修改现有金钱
				items.remove(new Integer(MutiPlayerGameMsg.TIME_TOOL));
				now_money = now_money + money_time;
				setMoney(now_money);
				canClick("time");
			}
		}
		// 分数
		else if (e.getSource() == score_bt && score_bt.isEnabled()) {
			if (is_choose_score == false) {
				choose_score.setVisible(true);
				confirm_score.setVisible(true);
				is_choose_score = true;
				items.add(MutiPlayerGameMsg.SCORE_TOOL);
				// 修改现有金钱
				if (canOffer(now_money, money_score)) {
					now_money = now_money - money_score;
					setMoney(now_money);
					canClick("score");
				}
			} else {
				choose_score.setVisible(false);
				confirm_score.setVisible(false);
				is_choose_score = false;
				items.remove(new Integer(MutiPlayerGameMsg.SCORE_TOOL));
				// 修改现有金钱
				now_money = now_money + money_score;
				setMoney(now_money);
				canClick("score");
			}
		}
		// 无敌时间
		else if (e.getSource() == super_bt && super_bt.isEnabled()) {
			if (is_choose_super == false) {
				choose_super.setVisible(true);
				confirm_super.setVisible(true);
				is_choose_super = true;
				items.add(MutiPlayerGameMsg.SUPER_TOOL);
				// 修改现有金钱
				if (canOffer(now_money, money_super)) {
					now_money = now_money - money_super;
					setMoney(now_money);
					canClick("super");
				}
			} else {
				items.remove(new Integer(MutiPlayerGameMsg.SCORE_TOOL));
				choose_super.setVisible(false);
				confirm_super.setVisible(false);
				is_choose_super = false;
				// 修改现有金钱
				now_money = now_money + money_super;
				setMoney(now_money);
				canClick("super");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == hint_bt && hint_bt.isEnabled()) {
			if (is_choose_hint == false) {
				choose_hint.setVisible(true);
			}
		} else if (e.getSource() == bomb_bt && bomb_bt.isEnabled()) {
			if (is_choose_bomb == false) {
				choose_bomb.setVisible(true);
			}
		} else if (e.getSource() == time_bt && time_bt.isEnabled()) {
			if (is_choose_time == false) {
				choose_time.setVisible(true);
			}
		} else if (e.getSource() == score_bt && score_bt.isEnabled()) {
			if (is_choose_score == false) {
				choose_score.setVisible(true);
			}
		} else if (e.getSource() == super_bt && super_bt.isEnabled()) {
			if (is_choose_super == false) {
				choose_super.setVisible(true);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == hint_bt && hint_bt.isEnabled()) {
			if (is_choose_hint == false) {
				choose_hint.setVisible(false);
			}
		} else if (e.getSource() == bomb_bt && bomb_bt.isEnabled()) {
			if (is_choose_bomb == false) {
				choose_bomb.setVisible(false);
			}
		} else if (e.getSource() == time_bt && time_bt.isEnabled()) {
			if (is_choose_time == false) {
				choose_time.setVisible(false);
			}
		} else if (e.getSource() == score_bt && score_bt.isEnabled()) {
			if (is_choose_score == false) {
				choose_score.setVisible(false);
			}
		} else if (e.getSource() == super_bt && super_bt.isEnabled()) {
			if (is_choose_super == false) {
				choose_super.setVisible(false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
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
	 * Re set.
	 */
	public void reSet() {
		items.clear();
		choose_hint.setVisible(false);
		confirm_hint.setVisible(false);
		is_choose_hint = false;

		choose_bomb.setVisible(false);
		confirm_bomb.setVisible(false);
		is_choose_bomb = false;

		choose_time.setVisible(false);
		confirm_time.setVisible(false);
		is_choose_time = false;

		choose_score.setVisible(false);
		confirm_score.setVisible(false);
		is_choose_score = false;

		choose_super.setVisible(false);
		confirm_super.setVisible(false);
		is_choose_super = false;

		canClick("nothing");
	}

	// 修改现有金钱数量
	/**
	 * Sets the money.
	 * 
	 * @param coin
	 *            the new money
	 */
	private void setMoney(int coin) {
		money.setText(Integer.toString(coin));
		money.repaint();
	}

	/**
	 * Update money.
	 * 
	 * @param coin
	 *            the coin
	 */
	public void updateMoney(int coin) {
		setMoney(coin);
		now_money = coin;
		canClick("nothing");
	}
}
