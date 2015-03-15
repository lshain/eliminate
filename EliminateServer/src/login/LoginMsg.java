/*
 * 
 */
package login;

import java.io.Serializable;

import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginMsg.
 */
@SuppressWarnings("serial")
public class LoginMsg implements Serializable {

	/** The Constant LOGIN. */
	public static final int LOGIN = 1;

	/** The Constant LOGIN_SUCEESS. */
	public static final int LOGIN_SUCEESS = 2;

	/** The Constant USERID_NOT_EXIST. */
	public static final int USERID_NOT_EXIST = 3;

	/** The Constant PASSWORD_WRONG. */
	public static final int PASSWORD_WRONG = 4;

	/** The Constant PUSH_OUT_LINE. */
	public static final int PUSH_OUT_LINE = 5;

	/** The user. */
	private User user;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new login msg.
	 * 
	 * @param type
	 *            the type
	 * @param user
	 *            the user
	 */
	public LoginMsg(int type, User user) {
		this.type = type;
		this.user = user;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
