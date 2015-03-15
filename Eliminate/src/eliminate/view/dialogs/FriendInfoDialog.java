/*
 * 
 */
package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import user.User;
import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class FriendInfoDialog.
 */
@SuppressWarnings("serial")
public class FriendInfoDialog {

	/**
	 * The Class ContentPanel.
	 */
	class ContentPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new content panel.
		 */
		public ContentPanel() {
			try {
//				this.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						System.out.println(e.getX() + "   " + e.getY());
//					}
//				});

				setLayout(null);

				// 加为好友
				friend_bt = MyButton.makeButton(friend, friend_down,
						friend_hover);
				add(friend_bt);
				friend_bt.setBounds(380, 405, 135, 35);
				friend_bt.addActionListener(this);
				if (isFriend) {
					friend_bt.setVisible(false);
				} else {
					friend_bt.setVisible(true);
				}

				// 确认退出按键
				leave_bt = MyButton.makeButton(leave, leave_down, leave_hover);
				add(leave_bt);
				leave_bt.setBounds(565, 405, 135, 35);
				leave_bt.addActionListener(this);

				try {
					font = (Font.createFont(Font.TRUETYPE_FONT, new File(
							fontPath)).deriveFont(40f));
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 昵称
				name = new JLabel("abcdefg");
				add(name);
				name.setBounds(455, 180, 196, 60);
				name.setHorizontalAlignment(SwingConstants.CENTER);
				name.setVerticalAlignment(SwingConstants.CENTER);
				name.setFont(font);
				name.setForeground(Color.BLACK);

				// 总局数
				total_game = new JLabel("abcdefg");
				add(total_game);
				total_game.setBounds(455, 240, 196, 60);
				total_game.setHorizontalAlignment(SwingConstants.CENTER);
				total_game.setVerticalAlignment(SwingConstants.CENTER);
				total_game.setFont(font);
				total_game.setForeground(Color.BLACK);

				// 最高分
				top_score = new JLabel("abcdefg");
				add(top_score);
				top_score.setBounds(455, 305, 196, 60);
				top_score.setHorizontalAlignment(SwingConstants.CENTER);
				top_score.setVerticalAlignment(SwingConstants.CENTER);
				top_score.setFont(font);
				top_score.setForeground(Color.BLACK);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer.playButtonOne();
			if (e.getSource() == leave_bt) {
				jd.dispose();
			} else if (e.getSource() == friend_bt) {
				// 加为好友
				LoginController.writeObject(new FriendMsg(user.getUserId(),
						FriendMsg.ASK_FOR_ADD_FRIEND));
				jd.dispose();
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
	}

	/** The jd. */
	JDialog jd;

	/** The user. */
	User user;

	/** The is friend. */
	boolean isFriend;

	/** The background_pic. */
	private String background_pic = "media/image/friends/other_info.png";

	// 昵称
	/** The name. */
	private JLabel name;

	// 总局数
	/** The total_game. */
	private JLabel total_game;

	// 最高分
	/** The top_score. */
	private JLabel top_score;

	// 字体
	/** The font. */
	private static Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	// 退出按钮
	/** The leave. */
	private String leave = "media/image/friends/leave.png";
	/** The leave_down. */
	private String leave_down = "media/image/friends/leave_down.png";
	/** The leave_hover. */
	private String leave_hover = "media/image/friends/leave_hover.png";

	/** The leave_bt. */
	JButton leave_bt;

	// 加为好友
	/** The friend. */
	private String friend = "media/image/friends/friend.png";

	/** The friend_down. */
	private String friend_down = "media/image/friends/friend_down.png";

	/** The friend_hover. */
	private String friend_hover = "media/image/friends/friend_hover.png";

	/** The friend_bt. */
	JButton friend_bt;

	/**
	 * Instantiates a new friend info dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 * @param isFriend
	 *            the is friend
	 */
	public FriendInfoDialog(JFrame owner, boolean modal, boolean isFriend) {
		jd = new JDialog(owner, modal);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocationRelativeTo(owner);
		this.isFriend = isFriend;
	}

	// 设置昵称
	/**
	 * Sets the name.
	 * 
	 * @param n
	 *            the new name
	 */
	public void setName(String n) {
		name.setText(n);
		name.repaint();
	}

	// 设置最高分
	/**
	 * Sets the top score.
	 * 
	 * @param score
	 *            the new top score
	 */
	public void setTopScore(int score) {
		top_score.setText(Integer.toString(score));
		top_score.repaint();
	}

	// 设置总局数
	/**
	 * Sets the total game.
	 * 
	 * @param game
	 *            the new total game
	 */
	public void setTotalGame(int game) {
		total_game.setText(Integer.toString(game));
		total_game.repaint();
	}

	/**
	 * Visible.
	 * 
	 * @param user
	 *            the user
	 */
	public void visible(User user) {
		this.user = user;
		jd.setContentPane(new ContentPanel());
		setName(user.getUserId());
		setTotalGame(user.getMultiTotal() + user.getTotal());
		setTopScore(user.getScore());
		jd.setLocation(MainFrame.getOwner().getLocation());
		jd.setVisible(true);
	}
}
