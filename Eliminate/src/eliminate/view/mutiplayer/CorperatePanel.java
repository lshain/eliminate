/*
 * 
 */
package eliminate.view.mutiplayer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import room.Room;
import room.RoomMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.InvinciblePanel;
import eliminate.view.MyButton;
import eliminate.view.MyScrollBarUI;
import eliminate.view.dialogs.HelpDialog;
import eliminate.view.dialogs.SettingDialog;

// TODO: Auto-generated Javadoc
/**
 * The Class CorperatePanel.
 */
@SuppressWarnings("serial")
public class CorperatePanel extends JPanel implements ActionListener {

	/** The jl rest time. */
	private JLabel jlRestTime = new JLabel("60");

	/** The jl scores. */
	private JLabel jlScores = new JLabel("0");

	/** The jl player. */
	private JLabel[] jlPlayer = new JLabel[4];

	// 聊天界面
	/** The content. */
	private JTextField content;

	/** The chat_content. */
	private JTextArea chat_content;

	/** The jsp. */
	private JScrollPane jsp;

	/** The send. */
	private JLabel send;


	/** The jsb. */
	private JScrollBar jsb;

	/** The background_pic. */
	private String background_pic = "media/image/mutiplayer/background.jpg";

	/** The chess view. */
	private ChessView chessView;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	/** The font. */
	private Font font;

	// 退出按键
	/** The exit. */
	private String exit = "media/image/all/exit2.png";

	/** The exit_down. */
	private String exit_down = "media/image/all/exit2_hover.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/all/exit2_hover.png";

	/** The exit_bt. */
	JButton exit_bt;

	// 设置按键
	/** The settings. */
	private String settings = "media/image/all/settings.png";

	/** The settings_down. */
	private String settings_down = "media/image/all/settings_hover.png";

	/** The settings_bt. */
	JButton settings_bt;

	// 帮助按键
	/** The help. */
	private String help = "media/image/all/help.png";

	/** The help_down. */
	private String help_down = "media/image/all/help_hover.png";

	/** The help_bt. */
	JButton help_bt;
	
	private String readyGoGif = "media/image/all/ready_go.png";
	
	JLabel ready_go_lb;

	/** The invincible panel. */
	JPanel invinciblePanel = new InvinciblePanel();

	/**
	 * Instantiates a new corperate panel.
	 */
	public CorperatePanel() {
		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(40f));

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// System.out.println(e.getPoint());
		// }
		// });
		this.chessView = new ChessView();
		this.setLayout(null);
		chessView.setBounds(520, 40, 486, 513);
		add(chessView);

		jlRestTime.setBounds(347, 60, 154, 45);
		jlRestTime.setFont(font);
		jlRestTime.setForeground(Color.WHITE);
		jlRestTime.setHorizontalAlignment(SwingConstants.CENTER);
		jlRestTime.setVerticalAlignment(SwingConstants.CENTER);
		add(jlRestTime);

		jlScores.setBounds(347, 152, 154, 41);
		jlScores.setFont(font);
		jlScores.setForeground(Color.WHITE);
		jlScores.setHorizontalAlignment(SwingConstants.CENTER);
		jlScores.setVerticalAlignment(SwingConstants.CENTER);
		add(jlScores);

		jlPlayer[0] = new JLabel("玩家1");
		jlPlayer[0].setFont(font.deriveFont(30f));
		jlPlayer[0].setForeground(Color.WHITE);
		jlPlayer[0].setHorizontalAlignment(SwingConstants.CENTER);
		jlPlayer[0].setVerticalAlignment(SwingConstants.CENTER);
		jlPlayer[0].setBounds(347, 242, 154, 44);
		add(jlPlayer[0]);

		jlPlayer[1] = new JLabel("玩家2");
		jlPlayer[1].setFont(font.deriveFont(30f));
		jlPlayer[1].setForeground(Color.WHITE);
		jlPlayer[1].setHorizontalAlignment(SwingConstants.CENTER);
		jlPlayer[1].setVerticalAlignment(SwingConstants.CENTER);
		jlPlayer[1].setBounds(347, 333, 154, 44);
		add(jlPlayer[1]);

		jlPlayer[2] = new JLabel("玩家2");
		jlPlayer[2].setFont(font.deriveFont(30f));
		jlPlayer[2].setForeground(Color.WHITE);
		jlPlayer[2].setHorizontalAlignment(SwingConstants.CENTER);
		jlPlayer[2].setVerticalAlignment(SwingConstants.CENTER);
		jlPlayer[2].setBounds(347, 425, 154, 44);
		add(jlPlayer[2]);

		jlPlayer[3] = new JLabel("玩家2");
		jlPlayer[3].setFont(font.deriveFont(30f));
		jlPlayer[3].setForeground(Color.WHITE);
		jlPlayer[3].setHorizontalAlignment(SwingConstants.CENTER);
		jlPlayer[3].setVerticalAlignment(SwingConstants.CENTER);
		jlPlayer[3].setBounds(347, 516, 154, 36);
		add(jlPlayer[3]);

