package friend;

import java.io.Serializable;
import java.util.ArrayList;

import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class FriendMsg.
 */
@SuppressWarnings("serial")
public class FriendMsg implements Serializable {
	// 征询加为好友
	/** The Constant ASK_FOR_ADD_FRIEND. */
	public static final int ASK_FOR_ADD_FRIEND = 1;
	// 拒绝加为好友
	/** The Constant REFUSE_ADD_FRIEND. */
	public static final int REFUSE_ADD_FRIEND = 2;
	// 同意加为好友
	/** The Constant APPROVE_ADD_FRIEND. */
	public static final int APPROVE_ADD_FRIEND = 3;
	// 删除好友
	/** The Constant DELETE_FRIEND. */
	public static final int DELETE_FRIEND = 4;
	// 查询资料
	/** The Constant USER_INFO. */
	public static final int USER_INFO = 5;
	// 获取用户所有好友
	/** The Constant ALL_FRIEND. */
	public static final int ALL_FRIEND = 6;
	// 添加好友失败
	/** The Constant FAIL_ADD_FRIEND. */
	public static final int FAIL_ADD_FRIEND = 7;
	// 获取在线好友
	/** The Constant ONLINE_FRIEND. */
	public static final int ONLINE_FRIEND = 8;

	/** The Constant INVITE_FRIEND. */
	public static final int INVITE_FRIEND = 9;

	/** The Constant INVITE_NOT_ONLINE. */
	public static final int INVITE_NOT_ONLINE = 10;

	/** The Constant INVITE_IN_GAME. */
	public static final int INVITE_IN_GAME = 11;

	/** The Constant INVITE_REFUSE. */
	public static final int INVITE_REFUSE = 12;

	/** The Constant INVITE_AGREE. */
	public static final int INVITE_AGREE = 13;

	/** The type. */
	private int type = 0;

	/** The user. */
	private User user;// 对方用户资料

	/** The friend id. */
	private String friendId = "";// 对方Id

	/** The online friends. */
	private ArrayList<String> onlineFriends;

	/** The offline friends. */
	private ArrayList<String> offlineFriends;

	/** The is friend. */
	private boolean isFriend = false;

	/**
	 * Instantiates a new friend msg.
	 * 
	 * @param friendId
	 *            the friend id
	 * @param type
	 *            the type
	 */
	public FriendMsg(String friendId, int type) {
		this.friendId = friendId;
		this.type = type;
	}

	/**
	 * Gets the friend id.
	 * 
	 * @return the friend id
	 */
	public String getFriendId() {
		return friendId;
	}

	/**
	 * Gets the checks if is friend.
	 * 
	 * @return the checks if is friend
	 */
	public boolean getIsFriend() {
		return isFriend;
	}

	/**
	 * Gets the offline friends.
	 * 
	 * @return the offline friends
	 */
	public ArrayList<String> getOfflineFriends() {
		return offlineFriends;
	}

	/**
	 * Gets the online friends.
	 * 
	 * @return the online friends
	 */
	public ArrayList<String> getOnlineFriends() {
		return onlineFriends;
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
	 * Sets the checks if is friend.
	 * 
	 * @param f
	 *            the new checks if is friend
	 */
	public void setIsFriend(boolean f) {
		this.isFriend = f;
	}

	/**
	 * Sets the offline friends.
	 * 
	 * @param l
	 *            the new offline friends
	 */
	public void setOfflineFriends(ArrayList<String> l) {
		this.offlineFriends = l;
	}

	/**
	 * Sets the online friends.
	 * 
	 * @param l
	 *            the new online friends
	 */
	public void setOnlineFriends(ArrayList<String> l) {
		this.onlineFriends = l;
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
