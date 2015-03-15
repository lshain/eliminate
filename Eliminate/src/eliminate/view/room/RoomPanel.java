/*
 * 
 */
package eliminate.view.room;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.controller.login.LoginController;
import eliminate.model.room.RoomList;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyScrollBarUI;
import eliminate.view.MyTable;
import eliminate.view.TableButtonRenderer;
import eliminate.view.TableLabelRenderer;
import eliminate.view.TableRolloverAdapter;
import eliminate.view.dialogs.HelpDialog;
import eliminate.view.dialogs.SettingDialog;
import eliminate.view.start.MainPanel;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomPanel.
 */
@SuppressWarnings("serial")
public class RoomPanel extends JPanel implements ActionListener, Observer {

	/** The background_pic. */
	private String background_pic = "media/image/roomChoose/background.jpg";

	// 退出按键
	/** The exit. */
	private String exit = "media/image/all/back.png";

	/** The exit_down. */
	private String exit_down = "media/image/all/back_hover.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/all/back_hover.png";

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

	/** The cooperate_game. */
	private String cooperate_game = "media/image/roomChoose/bt/cooperate.png";

	/** The cooperate_down. */
	private String cooperate_down = "media/image/roomChoose/bt/cooperate_down.png";

	/** The cooperate_hover. */
	private String cooperate_hover = "media/image/roomChoose/bt/cooperate_hover.png";

	/** The cooperate bt. */
	MyButton cooperateBt;

	/** The against_game. */
	private String against_game = "media/image/roomChoose/bt/against.png";

	/** The against_down. */
	private String against_down = "media/image/roomChoose/bt/against_down.png";

	/** The against_hover. */
	private String against_hover = "media/image/roomChoose/bt/against_hover.png";

	/** The against bt. */
	MyButton againstBt;

	/** The create room. */
	private String createRoom = "media/image/roomChoose/bt/create_room.png";

	/** The create room_down. */
	private String createRoom_down = "media/image/roomChoose/bt/create_room_down.png";

	/** The create room_hover. */
	private String createRoom_hover = "media/image/roomChoose/bt/create_room_hover.png";

	/** The create room bt. */
	MyButton createRoomBt;

	// 管理好友
	/** The friend. */
	private String friend = "media/image/friends/friend_bt.png";

	/** The friend_hover. */
	private String friend_hover = "media/image/friends/friend_bt_hover.png";

	// private String enter = "media/image/roomChoose/bt/enter.png";
	// private String enter_down = "media/image/roomChoose/bt/enter_down.png";
	// private String enter_hover = "media/image/roomChoose/bt/enter_hover.png";
	// private String enter_disable =
	// "media/image/roomChoose/bt/enter_disable.png";
	// MyButton enterBt;

	/** The friend_bt. */
	MyButton friend_bt;

	/** The jsp table. */
	private JScrollPane jspTable = new JScrollPane();

	/** The table data. */
	Object[][] tableData;

	/**
	 * Instantiates a new room panel.
	 */
	public RoomPanel() {

		// addMouseListener(new MouseAdapter(){
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// System.out.println(e.getPoint());
		// }
		// });
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AudioPlayer.playButtonOne();
		if (e.getSource().equals(againstBt) && cooperateBt.getSeleted()) {
			againstBt.setSelected(true);
			cooperateBt.setSelected(false);
			tableData = HandleInput.getRooms().getTableData2();
			setTableData(tableData);
		} else if (e.getSource().equals(cooperateBt) && againstBt.getSeleted()) {
			againstBt.setSelected(false);
			cooperateBt.setSelected(true);
			tableData = HandleInput.getRooms().getTableData1();
			setTableData(tableData);
		} else if (e.getSource().equals(createRoomBt)) {
			new NewRoomDialog(MainFrame.getOwner(), true);
		} else if (e.getSource().equals(settings_bt)) {
			new SettingDialog(MainFrame.getOwner(), true, false);
		} else if (e.getSource().equals(help_bt)) {
			new HelpDialog(MainFrame.getOwner(), true);
		} else if (e.getSource().equals(exit_bt)) {
			MainFrame.setContentPanel(new MainPanel());
		} else if (e.getSource().equals(friend_bt)) {
			LoginController.writeObject(new FriendMsg(LoginController.getUser()
					.getUserId(), FriendMsg.ALL_FRIEND));
		}
	}

