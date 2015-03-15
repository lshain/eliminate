/*
 * 
 */
package serverDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseOperation.
 */
public class DatabaseOperation implements LogicInterface {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		DatabaseOperation db = new DatabaseOperation();
		db.createTable();
	}

	/** The con. */
	private Connection con;

	/** The max ranking num. */
	private final int MAX_RANKING_NUM = 20;

	/** The ep. */
	private EncryptPassword ep = null;

	/**
	 * Instantiates a new database operation.
	 */
	public DatabaseOperation() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:database.db");
			ep = new EncryptPassword();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#addFriend(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean addFriend(String userId, String friendId) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		ArrayList<String> id = getFriendList(userId);
		if (id.indexOf(friendId) >= 0) {
			return false;
		} else {
			try {
				Statement st = con.createStatement();
				int rs = st
						.executeUpdate("insert into userFriend(userId,friendId) values ('"
								+ userId + "','" + friendId + "');");
				if (rs > 0) {
					isSuccess = true;
				}
				st.close();
			} catch (Exception e) {
				return isSuccess;
			}
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#changeUserPassword(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean changeUserPassword(String userId, String password) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		String newPassword = ep.createPassword(password);
		try {
			Statement st = con.createStatement();
			int rs = st.executeUpdate("update userList set password='"
					+ newPassword + "' where userId='" + userId + "';");
			if (rs > 0) {
				isSuccess = true;
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#createTable()
	 */
	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		try {
			Statement st = con.createStatement();
			st.executeUpdate("create table userList(userId varchar(16) not null,"
					+ "password varchar(32) not null,"
					+ "userType varchar(16) not null,"
					+ "gold int,"
					+ "score int,"
					+ "combo int,"
					+ "totalGame int,"
					+ "multiplayGame int,"
					+ "average int,"
					+ "primary key(userId));");

			st.executeUpdate("create table teamRanking(userId varchar(64),"
					+ "score int);");

			st.executeUpdate("create table singleRanking(userId varchar(16) not null,"
					+ "times varchar(9),"
					+ "score int,"
					+ "number int,"
					+ "combo int," + "tag int);");
			st.executeUpdate("create table userFriend(userId varchar(16) not null,"
					+ "friendId varchar(16) not null);");

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#deleteUser(java.lang.String)
	 */
	@Override
	public boolean deleteUser(String id) {
		boolean isSuccess = false;
		if (isHaveUser(id)) {
			try {
				Statement st = con.createStatement();
				int rs = st.executeUpdate("delete from userList where userId='"
						+ id + "';");
				int r = st
						.executeUpdate("delete from singleRanking where userId='"
								+ id + "';");
				int s = st
						.executeUpdate("delete from userFriend where userId='"
								+ id + "';");
				int m = st
						.executeUpdate("delete from userFriend where friendId='"
								+ id + "';");
				if (rs > 0 || r > 0 || s > 0 || m > 0) {
					isSuccess = true;
				}
				st.close();
			} catch (Exception e) {
				return false;
			}
		} else {
			System.out.println("No such man!");
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#delFriend(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean delFriend(String userId, String friendId) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		ArrayList<String> id = getFriendList(userId);
		if (id.indexOf(friendId) >= 0) {
			try {
				Statement st = con.createStatement();
				int rs = st
						.executeUpdate("delete from userFriend where userId='"
								+ userId + "' and friendId = '" + friendId
								+ "';");
				if (rs > 0) {
					isSuccess = true;
				}
				st.close();
			} catch (Exception e) {
				isSuccess = false;
			}
		} else {
			return false;
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#getAverage(java.lang.String)
	 */
	@Override
	public int[][] getAverage(String userId) {
		// TODO Auto-generated method stub
		ArrayList<int[]> temp = new ArrayList<int[]>();
		int[][] result = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from singleRanking where userId='"
							+ userId + "' and tag=0");
			while (rs.next()) {
				int[] t = new int[5];
				String times = rs.getString("times");
				t[0] = Integer.valueOf(times.substring(0, 4));
				t[1] = Integer.valueOf(times.substring(4, 6));
				t[2] = Integer.valueOf(times.substring(6));
				t[3] = rs.getInt("score");
				t[4] = rs.getInt("number");
				temp.add(t);
			}
			result = new int[temp.size()][5];
			for (int i = 0; i < temp.size(); i++) {
				for (int j = 0; j < 5; j++) {
					result[i][j] = temp.get(i)[j];
					System.out.print(result[i][j] + "---------");
				}
				System.out.println();
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#getFriendList(java.lang.String)
	 */
	@Override
	public ArrayList<String> getFriendList(String userId) {
		// TODO Auto-generated method stub
		ArrayList<String> id = new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from userFriend where userId='"
							+ userId + "';");
			while (rs.next()) {
				String friendId = rs.getString("friendId");
				id.add(friendId);
			}
			st.close();
		} catch (Exception e) {
			return id;
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#getSingleRanking(java.lang.String)
	 */
	@Override
	public int[][] getSingleRanking(String userId) {
		// TODO Auto-generated method stub
		ArrayList<Integer> score = new ArrayList<Integer>();
		ArrayList<Integer> combo = new ArrayList<Integer>();
		ArrayList<String> date = new ArrayList<String>();
		int[][] info = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from singleRanking where userId='"
							+ userId + "' and tag = 1;");
			while (rs.next()) {
				date.add(rs.getString("times"));
				score.add(rs.getInt("score"));
				combo.add(rs.getInt("combo"));
			}
			info = new int[score.size()][5];
			for (int i = 0; i < score.size(); i++) {
				String times = date.get(i);
				info[i][0] = Integer.valueOf(times.substring(0, 4));
				info[i][1] = Integer.valueOf(times.substring(4, 6));
				info[i][2] = Integer.valueOf(times.substring(6));
				info[i][3] = score.get(i);
				info[i][4] = combo.get(i);
			}
			for (int i = 0; i < score.size(); i++) {
				for (int j = i + 1; j < score.size(); j++) {
					int[] temp = new int[5];
					if (info[j][3] > info[i][3]) {
						temp = info[j];
						info[j] = info[i];
						info[i] = temp;
					}
				}
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#getTeamRanking()
	 */
	@Override
	public String[][] getTeamRanking() {
		// TODO Auto-generated method stub
		String[][] ranking = new String[MAX_RANKING_NUM][2];
		String[][] team = new String[MAX_RANKING_NUM][2];
		String[] temp = new String[2];
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from teamRanking;");
			int i = 0;
			while (rs.next()) {
				ranking[i][0] = rs.getString("userId");
				int score = rs.getInt("score");
				ranking[i][1] = Integer.toString(score);
				i++;
			}
			for (int j = 0; j < i; j++) {
				for (int k = j + 1; k < i; k++) {
					int a = Integer.parseInt(ranking[k][1]);
					int b = Integer.parseInt(ranking[j][1]);
					if (a > b) {
						temp[0] = ranking[k][0];
						temp[1] = ranking[k][1];
						ranking[k][0] = ranking[j][0];
						ranking[k][1] = ranking[j][1];
						ranking[j][0] = temp[0];
						ranking[j][1] = temp[1];
					}
				}
			}
			team = new String[i][2];
			for (int j = 0; j < i; j++) {
				team[j][0] = ranking[j][0];
				team[j][1] = ranking[j][1];
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return team;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#getUserInfo(java.lang.String)
	 */
	@Override
	public User getUserInfo(String userId) {
		// TODO Auto-generated method stub
		User user = new User("", "");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from userList where userId='"
							+ userId + "';");
			if (rs.next()) {
				user = new User(userId, "");
				user.setScore(rs.getInt("score"));
				user.setTotal(rs.getInt("totalGame"));
				user.setMultiTotal(rs.getInt("multiplayGame"));
			}
			st.close();
		} catch (Exception e) {
			return user;
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#insertUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean insertUser(String userId, String password) {
		boolean isSuccess = false;
		String newPassword = ep.createPassword(password);
		try {
			Statement st = con.createStatement();
			int rs = st
					.executeUpdate("insert into userList(userId,password,userType,gold,"
							+ "score,combo,totalGame,multiplayGame,average"
							+ ")values('"
							+ userId
							+ "','"
							+ newPassword
							+ "','" + "player" + "',1000,0,0,0,0,0);");
			if (rs > 0) {
				isSuccess = true;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#isHaveUser(java.lang.String)
	 */
	@Override
	public boolean isHaveUser(String userId) {
		boolean isOK = false;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from userList where userId='"
							+ userId + "';");
			while (rs.next()) {
				isOK = true;
				break;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String userId, String password) {
		User user = new User("", "");
		String newPassword = ep.createPassword(password);
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from userList where userId='"
							+ userId + "' and password='" + newPassword + "';");
			if (rs.next()) {
				user = new User(userId, password);
				user.setCombo(rs.getInt("combo"));
				user.setScore(rs.getInt("score"));
				user.setGold(rs.getInt("gold"));
				user.setTotal(rs.getInt("totalGame"));
				user.setAverage(rs.getInt("average"));
				user.setMultiTotal(rs.getInt("multiplayGame"));
				// System.out.println("登录成功！");
			}
			// else {
			// System.out.println("Fail");
			// }
			st.close();
		} catch (Exception e) {
			return user;
		}
		return user;
	}

	/**
	 * Test.
	 * 
	 * @return true, if successful
	 */
	public boolean test() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#updateSingleRanking(user.User, int, int)
	 */
	@Override
	public boolean updateSingleRanking(User user, int score, int combo) {
		// TODO Auto-generated method stub
		boolean isOk = false;
		String userId = user.getUserId();
		Calendar c = Calendar.getInstance();
		String times = Integer.toString(c.get(Calendar.YEAR));
		if (c.get(Calendar.MONTH) > 9) {
			times = times + Integer.toString(c.get(Calendar.MONTH) + 1);
		} else {
			times = times + "0" + Integer.toString(c.get(Calendar.MONTH) + 1);
		}
		times = times + Integer.toString(c.get(Calendar.DATE));
		try {
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from singleRanking where userId='"
							+ userId + "' and times='" + times
							+ "' and tag = 0;");

			if (rs.next()) {
				int grade = rs.getInt("score");
				int batter = rs.getInt("combo");
				int t = rs.getInt("number");

				// 必须加这个？？？？
				st.close();

				grade = (grade * t + score) / (t + 1);
				batter = (batter * t + combo) / (t + 1);
				t++;

				st = con.createStatement();
				int result = st.executeUpdate("UPDATE singleRanking SET score="
						+ grade + ", combo=" + batter + ", number =" + t
						+ " where userId='" + userId + "' and times='" + times
						+ "' and tag = 0;");
				if (result > 0) {
					isOk = true;
				}
			} else {
				st.close();
				st = con.createStatement();
				int result = st
						.executeUpdate("insert into singleRanking values ('"
								+ userId + "','" + times + "'," + score + ",1,"
								+ combo + ",0)");
				if (result > 0) {
					isOk = true;
				}
				st.close();
			}
			// 判断是否要替换排行榜上内容
			st = con.createStatement();
			// if (info.length < MAX_RANKING_NUM) {
			int result = st
					.executeUpdate("insert into singleRanking(userId,times,score,number,combo,tag) values ('"
							+ userId
							+ "','"
							+ times
							+ "',"
							+ score
							+ ",1,"
							+ combo + ",1);");
			if (result > 0) {
				isOk = isOk && true;
			} else {
				isOk = false;
			}
			st.close();
			// } else {
			// if (info[MAX_RANKING_NUM - 1][0] < score) {
			// int result = st
			// .executeUpdate("UPDATE singleRanking SET score="
			// + score + ", combo=" + combo + ", times='"
			// + times + "' where userId='" + userId
			// + "' and score="
			// + info[MAX_RANKING_NUM - 1][0]
			// + " and tag=1;");
			// if (result > 0) {
			// isOk = isOk && true;
			// } else {
			// isOk = false;
			// }
			// st.close();
			// }
			// }

			// 更新User
			if (user.getCombo() < combo) {
				user.setCombo(combo);
			}
			int total = user.getTotal();
			total++;
			user.setTotal(total);
			int average = user.getAverage();
			average = (average * (total - 1) + score) / total;
			user.setAverage(average);
			int top = user.getScore();
			if (top < score) {
				user.setScore(score);
			}
			updateUser(user);
		} catch (Exception e) {
			return isOk;
		}
		return isOk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#updateTeamRanking(java.util.ArrayList, int)
	 */
	@Override
	public boolean updateTeamRanking(ArrayList<String> userID, int score) {
		// TODO Auto-generated method stub
		String[][] team = getTeamRanking();
		String userIDs = "";
		for (String s : userID) {
			userIDs = userIDs + s + ",";
		}
		userIDs = userIDs.substring(0, userIDs.length() - 1);
		System.out.println(userIDs + "   " + score);
		boolean isOk = false;
		try {
			Statement st = con.createStatement();
			if (team.length < MAX_RANKING_NUM) {
				int rs = st
						.executeUpdate("insert into teamRanking(userId,score) values ('"
								+ userIDs
								+ "',"
								+ Integer.toString(score)
								+ ");");
				if (rs > 0) {
					isOk = true;
				}
			} else {
				int max_score = Integer.parseInt(team[MAX_RANKING_NUM - 1][1]);
				String ids = team[MAX_RANKING_NUM - 1][0];
				if (max_score < score) {
					  int rs = st.executeUpdate("UPDATE teamRanking SET userId='"
	                            +userIDs + "',score=" + score + " where userId='"+ids+"' and score="+max_score+";");
					if (rs > 0) {
						isOk = true;
					}
				}
			}
			st.close();

			st = con.createStatement();
			for (String id : userID) {
				int result = st
						.executeUpdate("UPDATE userList SET multiplayGame=multiplayGame+1"
								+ " where userId='" + id + "';");
				if (result > 0) {
					isOk = true;
				}
			}
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isOk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see serverDB.LogicInterface#updateUser(user.User)
	 */
	@Override
	public boolean updateUser(User user) {
		boolean isSuccess = false;
		String userId = user.getUserId();
		int gold = user.getGold();
		int score = user.getScore();
		int combo = user.getCombo();
		int totalGame = user.getTotal();
		int average = user.getAverage();
		int multiplayGame = user.getMultiTotal();

		try {
			Statement st = con.createStatement();
			int rs = st.executeUpdate("UPDATE userList SET gold=" + gold
					+ ",score=" + score + ",combo=" + combo + ",totalGame="
					+ totalGame + ",multiplayGame=" + multiplayGame
					+ ",average=" + average + " WHERE userList.userId='"
					+ userId + "';");
			if (rs > 0) {
				isSuccess = true;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

}
