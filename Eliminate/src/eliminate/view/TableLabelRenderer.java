/*
 * 
 */
package eliminate.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class TableLabelRenderer.
 */
public class TableLabelRenderer implements TableCellRenderer {

	/** The font. */
	private static Font font;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	/** The colorful. */
	private boolean colorful;

	/**
	 * Instantiates a new table label renderer.
	 * 
	 * @param colorful
	 *            the colorful
	 */
	public TableLabelRenderer(boolean colorful) {
		super();
		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(20f));

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.colorful = colorful;
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
		if (value instanceof String) {
			JLabel label = new JLabel((String) value);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);
			label.setFont(font);
			if (colorful) {
				if (row % 2 == 0) {
					label.setForeground(new Color(254, 144, 183));
				} else {
					label.setForeground(new Color(170, 250, 129));
				}
			} else {
				label.setForeground(Color.WHITE);
			}
			return label;
		}
		return null;
	}

}
