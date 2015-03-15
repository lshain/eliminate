/*
 * 
 */
package eliminate.view.single;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class AnimationPanel.
 */
@SuppressWarnings("serial")
public class AnimationPanel extends JPanel {

	/** The background. */
	private String background = "media/image/singlePlayer/animation/background.jpg";

	/** The normal_rabbit. */
	private String normal_rabbit = "media/image/singlePlayer/animation/normal/rabbit.png";

	/** The normal_dialog. */
	private String normal_dialog = "media/image/singlePlayer/animation/normal/dialog.png";

	/** The combo_4_rabbit. */
	private String combo_4_rabbit = "media/image/singlePlayer/animation/combo4/rabbit.png";

	/** The combo_4_dialog. */
	private String combo_4_dialog = "media/image/singlePlayer/animation/combo4/dialog.png";

	/** The combo_8_rabbit. */
	private String combo_8_rabbit = "media/image/singlePlayer/animation/combo8/rabbit.png";

	/** The combo_8_dialog. */
	private String combo_8_dialog = "media/image/singlePlayer/animation/combo8/dialog.png";

	/** The disappear_6_rabbit. */
	private String disappear_6_rabbit = "media/image/singlePlayer/animation/6/rabbit.png";

	/** The disappear_6_dialog. */
	private String disappear_6_dialog = "media/image/singlePlayer/animation/6/dialog.png";

	/** The time_over_rabbit. */
	private String time_over_rabbit = "media/image/singlePlayer/animation/time/rabbit.png";

	/** The time_over_dialog. */
	private String time_over_dialog = "media/image/singlePlayer/animation/time/dialog.png";

	/** The img. */
	private Image[][] img = new Image[5][2];

	/** The rabbit_end_x. */
	private int rabbit_end_x = 820;

	/** The rabbit_end_y. */
	private int rabbit_end_y = 277;

	/** The dialog_end_x. */
	private int dialog_end_x = 920;
	// private int dialog_end_y = 260;

	/** The dialog_start_x. */
	private int dialog_start_x = 900;

	/** The dialog_start_y. */
	private int dialog_start_y = 380;

	/** The rabbit_x. */
	private int rabbit_x = 820;

	/** The rabbit_y. */
	private int rabbit_y = 277;

	/** The r. */
	private int r = 300;

	/** The degree. */
	private double degree = Math.PI / 6;

	/** The dialog_x. */
	private int dialog_x = 920;

	/** The dialog_y. */
	private int dialog_y = 260;

	/** The rabbit_disappear_v. */
	private int rabbit_disappear_v = -50;

	/** The rabbit_speed_inc. */
	private int rabbit_speed_inc = 3;

	/** The dialog_alpha. */
	private float dialog_alpha = 1f;

	/** The animation. */
	private boolean animation = true;

	/** The type. */
	private int type = 0;

	// 正常时显示的兔子
	/** The Constant NORMAL. */
	public static final int NORMAL = 1;

	// 连击超过4的兔子
	/** The Constant COMBO_4. */
	public static final int COMBO_4 = 2;

	// 连击超过8的兔子
	/** The Constant COMBO_8. */
	public static final int COMBO_8 = 3;

	// 消除6个以上
	/** The Constant DISAPPEAR_6. */
	public static final int DISAPPEAR_6 = 4;

	// 时间快到
	/** The Constant TIME_OVER. */
	public static final int TIME_OVER = 5;

	/**
	 * Instantiates a new animation panel.
	 */
	public AnimationPanel() {
		setOpaque(false);
		init();
	}

	/**
	 * Appear.
	 */
	private void appear() {
		// Thread thread = new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		degree = Math.PI / 6;
		dialog_alpha = -1f;
		while (degree > -Math.PI / 36 && animation) {
			rabbit_x = rabbit_end_x + (int) (r * Math.sin(degree));
			rabbit_y = rabbit_end_y + r - (int) (r * Math.cos(degree));
			degree -= Math.PI / 180;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (degree < 0 && animation) {
			rabbit_x = rabbit_end_x + (int) (r * Math.sin(degree));
			rabbit_y = rabbit_end_y + r - (int) (r * Math.cos(degree));
			degree += Math.PI / 180;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		dialog_x = dialog_start_x;
		dialog_y = dialog_start_y;

		dialog_alpha = 0.4f;

		do {
			dialog_x += 1;
			dialog_y -= 6;
			dialog_alpha += 0.03f;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (dialog_x < dialog_end_x && animation);

		// }
		//
		// });
		// thread.start();
	}

	/**
	 * Change to.
	 * 
	 * @param temp
	 *            the temp
	 */
	public void changeTo(final int temp) {
		animation = false;
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		animation = true;
		if (this.type != 0) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					disappear();
					setType(temp);
					appear();
				}
			}).start();
		} else {
			this.type = temp;
			new Thread(new Runnable() {

				@Override
				public void run() {
					appear();
				}
			}).start();
		}
	}

	// private int temp = 0;

	/**
	 * Disappear.
	 */
	private void disappear() {
		// Thread thread = new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		rabbit_disappear_v = -20;
		// int i = 0;
		while (true && animation) {
			rabbit_y += rabbit_disappear_v;
			rabbit_disappear_v += rabbit_speed_inc;
			dialog_alpha -= 1f / 26;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Toolkit.getDefaultToolkit().getScreenSize().getHeight() < rabbit_y) {
				break;
			}
			// System.out.println(i++ + " times");
		}
		// }
		//
		// });
		// thread.start();
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Inits the.
	 */
	public void init() {

		try {
			img[0][0] = ImageIO.read(new File(normal_rabbit));
			img[0][1] = ImageIO.read(new File(normal_dialog));

			img[1][0] = ImageIO.read(new File(combo_4_rabbit));
			img[1][1] = ImageIO.read(new File(combo_4_dialog));

			img[2][0] = ImageIO.read(new File(combo_8_rabbit));
			img[2][1] = ImageIO.read(new File(combo_8_dialog));

			img[3][0] = ImageIO.read(new File(disappear_6_rabbit));
			img[3][1] = ImageIO.read(new File(disappear_6_dialog));

			img[4][0] = ImageIO.read(new File(time_over_rabbit));
			img[4][1] = ImageIO.read(new File(time_over_dialog));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(new ImageIcon(background).getImage(), 0, 0,
				this.getWidth(), this.getHeight(), this);
		if (type > 0 && type <= 5) {
			g2d.drawImage(img[type - 1][0], rabbit_x, rabbit_y,
					(int) (this.getWidth() * 448.0 / 1366),
					(int) (this.getHeight() * 510.0 / 768), this);

			// g2d.drawImage(new ImageIcon(normal_back).getImage(), 0, 0,
			// this.getWidth(), this.getHeight(), this);
			if (dialog_alpha > 0) {
				g2d.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, dialog_alpha));

				g2d.drawImage(img[type - 1][1], dialog_x, dialog_y,
						(int) (this.getWidth() * 320.0 / 1366),
						(int) (this.getHeight() * 208.0 / 768), this);
			}
		}
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	private void setType(int type) {
		this.type = type;
	}

}
