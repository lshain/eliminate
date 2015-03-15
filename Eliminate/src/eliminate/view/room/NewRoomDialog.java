/*
 * 
 */
package eliminate.view.room;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import room.Room;
import room.RoomMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.view.MyButton;

// TODO: Auto-generated Javadoc
/**
 * The Class NewRoomDialog.
 */
@SuppressWarnings("serial")
public class NewRoomDialog {

	/**
	 * The Class ContentPanel.
	 */
	class ContentPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new content panel.
		 */
		public ContentPanel() {

			try {
				font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
						.deriveFont(20f));

			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setOpaque(false);

			try {
				// this.addMouseListener(new MouseAdapter() {
				// @Override
				// public void mouseClicked(MouseEvent e) {
				// System.out.println(e.getX() + "   " + e.getY());
				// }
				// });

				setLayout(null);

				// 退出按键
				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(514, 37, 40, 40);
				jbExit.addActionListener(this);

				// 确认按键
				jbConfirm = MyButton.makeButton(confirm, confirm_down,
						confirm_hover);
				add(jbConfirm);
				jbConfirm.setBounds(124, 465, 100, 40);
				jbConfirm.addActionListener(this);

				// 取消按键
				jbCancel = MyButton.makeButton(cancel, cancel_down,
						cancel_hover);
				add(jbCancel);
				jbCancel.setBounds(366, 465, 100, 40);
				jbCancel.addActionListener(this);

				add(jtf);
				jtf.setBounds(270, 100, 200, 30);
				jtf.setOpaque(false);
				jtf.setFont(font);
				jtf.setForeground(Color.WHITE);
				jtf.setText("土豪的房间");

				ButtonGroup gameType = new ButtonGroup();

				cooperateJRB = createRadioButton();
				gameType.add(cooperateJRB);
				cooperateJRB.setBounds(264, 155, 40, 30);
				add(cooperateJRB);
				cooperateJRB.setSelected(true);

				againstJRB = createRadioButton();
				gameType.add(againstJRB);
				againstJRB.setBounds(264, 200, 30, 25);
				add(againstJRB);

				ButtonGroup maxPlayerNum = new ButtonGroup();

				twoJRB = createRadioButton();
				maxPlayerNum.add(twoJRB);
				twoJRB.setBounds(264, 255, 30, 25);
				add(twoJRB);
				twoJRB.setSelected(true);

				threeJRB = createRadioButton();
				maxPlayerNum.add(threeJRB);
				threeJRB.setBounds(264, 305, 30, 25);
				add(threeJRB);

				fourJRB = createRadioButton();
				maxPlayerNum.add(fourJRB);
				fourJRB.setBounds(264, 355, 30, 25);
				add(fourJRB);

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
			if (e.getSource() == jbExit || e.getSource() == jbCancel) {
				jd.dispose();
			} else if (e.getSource() == jbConfirm) {
				String roomName = jtf.getText().trim();
				int type;
				int maxPlayerNum;
				if (roomName.length() == 0) {
					MainFrame.showMsgDialog("请输入房间名称！", "创建房间");
				} else {
					if (cooperateJRB.isSelected()) {
						type = Room.COOPERATE;
					} else {
						type = Room.FIGHT_AGAINST;
					}
					if (twoJRB.isSelected()) {
						maxPlayerNum = 2;
					} else if (threeJRB.isSelected()) {
						maxPlayerNum = 3;
					} else {
						maxPlayerNum = 4;
					}
					LoginController.writeObject(new RoomMsg(new Room(type,
							maxPlayerNum, roomName), RoomMsg.CREATE_ROOM));
					jd.dispose();
					MainFrame.showLoadingDialog();
				}
			}
		}

		/**
		 * Creates the radio button.
		 * 
		 * @return the j radio button
		 */
		private JRadioButton createRadioButton() {
			JRadioButton temp = new JRadioButton();
			temp.setIcon(new ImageIcon(normalIcon));
			temp.setSelectedIcon(new ImageIcon(selectedIcon));
			temp.setOpaque(false);
			return temp;
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
	private String background_pic = "media/image/roomChoose/newRoomDialog/background.png";

	// 退出按钮
	/** The exit. */
	private String exit = "media/image/dialog/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/dialog/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/dialog/exit_hover.png";

	/** The jb exit. */
	JButton jbExit;

	// 确认退出按钮
	/** The confirm. */
	private String confirm = "media/image/roomChoose/newRoomDialog/confirm.png";

	/** The confirm_down. */
	private String confirm_down = "media/image/roomChoose/newRoomDialog/confirm_down.png";

	/** The confirm_hover. */
	private String confirm_hover = "media/image/roomChoose/newRoomDialog/confirm_hover.png";

	/** The jb confirm. */
	JButton jbConfirm;

	// 退出按钮
	/** The cancel. */
	private String cancel = "media/image/roomChoose/newRoomDialog/cancel.png";

	/** The cancel_down. */
	private String cancel_down = "media/image/roomChoose/newRoomDialog/cancel_down.png";

	/** The cancel_hover. */
	private String cancel_hover = "media/image/roomChoose/newRoomDialog/cancel_hover.png";

	/** The jb cancel. */
	JButton jbCancel;

	/** The selected icon. */
	private String selectedIcon = "media/image/roomChoose/newRoomDialog/selected.png";

	/** The normal icon. */
	private String normalIcon = "media/image/roomChoose/newRoomDialog/icon.png";

	/** The icon. */
	ImageIcon icon = new ImageIcon(normalIcon);

	/** The selected. */
	ImageIcon selected = new ImageIcon(selectedIcon);

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	/** The font. */
	Font font;

	/** The jtf. */
	JTextField jtf = new JTextField();

	/** The cooperate jrb. */
	JRadioButton cooperateJRB;

	/** The against jrb. */
	JRadioButton againstJRB;

	/** The two jrb. */
	JRadioButton twoJRB;

	/** The three jrb. */
	JRadioButton threeJRB;

	/** The four jrb. */
	JRadioButton fourJRB;

	/**
	 * Instantiates a new new room dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public NewRoomDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setContentPane(new ContentPanel());
		jd.setUndecorated(true);
		jd.setSize(642, 618);
		jd.setLocation(owner.getX() + 461, owner.getY());
		jd.setVisible(true);
	}
}
