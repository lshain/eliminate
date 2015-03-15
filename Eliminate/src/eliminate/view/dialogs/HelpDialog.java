/*
 * 
 */
package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
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
 * The Class HelpDialog.
 */
@SuppressWarnings("serial")
public class HelpDialog {

	/**
	 * The Class HelpPanel.
	 */
	class HelpPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new help panel.
		 */
		public HelpPanel() {
			try {
				this.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println(e.getX() + "   " + e.getY());
					}
				});

				setLayout(null);

				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(925, 65, 40, 40);
				jbExit.addActionListener(this);

				help_Label = new JLabel(new ImageIcon(background_content));
				help_Label.setBounds(150, 180, 655, 1695);

				help_scroll = new JScrollPane(help_Label);
				help_scroll.setBounds(150, 180, 655, 355);
				help_scroll.setBorder(BorderFactory.createEmptyBorder());
				help_scroll.getViewport().setOpaque(false);
				help_scroll
						.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				help_scroll.setOpaque(false);
				help_scroll.getVerticalScrollBar().setUnitIncrement(20);

				help_jsb = help_scroll.getVerticalScrollBar();
				help_jsb.setOpaque(false);
				help_jsb.setUI(new MyScrollBarUI(true));
				add(help_scroll);
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

	/** The help_scroll. */
	JScrollPane help_scroll;

	/** The help_ label. */
	JLabel help_Label;

	/** The help_jsb. */
	JScrollBar help_jsb;

	/** The background_pic. */
	private String background_pic = "media/image/dialog/help/help.png";

	/** The background_content. */
	private String background_content = "media/image/dialog/help/help_content.png";

	/** The help_content. */
	JTextArea help_content;

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
	 * Instantiates a new help dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public HelpDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setContentPane(new HelpPanel());
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocation(owner.getLocation());
		jd.setVisible(true);
	}
}