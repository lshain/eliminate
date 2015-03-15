/*
 * 
 */
package eliminate.view.fight;

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
 * The Class FightGame.
 */
@SuppressWarnings("serial")
public class FightGame extends JPanel implements ActionListener {

	/** The background. */
	private String background = "media/image/fightGame/background.jpg";

	// 字体
	/** The font. */
	private static Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	// 聊天文字
	/** The content. */
	private JTextField content;

	/** The chat_content. */
	private JTextArea chat_content;

	/** The jsp. */
	private JScrollPane jsp;

	/** The jsb. */
	private JScrollBar jsb;

	/** The send. */
	private JLabel send;

	/** The chess view. */
	BigChessView chessView;

	/** The small chess view. */
	SmallChessView smallChessView;

	/** The big invincible panel. */
	JPanel bigInvinciblePanel = new InvinciblePanel();

	/** The small invincible panel. */
	JPanel smallInvinciblePanel = new InvinciblePanel();

	/** The my time. */
	JLabel myTime = new JLabel("60");

	/** The my score. */
	JLabel myScore = new JLabel("1000000");

	/** The other time. */
	JLabel otherTime = new JLabel("60");

	/** The other score. */
	JLabel otherScore = new JLabel("1000000");

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
	JButton help_bt;
	
	private String readyGoGif = "media/image/all/ready_go.png";
	
	JLabel ready_go_lb;

