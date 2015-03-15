/*
 * 
 */
package eliminate.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

// TODO: Auto-generated Javadoc
/**
 * The Class TableFightRoomAdapter.
 */
public class TableFightRoomAdapter extends MouseAdapter {

	/** The row. */
	private int row = -1;

	/** The column. */
	private int column = -1;

	/** The table. */
	private JTable table;

	/**
	 * Instantiates a new table fight room adapter.
	 * 
	 * @param table
	 *            the table
	 */
	public TableFightRoomAdapter(JTable table) {
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
	public void mouseClicked(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (row >= 0 && (column == 3 || column == 4)) {
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

		if (row >= 0 && (column == 3 || column == 4)) {
			table.repaint(table.getCellRect(row, column, false));
		}
		if (lastRow >= 0 && (lastColumn == 3 || lastColumn == 4)) {
			table.repaint(table.getCellRect(lastRow, lastColumn, false));
		}
	}

}
