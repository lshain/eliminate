/*
 * 
 */
package eliminate.view.login;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eliminate.model.sound.AudioPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginPanel.
 */
@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	// 动画效果
	/** The animation on. */
	private static boolean animationOn = true;

	/**
	 * Sets the animation.
	 * 
	 * @param animationOn
	 *            the new animation
	 */
	public static void setAnimation(boolean animationOn) {
		LoginPanel.animationOn = animationOn;
	}

	// 厨师从下网上滚动效果
	/** The cook_pic. */
	private String cook_pic = "media/image/login/pic/cook.png";

	/** The cook_img. */
	private Image cook_img;

	/** The cook_bounds. */
	private Rectangle cook_bounds = new Rectangle(30, 620, 553, 506);

	// 让我出去的文字效果
	/** The let me out_pic. */
	private String letMeOut_pic = "media/image/login/pic/letMeOut.png";

	/** The let me out_img. */
	private Image letMeOut_img;

	/** The let me out_ pic_bottom. */
	JLabel letMeOut_Pic_bottom;

	/** The let me out_bounds. */
	private Rectangle letMeOut_bounds = new Rectangle(186, 345, 217, 85);

	/** The let me out_alpha. */
	private float letMeOut_alpha = 0f;

	// 兔子声音图像旋转自效果
	/** The bang_pic. */
	private String bang_pic = "media/image/login/pic/bang.png";

	/** The bang_img. */
	private Image bang_img;

	/** The bang angle. */
	private float bangAngle = (float) (-Math.PI / 7);

	/** The is bang visible. */
	private boolean isBangVisible = false;

	/** The bang direction. */
	private int bangDirection = 1;

	// 厨师说出的文字
	/** The eat_pic. */
	private String eat_pic = "media/image/login/pic/eat.png";

	/** The eat_img. */
	private Image eat_img;

	/** The eat_alpha. */
	private float eat_alpha = 0f;

	/** The eat_bounds. */
	private Rectangle eat_bounds = new Rectangle(350, 250, 281, 95);

	// 文字效果
	/** The for where. */
	private String forWhere = "media/image/login/pic/forWhere.png";

	/** The for where_ pic. */
	JLabel forWhere_Pic;

	/** The for where_data. */
	int forWhere_data[] = { -75, -74, -70, -61, -45, -20, 16, 16, -10, -18,
			-22, -22, -15, -11, -2, 16, 16, 7, 3, 3, 7, 16, 16 };

	// 兔子效果
	/** The rabbit_pic. */
	private String rabbit_pic = "media/image/login/pic/rabbit.png";

	/** The rabbit_img. */
	Image rabbit_img;

	/** The rabbit_bounds. */
	private Rectangle rabbit_bounds = new Rectangle(400, -200, 50, 50);

	// 只有兔子文字效果
	/** The rabbit only. */
	private String rabbitOnly = "media/image/login/pic/rabbitOnly.png";

	/** The rabbit only_ pic. */
	JLabel rabbitOnly_Pic;

	/** The rabbit only_data. */
	int rabbitOnly_data[] = { -96, -94, -90, -81, -65, -40, 4, 53, 100, 100,
			64, 39, 23, 14, 10, 9, 9, 10, 14, 23, 39, 64, 100, 100, 91, 87, 86,
			86, 87, 91, 100 };

	// 主题餐厅文字效果
	/** The theme cafe_pic. */
	private String themeCafe_pic = "media/image/login/pic/themeCafe.png";

	/** The theme cafe_img. */
	private Image themeCafe_img;

	/** The theme cafe_bounds. */
	private Rectangle themeCafe_bounds = new Rectangle(265, -200, 413, 164);

	/** The theme cafe_data. */
	int themeCafe_data[] = { -125, -124, -120, -104, -79, -43, -6, 20, 20, 19,
			15, 6, 6, 15, 19, 20, 20, 20, 20, 20 };

	/** The cafe angle. */
	private float cafeAngle = 0f;

	/** The cafe derication. */
	private int cafeDerication = 1;

	/** The panel. */
	private TogglePanel panel;

	/** The background_pic. */
	private String background_pic = "media/image/login/background.jpg";

	/** The background_img. */
	private Image background_img;

	/**
	 * Instantiates a new login panel.
	 */
	public LoginPanel() {

		init();

		setLayout(null);
		// addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// System.out.println(e.getPoint());
		// }
		// });
		letMeOut_Pic_bottom = new JLabel(new ImageIcon(letMeOut_pic));
		letMeOut_Pic_bottom.setBounds(190, 350, 217, 85);
		letMeOut_Pic_bottom.setVisible(false);
		add(letMeOut_Pic_bottom);

		forWhere_Pic = new JLabel(new ImageIcon(forWhere));
		add(forWhere_Pic);

		rabbitOnly_Pic = new JLabel(new ImageIcon(rabbitOnly));
		add(rabbitOnly_Pic);

		panel = new TogglePanel();
		panel.setBounds(650, 80, 414, 507);
		// (685,120,336,447);
		add(panel);

		new Thread(new Runnable() {

			@Override
			public void run() {
				AudioPlayer.playLogin(true);
				manDownToUp();
				isBangVisible = true;
				letMeOut_Pic_bottom.setVisible(true);
				new Thread(new Runnable() {
					@Override
					public void run() {
						formWhereDown();
						OnlyRabbit();
						themeCafeDown();
						rabbitOut();
						themeCafeShake();
						themeCafeShake();
					}
				}).start();
				while (animationOn) {
					letMeOut();
					manSpeak();
				}
			}

		}).start();
	}

	// fromWhere 文字下落效果
	/**
	 * Form where down.
	 */
	private void formWhereDown() {
		for (int n = 0; n < forWhere_data.length; n++) {
			forWhere_Pic.setBounds(-10, forWhere_data[n], 196, 111);
			try {
				Thread.sleep(40);
			} catch (Exception e) {
			}
			forWhere_Pic.repaint();
		}
	}

	/**
	 * Inits the.
	 */
	public void init() {
		try {
			background_img = ImageIO.read(new File(background_pic));
			cook_img = ImageIO.read(new File(cook_pic));
			letMeOut_img = ImageIO.read(new File(letMeOut_pic));
			bang_img = ImageIO.read(new File(bang_pic));
			eat_img = ImageIO.read(new File(eat_pic));
			themeCafe_img = ImageIO.read(new File(themeCafe_pic));
			rabbit_img = ImageIO.read(new File(rabbit_pic));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 让我出去文字效果
	/**
	 * Let me out.
	 */
	private void letMeOut() {
		letMeOut_bounds = new Rectangle(186, 345, 217, 85);
		letMeOut_alpha = 1f;
		for (int j = 0; j < 3; j++) {
			letMeOut_bounds.x -= 1;
			letMeOut_bounds.y -= 1;
			letMeOut_bounds.width += 3;
			letMeOut_bounds.height += 3;
			letMeOut_alpha -= 1f / 3f;
			bangAngle += bangDirection * Math.PI / 7;
			bangDirection = -bangDirection;
			if (letMeOut_alpha < 0) {
				letMeOut_alpha = 0;
			}
			repaint();
			try {
				Thread.sleep(250);
			} catch (Exception e) {
			}

		}
	}

	// 动画效果：
	/**
	 * Man down to up.
	 */
	private void manDownToUp() {
		for (int j = 0; j < 125; j++) {
			cook_bounds.y -= 4;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 厨师发出的文字效果
	/**
	 * Man speak.
	 */
	private void manSpeak() {
		eat_alpha = 1f;
		eat_bounds = new Rectangle(350, 250, 281, 95);
		for (int j = 0; j < 10; j++) {
			eat_bounds.x += 3;
			eat_bounds.y += 2;
			eat_alpha -= 1f / 10;
			if (eat_alpha < 0) {
				eat_alpha = 0;
			}
			repaint();
			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}
		}
	}

	// 只有兔子文字下落效果
	/**
	 * Only rabbit.
	 */
	private void OnlyRabbit() {
		for (int m = 0; m < rabbitOnly_data.length; m++) {
			rabbitOnly_Pic.setBounds(100, rabbitOnly_data[m], 480, 114);
			try {
				Thread.sleep(20);
			} catch (Exception e) {
			}
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
		g2d.drawImage(background_img, 0, 0, this.getWidth(), this.getHeight(),
				this);

		// 厨师从下网上飞出
		g2d.drawImage(cook_img, cook_bounds.x, cook_bounds.y,
				cook_bounds.width, cook_bounds.height, this);

		// 让我出去文字
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				letMeOut_alpha));
		g2d.drawImage(letMeOut_img, letMeOut_bounds.x, letMeOut_bounds.y,
				letMeOut_bounds.width, letMeOut_bounds.height, this);
		g2d.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, 1f));

		// 兔子输出的话的旋转效果
		if (isBangVisible) {
			g2d.rotate(bangAngle, 146, 262);
			g2d.drawImage(bang_img, 40, 130, 234, 138, this);
			g2d.rotate(-bangAngle, 146, 262);
		}

		// 厨师说出的话的效果
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				eat_alpha));
		g2d.drawImage(eat_img, eat_bounds.x, eat_bounds.y, eat_bounds.width,
				eat_bounds.height, this);
		g2d.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, 1f));

		// 主题餐厅文字图片下落效果
		g2d.rotate(cafeAngle, 460, 61);
		g2d.drawImage(themeCafe_img, themeCafe_bounds.x, themeCafe_bounds.y,
				themeCafe_bounds.width, themeCafe_bounds.height, this);
		g2d.rotate(-cafeAngle, 460, 61);

		// 兔子的效果
		g2d.drawImage(rabbit_img, rabbit_bounds.x, rabbit_bounds.y,
				rabbit_bounds.width, rabbit_bounds.height, this);
	}

	/**
	 * Rabbit out.
	 */
	private void rabbitOut() {
		rabbit_bounds = new Rectangle(400, -20, 50, 50);
		for (int j = 0; j < 100; j++) {
			if (j < 180)
				rabbit_bounds.setBounds(400, j - 20, (int) (50 + 1.5 * j),
						(int) (50 + 1.5 * j));
			else
				rabbit_bounds.setBounds(400, 320 - j, (int) (50 + 1.5 * j),
						(int) (50 + 1.5 * j));
			try {
				Thread.sleep(j / 20);
			} catch (Exception e) {
			}
			repaint();
		}
		for (int s = 0; s < 25; s++) {
			rabbit_bounds.setBounds(400, 20, 200 - 5 * s, 200 - 5 * s);
			try {
				Thread.sleep((26 - s) / 5);
			} catch (Exception e) {
			}
			repaint();
		}
	}

	// 主题餐厅文字效果
	/**
	 * Theme cafe down.
	 */
	private void themeCafeDown() {
		for (int h = 0; h < themeCafe_data.length; h++) {
			themeCafe_bounds.y = themeCafe_data[h];
			try {
				Thread.sleep(30);
			} catch (Exception e) {
			}
			repaint();
		}

		for (int k = 0; k < 6; k++) {
			try {
				cafeAngle += cafeDerication * (float) (Math.PI / 6);
				cafeDerication = -cafeDerication;
				repaint();
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}

	// 主题餐厅文字震动
	/**
	 * Theme cafe shake.
	 */
	private void themeCafeShake() {
		themeCafe_bounds.y -= 5;
		themeCafe_bounds.x += 10;
		repaint();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		themeCafe_bounds.y += 5;
		themeCafe_bounds.x -= 10;
		repaint();

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}