	/**
	 * Instantiates a new fight game.
	 */
	public FightGame() {
		this.setLayout(null);

		// this.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent arg0) {
		// System.out.println(arg0.getPoint());
		// }
		// });

		chessView = new BigChessView();
		this.add(chessView);
		chessView.setBounds(280, 50, 486, 513);

		smallChessView = new SmallChessView();
		this.add(smallChessView);
		smallChessView.setBounds(797, 274, 280, 290);

		// ******************聊天部分***************
		// 输入聊天文字
		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(20f));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		content = new JTextField();
		add(content);
		content.setBounds(10, 505, 168, 40);
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
		send = new JLabel("发送");
		send.setFont(font);
		add(send);
		send.setBounds(180, 505, 62, 40);
		send.setHorizontalAlignment(SwingConstants.CENTER);
		send.setVerticalAlignment(SwingConstants.CENTER);
		send.setForeground(Color.RED);
		send.setCursor(new Cursor(Cursor.HAND_CURSOR));
		send.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				// 发送消息
				RoomMsg msg = new RoomMsg(HandleInput.getRoom(),
						RoomMsg.ROOM_CHAT);
				msg.setChatContent(LoginController.getUser().getUserId() + ": "
						+ content.getText());
				LoginController.writeObject(msg);
				content.setText("");
				AudioPlayer.playButtonOne();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				send.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				send.setForeground(Color.RED);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				send.setForeground(Color.GREEN);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				send.setForeground(Color.BLUE);
			}

		});

		// 聊天框
		chat_content = new JTextArea();
		// chat_content.setText("a\nb\nd\ns\nf\nw\ne\nf\nns\nd\nf\newf\nassdfwe\na\nb\nc\nd\ne\nf\ng\nh\ni\ng");
		chat_content.setFont(font);
		chat_content.setForeground(Color.GREEN);
		chat_content.setLineWrap(true);
		jsp = new JScrollPane();
		jsp.getViewport().add(chat_content);
		jsp.getViewport().setOpaque(false);
		jsp.setOpaque(false);
		chat_content.setOpaque(false);
		add(jsp);
		jsp.setBounds(10, 50, 235, 450);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jsb = jsp.getVerticalScrollBar();
		jsb.setOpaque(false);
		jsb.setUI(new MyScrollBarUI(true));

		bigInvinciblePanel.setVisible(false);
		add(bigInvinciblePanel);
		bigInvinciblePanel.setBounds(-215, 20, 1043, 583);

		smallInvinciblePanel.setVisible(false);
		add(smallInvinciblePanel);
		smallInvinciblePanel.setBounds(508, 250, 600, 338);

		add(myTime);
		myTime.setFont(font);
		myTime.setForeground(Color.WHITE);
		myTime.setHorizontalAlignment(SwingConstants.CENTER);
		myTime.setVerticalAlignment(SwingConstants.CENTER);
		myTime.setBounds(789, 45, 146, 82);

		add(myScore);
		myScore.setFont(font);
		myScore.setForeground(Color.WHITE);
		myScore.setHorizontalAlignment(SwingConstants.CENTER);
		myScore.setVerticalAlignment(SwingConstants.CENTER);
		myScore.setBounds(789, 130, 146, 126);

		otherTime.setBounds(935, 45, 136, 82);
		otherTime.setFont(font);
		otherTime.setForeground(Color.WHITE);
		otherTime.setHorizontalAlignment(SwingConstants.CENTER);
		otherTime.setVerticalAlignment(SwingConstants.CENTER);
		add(otherTime);
		otherScore.setFont(font);
		otherScore.setForeground(Color.WHITE);
		otherScore.setHorizontalAlignment(SwingConstants.CENTER);
		otherScore.setVerticalAlignment(SwingConstants.CENTER);
		add(otherScore);
		otherScore.setBounds(935, 130, 136, 126);

		// 退出按键
		try {
			exit_bt = MyButton.makeButton(exit, exit_down, exit_hover);

			add(exit_bt);
			exit_bt.setBounds(15, 548, 70, 70);
			exit_bt.addActionListener(this);

			// 设置按键
			settings_bt = MyButton.makeButton(settings, settings_down,
					settings_down);
			add(settings_bt);
			settings_bt.setBounds(102, 548, 70, 70);
			settings_bt.addActionListener(this);

			// 帮助按键
			help_bt = MyButton.makeButton(help, help_down, help_down);
			add(help_bt);
			help_bt.setBounds(189, 548, 70, 70);
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
	
	public void frozenBig(int type){
		chessView.frozen(type);
	}
	
	public void frozenSmall(int type){
		smallChessView.frozen(type);
	}

	/**
	 * Change big chess state.
	 * 
	 * @param chess_state
	 *            the chess_state
	 */
	public void changeBigChessState(int[][][] chess_state) {
		chessView.changeChessState(chess_state);
	}

	/**
	 * Change small chess state.
	 * 
	 * @param chess_state
	 *            the chess_state
	 */
	public void changeSmallChessState(int[][][] chess_state) {
		smallChessView.changeChessState(chess_state);
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
		g.drawImage(new ImageIcon(background).getImage(), 0, 0,
				this.getWidth(), this.getHeight(), this);
	}

	/**
	 * Sets the big invincible.
	 * 
	 * @param invincible
	 *            the new big invincible
	 */
	public void setBigInvincible(boolean invincible) {
		bigInvinciblePanel.setVisible(invincible);
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
	 * Sets the my score.
	 * 
	 * @param score
	 *            the new my score
	 */
	public void setMyScore(int score) {
		myScore.setText(Integer.toString(score));
	}

	/**
	 * Sets the my time.
	 * 
	 * @param time
	 *            the new my time
	 */
	public void setMyTime(int time) {
		myTime.setText(Integer.toString(time));
	}

	/**
	 * Sets the other score.
	 * 
	 * @param score
	 *            the new other score
	 */
	public void setOtherScore(int score) {
		otherScore.setText(Integer.toString(score));
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
	 * Sets the other time.
	 * 
	 * @param time
	 *            the new other time
	 */
	public void setOtherTime(int time) {
		otherTime.setText(Integer.toString(time));
	}

	/**
	 * Sets the small invincible.
	 * 
	 * @param invincible
	 *            the new small invincible
	 */
	public void setSmallInvincible(boolean invincible) {
		smallInvinciblePanel.setVisible(invincible);
	}
}
