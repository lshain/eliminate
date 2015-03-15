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

	// ��Ӻ���
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

	// �޸�����
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

	// �������ݿ��
	/**
	 * Creates the table.
	 */
	public void createTable();

	// ɾ���û�
	/**
	 * Delete user.
	 * 
	 * @param userId
	 *            the user id
	 * @return true, if successful
	 */
	public boolean deleteUser(String userId);

	// ɾ������
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

	// ���ÿ��ƽ���÷�,int[][5],�ֱ�Ϊ�ꡢ�¡��ա�����������
	/**
	 * Gets the average.
	 * 
	 * @param userId
	 *            the user id
	 * @return the average
	 */
	public int[][] getAverage(String userId);

	// ��ȡ�����б�
	/**
	 * Gets the friend list.
	 * 
	 * @param userId
	 *            the user id
	 * @return the friend list
	 */
	public ArrayList<String> getFriendList(String userId);

	// ��ø������а�,int[][0/1/2]Ϊ������,int[][3]Ϊ����,int[][4]Ϊ����
	/**
	 * Gets the single ranking.
	 * 
	 * @param userId
	 *            the user id
	 * @return the single ranking
	 */
	public int[][] getSingleRanking(String userId);

	// ����������а�,�ֱ�Ϊ�û���������
	/**
	 * Gets the team ranking.
	 * 
	 * @return the team ranking
	 */
	public String[][] getTeamRanking();

	// ��ȡ�û�����
	/**
	 * Gets the user info.
	 * 
	 * @param userId
	 *            the user id
	 * @return the user info
	 */
	public User getUserInfo(String userId);

	// ����û�
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

	// �ж��Ƿ�����û�
	/**
	 * Checks if is have user.
	 * 
	 * @param userId
	 *            the user id
	 * @return true, if is have user
	 */
	public boolean isHaveUser(String userId);

	// �жϵ�¼�Ƿ�ɹ�
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

	// ���¸������а�
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

	// �����������а�
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

	// �����û�����
	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean updateUser(User user);

}
