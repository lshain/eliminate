/*
 * 
 */
package eliminate.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

// TODO: Auto-generated Javadoc
/**
 * The Class MyScrollBarUI.
 */
public class MyScrollBarUI extends BasicScrollBarUI {

	/** The thumb. */
	ImageIcon thumb = null;

	/** The has thumb pic. */
	boolean hasThumbPic = false;

	/** The Constant thumbPic. */
	private static final String thumbPic = "media/image/all/scrollBar.png";

	/**
	 * Instantiates a new my scroll bar ui.
	 * 
	 * @param hasIcon
	 *            the has icon
	 */
	public MyScrollBarUI(boolean hasIcon) {
		if (hasIcon) {
			hasThumbPic = true;
			thumb = new ImageIcon(thumbPic);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicScrollBarUI#createDecreaseButton(int)
	 */
	@Override
	protected JButton createDecreaseButton(int orientation) {
		return createZeroButton();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicScrollBarUI#createIncreaseButton(int)
	 */
	@Override
	protected JButton createIncreaseButton(int orientation) {
		return createZeroButton();
	}

	/**
	 * Creates the zero button.
	 * 
	 * @return the j button
	 */
	private JButton createZeroButton() {
		JButton jbutton = new JButton();
		jbutton.setPreferredSize(new Dimension(0, 0));
		jbutton.setMinimumSize(new Dimension(0, 0));
		jbutton.setMaximumSize(new Dimension(0, 0));
		return jbutton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicScrollBarUI#paintThumb(java.awt.Graphics,
	 * javax.swing.JComponent, java.awt.Rectangle)
	 */
	@Override
	protected void paintThumb(Graphics g, JComponent comp, Rectangle rect) {
		if (hasThumbPic) {
			g.drawImage(thumb.getImage(), rect.x, rect.y,
					(int) rect.getWidth(), (int) rect.getHeight(), comp);
		} else {
			g.setColor(new Color(0, 0, 255));
			g.fillRect(rect.x, rect.y, (int) rect.getWidth(), rect.height);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicScrollBarUI#paintTrack(java.awt.Graphics,
	 * javax.swing.JComponent, java.awt.Rectangle)
	 */
	@Override
	protected void paintTrack(Graphics g, JComponent comp, Rectangle rect) {
		// g.setColor(new Color(0, 255, 0));
		// g.fillRect(rect.x, rect.y, 10, rect.height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicScrollBarUI#setThumbBounds(int, int,
	 * int, int)
	 */
	@Override
	protected void setThumbBounds(int arg0, int arg1, int arg2, int arg3) {
		super.setThumbBounds(arg0, arg1, arg2, arg3);
	}
}
