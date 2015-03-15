/*
 * 
 */
package eliminate.view;

import java.awt.Graphics;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;
// TODO: Auto-generated Javadoc
//MetalSliderUI

/**
 * The Class MySliderUI.
 */
public class MySliderUI extends BasicSliderUI {

	/**
	 * Instantiates a new my slider ui.
	 * 
	 * @param b
	 *            the b
	 */
	public MySliderUI(JSlider b) {
		super(b);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicSliderUI#paintFocus(java.awt.Graphics)
	 */
	@Override
	public void paintFocus(Graphics g) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicSliderUI#paintTrack(java.awt.Graphics)
	 */
	@Override
	public void paintTrack(Graphics g) {

	}
}