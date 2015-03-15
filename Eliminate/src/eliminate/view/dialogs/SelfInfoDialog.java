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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import user.User;
import user.UserMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyPasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class SelfInfoDialog.
 */
@SuppressWarnings("serial")
public class SelfInfoDialog {

	/**
	 * The Class ContentPanel.
	 */
	class ContentPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new content panel.
		 */
		public ContentPanel() {
			try {
				this.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println(e.getX() + "   " + e.getY());
					}
				});

				setLayout(null);

				// 加为好友
				change_bt = MyButton.makeButton(change, change_down,
						change_hover);
				add(change_bt);
				change_bt.setBounds(380, 465, 135, 35);
				change_bt.addActionListener(this);

				// 确认退出按键
				leave_bt = MyButton.makeButton(leave, leave_down, leave_hover);
				add(leave_bt);
				leave_bt.setBounds(565, 465, 135, 35);
				leave_bt.addActionListener(this);

				try {
					font = (Font.createFont(Font.TRUETYPE_FONT, new File(
							fontPath)).deriveFont(35f));
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 昵称
				name = new JLabel("abcdefg");
				add(name);
				name.setBounds(452, 107, 196, 50);
				name.setHorizontalAlignment(SwingConstants.CENTER);
				name.setVerticalAlignment(SwingConstants.CENTER);
				name.setFont(font);
				name.setForeground(Color.BLACK);

				// 总局数
				total_game = new JLabel("abcdefg");
				add(total_game);
				total_game.setBounds(452, 165, 196, 50);
				total_game.setHorizontalAlignment(SwingConstants.CENTER);
				total_game.setVerticalAlignment(SwingConstants.CENTER);
				total_game.setFont(font);
				total_game.setForeground(Color.BLACK);

				// 最高分
				top_score = new JLabel("abcdefg");
				add(top_score);
				top_score.setBounds(452, 226, 196, 50);
				top_score.setHorizontalAlignment(SwingConstants.CENTER);
				top_score.setVerticalAlignment(SwingConstants.CENTER);
				top_score.setFont(font);
				top_score.setForeground(Color.BLACK);

				// 原密码
				original_password = MyPasswordField.makePasswordField(492, 318,
						245, 39);
				add(original_password);

				// 新密码
				new_key = MyPasswordField.makePasswordField(492, 388, 245, 39);
				add(new_key);

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
			} else if (e.getSource() == change_bt) {
				String password1 = String.valueOf(original_password.getPassword()).trim();
				String password2 = String.valueOf(new_key.getPassword()).trim();
				if (!password1.equals(password2)) {
					MainFrame.showMsgDialog("您两次输入的密码不一致，请确认后输入！", "修改密码");
				} else if (password1.length() < 6 || password1.length() > 30) {
					MainFrame.showMsgDialog("密码应为6~30位！", "修改密码");
					return;
				} else {
					LoginController.writeObject(new UserMsg(password1));
					jd.dispose();
				}
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

	/** The background_pic. */
	private String background_pic = "media/image/friends/self_info.png";

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

	// 密码
	/** The original_password. */
	private JPasswordField original_password;
	/** The new_key. */
	private JPasswordField new_key;
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
	/** The change. */
	private String change = "media/image/friends/change.png";
	/** The change_down. */
	private String change_down = "media/image/friends/change_down.png";

	/** The change_hover. */
	private String change_hover = "media/image/friends/change_hover.png";

	/** The change_bt. */
	JButton change_bt;

	/**
	 * Instantiates a new self info dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public SelfInfoDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocationRelativeTo(owner);
		visible(HandleInput.getUser());
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
