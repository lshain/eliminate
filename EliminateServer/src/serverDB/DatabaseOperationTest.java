package serverDB;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseOperationTest.
 */
public class DatabaseOperationTest {

	/** The db. */
	DatabaseOperation db = new DatabaseOperation();

	/** The user id. */
	String userId = "test";

	/** The password. */
	String password = "abcdefg";

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		db.insertUser(userId, password);
	}

	/**
	 * Tear down.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
		db.deleteUser(userId);
	}

	/**
	 * Test change user password.
	 */
	@Test
	public void testChangeUserPassword() {
		boolean isSuccess = db.changeUserPassword(userId, "123456");
		assertTrue(isSuccess);
		User user = db.login(userId, password);
		assertTrue(user.getUserId().equals(""));
		user = db.login(userId, "123456");
		assertTrue(user.getUserId().equals(userId));
	}

	/**
	 * Test delete user.
	 */
	@Test
	public void testDeleteUser() {
		boolean isSuccess = db.deleteUser(userId);
		assertTrue(isSuccess);
		isSuccess = db.deleteUser("test1");
		assertFalse(isSuccess);
	}

	/**
	 * Test get average.
	 */
	@Test
	public void testGetAverage() {
		User user = db.login(userId, password);
		db.updateSingleRanking(user, 100, 20);
		int[][] info = db.getAverage(userId);
		Calendar c = Calendar.getInstance();
		assertTrue(c.get(Calendar.YEAR) == info[0][0]);
		assertTrue((c.get(Calendar.MONTH) + 1) == info[0][1]);
		assertTrue(c.get(Calendar.DATE) == info[0][2]);
		assertTrue(info[0][3] == 100);
	}

	/**
	 * Test is have user.
	 */
	@Test
	public void testIsHaveUser() {
		boolean isHave = db.isHaveUser(userId);
		assertTrue(isHave);
		isHave = db.isHaveUser("test1");
		assertFalse(isHave);
	}

	/**
	 * Test login.
	 */
	@Test
	public void testLogin() {
		User user = db.login(userId, password);
		assertTrue(user.getUserId().equals(userId));
		user = db.login("test1", "abcdefg");
		assertTrue(user.getUserId().equals(""));
		assertFalse(user.getUserId().equals("test1"));
		user = db.login("test", "asdfe");
		assertTrue(user.getUserId().equals(""));
		assertFalse(user.getUserId().equals("test"));
	}

	/**
	 * Test update and get single ranking.
	 */
	@Test
	public void testUpdateAndGetSingleRanking() {
		User user = db.login(userId, password);
		boolean isSuccess = db.updateSingleRanking(user, 100, 20);
		assertTrue(isSuccess);
		int[][] info = db.getSingleRanking(userId);
		assertTrue(info[0][0] == 100);
		assertTrue(info[0][1] == 20);
		db.deleteUser(userId);
		info = db.getSingleRanking(userId);
		assertTrue(info.length == 0);
	}

	/**
	 * Test update user.
	 */
	@Test
	public void testUpdateUser() {
		User user = db.login(userId, password);
		user.setAverage(10);
		user.setCombo(2);
		user.setGold(2);
		user.setScore(20);
		user.setTotal(5);
		boolean isOk = db.updateUser(user);
		assertTrue(isOk);
		user = db.login(userId, password);
		assertTrue(user.getAverage() == 10);
		assertTrue(user.getCombo() == 2);
		assertTrue(user.getGold() == 2);
		assertTrue(user.getScore() == 20);
		assertTrue(user.getTotal() == 5);
	}

}
