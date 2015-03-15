/*
 * 
 */
package eliminate.view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class TableFightRoomRenderer.
 */
public class TableFightRoomRenderer implements TableCellRenderer {

	/** The adapter. */
	private TableFightRoomAdapter adapter;

	/**
	 * Instantiates a new table fight room renderer.
	 * 
	 * @param adapter
	 *            the adapter
	 */
	public TableFightRoomRenderer(TableFightRoomAdapter adapter) {
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
		int length = table.getRowCount();
		int[] team = { 0, 0 };
		for (int i = 0; i < length; i++) {
			String temp = (String) table.getModel().getValueAt(i, 1);
			if (temp.equals("1")) {
				team[0]++;
			} else {
				team[1]++;
			}
		}
		String s = (String) table.getModel().getValueAt(row, 1);
		if (team[0] == 2 && s.equals("2") && column == 3) {
			button.getModel().setEnabled(false);
		} else if (team[1] == 2 && s.equals("1") && column == 3) {
			button.getModel().setEnabled(false);
		}

		String a = (String) table.getModel().getValueAt(row, 0);
		if (a.equals("·¿Ö÷") && column == 4) {
			button.getModel().setEnabled(false);
		}
		//
		// button.setBounds(0, 0, 55, 28);

		return button;
	}
}