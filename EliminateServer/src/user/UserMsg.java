package user;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class UserMsg.
 */
@SuppressWarnings("serial")
public class UserMsg implements Serializable {

	/** The Constant PREPARE_GAME. */
	public static final int PREPARE_GAME = 1;

	/** The Constant START_GAME. */
	public static final int START_GAME = 2;

	/** The Constant UPDATE_USER_INFO. */
	public static final int UPDATE_USER_INFO = 3;

	/** The Constant BUY_ITEM. */
	public static final int BUY_ITEM = 4;
	
	public static final int CHANGE_PASSWORD = 5;
	
	/** The items. */
	ArrayList<Integer> items = new ArrayList<Integer>();

	/** The user. */
	User user = null;

	String password;
	
	/** The type. */
	int type = 0;

	/**
	 * Instantiates a new user msg.
	 * 
	 * @param array
	 *            the array
	 */
	public UserMsg(ArrayList<Integer> array) {
		this.items = array;
		type = BUY_ITEM;
	}
	
	public UserMsg(String password){
		this.password = password;
		this.type = CHANGE_PASSWORD;
	}
	

	/**
	 * Instantiates a new user msg.
	 * 
	 * @param user
	 *            the user
	 * @param type
	 *            the type
	 */
	public UserMsg(User user, int type) {
		this.user = user;
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

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	public String getPassword(){
		return password;
	}
}
