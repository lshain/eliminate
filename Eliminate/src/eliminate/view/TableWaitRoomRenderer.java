/*
 * 
 */
package eliminate.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class TableWaitRoomRenderer.
 */
public class TableWaitRoomRenderer implements TableCellRenderer {

	/** The adapter. */
	private TableWaitRoomAdapter adapter;

	/**
	 * Instantiates a new table wait room renderer.
	 * 
	 * @param adapter
	 *            the adapter
	 */
	public TableWaitRoomRenderer(TableWaitRoomAdapter adapter) {
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
		String a = (String) table.getModel().getValueAt(row, 0);
		if (a.equals("·¿Ö÷")) {
			button.getModel().setEnabled(false);
		}
		//
		// button.setBounds(0, 0, 55, 28);

		return button;
	}
}