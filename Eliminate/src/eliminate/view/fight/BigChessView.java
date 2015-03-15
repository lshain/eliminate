/*
 * 
 */
package eliminate.view.fight;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import chess.ChessMsg;
import eliminate.controller.login.LoginController;
import eliminate.model.chess.ChessModel;

// TODO: Auto-generated Javadoc
/**
 * The Class BigChessView.
 */
@SuppressWarnings("serial")
public class BigChessView extends JPanel implements Observer {

	/**
	 * The Class ChessMotionAdapter.
	 */
	class ChessMotionAdapter extends MouseAdapter {

		/** The pressed. */
		boolean pressed = false;

		/** The pos_x. */
		int pos_x;

		/** The pos_y. */
		int pos_y;

		/** The pos_start_x. */
		int pos_start_x;

		/** The pos_start_y. */
		int pos_start_y;

		/** The start_x. */
		int start_x;

		/** The start_y. */
		int start_y;

		/** The end_x. */
		int end_x;

		/** The end_y. */
		int end_y;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			start_x = e.getX() / chessModel.getWidth() + 1;
			start_y = e.getY() / chessModel.getHeight() + 1;
			int value = chessModel.getValue(start_x, start_y, 0);
			if (value > ChessModel.PROP_A) {
				LoginController.writeObject(new ChessMsg(new Point(start_x,
						start_y), 0));
			} else if (value == ChessModel.PROP_B) {
				LoginController.writeObject(new ChessMsg(new Point(start_x,
						start_y), 0));
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			pressed = true;
			pos_start_x = e.getX();
			pos_start_y = e.getY();
			start_x = e.getX() / chessModel.getWidth() + 1;
			start_y = e.getY() / chessModel.getHeight() + 1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			pressed = false;

			end_x = e.getX() / chessModel.getWidth() + 1;
			end_y = e.getY() / chessModel.getHeight() + 1;
			if (end_x >= chessModel.getHorizontalNum()
					|| end_y >= chessModel.getVerticalNum()) {
				return;
			}
			end_x -= start_x;
			end_y -= start_y;
			if (Math.abs(end_x) >= Math.abs(end_y)) {
				if (end_x < 0 && start_x > 0) {
					end_x = start_x - 1;
				} else if (end_x > 0 && start_x < chessModel.getHorizontalNum()) {
					end_x = start_x + 1;
				} else {
					return;
				}
				end_y = start_y;
			} else {
				if (end_y < 0 && start_y > 0) {
					end_y = start_y - 1;
				} else if (end_y > 0 && start_y < chessModel.getVerticalNum()) {
					end_y = start_y + 1;
				} else {
					return;
				}
				end_x = start_x;
			}
			
			if(chessModel.getValue(start_x, start_y, 0) == frozenType ||
					chessModel.getValue(end_x, end_y, 0) == frozenType){
				return;
			}
			LoginController.writeObject(new ChessMsg(
					new Point(start_x, start_y), new Point(end_x, end_y), 0));
		}
	}

	/** The chess. */
	private String chess = "media/image/chess/";

	/** The chess_img. */
	private static Image[] chess_img = new Image[18];

	/** The chess model. */
	ChessModel chessModel = new ChessModel();

	/** The mouse adapter. */
	private MouseAdapter mouseAdapter = new ChessMotionAdapter();

	/** The hint. */
	ArrayList<Point> hint;

	/** The has hint. */
	boolean hasHint = false;

	/**
	 * Instantiates a new big chess view.
	 */
	public BigChessView() {
		init();
	}

	/**
	 * Instantiates a new big chess view.
	 * 
	 * @param model
	 *            the model
	 */
	public BigChessView(ChessModel model) {
		this.chessModel.changeModel(model);
		init();
	}

	/**
	 * Change chess state.
	 * 
	 * @param chess_state
	 *            the chess_state
	 */
	public void changeChessState(int[][][] chess_state) {
		chessModel.setData(chess_state);
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
		if (type == 0) {
			return;
		} else if (type < 15) {
			if (type == frozenType) {
				g.drawImage(
						chess_img[17],
						(x - 1) * chessModel.getWidth()
								+ chessModel.getValue(x, y, 1),
						(y - 1) * chessModel.getHeight()
								+ chessModel.getValue(x, y, 2),
						chessModel.getWidth(), chessModel.getHeight(), this);
			}
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
	 * Inits the.
	 */
	private void init() {
		chessModel.addObserver(this);
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
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
			chess_img[17] = ImageIO.read(new File(
					"media/image/chess/frozen.png"));
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
	 * Restart.
	 */
	public void restart() {
		chessModel.changeModel(new ChessModel());
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
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
	}

	/**
	 * Start.
	 */
	public void start() {
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
	}

	/**
	 * Stop.
	 */
	public void stop() {
		this.removeMouseListener(mouseAdapter);
		this.removeMouseMotionListener(mouseAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	public void frozen(int type) {
		this.frozenType = type;
		repaint();
	}

	private int frozenType = -1;
}
