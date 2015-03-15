package register;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterMsg.
 */
@SuppressWarnings("serial")
public class RegisterMsg implements Serializable {

	/** The Constant ID_DUPLICATION. */
	public static final int ID_DUPLICATION = 1;

	/** The Constant SYSTEM_ERROR. */
	public static final int SYSTEM_ERROR = 2;

	/** The Constant SUCESS. */
	public static final int SUCESS = 3;

	/** The type. */
	private int type = 3;

	/**
	 * Instantiates a new register msg.
	 * 
	 * @param type
	 *            the type
	 */
	public RegisterMsg(int type) {
		this.type = type;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}
}
