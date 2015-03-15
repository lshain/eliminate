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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyScrollBarUI;
import eliminate.view.MyTable;
import eliminate.view.TableFriendAdapter;
import eliminate.view.TableFriendLabelRenderer;
import eliminate.view.TableFriendRenderer;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class FriendManageDialog.
 */
@SuppressWarnings("serial")
public class FriendManageDialog {

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

				try {
					font = (Font.createFont(Font.TRUETYPE_FONT, new File(
							fontPath)).deriveFont(20f));
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 退出按键
				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(757, 46, 40, 40);
				jbExit.addActionListener(this);

				// 加为好友
				add_bt = MyButton.makeButton(add, add_down, add_hover);
				add(add_bt);
				add_bt.setBounds(710, 500, 40, 40);
				add_bt.addActionListener(this);

				// 搜索文字框
				search = new JTextField();
				add(search);
				search.setBounds(430, 500, 280, 36);
				search.setFont(font);
				search.setBorder(BorderFactory.createEmptyBorder());
				search.setText("看上哪个妹纸/汉纸了~");
				search.addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						if (search.getText().toString().equals("看上哪个妹纸/汉纸了~")) {
							search.setText("");
						}
					}

					@Override
					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub
						if (search.getText().toString().equals("")) {
							search.setText("看上哪个妹纸/汉纸了~");
						}
					}
				});

				jsp = new JScrollPane();
				add(jsp);
				jsp.setBounds(325, 125, 450, 360);
				jsp.setBorder(BorderFactory.createEmptyBorder());
				jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				jsp.setOpaque(false);

				jsb = jsp.getVerticalScrollBar();
				jsb.setOpaque(false);
				jsb.setUI(new MyScrollBarUI(true));
				initTable(online_player, offline_player);
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
			if (e.getSource() == add_bt) {
				String id = search.getText().toString();
				// 加用户名为id的好友
				List<String> a = Arrays.asList(online_player);
				List<String> b = Arrays.asList(offline_player);
				if (a.contains(id) || b.contains(id)) {
					MainFrame.showMsgDialog("该用户已经是您好友!", "添加好友失败");
				} else {
					LoginController.writeObject(new FriendMsg(id,
							FriendMsg.ASK_FOR_ADD_FRIEND));
				}
				jd.dispose();
			} else if (e.getSource() == jbExit) {
				// 退出
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

	/** The background_pic. */
	private String background_pic = "media/image/friends/friend_manage.png";

	// 字体
	/** The font. */
	private static Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	// 搜索文字框
	/** The search. */
	private JTextField search;

	// 好友列表
	/** The jsp. */
	private JScrollPane jsp;

	/** The friends. */
	private JTable friends = null;

	/** The jsb. */
	private JScrollBar jsb;

	/** The online_player. */
	private String[] online_player = null;

	/** The offline_player. */
	private String[] offline_player = null;

	// 删除好友按钮
	/** The delete. */
	private String delete = "media/image/friends/delete.png";

	/** The delete_down. */
	private String delete_down = "media/image/friends/delete_down.png";

	/** The delete_hover. */
	private String delete_hover = "media/image/friends/delete_hover.png";

	// 退出按钮
	/** The exit. */
	private String exit = "media/image/dialog/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/dialog/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/dialog/exit_hover.png";

	/** The jb exit. */
	JButton jbExit;

	// 加为好友
	/** The add. */
	private String add = "media/image/friends/add.png";

	/** The add_down. */
	private String add_down = "media/image/friends/add_down.png";

	/** The add_hover. */
	private String add_hover = "media/image/friends/add_hover.png";

	/** The add_bt. */
	JButton add_bt;

	/**
	 * Instantiates a new friend manage dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public FriendManageDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocationRelativeTo(owner);
	}

	/**
	 * Inits the table.
	 * 
	 * @param online
	 *            the online
	 * @param offline
	 *            the offline
	 */
	public void initTable(String[] online, String[] offline) {
		Object[][] p = new Object[online.length + offline.length][3];
		for (int i = 0; i < p.length; i++) {
			if (i < online.length) {
				p[i][0] = online[i];
				p[i][2] = "1";
			} else {
				p[i][0] = offline[i - online.length];
				p[i][2] = "2";
			}
			try {
				p[i][1] = MyButton
						.makeButton(delete, delete_down, delete_hover);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				p[i][1] = new JButton("删除好友");
				e.printStackTrace();
			}
		}

		friends = new MyTable(p, new String[] { "好友名", "删除好友", "标记" });
		friends.setRowHeight(51);
		friends.setRowMargin(2);
		friends.setShowGrid(false);
		TableFriendLabelRenderer tlr = new TableFriendLabelRenderer(true);
		friends.getColumnModel().getColumn(0).setCellRenderer(tlr);
		friends.getColumnModel().getColumn(0).setPreferredWidth(400);
		friends.getColumnModel().getColumn(1).setPreferredWidth(50);
		friends.getColumnModel().getColumn(2).setMaxWidth(0);
		friends.getColumnModel().getColumn(2).setMinWidth(0);
		friends.getColumnModel().getColumn(2).setPreferredWidth(0);
		TableFriendAdapter adapter = new TableFriendAdapter(friends, jd);
		friends.addMouseListener(adapter);
		friends.addMouseMotionListener(adapter);
		friends.getColumnModel().getColumn(1)
				.setCellRenderer(new TableFriendRenderer(adapter));
		friends.setOpaque(false);

		jsp.setViewportView(friends);
		jsp.getViewport().setOpaque(false);
	}

	/**
	 * Visible.
	 * 
	 * @param online
	 *            the online
	 * @param offline
	 *            the offline
	 */
	public void visible(ArrayList<String> online, ArrayList<String> offline) {
		int size = online.size();
		online_player = new String[size];
		for (int i = 0; i < size; i++) {
			online_player[i] = online.get(i);
		}
		size = offline.size();
		offline_player = new String[size];
		for (int i = 0; i < size; i++) {
			offline_player[i] = offline.get(i);
		}
		jd.setContentPane(new ContentPanel());
		jd.setLocation(MainFrame.getOwner().getLocation());
		jd.setVisible(true);
	}
}
