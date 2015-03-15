/*
 * 
 */
package eliminate.controller.register;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import register.RegisterMsg;
import user.User;
import eliminate.controller.MainFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterController.
 */
public class RegisterController {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		register("wuzhao", "hello");
	}
	
	private static String serverHost;

	/**
	 * Register.
	 * 
	 * @param userID
	 *            the user id
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	public static boolean register(String userID, String password) {
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
			Socket socket = new Socket(serverHost, 8004);
			ObjectOutputStream output = new ObjectOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			output.writeObject(new User(userID, password));
			output.flush();
			ObjectInputStream input = new ObjectInputStream(
					new BufferedInputStream(socket.getInputStream()));
			Object obj = input.readObject();
			if (obj instanceof RegisterMsg) {
				RegisterMsg msg = (RegisterMsg) obj;
				if (msg.getType() == 3) {
					result = true;
					MainFrame.showMsgDialog("注册成功！", "注册用户");
				} else if (msg.getType() == 1) {
					MainFrame.showMsgDialog("该用户名已经存在，请重新选择！", "注册用户");
				} else if (msg.getType() == 2) {
					MainFrame.showMsgDialog("服务器系统错误，请稍候再试！", "注册用户");
				}
			}
			input.close();
			output.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
