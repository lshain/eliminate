/*
 * 
 */
package eliminate.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class TableButtonRenderer.
 */
public class TableButtonRenderer implements TableCellRenderer {

	/** The adapter. */
	private TableRolloverAdapter adapter;

	/**
	 * Instantiates a new table button renderer.
	 * 
	 * @param adapter
	 *            the adapter
	 */
	public TableButtonRenderer(TableRolloverAdapter adapter) {
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
		String[] num = ((String) table.getModel().getValueAt(row, 1))
				.split("/");
		if (Integer.parseInt(num[0]) == Integer.parseInt(num[1])) {
			button.getModel().setEnabled(false);
		}
		//
		// button.setBounds(0, 0, 55, 28);

		return button;
	}
}