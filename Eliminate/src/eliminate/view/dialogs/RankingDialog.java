/*
 * 
 */
package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import rank.RankMsg;
import user.User;
import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.LineChart;
import eliminate.view.MyButton;
import eliminate.view.MyScrollBarUI;
import eliminate.view.MyTableModel;
import eliminate.view.TableLabelRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class RankingDialog.
 */
@SuppressWarnings("serial")
public class RankingDialog {

	/**
	 * The Class RankingPanel.
	 */
	class RankingPanel extends JPanel implements MouseListener, ActionListener {

		/**
		 * Instantiates a new ranking panel.
		 */
		public RankingPanel() {
			try {
				font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
						.deriveFont(20f));
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setLayout(null);

			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println(e.getX() + " " + e.getY());
				}
			});

			// �˳�����
			try {
				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(780, 65, 33, 33);
				jbExit.addActionListener(this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// �ܾ���
			total = 28;
			total_game = new JLabel(Integer.toString(total));
			total_game.setFont(font);
			total_game.setHorizontalAlignment(SwingConstants.CENTER);
			total_game.setVerticalAlignment(SwingConstants.CENTER);
			total_game.setForeground(Color.BLUE);
			add(total_game);
			total_game.setBounds(200, 148, 80, 20);
			// ƽ���÷�
			average = 300000;
			average_score = new JLabel(Integer.toString(average));
			average_score.setFont(font);
			average_score.setHorizontalAlignment(SwingConstants.CENTER);
			average_score.setVerticalAlignment(SwingConstants.CENTER);
			average_score.setForeground(Color.BLUE);
			add(average_score);
			average_score.setBounds(370, 148, 100, 20);
			// ��ߵ÷�
			score = 400000;
			top_score = new JLabel(Integer.toString(score));
			top_score.setFont(font);
			top_score.setHorizontalAlignment(SwingConstants.CENTER);
			top_score.setVerticalAlignment(SwingConstants.CENTER);
			top_score.setForeground(Color.BLUE);
			add(top_score);
			top_score.setBounds(525, 148, 100, 20);
			// �������
			combo = 30;
			top_combo = new JLabel(Integer.toString(combo));
			top_combo.setFont(font);
			top_combo.setHorizontalAlignment(SwingConstants.CENTER);
			top_combo.setVerticalAlignment(SwingConstants.CENTER);
			top_combo.setForeground(Color.BLUE);
			add(top_combo);
			top_combo.setBounds(710, 148, 100, 20);

			// �վ���
			daily_times = new JLabel("�վ�������");
			daily_times.setFont(font);
			daily_times.setHorizontalAlignment(SwingConstants.CENTER);
			daily_times.setVerticalAlignment(SwingConstants.CENTER);
			daily_times.setForeground(Color.WHITE);
			add(daily_times);
			daily_times.setBounds(115, 180, 170, 32);
			is_times_click = false;
			daily_times.addMouseListener(this);

			// �յ÷�
			daily_score = new JLabel("�յ÷�����");
			daily_score.setFont(font);
			daily_score.setHorizontalAlignment(SwingConstants.CENTER);
			daily_score.setVerticalAlignment(SwingConstants.CENTER);
			daily_score.setForeground(Color.WHITE);
			add(daily_score);
			daily_score.setBounds(285, 180, 170, 32);
			is_score_click = false;
			daily_score.addMouseListener(this);

			// ÿ�ֵ÷�
			each_game = new JLabel("ÿ�ֵ÷�");
			each_game.setFont(font);
			each_game.setHorizontalAlignment(SwingConstants.CENTER);
			each_game.setVerticalAlignment(SwingConstants.CENTER);
			each_game.setForeground(Color.RED);
			add(each_game);
			each_game.setBounds(455, 180, 170, 32);
			is_game_click = true;
			each_game.addMouseListener(this);

			// ��������
			multiplayer_ranking = new JLabel("��������");
			multiplayer_ranking.setFont(font);
			multiplayer_ranking.setHorizontalAlignment(SwingConstants.CENTER);
			multiplayer_ranking.setVerticalAlignment(SwingConstants.CENTER);
			multiplayer_ranking.setForeground(Color.RED);
			add(multiplayer_ranking);
			multiplayer_ranking.setBounds(625, 180, 170, 32);
			is_ranking_click = false;
			multiplayer_ranking.addMouseListener(this);

			resetLabel();
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
			// �˳���ť
			if (e.getSource() == jbExit) {
				jd.setVisible(false);
			}
		}

		// ��ʼ��ÿ������
		// data[][4]�ֱ�Ϊ�꣬�£��գ�����/����,type=0Ϊÿ�վ�����1Ϊÿ��ƽ���÷�
		/**
		 * Inits the chart panel.
		 * 
		 * @param data
		 *            the data
		 * @param type
		 *            the type
		 */
		public void initChartPanel(int[][] data, int type) {
			if (chart != null) {
				remove(chart);
			}
			if (game_detail != null) {
				remove(jsp);
			}
			chart = new LineChart(data, type).getChartPanel();
			add(chart);
			chart.setForeground(Color.BLACK);
			;
			chart.setBounds(125, 225, 665, 240);
			chart.setOpaque(false);
			this.repaint();
		}

		// ��ʼ��ÿ�ֵ÷�
		/**
		 * Inits the game detail.
		 * 
		 * @param data
		 *            the data
		 * @param column
		 *            the column
		 */
		public void initGameDetail(String[][] data, String[] column) {
			if (game_detail != null) {
				remove(jsp);
			}
			if (chart != null) {
				remove(chart);
			}
			game_detail = new JTable(new MyTableModel(data, column));
			game_detail.setAutoCreateRowSorter(true);
			game_detail.setRowMargin(2);
			game_detail.setRowHeight(50);
			game_detail.setShowGrid(true);
			game_detail.setGridColor(Color.BLACK);
			game_detail.setOpaque(false);

			game_detail.getTableHeader().setOpaque(false);
			game_detail.getTableHeader().setBackground(Color.WHITE);
			game_detail.getTableHeader().setFont(font);
			game_detail.getTableHeader().setForeground(Color.BLACK);

			int size = game_detail.getColumnModel().getColumnCount();
			TableLabelRenderer tlr = new TableLabelRenderer(true);
			for (int i = 0; i < size; i++) {
				game_detail.getColumnModel().getColumn(i).setCellRenderer(tlr);
			}

			jsp = new JScrollPane(game_detail);
			jsp.setViewportView(game_detail);
			jsp.setOpaque(false);
			jsp.getViewport().setOpaque(false);
			JScrollBar jsb = jsp.getVerticalScrollBar();
			jsb.setOpaque(false);
			jsb.setUI(new MyScrollBarUI(true));

			add(jsp);
			jsp.setBounds(125, 225, 665, 240);
			this.repaint();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			AudioPlayer.playButtonOne();
			if (e.getSource() == daily_times && !is_times_click) {
				daily_times.setForeground(Color.RED);
				is_times_click = true;
				is_score_click = false;
				is_game_click = false;
				is_ranking_click = false;
				resetLabel();
				LoginController.writeObject(new RankMsg(RankMsg.DAILYTIMES,
						null));
			} else if (e.getSource() == daily_score && !is_score_click) {
				daily_score.setForeground(Color.RED);
				is_times_click = false;
				is_score_click = true;
				is_game_click = false;
				is_ranking_click = false;
				resetLabel();
				LoginController.writeObject(new RankMsg(RankMsg.DAILYASCORE,
						null));
			} else if (e.getSource() == each_game && !is_game_click) {
				each_game.setForeground(Color.RED);
				is_times_click = false;
				is_score_click = false;
				is_game_click = true;
				is_ranking_click = false;
				resetLabel();
				LoginController.writeObject(new RankMsg(RankMsg.GAME_DETAIL,
						null));
			} else if (e.getSource() == multiplayer_ranking
					&& !is_ranking_click) {
				multiplayer_ranking.setForeground(Color.RED);
				is_times_click = false;
				is_score_click = false;
				is_game_click = false;
				is_ranking_click = true;
				resetLabel();
				LoginController.writeObject(new RankMsg(null));
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == daily_times && !is_times_click) {
				daily_times.setForeground(Color.GREEN);
			} else if (e.getSource() == daily_score && !is_score_click) {
				daily_score.setForeground(Color.GREEN);
			} else if (e.getSource() == each_game && !is_game_click) {
				each_game.setForeground(Color.GREEN);
			} else if (e.getSource() == multiplayer_ranking
					&& !is_ranking_click) {
				multiplayer_ranking.setForeground(Color.GREEN);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == daily_times && !is_times_click) {
				daily_times.setForeground(Color.WHITE);
			} else if (e.getSource() == daily_score && !is_score_click) {
				daily_score.setForeground(Color.WHITE);
			} else if (e.getSource() == each_game && !is_game_click) {
				each_game.setForeground(Color.WHITE);
			} else if (e.getSource() == multiplayer_ranking
					&& !is_ranking_click) {
				multiplayer_ranking.setForeground(Color.WHITE);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == daily_times && !is_times_click) {
				daily_times.setForeground(Color.RED);
			} else if (e.getSource() == daily_score && !is_score_click) {
				daily_score.setForeground(Color.RED);
			} else if (e.getSource() == each_game && !is_game_click) {
				each_game.setForeground(Color.RED);
			} else if (e.getSource() == multiplayer_ranking
					&& !is_ranking_click) {
				multiplayer_ranking.setForeground(Color.RED);
			}
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

		// ����ÿ�յ÷����ߡ�ÿ�վ������ߡ�ÿ�ֵ÷�3��Label
		/**
		 * Reset label.
		 */
		private void resetLabel() {
			if (is_times_click) {
				daily_times.removeMouseListener(this);
			} else {
				daily_times.addMouseListener(this);
				daily_times.setForeground(Color.WHITE);
			}
			if (is_score_click) {
				daily_score.removeMouseListener(this);
			} else {
				daily_score.addMouseListener(this);
				daily_score.setForeground(Color.WHITE);
			}
			if (is_game_click) {
				each_game.removeMouseListener(this);
			} else {
				each_game.addMouseListener(this);
				each_game.setForeground(Color.WHITE);
			}

			if (is_ranking_click) {
				multiplayer_ranking.removeMouseListener(this);
			} else {
				multiplayer_ranking.addMouseListener(this);
				multiplayer_ranking.setForeground(Color.WHITE);
			}
		}

	}

	/** The jd. */
	JDialog jd;

	/** The panel. */
	RankingPanel panel;

	// ����ͼƬ��ַ
	/** The background_pic. */
	private String background_pic = "media/image/dialog/rank/ranking.png";

	// �˳���ť
	/** The exit. */
	private String exit = "media/image/dialog/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/dialog/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/dialog/exit_hover.png";

	/** The jb exit. */
	JButton jbExit;

	// �ܾ���
	/** The total_game. */
	private JLabel total_game;

	/** The total. */
	private int total;

	// ƽ���÷�
	/** The average_score. */
	private JLabel average_score;

	/** The average. */
	private int average;

	// ��߷�
	/** The top_score. */
	private JLabel top_score;

	/** The score. */
	private int score;

	// ���������
	/** The top_combo. */
	private JLabel top_combo;

	/** The combo. */
	private int combo;
	// ����
	/** The font. */
	private Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	// ͼ��
	/** The chart. */
	private JPanel chart = null;

	// �վ�������
	/** The daily_times. */
	private JLabel daily_times;

	/** The is_times_click. */
	private boolean is_times_click;

	// �վ�������
	/** The daily_score. */
	private JLabel daily_score;

	/** The is_score_click. */
	private boolean is_score_click;

	// ÿ�ֵ÷�
	/** The each_game. */
	private JLabel each_game;

	/** The is_game_click. */
	private boolean is_game_click;

	// ��������
	/** The multiplayer_ranking. */
	private JLabel multiplayer_ranking;

	/** The is_ranking_click. */
	private boolean is_ranking_click;

	// table����
	/** The game_detail. */
	private JTable game_detail = null;

	/** The jsp. */
	private JScrollPane jsp;

	// test
	// private int[][] test =
	// {{2014,5,1,3},{2014,5,5,5},{2014,5,9,9},{2014,5,11,5},{2014,5,22,2},{2014,5,24,7}};
	/** The column. */
	String[] column = { "����", "����", "����" };

	/** The rank_column. */
	String[] rank_column = { "����", "���", "�÷�" };

	// String[][] d =
	// {{"1","abc,def","123124"},{"2","asdfe,def","12123"},{"3","abc,def","123124"},{"4","abc,def","1few"},{"5","abc,def","123124"},{"6","abc,def","123124"},{"7","abc,def","123124"}};
	// String[][] data = {{"2014��5��1��","1","1"},{"2014��5��1��","1","2"},
	// {"2014��5��1��","2","1"},{"2014��5��2��","4","3"},{"2014��6��1��","8","5"}};
	/**
	 * Instantiates a new ranking dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public RankingDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		panel = new RankingPanel();
		jd.setContentPane(panel);
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(912, 513);
		jd.setLocationRelativeTo(owner);
		jd.setVisible(false);
	}

	/**
	 * Hide.
	 */
	public void hide() {
		jd.setVisible(false);
	}

	// ����ƽ���÷�
	/**
	 * Sets the average.
	 * 
	 * @param a
	 *            the new average
	 */
	private void setAverage(int a) {
		average = a;
		average_score.setText(Integer.toString(average));
		average_score.repaint();
	}

	// �������������
	/**
	 * Sets the top combo.
	 * 
	 * @param c
	 *            the new top combo
	 */
	private void setTopCombo(int c) {
		combo = c;
		top_combo.setText(Integer.toString(combo));
		top_combo.repaint();
	}

	// ������߷�
	/**
	 * Sets the top score.
	 * 
	 * @param t
	 *            the new top score
	 */
	private void setTopScore(int t) {
		score = t;
		top_score.setText(Integer.toString(score));
		top_score.repaint();
	}

	// �����ܾ���
	/**
	 * Sets the total game.
	 * 
	 * @param t
	 *            the new total game
	 */
	private void setTotalGame(int t) {
		total = t;
		total_game.setText(Integer.toString(total));
		total_game.repaint();
	}

	/**
	 * Sets the user info.
	 * 
	 * @param user
	 *            the new user info
	 */
	public void setUserInfo(User user) {
		if (user != null) {
			setTotalGame(user.getTotal());
			setAverage(user.getAverage());
			setTopScore(user.getScore());
			setTopCombo(user.getCombo());
		}
	}

	/**
	 * Show.
	 */
	public void show() {
		is_times_click = false;
		is_score_click = false;
		is_game_click = true;
		is_ranking_click = true;
		panel.resetLabel();
		jd.setLocation(MainFrame.getOwner().getLocation());
		jd.setVisible(true);
	}

	/**
	 * Show dialy score.
	 * 
	 * @param data
	 *            the data
	 */
	public void showDialyScore(int[][] data) {
		panel.initChartPanel(data, 1);
	}

	/**
	 * Show dialy time.
	 * 
	 * @param data
	 *            the data
	 */
	public void showDialyTime(int[][] data) {
		panel.initChartPanel(data, 0);
	}

	/**
	 * Show single score.
	 * 
	 * @param data
	 *            the data
	 */
	public void showSingleScore(String[][] data) {
		panel.initGameDetail(data, column);
	}

	/**
	 * Show team rank.
	 * 
	 * @param data
	 *            the data
	 */
	public void showTeamRank(String[][] data) {
		panel.initGameDetail(data, rank_column);
	}

}
