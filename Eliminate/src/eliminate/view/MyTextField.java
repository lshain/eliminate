/*
 * 
 */
package eliminate.view;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class MyTextField.
 */
@SuppressWarnings("serial")
public class MyTextField extends JTextField {

	/**
	 * Make text field.
	 * 
	 * @param Xaxis
	 *            the xaxis
	 * @param Yaxis
	 *            the yaxis
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the j text field
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static JTextField makeTextField(int Xaxis, int Yaxis, int width,
			int height) throws IOException {
		return new MyTextField(Xaxis, Yaxis, width, height);
	}

	/**
	 * Instantiates a new my text field.
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
	public MyTextField(int Xaxis, int Yaxis, int width, int height) {
		setBorder(null);
		setBounds(Xaxis, Yaxis, width, height);
		setFont(new Font("ºÚÌå", Font.PLAIN, 15));
	}
}
