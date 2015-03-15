/*
 * 
 */
package serverDB;

import java.util.ArrayList;

import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface LogicInterface.
 */
public interface LogicInterface {

	// 添加好友
	/**
	 * Adds the friend.
	 * 
	 * @param userId
	 *            the user id
	 * @param friendId
	 *            the friend id
	 * @return true, if successful
	 */
	public boolean addFriend(String userId, String friendId);

	// 修改密码
	/**
	 * Change user password.
	 * 
	 * @param userId
	 *            the user id
	 * @param oldPassword
	 *            the old password
	 * @return true, if successful
	 */
	public boolean changeUserPassword(String userId, String oldPassword);

	// 创建数据库表
	/**
	 * Creates the table.
	 */
	public void createTable();

	// 删除用户
	/**
	 * Delete user.
	 * 
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	public boolean deleteUser(String userId);

	// 删除好友
	/**
	 * Del friend.
	 * 
	 * @param userId
	 *            the user id
	 * @param friendId
	 *            the friend id
	 * @return true, if successful
	 */
	public boolean delFriend(String userId, String friendId);

	// 获得每日平均得分,int[][5],分别为年、月、日、分数、局数
	/**
	 * Gets the average.
	 * 
	 * @param userId
	 *            the user id
	 * @return the average
	 */
	public int[][] getAverage(String userId);

	// 获取好友列表
	/**
	 * Gets the friend list.
	 * 
	 * @param userId
	 *            the user id
	 * @return the friend list
	 */
	public ArrayList<String> getFriendList(String userId);

	// 获得个人排行榜,int[][0/1/2]为年月日,int[][3]为分数,int[][4]为连击
	/**
	 * Gets the single ranking.
	 * 
	 * @param userId
	 *            the user id
	 * @return the single ranking
	 */
	public int[][] getSingleRanking(String userId);

	// 获得网络排行榜,分别为用户名、分数
	/**
	 * Gets the team ranking.
	 * 
	 * @return the team ranking
	 */
	public String[][] getTeamRanking();

	// 获取用户数据
	/**
	 * Gets the user info.
	 * 
	 * @param userId
	 *            the user id
	 * @return the user info
	 */
	public User getUserInfo(String userId);

	// 添加用户
	/**
	 * Insert user.
	 * 
	 * @param userId
	 *            the user id
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	public boolean insertUser(String userId, String password);

	// 判断是否存在用户
	/**
	 * Checks if is have user.
	 * 
	 * @param userId
	 *            the user id
	 * @return true, if is have user
	 */
	public boolean isHaveUser(String userId);

	// 判断登录是否成功
	/**
	 * Login.
	 * 
	 * @param userId
	 *            the user id
	 * @param password
	 *            the password
	 * @return the user
	 */
	public User login(String userId, String password);

	// 更新个人排行榜
	/**
	 * Update single ranking.
	 * 
	 * @param user
	 *            the user
	 * @param score
	 *            the score
	 * @param combo
	 *            the combo
	 * @return true, if successful
	 */
	public boolean updateSingleRanking(User user, int score, int combo);

	// 更新网络排行榜
	/**
	 * Update team ranking.
	 * 
	 * @param userID
	 *            the user id
	 * @param score
	 *            the score
	 * @return true, if successful
	 */
	public boolean updateTeamRanking(ArrayList<String> userID, int score);

	// 更新用户数据
	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean updateUser(User user);

}
