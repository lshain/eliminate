/*
 * 
 */
package eliminate.view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import eliminate.controller.MainFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class TitleBar.
 */
@SuppressWarnings("serial")
public class TitleBar extends JPanel implements ActionListener {

	/** The exit. */
	private String exit = "media/image/all/close.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/all/close_hover.png";

	/** The exit_down. */
	private String exit_down = "media/image/all/close_down.png";

	/** The jb close. */
	MyButton jbClose;

	/** The help. */
	private String help = "media/image/all/help2.png";

	/** The help_hover. */
	private String help_hover = "media/image/all/help_down.png";

	/** The help_down. */
	private String help_down = "media/image/all/help_down.png";

	/** The jb help. */
	MyButton jbHelp;

	/** The minimize. */
	private String minimize = "media/image/all/minimize.png";

	/** The minimize_hover. */
	private String minimize_hover = "media/image/all/minimize_down.png";

	/** The minimize_down. */
	private String minimize_down = "media/image/all/minimize_down.png";

	/** The jb minimize. */
	MyButton jbMinimize;

	/**
	 * Instantiates a new title bar.
	 */
	public TitleBar() {
		setOpaque(false);
		setLayout(null);
		try {
			jbClose = MyButton.makeButton(exit, exit_down, exit_hover);
			add(jbClose);
			jbClose.setBounds(1060, 4, 36, 27);
			jbClose.addActionListener(this);

			jbHelp = MyButton.makeButton(help, help_down, help_hover);
			jbHelp.setBounds(1020, 4, 36, 27);
			add(jbHelp);
			jbHelp.addActionListener(this);

			jbMinimize = MyButton.makeButton(minimize, minimize_down,
					minimize_hover);
			jbMinimize.setBounds(980, 4, 36, 27);
			add(jbMinimize);
			jbMinimize.addActionListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jbClose)) {
			System.exit(0);
		} else if (e.getSource().equals(jbMinimize)) {
			MainFrame.getOwner().setState(Frame.ICONIFIED);
		} else if (e.getSource().equals(jbHelp)) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0.8f, 0.8f, 0.8f, 0.5f));
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		// g.setColor(new Color(1f, 0f, 0f, 1f));
		// g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
