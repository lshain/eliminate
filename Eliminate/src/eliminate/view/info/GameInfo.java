/*
 * 
 */
package eliminate.view.info;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import chess.GameTimer;

// TODO: Auto-generated Javadoc
/**
 * The Class GameInfo.
 */
@SuppressWarnings("serial")
public class GameInfo extends JPanel implements Observer {

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	/** The font. */
	private Font font;

	/** The rest time lb. */
	private JLabel restTimeLB;

	/** The max combo lb. */
	private JLabel maxComboLB;

	/** The score lb. */
	private JLabel scoreLB;

	/** The max score lb. */
	private JLabel maxScoreLB;

	/**
	 * Instantiates a new game info.
	 */
	public GameInfo() {
		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(40f));

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setLayout(null);
		setOpaque(false);

		restTimeLB = new JLabel("60");
		restTimeLB.setFont(font);
		restTimeLB.setForeground(Color.WHITE);
		restTimeLB.setHorizontalAlignment(SwingConstants.CENTER);
		restTimeLB.setVerticalAlignment(SwingConstants.CENTER);
		add(restTimeLB);
		restTimeLB.setBounds(70, 103, 200, 60);

		scoreLB = new JLabel("0");
		scoreLB.setFont(font);
		scoreLB.setForeground(Color.WHITE);
		scoreLB.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLB.setVerticalAlignment(SwingConstants.CENTER);
		add(scoreLB);
		scoreLB.setBounds(61, 212, 216, 53);

		maxComboLB = new JLabel("0");
		maxComboLB.setFont(font);
		maxComboLB.setForeground(Color.WHITE);
		maxComboLB.setHorizontalAlignment(SwingConstants.CENTER);
		maxComboLB.setVerticalAlignment(SwingConstants.CENTER);
		add(maxComboLB);
		maxComboLB.setBounds(61, 314, 216, 53);

		maxScoreLB = new JLabel("");
		maxScoreLB.setFont(font);
		maxScoreLB.setForeground(Color.WHITE);
		maxScoreLB.setHorizontalAlignment(SwingConstants.CENTER);
		maxScoreLB.setVerticalAlignment(SwingConstants.CENTER);
		add(maxScoreLB);
		maxScoreLB.setBounds(61, 410, 216, 53);
	}

	/**
	 * Sets the max score.
	 * 
	 * @param score
	 *            the new max score
	 */
	public void setMaxScore(int score) {
		maxScoreLB.setText(Integer.toString(score));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		restTimeLB.setText(Integer.toString(((GameTimer) arg0).getRestTime()));
		scoreLB.setText(Integer.toString(((GameTimer) arg0).getScore()));
		maxComboLB.setText(Integer.toString(((GameTimer) arg0).getMaxCombo()));
		repaint();
	}
}
