package chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

import login.OnlineUser;
import serverDB.DatabaseOperation;
import user.User;

/**
 *
 */
public class ChatThread implements Runnable {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new ChatThread().start();
	}

	/** The i. */
	int i = 0;

	/** The db. */
	DatabaseOperation db = new DatabaseOperation();

	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	/** The thread. */
	Thread thread;

	/**
	 * Instantiates a new chat thread.
	 */
	public ChatThread() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8003);
			ObjectInputStream input;
			while (thread != null) {
				Socket socket = serverSocket.accept();
				input = new ObjectInputStream(socket.getInputStream());
				Object obj = input.readObject();
				if (obj instanceof User) {
					if (OnlineUser.getSockets(((User) obj).getUserId()) != null) {
						BufferedOutputStream output = new BufferedOutputStream(
								socket.getOutputStream());
						OnlineUser.getSockets(((User) obj).getUserId())
								.setVoiceSocket(socket, output);
						new Thread(
								new InputVoice((User) obj,
										new BufferedInputStream(
												socket.getInputStream())))
								.start();
					}

				}
			}
			serverSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Chat Socket Closed!");
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
