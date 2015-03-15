/*
 * 
 */
package eliminate.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import room.RoomMsg;
import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.room.RoomList;

// TODO: Auto-generated Javadoc
/**
 * The Class TableRolloverAdapter.
 */
public class TableRolloverAdapter extends MouseAdapter {

	/** The row. */
	private int row = -1;

	/** The column. */
	private int column = -1;

	/** The table. */
	private JTable table;

	/**
	 * Instantiates a new table rollover adapter.
	 * 
	 * @param table
	 *            the table
	 */
	public TableRolloverAdapter(JTable table) {
		this.table = table;
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
	public void mouseClicked(MouseEvent arg0) {
		if (row >= 0 && column == 3) {
			LoginController.writeObject(new RoomMsg(RoomList
					.getRoom((Long) table.getModel().getValueAt(row, 0)),
					RoomMsg.ENTER_ROOM));
			MainFrame.showLoadingDialog();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (row >= 0 && column == 3) {
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

		if (row >= 0 && column == 3) {
			table.repaint(table.getCellRect(row, column, false));
		}
		if (lastRow >= 0 && lastColumn == 3) {
			table.repaint(table.getCellRect(lastRow, lastColumn, false));
		}
	}

}
