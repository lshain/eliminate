/*
 * 
 */
package eliminate.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class InvinciblePanel.
 */
@SuppressWarnings("serial")
public class InvinciblePanel extends JPanel {

	/** The background. */
	private String background = "media/image/mutiplayer/invincible_state.png";

	/**
	 * Instantiates a new invincible panel.
	 */
	public InvinciblePanel() {
		setOpaque(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(background).getImage(), 0, 0,
				this.getWidth(), this.getHeight(), this);
	}
}
