/*
 * 
 */
package eliminate.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class MyTable.
 */
@SuppressWarnings("serial")
public class MyTable extends JTable {

	/**
	 * Instantiates a new my table.
	 * 
	 * @param tableModel
	 *            the table model
	 */
	
	public MyTable(){
		super();
	}
	
	public MyTable(DefaultTableModel tableModel) {
		super(tableModel);
		init();
	}

	/**
	 * Instantiates a new my table.
	 * 
	 * @param content
	 *            the content
	 * @param header
	 *            the header
	 */
	public MyTable(Object[][] content, Object[] header) {
		super(content, header);
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		getTableHeader().setVisible(false);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setPreferredSize(new Dimension(0, 0));
		getTableHeader().setDefaultRenderer(renderer);
		setRowMargin(3);
		setRowHeight(30);
		setEnabled(false);
		setBorder(BorderFactory.createEmptyBorder());
		setOpaque(false);
		// setBackground(new Color(0f, 0f, 0f, 0f));
		setShowGrid(false);
		setBorder(BorderFactory.createEmptyBorder());
	}
}
