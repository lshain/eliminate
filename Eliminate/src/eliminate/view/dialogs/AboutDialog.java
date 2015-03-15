/*
 * 
 */
package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyScrollBarUI;

// TODO: Auto-generated Javadoc
/**
 * The Class AboutDialog.
 */
@SuppressWarnings("serial")
public class AboutDialog {

	/**
	 * The Class AboutPanel.
	 */
	class AboutPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new about panel.
		 */
		public AboutPanel() {
			try {

				setLayout(null);

				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(772, 58, 40, 40);
				jbExit.addActionListener(this);

				about_Label = new JLabel(new ImageIcon(background_content));
				about_Label.setBounds(50, 0, 337, 692);

				about_scroll = new JScrollPane(about_Label);
				about_scroll.setBounds(418, 140, 364, 370);
				about_scroll.setBorder(null);
				about_scroll
						.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				about_scroll.getViewport().setOpaque(false);
				about_scroll.setOpaque(false);
				about_scroll.getVerticalScrollBar().setUnitIncrement(20);

				about_jsb = about_scroll.getVerticalScrollBar();
				about_jsb.setOpaque(false);
				about_jsb.setUI(new MyScrollBarUI(true));
				add(about_scroll);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer.playButtonOne();
			if (e.getSource() == jbExit) {
				jd.dispose();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon(background_pic).getImage(), 0, 0,
					this.getWidth(), this.getHeight(), this);
		}
	}

	/** The jd. */
	JDialog jd;

	/** The about_scroll. */
	JScrollPane about_scroll;

	/** The about_ label. */
	JLabel about_Label;

	/** The about_jsb. */
	JScrollBar about_jsb;

	/** The background_pic. */
	private String background_pic = "media/image/dialog/about/about.png";

	/** The background_content. */
	private String background_content = "media/image/dialog/about/about_content.png";

	/** The about_content. */
	JTextArea about_content;

	// ÍË³ö°´Å¥
	/** The exit. */
	private String exit = "media/image/dialog/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/dialog/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/dialog/exit_hover.png";

	/** The jb exit. */
	JButton jbExit;

	/**
	 * Instantiates a new about dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public AboutDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setContentPane(new AboutPanel());
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocation(owner.getLocation());
		jd.setVisible(true);
	}
}