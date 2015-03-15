/*
 * 
 */
package login;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import register.RegisterThread;
import serverDB.DatabaseOperation;
import user.User;
import chat.ChatThread;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginThread.
 */
public class LoginThread implements Runnable {

	/** The i. */
	int i = 0;

	/** The db. */
	static DatabaseOperation db = new DatabaseOperation();

	/**
	 * Gets the user.
	 * 
	 * @param userID
	 *            the user id
	 * @param password
	 *            the password
	 * @return the user
	 */
	public static User getUser(String userID, String password) {
		return db.login(userID, password);
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		LoginThread test = new LoginThread();
		test.start();
		ChatThread chat = new ChatThread();
		chat.start();
		new RegisterThread().start();
	}

	/**
	 * Save user.
	 * 
	 * @param user
	 *            the user
	 */
	public static void saveUser(User user) {
		db.updateUser(user);
	}
	
	
	public static void changePassword(String user, String password){
		db.changeUserPassword(user, password);
	}

	/**
	 * Update single rank.
	 * 
	 * @param user
	 *            the user
	 * @param score
	 *            the score
	 * @param combo
	 *            the combo
	 */
	public static void updateSingleRank(User user, int score, int combo) {
		db.updateSingleRanking(user, score, combo);
	}

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
	public static boolean updateTeamRanking(ArrayList<String> userID, int score) {
		return db.updateTeamRanking(userID, score);
	}

	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	/** The thread. */
	Thread thread;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8005);
			ObjectOutputStream output;
			ObjectInputStream input;
			while (thread != null) {
				Socket socket = serverSocket.accept();
				input = new ObjectInputStream(socket.getInputStream());
				output = new ObjectOutputStream(new BufferedOutputStream(
						socket.getOutputStream()));
				Object obj = input.readObject();
				if (obj instanceof LoginMsg) {
					LoginMsg msg = (LoginMsg) obj;
					boolean hasUser = db.isHaveUser(msg.getUser().getUserId());
					if (!hasUser) {
						output.writeObject(new LoginMsg(
								LoginMsg.USERID_NOT_EXIST, null));
						output.flush();
						System.out.println(df.format(new Date()) + ": "
								+ socket.getRemoteSocketAddress()
								+ "  Login failed");
					} else {
						User user = db.login(msg.getUser().getUserId(), msg
								.getUser().getPassword());
						if (user.getUserId().equals("")) {
							output.writeObject(new LoginMsg(
									LoginMsg.PASSWORD_WRONG, null));
							output.flush();
							System.out.println(df.format(new Date()) + ": "
									+ socket.getRemoteSocketAddress()
									+ "  Login failed");
						} else {
							OnlineUser.addUser(socket, user, output, input);
							output.writeObject(new LoginMsg(
									LoginMsg.LOGIN_SUCEESS, user));
							output.flush();
							output.reset();
							System.out.println(df.format(new Date()) + ": "
									+ socket.getRemoteSocketAddress()
									+ "  Login Sucess");
						}
					}
				}
			}
			serverSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Start.
	 */
	public void start() {
		if (thread == null)
			thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		thread = null;
	}
}
