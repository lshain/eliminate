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
import javax.swing.JPanel;
import javax.swing.JTextArea;

import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class FriendAskForInviteDialog.
 */
@SuppressWarnings("serial")
public class FriendAskForInviteDialog {

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
							fontPath)).deriveFont(40f));
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// ��Ϣ
				information = new JTextArea();
				information.setFont(font);
				information.setLineWrap(true);
				information.setForeground(Color.BLUE);
				add(information);
				information.setBounds(430, 220, 295, 185);
				information.setEditable(false);
				information.setOpaque(false);
				if (!id.equals("")) {
					information.setText(id + "\n   �����������Ϸ��");
				}

				// �ܾ�����
				refuse_bt = MyButton.makeButton(refuse, refuse_down,
						refuse_hover);
				add(refuse_bt);
				refuse_bt.setBounds(620, 395, 100, 40);
				refuse_bt.addActionListener(this);

				// ͬ�ⰴ��
				approve_bt = MyButton.makeButton(approve, approve_down,
						approve_hover);
				add(approve_bt);
				approve_bt.setBounds(450, 395, 100, 40);
				approve_bt.addActionListener(this);

				// �˳�����
				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(769, 146, 41, 41);
				jbExit.addActionListener(this);

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
				// �˳�
				jd.dispose();
			} else if (e.getSource() == refuse_bt) {
				LoginController.writeObject(new FriendMsg(id,
						FriendMsg.INVITE_REFUSE));
				jd.dispose();
			} else if (e.getSource() == approve_bt) {
				LoginController.writeObject(new FriendMsg(id,
						FriendMsg.INVITE_AGREE));
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

	/** The id. */
	String id = "";

	/** The background_pic. */
	private String background_pic = "media/image/friends/ask_for_friend.png";

	// ����
	/** The font. */
	private static Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	// �����Ϣ
	/** The information. */
	private JTextArea information;

	// �ܾ���ť
	/** The refuse. */
	private String refuse = "media/image/friends/refuse.png";

	/** The refuse_down. */
	private String refuse_down = "media/image/friends/refuse_down.png";

	/** The refuse_hover. */
	private String refuse_hover = "media/image/friends/refuse_hover.png";

	/** The refuse_bt. */
	private JButton refuse_bt;

	// �ܾ���ť
	/** The approve. */
	private String approve = "media/image/friends/approve.png";

	/** The approve_down. */
	private String approve_down = "media/image/friends/approve_down.png";

	/** The approve_hover. */
	private String approve_hover = "media/image/friends/approve_hover.png";

	/** The approve_bt. */
	private JButton approve_bt;

	// �˳���ť
	/** The exit. */
	private String exit = "media/image/startFrame/exitDialog/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/startFrame/exitDialog/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/startFrame/exitDialog/exit_hover.png";

	/** The jb exit. */
	JButton jbExit;

	/**
	 * Instantiates a new friend ask for invite dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public FriendAskForInviteDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocation(owner.getLocation());
		;
	}

	/**
	 * Sets the ask information.
	 * 
	 * @param info
	 *            the new ask information
	 */
	public void setAskInformation(String info) {
		information.setText(info);
		information.repaint();
	}

	/**
	 * Visible.
	 * 
	 * @param id
	 *            the id
	 */
	public void visible(String id) {
		this.id = id;
		jd.setContentPane(new ContentPanel());
		if (information.getText().toString().equals("")) {
			setAskInformation(id + "\n   �����������Ϸ!");
		}
		jd.setLocation(MainFrame.getOwner().getLocation());
		jd.setVisible(true);
	}
}
