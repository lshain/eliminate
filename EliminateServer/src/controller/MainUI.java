package controller;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import login.LoginThread;
import register.RegisterThread;
import chat.ChatThread;

// TODO: Auto-generated Javadoc
/**
 * The Class MainUI.
 */
@SuppressWarnings("serial")
public class MainUI extends JFrame implements MouseListener {
	// int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	// int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new MainUI().initLeftPanel();
	}

	/** The chart. */
	JPanel chart = new JPanel();

	/** The left panel. */
	JPanel leftPanel;

	/** The card panel. */
	JPanel cardPanel;

	/** The card. */
	CardLayout card;

	/** The turn_on. */
	JButton turn_on;

	/** The turn_off. */
	JButton turn_off;

	/** The is_on. */
	boolean is_on = false;

	/** The jsp. */
	JSplitPane jsp = null;

	/** The test. */
	LoginThread test = new LoginThread();

	/** The chat. */
	ChatThread chat = new ChatThread();

	/** The register. */
	RegisterThread register = new RegisterThread();

	/** The tsc. */
	TimeSeriesChart tsc = new TimeSeriesChart("时序图", "在线人数", "数值", is_on);

	/** The t. */
	Thread t;

	/**
	 * Inits the left panel.
	 */
	public void initLeftPanel() {
		this.setSize(1103, 618);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardPanel = new JPanel();
		card = new CardLayout();
		cardPanel.setLayout(card);

		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		cardPanel.add(leftPanel);
		turn_on = new JButton("开启服务器");
		turn_off = new JButton("关闭服务器");
		leftPanel.add(turn_on);
		leftPanel.add(turn_off);
		turn_on.setBounds(37, 106, 175, 85);
		turn_off.setBounds(37, 310, 175, 85);
		turn_on.addMouseListener(this);
		turn_off.addMouseListener(this);
		turn_off.setEnabled(false);

		// chart_title = new JLabel("时序图");
		// cardPanel.add(chart_title);
		// chart_title.addMouseListener(this);

		chart = setUI();
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, cardPanel,
				chart);
		jsp.setDividerLocation(250);
		// jsp.setDividerSize(0);
		this.add(jsp);

		this.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == turn_on) {
			is_on = true;
			test.start();
			chat.start();
			register.start();
			tsc.start();
			turn_on.setEnabled(false);
			turn_off.setEnabled(true);
			repaint();
		} else if (e.getSource() == turn_off) {
			System.exit(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the ui.
	 * 
	 * @return the j panel
	 */
	public JPanel setUI() {
		return tsc.getUI();

	}
}