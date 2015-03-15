/*
 * 
 */
package eliminate.controller;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eliminate.view.TitleBar;
import eliminate.view.WindowDraggable;
import eliminate.view.dialogs.LoadingDialog;
import eliminate.view.dialogs.MessageDialog;
import eliminate.view.login.LoginPanel;

/**
 * The Class MainFrame.
 */
public class MainFrame {

	/** The main_frame. */
	private static JFrame main_frame;

	/** The content panel. */
	private static JPanel contentPanel;

	/** The title. */
	private static TitleBar title = new TitleBar();

	/** The is title show. */
	private static boolean isTitleShow = false;

	/** The animation. */
	private static boolean animation = true;

	/** The load dialog. */
	private static LoadingDialog loadDialog;

	/**
	 * Gets the checks if is title bar show.
	 * 
	 * @return the checks if is title bar show
	 */
	public static boolean getIsTitleBarShow() {
		return isTitleShow;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public static Point getLocation() {
		return main_frame.getLocation();
	}

	/**
	 * Gets the owner.
	 * 
	 * @return the owner
	 */
	public static JFrame getOwner() {
		return main_frame;
	}

	/**
	 * Hide loading dialog.
	 */
	public static void hideLoadingDialog() {
		loadDialog.setCanStop(true);
	}

	/**
	 * Hide title bar.
	 */
	public static void hideTitleBar() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Rectangle rect = title.getBounds();
				if (rect.y == 0) {
					isTitleShow = false;
					for (int i = 0; i < 38; i += 2) {
						rect.y -= 2;
						title.setBounds(rect);
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (!animation) {
							break;
						}
					}
				}
			}
		}).start();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		MainFrame.startGame();
	}

	/**
	 * Sets the content panel.
	 * 
	 * @param panel
	 *            the new content panel
	 */
	public static void setContentPanel(JPanel panel) {
		main_frame.remove(contentPanel);
		animation = false;
		contentPanel = panel;
		contentPanel.add(title);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		animation = true;
		title.setBounds(0, 0, 1103, 36);
		main_frame.setContentPane(contentPanel);
		main_frame.revalidate();
		hideTitleBar();
		hideLoadingDialog();
	}

	/**
	 * Sets the location.
	 * 
	 * @param loc
	 *            the new location
	 */
	public static void setLocation(Point loc) {
		main_frame.setLocation(loc);
	}

	/**
	 * Show loading dialog.
	 */
	public static void showLoadingDialog() {
		loadDialog.startPlay();
	}

	/**
	 * Show msg dialog.
	 * 
	 * @param msg
	 *            the msg
	 * @param title
	 *            the title
	 */
	public static void showMsgDialog(String msg, String title) {
		new MessageDialog(MainFrame.getOwner(), true).visible(title, msg);
	}

	/**
	 * Show title bar.
	 */
	public static void showTitleBar() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Rectangle rect = title.getBounds();
				if (rect.y == -38) {
					isTitleShow = true;
					for (int i = 0; i < 38; i += 2) {
						rect.y += 2;
						title.setBounds(rect);
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (!animation) {
							break;
						}
					}
				}
			}
		}).start();
	}

	/**
	 * Start game.
	 */
	public static void startGame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				main_frame = new JFrame();
				main_frame.setUndecorated(true);
				contentPanel = new LoginPanel();
				main_frame.setContentPane(contentPanel);
				contentPanel.add(title);
				title.setBounds(0, 0, 1103, 36);
				main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main_frame.setSize(1103, 618);
				main_frame.setVisible(true);
				WindowDraggable drag = new WindowDraggable();
				main_frame.addMouseListener(drag);
				main_frame.addMouseMotionListener(drag);
				main_frame.setLocationRelativeTo(null);
				hideTitleBar();
				new Thread(new Runnable() {
					@Override
					public void run() {
						LoadingDialog.init();
					}
				}).start();
				loadDialog = new LoadingDialog(main_frame, true);
			}
		});
	}

	/**
	 * Instantiates a new main frame.
	 */
	public MainFrame() {

	}
}
