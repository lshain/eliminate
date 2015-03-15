/*
 * 
 */
package eliminate.view.login;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class TogglePanel.
 */
@SuppressWarnings("serial")
public class TogglePanel extends JPanel {

	/**
	 * Change to.
	 * 
	 * @param toLogin
	 *            the to login
	 */
	public static void changeTo(final boolean toLogin) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (toLogin && pos == -414) {
					while (pos != 0) {
						pos += 6;
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						loginLabel.setBounds(35 + pos, 40, 336, 447);
						registerLabel.setBounds(27 + pos + 414, 0, 360, 457);
					}
				} else if ((!toLogin) && pos == 0) {
					while (pos != -414) {
						pos -= 6;
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						loginLabel.setBounds(35 + pos, 40, 336, 447);
						registerLabel.setBounds(27 + pos + 414, 0, 360, 457);
					}
				}
			}
		}).start();

	}

	/** The background. */
	private String background = "media/image/login/login_bottom.png";

	/** The background_img. */
	private Image background_img;

	/** The pos. */
	private static int pos = 0;

	/** The login label. */
	static LoginLabel loginLabel = new LoginLabel();

	/** The register label. */
	static RegisterLabel registerLabel = new RegisterLabel();

	/**
	 * Instantiates a new toggle panel.
	 */
	public TogglePanel() {
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		try {
			background_img = ImageIO.read(new File(background));
			pos = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setLayout(null);
		setOpaque(false);
		loginLabel.setBounds(35 + pos, 40, 336, 447);
		add(loginLabel);

		registerLabel.setBounds(27 + pos + 414, 0, 360, 457);
		add(registerLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background_img, 0, 0, this.getWidth(), this.getHeight(),
				this);
	}
}
