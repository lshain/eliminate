/*
 * 
 */
package eliminate.view.single;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import eliminate.model.chess.ChessModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ChessView.
 */
@SuppressWarnings("serial")
public class ChessView extends JPanel implements Observer {

	/** The chess. */
	private String chess = "media/image/chess/";

	/** The chess_img. */
	private static Image[] chess_img = new Image[17];

	// public static GameTimer timer;
	/** The chess model. */
	ChessModel chessModel = new ChessModel();

	/** The hint. */
	ArrayList<Point> hint;

	/** The has hint. */
	boolean hasHint = false;

	/**
	 * Instantiates a new chess view.
	 */
	public ChessView() {
		init();
	}

	/**
	 * Draw image.
	 * 
	 * @param g
	 *            the g
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param type
	 *            the type
	 */
	private void DrawImage(Graphics g, int x, int y, int type) {
		// if (type == 0) {
		// return;
		// }
		// g.drawImage(
		// chess_img[type],
		// (x - 1) * chessModel.getWidth() + chessModel.getValue(x, y, 1),
		// (y - 1) * chessModel.getHeight() + chessModel.getValue(x, y, 2),
		// chessModel.getWidth(), chessModel.getHeight(), this);
		if (type == 0) {
			return;
		} else if (type < 15) {
			g.drawImage(
					chess_img[type],
					(x - 1) * chessModel.getWidth() + 7
							+ chessModel.getValue(x, y, 1),
					(y - 1) * chessModel.getHeight()
							+ chessModel.getValue(x, y, 2) + 9,
					chessModel.getWidth() - 14, chessModel.getHeight() - 18,
					this);
		} else {
			g.drawImage(
					chess_img[ChessModel.PROP_A],
					(x - 1) * chessModel.getWidth()
							+ chessModel.getValue(x, y, 1),
					(y - 1) * chessModel.getHeight()
							+ chessModel.getValue(x, y, 2),
					chessModel.getWidth(), chessModel.getHeight(), this);

			g.drawImage(
					chess_img[type - ChessModel.PROP_A],
					(x - 1) * chessModel.getWidth() + 7
							+ chessModel.getValue(x, y, 1),
					(y - 1) * chessModel.getHeight()
							+ chessModel.getValue(x, y, 2) + 9,
					chessModel.getWidth() - 14, chessModel.getHeight() - 18,
					this);
		}
	}

	/**
	 * Gets the hint.
	 * 
	 * @return the hint
	 */
	public boolean getHint() {
		return hasHint;
	}

	/**
	 * Inits the.
	 */
	private void init() {
		this.setOpaque(false);
		for (int i = 1; i <= ChessModel.NUM_CHESS; i++) {
			try {
				chess_img[i] = ImageIO.read(new File(chess + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			chess_img[16] = ImageIO
					.read(new File("media/image/chess/hint.png"));
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
		// g.drawImage(new ImageIcon(background_pic).getImage(), 0, 0,
		// this.getWidth(), this.getHeight(), this);

		if (hasHint) {
			for (Point p : hint) {
				g.drawImage(
						chess_img[16],
						(p.x - 1) * chessModel.getWidth()
								+ chessModel.getValue(p.x, p.y, 1),
						(p.y - 1) * chessModel.getHeight()
								+ chessModel.getValue(p.x, p.y, 2),
						chessModel.getWidth(), chessModel.getHeight(), this);

			}
		}

		for (int i = 0; i < chessModel.getHorizontalNum(); i++) {
			for (int k = 0; k < chessModel.getVerticalNum(); k++) {
				DrawImage(g, i, k, chessModel.getValue(i, k, 0));
			}
		}
	}

	/**
	 * Sets the hint.
	 * 
	 * @param hint
	 *            the new hint
	 */
	public void setHint(ArrayList<Point> hint) {
		this.hint = hint;
		hasHint = true;
		repaint();
	}

	/**
	 * Sets the hint.
	 * 
	 * @param hasHint
	 *            the new hint
	 */
	public void setHint(boolean hasHint) {
		this.hasHint = hasHint;
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ChessModel) {
			this.chessModel = (ChessModel) o;
			repaint();
		}
	}
}