	/**
	 * Inits the.
	 */
	private void init() {
		try {
			setLayout(null);
			// 退出按键
			exit_bt = MyButton.makeButton(exit, exit_down, exit_hover);
			add(exit_bt);
			exit_bt.setBounds(30, 549, 65, 65);
			exit_bt.addActionListener(this);

			// 设置按键
			settings_bt = MyButton.makeButton(settings, settings_down,
					settings_down);
			add(settings_bt);
			settings_bt.setBounds(120, 549, 65, 65);
			settings_bt.addActionListener(this);

			// 帮助按键
			help_bt = MyButton.makeButton(help, help_down, help_down);
			add(help_bt);
			help_bt.setBounds(210, 549, 65, 65);
			help_bt.addActionListener(this);

			// 管理好友按键
			friend_bt = MyButton.makeButton(friend, friend, friend_hover);
			add(friend_bt);
			friend_bt.setBounds(300, 549, 65, 65);
			friend_bt.addActionListener(this);

			//
			cooperateBt = MyButton.makeButton(cooperate_game, cooperate_down,
					cooperate_hover);
			add(cooperateBt);
			cooperateBt.setBounds(5, 5, 179, 45);
			cooperateBt.addActionListener(this);
			cooperateBt.setSelected(true);

			againstBt = MyButton.makeButton(against_game, against_down,
					against_hover);
			add(againstBt);
			againstBt.setBounds(184, 5, 175, 45);
			againstBt.addActionListener(this);

			jspTable.setBorder(null);
			jspTable.getVerticalScrollBar().setUI(new MyScrollBarUI(false));

			jspTable.setBounds(6, 96, 355, 420);
			jspTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			jspTable.getVerticalScrollBar().setOpaque(false);
			jspTable.setBorder(BorderFactory.createEmptyBorder());
			jspTable.getViewport().setOpaque(false);
			jspTable.setOpaque(false);
			add(jspTable);

			createRoomBt = MyButton.makeButton(createRoom, createRoom_down,
					createRoom_hover);
			add(createRoomBt);
			createRoomBt.setBounds(260, 497, 100, 50);
			createRoomBt.addActionListener(this);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

	/**
	 * Sets the table data.
	 * 
	 * @param tableData
	 *            the new table data
	 */
	public void setTableData(Object[][] tableData) {
		Object[] tableHeader = { "", "", "", "" };
		MyTable table = new MyTable(tableData, tableHeader);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(68);

		table.getColumnModel().getColumn(1)
				.setCellRenderer(new TableLabelRenderer(true));
		table.getColumnModel().getColumn(2)
				.setCellRenderer(new TableLabelRenderer(false));

		TableRolloverAdapter adapter = new TableRolloverAdapter(table);
		table.addMouseListener(adapter);
		table.addMouseMotionListener(adapter);
		table.getColumnModel().getColumn(3)
				.setCellRenderer(new TableButtonRenderer(adapter));
		table.setRowHeight(36);
		// table.getColumnModel().getColumn(3).setCellEditor(new
		// TableButtonCellEditor());
		jspTable.setViewportView(table);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof RoomList) {
			if (cooperateBt.getSeleted()) {
				tableData = ((RoomList) arg1).getTableData1();
			} else if (againstBt.getSeleted()) {
				tableData = ((RoomList) arg1).getTableData2();
			}
			setTableData(tableData);
			System.out.println("Watch RoomList: " + tableData);
		}
	}
}
