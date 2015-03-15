package register;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import serverDB.DatabaseOperation;
import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterThread.
 */
public class RegisterThread implements Runnable {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new RegisterThread().start();
	}

	/** The thread. */
	Thread thread;

	/** The db. */
	DatabaseOperation db = new DatabaseOperation();

	/** The df. */
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	/**
	 * Checks for same id.
	 * 
	 * @param address
	 *            the address
	 */
	public void hasSameID(String address) {
		System.out.println(df.format(new Date()) + ": " + address
				+ " register failed: already has the ID!");
	}

	/**
	 * Register failed.
	 * 
	 * @param address
	 *            the address
	 */
	public void registerFailed(String address) {
		System.out.println(df.format(new Date()) + ": " + address
				+ " register failed!");
	}

	/**
	 * Register sucess.
	 * 
	 * @param address
	 *            the address
	 */
	public void registerSucess(String address) {
		System.out.println(df.format(new Date()) + ": " + address
				+ " register sucess!");
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
			serverSocket = new ServerSocket(8004);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (thread != null) {
			try {
				Socket socket = serverSocket.accept();
				ObjectOutputStream output = new ObjectOutputStream(
						new BufferedOutputStream(socket.getOutputStream()));
				ObjectInputStream input = new ObjectInputStream(
						new BufferedInputStream(socket.getInputStream()));
				Object obj = input.readObject();
				if (obj instanceof User) {
					if (db.isHaveUser(((User) obj).getUserId())) {
						output.writeObject(new RegisterMsg(
								RegisterMsg.ID_DUPLICATION));
						output.flush();
						hasSameID(socket.getInetAddress().toString());
					} else {
						if (db.insertUser(((User) obj).getUserId(),
								((User) obj).getPassword())) {
							output.writeObject(new RegisterMsg(
									RegisterMsg.SUCESS));
							registerSucess(socket.getInetAddress().toString());
						} else {
							output.writeObject(new RegisterMsg(
									RegisterMsg.SYSTEM_ERROR));
							registerFailed(socket.getInetAddress().toString());
						}
						output.flush();
					}
				}
				socket.close();
				input.close();
				output.close();
			} catch (IOException | ClassNotFoundException e) {

			}
		}
		
		try {
			serverSocket.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
