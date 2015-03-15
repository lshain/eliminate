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

import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;

// TODO: Auto-generated Javadoc
/**
 * The Class ReplayDialog.
 */
@SuppressWarnings("serial")
public class ReplayDialog {

	/**
	 * The Class RepalyPanel.
	 */
	class RepalyPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new repaly panel.
		 */
		public RepalyPanel() {
			try {
//				this.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						System.out.println(e.getX() + "   " + e.getY());
//					}
//				});

				setLayout(null);

				// 退出按键
				jbRestart = MyButton.makeButton(restart, restart_down,
						restart_hover);
				add(jbRestart);
				jbRestart.setBounds(540, 355, 140, 56);
				jbRestart.addActionListener(this);

				// 确认退出按键
				jbContinue = MyButton.makeButton(continue_pic, continue_down,
						continue_hover);
				add(jbContinue);
				jbContinue.setBounds(345, 355, 140, 56);
				jbContinue.addActionListener(this);
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
			if (e.getSource() == jbRestart) {
				jd.dispose();
			} else if (e.getSource() == jbContinue) {
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
	private String background_pic = "media/image/dialog/replayDialog/background_pic.png";

	// 重新开始按钮
	/** The restart. */
	private String restart = "media/image/dialog/replayDialog/bt/replay.png";

	/** The restart_down. */
	private String restart_down = "media/image/dialog/replayDialog/bt/replay_down.png";

	/** The restart_hover. */
	private String restart_hover = "media/image/dialog/replayDialog/bt/replay_hover.png";

	/** The jb restart. */
	JButton jbRestart;

	// 继续游戏按钮
	/** The continue_pic. */
	private String continue_pic = "media/image/dialog/replayDialog/bt/continue.png";

	/** The continue_down. */
	private String continue_down = "media/image/dialog/replayDialog/bt/continue_down.png";

	/** The continue_hover. */
	private String continue_hover = "media/image/dialog/replayDialog/bt/continue_hover.png";

	/** The jb continue. */
	JButton jbContinue;

	/**
	 * Instantiates a new replay dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public ReplayDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setContentPane(new RepalyPanel());
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(912, 513);
		jd.setLocation(owner.getLocation());
		jd.setVisible(true);
	}

}
