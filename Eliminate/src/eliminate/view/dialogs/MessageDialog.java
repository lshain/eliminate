package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;

@SuppressWarnings("serial")
public class MessageDialog {
	class MessagePanel extends JPanel implements ActionListener {
		public MessagePanel() {
			try {
				this.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println(e.getX() + "   " + e.getY());
					}
				});

				setLayout(null);

				try {
					font = (Font.createFont(Font.TRUETYPE_FONT, new File(
							fontPath)).deriveFont(25f));
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// title
				title_msg = new JLabel("adsjfewif");
				title_msg.setFont(font);
				title_msg.setHorizontalAlignment(SwingConstants.CENTER);
				title_msg.setVerticalAlignment(SwingConstants.CENTER);
				title_msg.setForeground(Color.GREEN);
				add(title_msg);
				title_msg.setBounds(350, 115, 180, 50);

				try {
					font = (Font.createFont(Font.TRUETYPE_FONT, new File(
							fontPath)).deriveFont(30f));
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 内容
				content_msg = new JTextArea("asdfe");
				content_msg.setFont(font);
				content_msg.setLineWrap(true);
				content_msg.setEditable(false);
				content_msg.setOpaque(false);
				content_msg.setForeground(Color.WHITE);
				add(content_msg);
				content_msg.setBounds(295, 185, 270, 140);

				// 取消按键
				ok_bt = MyButton.makeButton(ok, ok_down, ok_hover);
				add(ok_bt);
				ok_bt.setBounds(365, 355, 150, 40);
				ok_bt.addActionListener(this);
				setOpaque(false);
				;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer.playButtonOne();
			if (e.getSource() == ok_bt) {
				jd.dispose();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon(background_pic).getImage(), 0, 0,
					this.getWidth(), this.getHeight(), this);
		}
	}

	JDialog jd;

	private String background_pic = "media/image/all/blank_dialog.png";

	// 退出按钮
	private String ok = "media/image/all/ok.png";

	private String ok_hover = "media/image/all/ok_hover.png";

	private String ok_down = "media/image/all/ok_down.png";

	JButton ok_bt;

	// 字体
	private Font font;

	private String fontPath = "media/font/font.ttf";
	// 标题
	private JLabel title_msg;
	// 内容
	private JTextArea content_msg;
	public MessageDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(912, 513);
		jd.setLocation(owner.getLocation().x + 100, owner.getLocation().y + 50);
	}

	public void closeThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3050);
					jd.dispose();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}
	public void setContent(String content) {
		content_msg.setText(content);
		content_msg.repaint();
	}

	public void setTitle(String title) {
		title_msg.setText(title);
		title_msg.repaint();
	}
	public void visible(String title, String msg) {
		jd.setContentPane(new MessagePanel());
		String s = "";
		if (msg.length() < 9) {
			for (int i = 0; i < 9 - msg.length(); i++) {
				s = s + " ";
			}
		}
		s = s + msg;
		setTitle(title);
		setContent(s);
		// closeThread();
		jd.setVisible(true);
	}
}
