/*
 * 
 */
package eliminate.view;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JPasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class MyPasswordField.
 */
@SuppressWarnings("serial")
public class MyPasswordField extends JPasswordField {

	/**
	 * Make password field.
	 * 
	 * @param Xaxis
	 *            the xaxis
	 * @param Yaxis
	 *            the yaxis
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the j password field
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static JPasswordField makePasswordField(int Xaxis, int Yaxis,
			int width, int height) throws IOException {
		return new MyPasswordField(Xaxis, Yaxis, width, height);
	}

	/**
	 * Instantiates a new my password field.
	 * 
	 * @param Xaxis
	 *            the xaxis
	 * @param Yaxis
	 *            the yaxis
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public MyPasswordField(int Xaxis, int Yaxis, int width, int height) {
		setBorder(null);
		setBounds(Xaxis, Yaxis, width, height);
		setFont(new Font("ºÚÌå", Font.PLAIN, 15));
		setEchoChar('*');
	}
}
