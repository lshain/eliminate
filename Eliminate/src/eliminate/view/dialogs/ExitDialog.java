/*
 * 
 */
package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.login.LoginPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ExitDialog.
 */
@SuppressWarnings("serial")
public class ExitDialog {

	/**
	 * The Class ExitPanel.
	 */
	class ExitPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new exit panel.
		 */
		public ExitPanel() {
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
				jbExit.setBounds(461, 120, 32, 32);
				jbExit.addActionListener(this);

				// 确认退出按键
				jbConfirm = MyButton.makeButton(confirm, confirm_down,
						confirm_down);
				add(jbConfirm);
				jbConfirm.setBounds(214, 328, 98, 17);
				jbConfirm.addActionListener(this);

				// 取消按键
				jbCancel = MyButton
						.makeButton(cancel, cancel_down, cancel_down);
				add(jbCancel);
				jbCancel.setBounds(364, 328, 84, 17);
				jbCancel.addActionListener(this);

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
			if (e.getSource() == jbExit || e.getSource() == jbCancel) {
				jd.dispose();
			} else if (e.getSource() == jbConfirm) {
				LoginController.closeSocket();
				jd.dispose();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				MainFrame.setContentPanel(new LoginPanel());
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
	private String background_pic = "media/image/dialog/exitDialog/exit_dialog.png";

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
	private String confirm = "media/image/dialog/exitDialog/confirm.png";

	/** The confirm_down. */
	private String confirm_down = "media/image/dialog/exitDialog/confirm_down.png";

	/** The jb confirm. */
	JButton jbConfirm;

	// 退出按钮
	/** The cancel. */
	private String cancel = "media/image/dialog/exitDialog/wait.png";

	/** The cancel_down. */
	private String cancel_down = "media/image/dialog/exitDialog/wait_down.png";

	/** The jb cancel. */
	JButton jbCancel;

	/**
	 * Instantiates a new exit dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public ExitDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setContentPane(new ExitPanel());
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(912, 513);
		jd.setLocation(owner.getLocation().x + 240, owner.getLocation().getLocation().y + 50);;
		jd.setVisible(true);
	}
}
