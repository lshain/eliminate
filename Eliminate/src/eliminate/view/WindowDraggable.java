/*
 * 
 */
package eliminate.view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import eliminate.controller.MainFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class WindowDraggable.
 */
public class WindowDraggable extends MouseAdapter implements
		MouseMotionListener {

	/** The press point. */
	private Point pressPoint;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = MainFrame.getLocation();
		Point point = new Point(p.x + e.getX() - (int) pressPoint.getX(), p.y
				+ e.getY() - (int) pressPoint.getY());

		MainFrame.setLocation(point);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getY() < 36 && !MainFrame.getIsTitleBarShow()) {
			MainFrame.showTitleBar();
		} else if (e.getY() > 36 && MainFrame.getIsTitleBarShow()) {
			MainFrame.hideTitleBar();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		pressPoint = e.getPoint();
	}
}
