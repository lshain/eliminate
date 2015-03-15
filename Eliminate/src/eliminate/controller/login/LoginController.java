/*
 * 
 */
package eliminate.controller.login;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import login.LoginMsg;
import room.Room;
import room.RoomMsg;
import user.User;
import eliminate.controller.MainFrame;
import eliminate.controller.input.HandleInput;
import eliminate.model.voice.Capture;
import eliminate.model.voice.Playback;
import eliminate.view.dialogs.NetworkDialog;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
public class LoginController {
	
	private static String serverHost;

	/** The socket. */
	private static Socket socket;

	/** The writer. */
	private static ObjectOutputStream writer;

	/** The user. */
	private static User user;

	/** The input handler. */
	private static HandleInput inputHandler;

	/** The capture. */
	private static Capture capture;

	/** The voice socket. */
	private static Socket voiceSocket;

	private static Playback playBack;

	/**
	 * Chat thread start.
	 */
	public static void chatThreadStart() {
		try {
			voiceSocket = new Socket(serverHost, 8003);
			ObjectOutputStream writer2 = new ObjectOutputStream(
					voiceSocket.getOutputStream());

			writer2.writeObject(new User(user.getUserId(), user.getPassword()));

			playBack = new Playback(voiceSocket);
			playBack.start();
			new Capture(voiceSocket).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Close socket.
	 */
	public static void closeSocket() {
		try {
			voiceSocket.close();
			inputHandler.stop();
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static float getChatVoice() {
		try {
			return playBack.getDB();
		} catch (Exception e) {
			return 1f;
		}
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public static User getUser() {
		return user;
	}

	/**
	 * Login.
	 * 
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	public static boolean Login(String userName, String password){
		boolean result = false;
		Properties property = new Properties();
		try {
			FileInputStream stream = new FileInputStream("media/config.properties");
			property.load(stream);
			stream.close();
			serverHost = (String) (property.get("ServerHost"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		try {
			socket = new Socket(serverHost, 8005);
			writer = new ObjectOutputStream(new BufferedOutputStream(
					socket.getOutputStream()));
			writer.writeObject(new LoginMsg(LoginMsg.LOGIN, new User(userName,
					password)));
			writer.flush();
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());
			Object obj = input.readObject();
			if (obj instanceof LoginMsg) {
				LoginMsg msg = (LoginMsg) obj;
				switch (msg.getType()) {
				case LoginMsg.LOGIN_SUCEESS:
					result = true;
					user = msg.getUser();
					inputHandler = new HandleInput(input, user);
					inputHandler.start();
					chatThreadStart();
					return result;
				case LoginMsg.USERID_NOT_EXIST:
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					MainFrame.hideLoadingDialog();
					MainFrame.showMsgDialog("用户名不存在!", "用户登录");
					break;
				case LoginMsg.PASSWORD_WRONG:
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					MainFrame.hideLoadingDialog();
					MainFrame.showMsgDialog("密码错误!", "用户登录");
					break;
				}
			}
			socket.close();
			return result;
		} catch (IOException | ClassNotFoundException e) {
			MainFrame.hideLoadingDialog();
			new NetworkDialog(MainFrame.getOwner(), true);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// Login("wuzhao", "hello");
		writeObject(new RoomMsg(new Room(Room.COOPERATE, 4, "土豪"),
				RoomMsg.CREATE_ROOM));
	}

	public static void setChatVoice(float value) {
		try {
			playBack.setDB(value);
		} catch (Exception e) {

		}
	}

	/**
	 * Voice chat start.
	 */
	public static void voiceChatStart() {
		voiceChatStop();
		new Thread(capture).start();
	}

	/**
	 * Voice chat stop.
	 */
	public static void voiceChatStop() {
		try {
			voiceSocket.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Write object.
	 * 
	 * @param obj
	 *            the obj
	 */
	public static void writeObject(Object obj) {
		try {
			if (obj instanceof LoginMsg) {
				return;
			}
			writer.writeObject(obj);
			writer.flush();
			writer.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
