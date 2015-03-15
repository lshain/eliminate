/*
 * 
 */
package eliminate.view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class MyButton.
 */
@SuppressWarnings("serial")
public class MyButton extends JButton {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame();

		jf.setSize(800, 600);
		jf.setLayout(new FlowLayout());
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Make button.
	 * 
	 * @param pic_path
	 *            the pic_path
	 * @param pic_down
	 *            the pic_down
	 * @param pic_hover
	 *            the pic_hover
	 * @return the my button
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static MyButton makeButton(String pic_path, String pic_down,
			String pic_hover) throws IOException {
		Image btton_normal = ImageIO.read(new File(pic_path));
		Image button_down = ImageIO.read(new File(pic_down));
		Image button_hover = ImageIO.read(new File(pic_hover));
		return new MyButton(btton_normal, button_down, button_hover);
	}

	/**
	 * Make button.
	 * 
	 * @param pic_path
	 *            the pic_path
	 * @param pic_down
	 *            the pic_down
	 * @param pic_hover
	 *            the pic_hover
	 * @param pic_disable
	 *            the pic_disable
	 * @return the my button
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static MyButton makeButton(String pic_path, String pic_down,
			String pic_hover, String pic_disable) throws IOException {
		Image btton_normal = ImageIO.read(new File(pic_path));
		Image button_down = ImageIO.read(new File(pic_down));
		Image button_hover = ImageIO.read(new File(pic_hover));
		Image button_disable = ImageIO.read(new File(pic_disable));
		return new MyButton(btton_normal, button_down, button_hover,
				button_disable);
	}

	/** The bt_img_armed. */
	Image bt_img_armed = null;

	/** The bt_img_rollover. */
	Image bt_img_rollover = null;

	/** The bt_img_normal. */
	Image bt_img_normal = null;

	/** The bt_img_disable. */
	Image bt_img_disable = null;

	/** The selected. */
	private boolean selected = false;

	/**
	 * Instantiates a new my button.
	 * 
	 * @param bt_img_normal
	 *            the bt_img_normal
	 * @param bt_img_armed
	 *            the bt_img_armed
	 * @param bt_img_rollover
	 *            the bt_img_rollover
	 */
	private MyButton(Image bt_img_normal, Image bt_img_armed,
			Image bt_img_rollover) {
		this.bt_img_armed = bt_img_armed;
		this.bt_img_normal = bt_img_normal;
		this.bt_img_rollover = bt_img_rollover;

		setBorder(null);
		this.setContentAreaFilled(false);
	}

	/**
	 * Instantiates a new my button.
	 * 
	 * @param bt_img_normal
	 *            the bt_img_normal
	 * @param bt_img_armed
	 *            the bt_img_armed
	 * @param bt_img_rollover
	 *            the bt_img_rollover
	 * @param bt_img_disable
	 *            the bt_img_disable
	 */
	private MyButton(Image bt_img_normal, Image bt_img_armed,
			Image bt_img_rollover, Image bt_img_disable) {
		this.bt_img_armed = bt_img_armed;
		this.bt_img_normal = bt_img_normal;
		this.bt_img_rollover = bt_img_rollover;
		this.bt_img_disable = bt_img_disable;

		setBorder(null);
		this.setContentAreaFilled(false);
	}

	/**
	 * Gets the seleted.
	 * 
	 * @return the seleted
	 */
	public boolean getSeleted() {
		return selected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		if (!getModel().isEnabled() && bt_img_disable != null) {
			g.drawImage(bt_img_disable, 0, 0, this.getWidth(),
					this.getHeight(), this);
		} else if (getModel().isArmed() || selected) {
			g.drawImage(bt_img_armed, 0, 0, this.getWidth(), this.getHeight(),
					this);
		} else if (getModel().isRollover()) {
			g.drawImage(bt_img_rollover, 0, 0, this.getWidth(),
					this.getHeight(), this);
		} else {
			g.drawImage(bt_img_normal, 0, 0, this.getWidth(), this.getHeight(),
					this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractButton#setSelected(boolean)
	 */
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
		repaint();
	}
}
