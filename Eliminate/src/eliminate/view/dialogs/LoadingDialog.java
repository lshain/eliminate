package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import eliminate.controller.MainFrame;

/**
 * The Class LoadingDialog.
 * 
 * @author Œ‰’Ÿ
 */
@SuppressWarnings("serial")
public class LoadingDialog {

	/**
	 * The Class LoadingPanel.
	 */
	class LoadingPanel extends JPanel {

		/**
		 * Instantiates a new loading panel.
		 */
		public LoadingPanel() {
			setLayout(null);
			setOpaque(false);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paint(java.awt.Graphics)
		 */
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(new ImageIcon(background_img[num]).getImage(), 0, 0,
					this.getWidth(), this.getHeight(), this);
			num = (num + 1) % 16;
		}
	}

	/**
	 * Inits the.
	 */
	public static void init() {
		for (int i = 0; i < 16; i++) {
			try {
				background_img[i] = ImageIO.read(new File(
						"media/image/dialog/loadingDialog/loading"
								+ Integer.toString(i) + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** The jd. */
	private JDialog jd;

	/** The num. */
	private int num = 0;

	/** The can stop. */
	private boolean canStop = false;

	/** The background_img. */
	private static Image[] background_img = new Image[16];

	/** The panel. */
	private LoadingPanel panel;

	/**
	 * Instantiates a new loading dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public LoadingDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		panel = new LoadingPanel();
		jd.setContentPane(panel);
		jd.setSize(1103, 618);
		jd.setLocationRelativeTo(owner);
		jd.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	/**
	 * Sets the can stop.
	 * 
	 * @param b
	 *            the new can stop
	 */
	public void setCanStop(boolean b) {
		canStop = b;
	}

	/**
	 * Start play.
	 */
	public void startPlay() {
		canStop = false;
		num = 0;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!canStop) {
					panel.repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				jd.dispose();
			}

		}).start();
		jd.setLocation(MainFrame.getOwner().getLocation());
		jd.setVisible(true);
	}
}
