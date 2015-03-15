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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eliminate.controller.MainFrame;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.login.LoginPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class NetworkDialog.
 */
@SuppressWarnings("serial")
public class NetworkDialog {

	/**
	 * The Class NetworkPanel.
	 */
	class NetworkPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new network panel.
		 */
		public NetworkPanel() {
			setLayout(null);

			try {
				back_bt = MyButton.makeButton(back, back_hover, back_hover);
				add(back_bt);
				back_bt.setBounds(73, 263, 65, 27);
				back_bt.addActionListener(this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
			if (e.getSource() == back_bt) {
				MainFrame.setContentPanel(new LoginPanel());
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
	private String background_pic = "media/image/dialog/network/background.jpg";

	/** The back. */
	private String back = "media/image/dialog/network/bt.png";

	/** The back_hover. */
	private String back_hover = "media/image/dialog/network/bt_down.png";

	/** The back_bt. */
	private MyButton back_bt;

	/**
	 * Instantiates a new network dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public NetworkDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setContentPane(new NetworkPanel());
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocation(owner.getLocation());
		jd.setVisible(true);
	}
}
