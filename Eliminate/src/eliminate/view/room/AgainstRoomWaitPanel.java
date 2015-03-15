/*
 * 
 */
package eliminate.view.room;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import room.RoomMsg;
import chess.MutiPlayerGameMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyScrollBarUI;
import eliminate.view.MyTable;
import eliminate.view.TableFightRoomAdapter;
import eliminate.view.TableFightRoomLabelRenderer;
import eliminate.view.TableFightRoomRenderer;
import eliminate.view.dialogs.ExitDialog;
import eliminate.view.dialogs.HelpDialog;
import eliminate.view.dialogs.SelfInfoDialog;
import eliminate.view.dialogs.SettingDialog;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class AgainstRoomWaitPanel.
 */
@SuppressWarnings("serial")
public class AgainstRoomWaitPanel extends JPanel implements ActionListener,
		MouseListener, Observer {

	// 背景图片
	/** The background_pic. */
	private String background_pic = "media/image/roomWait/background.jpg";

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

	// 设置按键
	/** The settings. */
	private String settings = "media/image/all/settings.png";

	/** The settings_down. */
	private String settings_down = "media/image/all/settings_hover.png";

	/** The settings_bt. */
	private JButton settings_bt;

	// 帮助按键
	/** The help. */
	private String help = "media/image/all/help.png";

	/** The help_down. */
	private String help_down = "media/image/all/help_hover.png";

	/** The help_bt. */
	private JButton help_bt;

	// 离开按钮
	/** The leave. */
	private String leave = "media/image/roomWait/bt/leave.png";

	/** The leave_down. */
	private String leave_down = "media/image/roomWait/bt/leave_down.png";

	/** The leave_hover. */
	private String leave_hover = "media/image/roomWait/bt/leave_hover.png";

	/** The leave_bt. */
	private JButton leave_bt;

	// 选用道具
	/** The choose. */
	private String choose = "media/image/roomWait/bt/choose.png";

	// 选用道具的光圈
	/** The choose_super. */
	private JButton choose_hint, choose_bomb, choose_time, choose_score,
			choose_super;

	// 设置提示道具价格
	// private void setHintMoney(int i) {
	// hint_bt.setText(Integer.toString(i));
	// hint_bt.repaint();
	// }
	//
	// //设置提示道具价格
	// private void setBombMoney(int i) {
	// bomb_bt.setText(Integer.toString(i));
	// bomb_bt.repaint();
	// }
	//
	// //设置时间道具价格
	// private void setTimeMoney(int i) {
	// time_bt.setText(Integer.toString(i));
	// time_bt.repaint();
	// }
	//
	// //设置分数道具价格
	// private void setScoreMoney(int i) {
	// score_bt.setText(Integer.toString(i));
	// score_bt.repaint();
	// }
	//
	// //设置无敌时间道具价格
	// private void setSuperMoney(int i) {
	// super_bt.setText(Integer.toString(i));
	// super_bt.repaint();
	// }
	//

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

	/** The confirm_super. */
	private JButton confirm_hint, confirm_bomb, confirm_time, confirm_score,
			confirm_super;

	// 提示
	/** The hint. */
	private String hint = "media/image/roomWait/hint.png";

	/** The hint_disable. */
	private String hint_disable = "media/image/roomWait/hint_disable.png";

	// 花费道具的总金钱及个数
	// private int total_money;
	// private int item_num;

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

	// 准备按钮
	/** The ready. */
	private String ready = "media/image/roomWait/bt/ready.png";

	/** The ready_down. */
	private String ready_down = "media/image/roomWait/bt/ready_down.png";
	/** The ready_hover. */
	private String ready_hover = "media/image/roomWait/bt/ready_hover.png";
	/** The ready_bt. */
	private JButton ready_bt;
	// 取消准备
	/** The cancel_ready. */
	private String cancel_ready = "media/image/roomWait/bt/cancel_ready.png";

	/** The cancel_ready_down. */
	private String cancel_ready_down = "media/image/roomWait/bt/cancel_ready_down.png";
	/** The cancel_ready_hover. */
	private String cancel_ready_hover = "media/image/roomWait/bt/cancel_ready_hover.png";

	/** The cancel_ready_bt. */
	private JButton cancel_ready_bt;

	// 开始按钮
	/** The start. */
	private String start = "media/image/roomWait/bt/start.png";

	/** The start_down. */
	private String start_down = "media/image/roomWait/bt/start_down.png";

	/** The start_hover. */
	private String start_hover = "media/image/roomWait/bt/start_hover.png";

	/** The start_bt. */
	private JButton start_bt;

	// 是否为房主
	/** The is room owner. */
	private boolean isRoomOwner = true;

	// 字体
	/** The font. */
	private static Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	// 聊天文字
	/** The content. */
	private JTextField content;

	/** The chat_content. */
	private JTextArea chat_content;

	/** The jsp. */
	private JScrollPane jsp;

	/** The send. */
	private JLabel send;

	// 花费道具的总金钱及个数
	/** The items. */
	private ArrayList<Integer> items = new ArrayList<Integer>();

	// 玩家信息
	/** The players. */
	private JTable players = null;

	/** The jsb. */
	private JScrollBar jsb;

	/** The player. */
	private Object[][] player = new String[][] { { "房主", "1", "炮灰乙" },
			{ "玩家", "1", "流氓丙" }, { "玩家", "1", "土匪丁" } };

	// 踢人符号
	/** The kick. */
	private String kick = "media/image/roomWait/roomOwner/kick.png";

	/** The kick_down. */
	private String kick_down = "media/image/roomWait/roomOwner/kick_down.png";

	/** The kick_hover. */
	private String kick_hover = "media/image/roomWait/roomOwner/kick_hover.png";

	/** The kick_disable. */
	private String kick_disable = "media/image/roomWait/roomOwner/kick_disable.png";

	// 强制换队
	/** The force. */
	private String force = "media/image/roomWait/roomOwner/force.png";

	/** The force_down. */
	private String force_down = "media/image/roomWait/roomOwner/force_down.png";

	/** The force_hover. */
	private String force_hover = "media/image/roomWait/roomOwner/force_hover.png";

	/** The force_disable. */
	private String force_disable = "media/image/roomWait/roomOwner/force_disable.png";

	// 换队
	/** The change. */
	private String change = "media/image/roomWait/roomOwner/change.png";

	/** The change_down. */
	private String change_down = "media/image/roomWait/roomOwner/change_down.png";

	/** The change_hover. */
	private String change_hover = "media/image/roomWait/roomOwner/change_hover.png";

	/** The change_disable. */
	private String change_disable = "media/image/roomWait/roomOwner/change_disable.png";

	// 邀请按钮
	/** The invite. */
	private String invite = "media/image/friends/invite_bt.png";

	/** The invite_hover. */
	private String invite_hover = "media/image/friends/invite_bt_hover.png";

	/** The invite_bt. */
	private JButton invite_bt;

	/**
	 * Instantiates a new against room wait panel.
	 */
	public AgainstRoomWaitPanel() {
		isRoomOwner = true;
		// 各个道具的金钱
		money_hint = 35;
		money_bomb = 35;
		money_time = 35;
		money_score = 35;
		money_super = 35;
		// 现有金钱
		now_money = 90;
		// 花费道具的总金钱
		// total_money = 0;
		// item_num = 0;

		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(20f));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
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
			new ExitDialog(MainFrame.getOwner(), true);
		}
		// 离开房间
		else if (e.getSource() == leave_bt) {
			LoginController.writeObject(new RoomMsg(HandleInput.getRoom(),
					RoomMsg.LEAVE_ROOM));
		} else if (e.getSource() == invite_bt) {
			LoginController.writeObject(new FriendMsg(LoginController.getUser()
					.getUserId(), FriendMsg.ONLINE_FRIEND));
		}
		// 设置
		else if (e.getSource() == settings_bt) {
			new SettingDialog(MainFrame.getOwner(), true, false);
		}
		// 帮助
		else if (e.getSource() == help_bt) {
			new HelpDialog(MainFrame.getOwner(), true);
		}
		// 准备
		else if (e.getSource() == ready_bt) {
			LoginController.writeObject(new RoomMsg(HandleInput.getRoom(),
					RoomMsg.PREPARE_GAME));
		}
		// 取消准备
		else if (e.getSource() == cancel_ready_bt) {
			LoginController.writeObject(new RoomMsg(HandleInput.getRoom(),
					RoomMsg.CANCEL_PREPARED));
		}
		// 开始
		else if (e.getSource() == start_bt) {
			LoginController.writeObject(new RoomMsg(HandleInput.getRoom(),
					RoomMsg.START_GAME));
		}
	}

	/**
	 * Adds the chat content.
	 * 
	 * @param content
	 *            the content
	 */
	public void addChatContent(String content) {
		chat_content.setText(chat_content.getText() + content);
		chat_content.selectAll();
	}

	/**
	 * Buy items.
	 */
	public void buyItems() {
		LoginController.writeObject(new MutiPlayerGameMsg(items, 0));
		reset();
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
	 * Clear items.
	 */
	public void clearItems() {
		items.clear();
	}

	/**
	 * Hide ready button.
	 */
	public void hideReadyButton() {
		ready_bt.setVisible(false);
		cancel_ready_bt.setVisible(true);
	}

	/**
	 * Inits the.
	 */
	private void init() {

		// addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// System.out.println(e.getX() + "  " + e.getY());
		// }
		// });

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
			exit_bt.setBounds(326, 542, 67, 67);
			exit_bt.addActionListener(this);

			// 离开按钮
			leave_bt = MyButton.makeButton(leave, leave_down, leave_hover);
			add(leave_bt);
			leave_bt.setBounds(413, 541, 71, 71);
			leave_bt.addActionListener(this);

			// 邀请按钮
			invite_bt = MyButton.makeButton(invite, invite, invite_hover);
			add(invite_bt);
			invite_bt.setBounds(680, 542, 67, 67);
			invite_bt.setVisible(false);
			invite_bt.addActionListener(this);

			// 设置按键
			settings_bt = MyButton.makeButton(settings, settings_down,
					settings_down);
			add(settings_bt);
			settings_bt.setBounds(501, 542, 67, 67);
			settings_bt.addActionListener(this);

			// 帮助按键
			help_bt = MyButton.makeButton(help, help_down, help_down);
			add(help_bt);
			help_bt.setBounds(589, 542, 67, 67);
			help_bt.addActionListener(this);

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

			// ************************************准备按钮部分****************************
			ready_bt = MyButton.makeButton(ready, ready_down, ready_hover);
			add(ready_bt);
			ready_bt.setBounds(873, 506, 174, 68);
			ready_bt.addActionListener(this);

			cancel_ready_bt = MyButton.makeButton(cancel_ready,
					cancel_ready_down, cancel_ready_hover);
			add(cancel_ready_bt);
			cancel_ready_bt.setBounds(873, 506, 174, 68);
			cancel_ready_bt.setVisible(false);
			cancel_ready_bt.addActionListener(this);

			start_bt = MyButton.makeButton(start, start_down, start_hover);
			add(start_bt);
			start_bt.setBounds(873, 506, 174, 68);
			start_bt.addActionListener(this);
			start_bt.setVisible(false);

			// 判断是否能够购买道具
			canClick("");

			// ************************************聊天部分****************************
			// 输入聊天文字
			content = new JTextField();
			add(content);
			content.setBounds(10, 578, 202, 35);
			content.setFont(font);
			content.setBorder(BorderFactory.createEmptyBorder());
			content.setText("想要说点什么嘛？~");
			content.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if (content.getText().toString().equals("想要说点什么嘛？~")) {
						content.setText("");
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if (content.getText().toString().equals("")) {
						content.setText("想要说点什么嘛？~");
					}
				}
			});

			content.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == 10
							&& !content.getText().toString().equals("")) {
						RoomMsg msg = new RoomMsg(HandleInput.getRoom(),
								RoomMsg.ROOM_CHAT);
						msg.setChatContent(LoginController.getUser()
								.getUserId() + ": " + content.getText());
						LoginController.writeObject(msg);
						content.setText("");
					}
				};
			});

			// 发送按钮
			send = new JLabel("发送");
			send.setFont(font);
			add(send);
			send.setBounds(215, 578, 78, 35);
			send.setHorizontalAlignment(SwingConstants.CENTER);
			send.setVerticalAlignment(SwingConstants.CENTER);
			send.setForeground(Color.RED);
			send.setCursor(new Cursor(Cursor.HAND_CURSOR));
			send.addMouseListener(this);

			// 聊天框
			chat_content = new JTextArea();
			// chat_content.setText("abdsfwef\nasdfewf\nassdfwe\na\nb\nc\nd\ne\nf\ng\nh\ni\ng");
			chat_content.setFont(font);
			chat_content.setForeground(Color.GREEN);
			chat_content.setLineWrap(true);
			jsp = new JScrollPane();
			jsp.getViewport().add(chat_content);
			jsp.getViewport().setOpaque(false);
			jsp.setOpaque(false);
			chat_content.setOpaque(false);
			add(jsp);
			jsp.setBounds(10, 341, 280, 235);
			jsp.setBorder(BorderFactory.createEmptyBorder());
			jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			jsb = jsp.getVerticalScrollBar();
			jsb.setOpaque(false);
			jsb.setUI(new MyScrollBarUI(true));

			// 玩家信息
			// initPlayer(player);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Inits the invite.
	 */
	public void initInvite() {
		int length = players.getRowCount();
		if (length == 4) {
			invite_bt.setVisible(false);
			invite_bt.repaint();
		} else {
			invite_bt.setVisible(true);
			invite_bt.repaint();
		}
	}

	/**
	 * Inits the money.
	 * 
	 * @param coin
	 *            the coin
	 */
	public void initMoney(int coin) {
		setMoney(coin);
		now_money = coin;
		canClick("nothing");
	}

	// 更新房间玩家
	/**
	 * Inits the player.
	 * 
	 * @param p
	 *            the p
	 */
	public void initPlayer(Object[][] p) {
		// 房主
		setRoomOwner(LoginController.getUser().getUserId().equals(p[0][2]));
		if (isRoomOwner) {
			this.player = new Object[p.length][5];
			for (int i = 0; i < p.length; i++) {
				player[i][0] = p[i][0];
				player[i][1] = p[i][1];
				player[i][2] = p[i][2];
				try {
					if (i == 0) {
						player[i][3] = MyButton.makeButton(change, change_down,
								change_hover, change_disable);
					} else {
						player[i][3] = MyButton.makeButton(force, force_down,
								force_hover, force_disable);
					}
					player[i][4] = MyButton.makeButton(kick, kick_down,
							kick_hover, kick_disable);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					player[i][3] = new JButton("换队");
					player[i][4] = new JButton("踢人");
					e.printStackTrace();
				}
			}
			if (players != null) {
				remove(players);
			}
			players = new MyTable(player, new String[] { "队伍", "备注", "昵称",
					"换队", "踢人" });
		}
		// 玩家
		else {
			this.player = new Object[p.length][4];
			for (int i = 0; i < p.length; i++) {
				player[i][0] = p[i][0];
				player[i][1] = p[i][1];
				player[i][2] = p[i][2];
				try {
					player[i][3] = MyButton.makeButton(change, change_down,
							change_hover, change_disable);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (players != null) {
				remove(players);
			}
			players = new MyTable(player,
					new String[] { "队伍", "备注", "昵称", "换队" });
		}
		add(players);
		players.setBounds(10, 108, 282, 216);
		players.setRowHeight(51);
		players.setRowMargin(2);
		players.setShowGrid(true);
		players.setGridColor(Color.BLACK);
		players.setOpaque(false);

		TableFightRoomLabelRenderer tlr = new TableFightRoomLabelRenderer(true);
		for (int i = 0; i < 3; i++) {
			players.getColumnModel().getColumn(i).setCellRenderer(tlr);
		}
		players.getColumnModel().getColumn(1).setMaxWidth(0);
		players.getColumnModel().getColumn(1).setMinWidth(0);
		players.getColumnModel().getColumn(1).setPreferredWidth(0);
		players.getColumnModel().getColumn(0).setPreferredWidth(71);
		TableFightRoomAdapter adapter = new TableFightRoomAdapter(players);
		if (isRoomOwner) {
			players.getColumnModel().getColumn(2).setPreferredWidth(119);
			players.getColumnModel().getColumn(3).setPreferredWidth(45);
			players.getColumnModel().getColumn(4).setPreferredWidth(45);
			players.getColumnModel().getColumn(4)
					.setCellRenderer(new TableFightRoomRenderer(adapter));
		} else {
			players.getColumnModel().getColumn(2).setPreferredWidth(159);
			players.getColumnModel().getColumn(3).setPreferredWidth(50);
		}
		players.addMouseListener(adapter);
		players.addMouseMotionListener(adapter);
		players.getColumnModel().getColumn(3)
				.setCellRenderer(new TableFightRoomRenderer(adapter));

		players.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 点击的行号
				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
				// 点击的列号
				int column = ((JTable) e.getSource()).columnAtPoint(e
						.getPoint());

				if (row >= 0 && column == 3) {
					String name = (String) (players.getValueAt(row, 2));
					if (HandleInput.getUser().getUserId().equals(name) || isRoomOwner) {
						// 换队
						MutiPlayerGameMsg msg = new MutiPlayerGameMsg(
								MutiPlayerGameMsg.CHANGE_TEAM, 0);
						msg.setPlayerID(name);
						LoginController.writeObject(msg);
						AudioPlayer.playButtonOne();
					}
				} else if (row > 0 && column == 4) {
					String name = (String) (players.getValueAt(row, 2));
					if (isRoomOwner) {
						// 踢人
						LoginController.writeObject(new MutiPlayerGameMsg(name,
								0));
						AudioPlayer.playButtonOne();
					}
				} else if (e.getClickCount() == 2) {
					if (column != 3 && column != 4) {
						// 如果点击的是自己哪一行
						String player_name = players.getValueAt(row, 2)
								.toString();
						String s = LoginController.getUser().getUserId();
						if (player_name.equals(s)) {
							new SelfInfoDialog(MainFrame.getOwner(), true);
						} else {
							LoginController.writeObject(new FriendMsg(
									player_name, FriendMsg.USER_INFO));
						}
					}
				}
			}
		});

		initInvite();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		AudioPlayer.playButtonOne();
		if (e.getSource() == hint_bt && hint_bt.isEnabled()
				&& (ready_bt.isVisible() || start_bt.isVisible())) {
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
		else if (e.getSource() == bomb_bt && bomb_bt.isEnabled()
				&& (ready_bt.isVisible() || start_bt.isVisible())) {
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
		} else if (e.getSource() == time_bt && time_bt.isEnabled()
				&& (ready_bt.isVisible() || start_bt.isVisible())) {
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
		else if (e.getSource() == score_bt && score_bt.isEnabled()
				&& (ready_bt.isVisible() || start_bt.isVisible())) {
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
		else if (e.getSource() == super_bt && super_bt.isEnabled()
				&& (ready_bt.isVisible() || start_bt.isVisible())) {
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
		} else if (e.getSource() == send) {
			RoomMsg msg = new RoomMsg(HandleInput.getRoom(), RoomMsg.ROOM_CHAT);
			msg.setChatContent(LoginController.getUser().getUserId() + ": "
					+ content.getText());
			LoginController.writeObject(msg);
			content.setText("");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
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
		} else if (e.getSource() == send) {
			send.setForeground(Color.BLUE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
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
		} else if (e.getSource() == send) {
			send.setForeground(Color.RED);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == send) {
			send.setForeground(Color.GREEN);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == send) {
			send.setForeground(Color.BLUE);
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
	 * Reset.
	 */
	public void reset() {
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

		items.clear();
		canClick("nothing");
		cancel_ready_bt.setVisible(false);
		if (!start_bt.isVisible()) {
			ready_bt.setVisible(true);
		}
		chat_content.setText("");
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
	 * Sets the room owner.
	 * 
	 * @param roomOwner
	 *            the new room owner
	 */
	public void setRoomOwner(boolean roomOwner) {
		this.start_bt.setVisible(roomOwner);
		this.ready_bt.setVisible(!roomOwner);
		this.cancel_ready_bt.setVisible(false);
		isRoomOwner = roomOwner;
	}

	/**
	 * Show ready button.
	 */
	public void showReadyButton() {
		ready_bt.setVisible(true);
		cancel_ready_bt.setVisible(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

	}
}
