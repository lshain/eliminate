/*
 * 
 */
package eliminate.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class TableFriendRenderer.
 */
public class TableFriendRenderer implements TableCellRenderer {

	/** The adapter. */
	private TableFriendAdapter adapter;

	/**
	 * Instantiates a new table friend renderer.
	 * 
	 * @param adapter
	 *            the adapter
	 */
	public TableFriendRenderer(TableFriendAdapter adapter) {
		this.adapter = adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax
	 * .swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		MyButton button = (MyButton) value;

		if (isSelected) {
			button.getModel().setArmed(true);
		} else if (adapter.isRolloverCell(row, column)) {
			button.getModel().setRollover(adapter.isRolloverCell(row, column));
		}
		//
		// button.setBounds(0, 0, 55, 28);

		return button;
	}
}