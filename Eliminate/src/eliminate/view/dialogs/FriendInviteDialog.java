/*
 * 
 */
package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import eliminate.controller.MainFrame;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyScrollBarUI;
import eliminate.view.MyTable;
import eliminate.view.TableFriendAdapter;
import eliminate.view.TableFriendRenderer;
import eliminate.view.TableLabelRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class FriendInviteDialog.
 */
@SuppressWarnings("serial")
public class FriendInviteDialog {

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

				// 退出按键
				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(757, 46, 40, 40);
				jbExit.addActionListener(this);

				jsp = new JScrollPane();
				add(jsp);
				jsp.setBounds(325, 125, 450, 415);
				jsp.setBorder(BorderFactory.createEmptyBorder());
				jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				jsp.setOpaque(false);

				jsb = jsp.getVerticalScrollBar();
				jsb.setOpaque(false);
				jsb.setUI(new MyScrollBarUI(true));
				initTable(player);
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
			if (e.getSource() == jbExit) {
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
	private String background_pic = "media/image/friends/invite_friend.png";

	// 好友列表
	/** The jsp. */
	private JScrollPane jsp;

	/** The friends. */
	private JTable friends = null;

	/** The jsb. */
	private JScrollBar jsb;

	/** The player. */
	private String[] player = { "" };

	// 邀请好友按钮
	/** The invite. */
	private String invite = "media/image/friends/invite.png";

	/** The invite_down. */
	private String invite_down = "media/image/friends/invite_down.png";

	/** The invite_hover. */
	private String invite_hover = "media/image/friends/invite_hover.png";

	// 退出按钮
	/** The exit. */
	private String exit = "media/image/dialog/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/dialog/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/dialog/exit_hover.png";

	/** The jb exit. */
	JButton jbExit;

	/**
	 * Instantiates a new friend invite dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public FriendInviteDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocationRelativeTo(owner);
	}

	/**
	 * Inits the table.
	 * 
	 * @param players
	 *            the players
	 */
	public void initTable(String[] players) {
		Object[][] p = new Object[players.length][3];
		for (int i = 0; i < players.length; i++) {
			p[i][0] = players[i];
			p[i][1] = "";
			try {
				p[i][2] = MyButton
						.makeButton(invite, invite_down, invite_hover);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				p[i][2] = new JButton("邀请好友");
				e.printStackTrace();
			}
		}
		friends = new MyTable(p, new String[] { "好友名", "", "邀请好友" });
		friends.setRowHeight(51);
		friends.setRowMargin(2);
		friends.setShowGrid(false);
		TableLabelRenderer tlr = new TableLabelRenderer(true);
		friends.getColumnModel().getColumn(0).setCellRenderer(tlr);
		friends.getColumnModel().getColumn(0).setPreferredWidth(400);
		friends.getColumnModel().getColumn(1).setMaxWidth(0);
		friends.getColumnModel().getColumn(1).setMinWidth(0);
		friends.getColumnModel().getColumn(1).setPreferredWidth(0);
		friends.getColumnModel().getColumn(2).setPreferredWidth(50);
		TableFriendAdapter adapter = new TableFriendAdapter(friends, jd);
		friends.addMouseListener(adapter);
		friends.addMouseMotionListener(adapter);
		friends.getColumnModel().getColumn(2)
				.setCellRenderer(new TableFriendRenderer(adapter));
		friends.setOpaque(false);

		jsp.setViewportView(friends);
		jsp.getViewport().setOpaque(false);
	}

	/**
	 * Visible.
	 * 
	 * @param friend
	 *            the friend
	 */
	public void visible(ArrayList<String> friend) {
		int size = friend.size();
		player = new String[size];
		for (int i = 0; i < size; i++) {
			player[i] = friend.get(i);
		}
		jd.setContentPane(new ContentPanel());
		jd.setLocation(MainFrame.getOwner().getLocation());
		jd.setVisible(true);
	}
}
