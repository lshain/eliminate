/*
 * 
 */
package eliminate.view.fight;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import eliminate.model.chess.ChessModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SmallChessView.
 */
@SuppressWarnings("serial")
public class SmallChessView extends JPanel implements Observer {

	/** The chess. */
	private String chess = "media/image/chess/";

	/** The chess_img. */
	private static Image[] chess_img = new Image[17];

	/** The Constant SMALL_RATE_x. */
	private static final double SMALL_RATE_x = 0.576;

	/** The Constant SMALL_RATE_y. */
	private static final double SMALL_RATE_y = 0.565;

	/** The chess model. */
	ChessModel chessModel = new ChessModel();

	/**
	 * Instantiates a new small chess view.
	 */
	public SmallChessView() {
		init();
	}

	/**
	 * Instantiates a new small chess view.
	 * 
	 * @param model
	 *            the model
	 */
	public SmallChessView(ChessModel model) {
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
						chess_img[16],
						(int) (((x - 1) * chessModel.getWidth() + chessModel
								.getValue(x, y, 1)) * SMALL_RATE_x),
						(int) (((y - 1) * chessModel.getHeight() + chessModel
								.getValue(x, y, 2)) * SMALL_RATE_y),
						(int) (chessModel.getWidth() * SMALL_RATE_x),
						(int) (chessModel.getHeight() * SMALL_RATE_y), this);
			}
			g.drawImage(
					chess_img[type],
					(int) (((x - 1) * chessModel.getWidth() + 7 + chessModel
							.getValue(x, y, 1)) * SMALL_RATE_x),
					(int) (((y - 1) * chessModel.getHeight()
							+ chessModel.getValue(x, y, 2) + 9) * SMALL_RATE_y),
					(int) ((chessModel.getWidth() - 14) * SMALL_RATE_x),
					(int) ((chessModel.getHeight() - 18) * SMALL_RATE_y), this);
		} else {
			g.drawImage(chess_img[ChessModel.PROP_A],
					(int) (((x - 1) * chessModel.getWidth() + chessModel
							.getValue(x, y, 1)) * SMALL_RATE_x),
					(int) (((y - 1) * chessModel.getHeight() + chessModel
							.getValue(x, y, 2)) * SMALL_RATE_y),
					(int) (chessModel.getWidth() * SMALL_RATE_x),
					(int) (chessModel.getHeight() * SMALL_RATE_y), this);

			g.drawImage(
					chess_img[type - ChessModel.PROP_A],
					(int) (((x - 1) * chessModel.getWidth() + 7 + chessModel
							.getValue(x, y, 1)) * SMALL_RATE_x),
					(int) (((y - 1) * chessModel.getHeight()
							+ chessModel.getValue(x, y, 2) + 9) * SMALL_RATE_y),
					(int) ((chessModel.getWidth() - 14) * SMALL_RATE_x),
					(int) ((chessModel.getHeight() - 18) * SMALL_RATE_y), this);
		}
	}

	
	public void frozen(int type) {
		this.frozenType = type;
		repaint();
	}
	
	/**
	 * Inits the.
	 */
	private void init() {
		chessModel.addObserver(this);
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
					.read(new File("media/image/chess/frozen.png"));
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
		for (int i = 0; i < chessModel.getHorizontalNum(); i++) {
			for (int k = 0; k < chessModel.getVerticalNum(); k++) {
				DrawImage(g, i, k, chessModel.getValue(i, k, 0));
			}
		}
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
	
	private int frozenType = -1;
}
