/*
 * 
 */
package eliminate.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JTable;

import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.controller.login.LoginController;
import friend.FriendMsg;

// TODO: Auto-generated Javadoc
/**
 * The Class TableFriendAdapter.
 */
public class TableFriendAdapter extends MouseAdapter {

	/** The row. */
	private int row = -1;

	/** The column. */
	private int column = -1;

	/** The table. */
	private JTable table;

	/** The jd. */
	private JDialog jd;

	/**
	 * Instantiates a new table friend adapter.
	 * 
	 * @param table
	 *            the table
	 * @param jd
	 *            the jd
	 */
	public TableFriendAdapter(JTable table, JDialog jd) {
		this.table = table;
		this.jd = jd;
	}

	/**
	 * Checks if is rollover cell.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return true, if is rollover cell
	 */
	public boolean isRolloverCell(int row, int column) {
		return this.row == row && this.column == column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (row >= 0 && column == 1) {
			// 删除好友
			LoginController.writeObject(new FriendMsg(table.getValueAt(row, 0)
					.toString(), FriendMsg.DELETE_FRIEND));
			jd.dispose();
		} else if (row >= 0 && column == 2) {
			// 邀请好友
			String name = table.getValueAt(row, 0).toString();
			if(HandleInput.getRoom().getUserList().contains(name) && 
					(!table.getValueAt(0, 0).equals(HandleInput.getUser().getUserId()))){
				jd.dispose();
				MainFrame.showMsgDialog(name + "已经在房间内！", "邀请好友");
				return;
			};
			LoginController.writeObject(new FriendMsg(name, FriendMsg.INVITE_FRIEND));
			jd.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (row >= 0 && (column == 1 || column == 2)) {
			table.repaint(table.getCellRect(row, column, false));
		}
		row = column = -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		int lastRow = row;
		int lastColumn = column;

		row = table.rowAtPoint(e.getPoint());
		column = table.columnAtPoint(e.getPoint());

		if (row == lastRow && column == lastColumn)
			return;

		if (row >= 0 && (column == 1 || column == 2)) {
			table.repaint(table.getCellRect(row, column, false));
		}
		if (lastRow >= 0 && (lastColumn == 1 || lastColumn == 2)) {
			table.repaint(table.getCellRect(lastRow, lastColumn, false));
		}
	}

}