		// 聊天界面
		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(20f));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		content = new JTextField();
		add(content);
		content.setBounds(35, 505, 178, 39);
		content.setFont(font);
		content.setBorder(BorderFactory.createEmptyBorder());
		content.setText("想要说点什么嘛？~");
		content.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (content.getText().toString().equals("想要说点什么嘛？~")) {
					content.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (content.getText().toString().equals("")) {
					content.setText("想要说点什么嘛？~");
				}
			}
		});

		content.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10
						&& !content.getText().toString().equals("")) {
					RoomMsg msg = new RoomMsg(HandleInput.getRoom(),
							RoomMsg.ROOM_CHAT);
					msg.setChatContent(LoginController.getUser().getUserId()
							+ ": " + content.getText());
					LoginController.writeObject(msg);
					content.setText("");
					AudioPlayer.playButtonOne();
				}
			};
		});

		// 发送按钮
		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(24f));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		send = new JLabel("发送");
		send.setFont(font);
		add(send);
		send.setBounds(217, 505, 60, 40);
		send.setHorizontalAlignment(SwingConstants.CENTER);
		send.setVerticalAlignment(SwingConstants.CENTER);
		send.setForeground(Color.RED);
		send.setCursor(new Cursor(Cursor.HAND_CURSOR));
		send.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				RoomMsg msg = new RoomMsg(HandleInput.getRoom(),
						RoomMsg.ROOM_CHAT);
				msg.setChatContent(LoginController.getUser().getUserId() + ": "
						+ content.getText());
				LoginController.writeObject(msg);
				content.setText("");
				AudioPlayer.playButtonOne();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == send) {
					send.setForeground(Color.BLUE);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == send) {
					send.setForeground(Color.RED);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == send) {
					send.setForeground(Color.GREEN);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == send) {
					send.setForeground(Color.BLUE);
				}
			}

		});

		// 聊天框
		chat_content = new JTextArea();
		chat_content.setLineWrap(true);
		chat_content.setFont(font);
		chat_content.setForeground(Color.GREEN);
		jsp = new JScrollPane();
		jsp.getViewport().add(chat_content);
		jsp.getViewport().setOpaque(false);
		jsp.setOpaque(false);
		chat_content.setOpaque(false);
		chat_content.setLineWrap(true);
		add(jsp);
		jsp.setBounds(35, 51, 248, 450);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jsb = jsp.getVerticalScrollBar();
		jsb.setOpaque(false);
		jsb.setUI(new MyScrollBarUI(true));

		invinciblePanel.setVisible(false);
		add(invinciblePanel);
		invinciblePanel.setBounds(0, 0, 1103, 618);

		// 退出按键
		try {
			exit_bt = MyButton.makeButton(exit, exit_down, exit_hover);

			add(exit_bt);
			exit_bt.setBounds(35, 548, 70, 70);
			exit_bt.addActionListener(this);

			// 设置按键
			settings_bt = MyButton.makeButton(settings, settings_down,
					settings_down);
			add(settings_bt);
			settings_bt.setBounds(122, 548, 70, 70);
			settings_bt.addActionListener(this);

			// 帮助按键
			help_bt = MyButton.makeButton(help, help_down, help_down);
			add(help_bt);
			help_bt.setBounds(209, 548, 70, 70);
			help_bt.addActionListener(this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ready_go_lb = new JLabel(new ImageIcon(readyGoGif));
		add(ready_go_lb);
		ready_go_lb.setBounds(0, 0, 1103, 618);
		ready_go_lb.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AudioPlayer.playButtonOne();
		if (e.getSource() == settings_bt) {
			new SettingDialog(MainFrame.getOwner(), true, false);
		} else if (e.getSource() == help_bt) {
			new HelpDialog(MainFrame.getOwner(), true);
		} else if (e.getSource() == exit_bt) {
			LoginController.writeObject(new RoomMsg(HandleInput.getRoom(),
					RoomMsg.LEAVE_ROOM));
			HandleInput.changeToRoomPanel();
			MainFrame.showLoadingDialog();
		}
	}

	/**
	 * Adds the chat content.
	 * 
	 * @param content
	 *            the content
	 */
	public void addChatContent(String content) {
		chat_content.setText(chat_content.getText() + content);
		chat_content.selectAll();
	}

	/**
	 * Change chess state.
	 * 
	 * @param chess_state
	 *            the chess_state
	 */
	public void changeChessState(int[][][] chess_state) {
		chessView.changeChessState(chess_state);
	}

	/**
	 * Clear text.
	 */
	public void clearText() {
		chat_content.setText("");
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

	/**
	 * Sets the checks for hint.
	 * 
	 * @param hasHint
	 *            the new checks for hint
	 */
	public void setHasHint(boolean hasHint) {
		chessView.setHint(hasHint);
	}

	/**
	 * Sets the hints.
	 * 
	 * @param hints
	 *            the new hints
	 */
	public void setHints(ArrayList<Point> hints) {
		chessView.setHint(hints);
	}

	/**
	 * Sets the invincible.
	 * 
	 * @param invincible
	 *            the new invincible
	 */
	public void setInvincible(boolean invincible) {
		invinciblePanel.setVisible(invincible);
	}

	/**
	 * Sets the players.
	 * 
	 * @param room
	 *            the new players
	 */
	public void setPlayers(Room room) {
		int i = 0;
		for (String usr : room.getUserList()) {
			jlPlayer[i].setText(usr);
			i++;
			if (i > 3) {
				break;
			}
		}

		for (; i < 4; i++) {
			jlPlayer[i].setText("");
		}
	}

	/**
	 * Sets the rest time.
	 * 
	 * @param s
	 *            the new rest time
	 */
	public void setRestTime(String s) {
		jlRestTime.setText(s);
	}
	
	public void showReadyGo(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				ready_go_lb.setVisible(true);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ready_go_lb.setVisible(false);
			}
		}).start();
	}

	/**
	 * Sets the scores.
	 * 
	 * @param s
	 *            the new scores
	 */
	public void setScores(String s) {
		jlScores.setText(s);
	}
}
