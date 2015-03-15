/*
 * 
 */
package eliminate.view;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class LimitedDocument.
 */
@SuppressWarnings("serial")
public class LimitedDocument extends PlainDocument {

	/** The limited num. */
	public static int limitedNum = 16;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		System.out.println("dsfadfads@#323123___dsf".replaceAll("[\\W_]", "*"));
	}

	/**
	 * Instantiates a new limited document.
	 */
	public LimitedDocument() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.text.PlainDocument#insertString(int, java.lang.String,
	 * javax.swing.text.AttributeSet)
	 */
	@Override
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		// TODO Auto-generated method stub
		if (str == null)
			return;
		String s = str.replaceAll("[\\W_]", "");
		if (getLength() == limitedNum) {
			Toolkit.getDefaultToolkit().beep();
			return;
		} else if (s.length() + getLength() > limitedNum) {
			System.out.println(limitedNum - getLength());
			s = s.substring(limitedNum - getLength());
		}

		super.insertString(offset, s, attr);
	}
}
