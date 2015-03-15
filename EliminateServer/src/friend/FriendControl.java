/*
 * 
 */
package friend;

import java.util.ArrayList;

import login.OnlineUser;
import serverDB.DatabaseOperation;
import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class FriendControl.
 */
public class FriendControl {
	// 同意加为好友
	/**
	 * Approve add friend.
	 * 
	 * @param user
	 *            the user
	 * @param friendId
	 *            the friend id
	 */
	public static void approveAddFriend(User user, String friendId) {
		boolean isOnline = OnlineUser.isOnline(friendId);
		if (isOnline) {
			OnlineUser.getSockets(friendId).writeObject(
					new FriendMsg(user.getUserId(),
							FriendMsg.APPROVE_ADD_FRIEND));
			DatabaseOperation db = new DatabaseOperation();
			db.addFriend(user.getUserId(), friendId);
			db.addFriend(friendId, user.getUserId());
		} else {
			OnlineUser.getSockets(user.getUserId()).writeObject(
					new FriendMsg(user.getUserId(),
							FriendMsg.FAIL_ADD_FRIEND));
		}
	}

	// 征询是否能够加为好友
	/**
	 * Ask for add friend.
	 * 
	 * @param user
	 *            the user
	 * @param friendId
	 *            the friend id
	 */
	public static void askForAddFriend(User user, String friendId) {
		boolean isOnline = OnlineUser.isOnline(friendId);
		if (isOnline) {
			OnlineUser.getSockets(friendId).writeObject(
					new FriendMsg(user.getUserId(),
							FriendMsg.ASK_FOR_ADD_FRIEND));
		} else {
			OnlineUser.getSockets(user.getUserId()).writeObject(
					new FriendMsg(user.getUserId(),
							FriendMsg.FAIL_ADD_FRIEND));
		}
	}

	// 删除好友
	/**
	 * Delete friend.
	 * 
	 * @param user
	 *            the user
	 * @param friendId
	 *            the friend id
	 */
	public static void deleteFriend(User user, String friendId) {
		System.out.println("OK");
		DatabaseOperation db = new DatabaseOperation();
		db.delFriend(user.getUserId(), friendId);
		db.delFriend(friendId, user.getUserId());
		ArrayList<String> friend = db.getFriendList(user.getUserId());
		ArrayList<String> online = new ArrayList<String>();
		ArrayList<String> offline = new ArrayList<String>();
		for (String id : friend) {
			if (OnlineUser.isOnline(id)) {
				online.add(id);
			} else {
				offline.add(id);
			}
		}
		FriendMsg msg = new FriendMsg(user.getUserId(),
				FriendMsg.DELETE_FRIEND);
		java.util.Collections.sort(online);
		java.util.Collections.sort(offline);
		msg.setOnlineFriends(online);
		msg.setOfflineFriends(offline);
		System.out.println("1");
		OnlineUser.getSockets(user.getUserId()).writeObject(msg);
	}

	// 获取用户所有好友
	/**
	 * Gets the all friend.
	 * 
	 * @param user
	 *            the user
	 * @return the all friend
	 */
	public static void getAllFriend(User user) {
		DatabaseOperation db = new DatabaseOperation();
		ArrayList<String> friend = db.getFriendList(user.getUserId());
		ArrayList<String> online = new ArrayList<String>();
		ArrayList<String> offline = new ArrayList<String>();
		for (String id : friend) {
			if (OnlineUser.isOnline(id)) {
				online.add(id);
			} else {
				offline.add(id);
			}
		}
		java.util.Collections.sort(online);
		java.util.Collections.sort(offline);
		FriendMsg msg = new FriendMsg(user.getUserId(),
				FriendMsg.ALL_FRIEND);
		msg.setOnlineFriends(online);
		msg.setOfflineFriends(offline);
		OnlineUser.getSockets(user.getUserId()).writeObject(msg);
	}

	// 获取在线好友
	/**
	 * Gets the online friend.
	 * 
	 * @param user
	 *            the user
	 * @return the online friend
	 */
	public static void getOnlineFriend(User user) {
		DatabaseOperation db = new DatabaseOperation();
		ArrayList<String> friend = db.getFriendList(user.getUserId());
		ArrayList<String> online = new ArrayList<String>();
		for (String id : friend) {
			if (OnlineUser.isOnline(id)) {
				online.add(id);
			}
		}
		java.util.Collections.sort(online);
		FriendMsg msg = new FriendMsg(user.getUserId(),
				FriendMsg.ONLINE_FRIEND);
		msg.setOnlineFriends(online);
		OnlineUser.getSockets(user.getUserId()).writeObject(msg);
	}

	// 获取在线好友
	/**
	 * Invite friend.
	 * 
	 * @param myID
	 *            the my id
	 * @param friendID
	 *            the friend id
	 */
	public static void InviteFriend(String myID, String friendID) {
		if (OnlineUser.isOnline(friendID)) {
			OnlineUser.getSockets(friendID).writeObject(
					new FriendMsg(myID, FriendMsg.INVITE_FRIEND));
		} else {
			OnlineUser.getSockets(myID).writeObject(
					new FriendMsg(friendID, FriendMsg.INVITE_NOT_ONLINE));
		}
	}

	// 判断是否为好友
	/**
	 * Checks if is friend.
	 * 
	 * @param user
	 *            the user
	 * @param id
	 *            the id
	 * @return true, if is friend
	 */
	public static boolean isFriend(User user, String id) {
		DatabaseOperation db = new DatabaseOperation();
		ArrayList<String> friend = db.getFriendList(user.getUserId());
		return friend.contains(id);
	}

	// 拒绝加为好友
	/**
	 * Refuse add friend.
	 * 
	 * @param user
	 *            the user
	 * @param friendId
	 *            the friend id
	 */
	public static void refuseAddFriend(User user, String friendId) {
		boolean isOnline = OnlineUser.isOnline(friendId);
		if (isOnline) {
			OnlineUser.getSockets(friendId).writeObject(
					new FriendMsg(user.getUserId(),
							FriendMsg.REFUSE_ADD_FRIEND));
		}
	}

	// 搜索用户信息
	/**
	 * Search user info.
	 * 
	 * @param user
	 *            the user
	 * @param friendId
	 *            the friend id
	 */
	public static void searchUserInfo(User user, String friendId) {
		DatabaseOperation db = new DatabaseOperation();
		User u = db.getUserInfo(friendId);
		FriendMsg msg = new FriendMsg(friendId, FriendMsg.USER_INFO);
		msg.setUser(u);
		msg.setIsFriend(isFriend(user, friendId));
		OnlineUser.getSockets(user.getUserId()).writeObject(msg);
	}
}
