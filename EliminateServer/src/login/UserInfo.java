/*
 * 
 */
package login;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import user.User;
import controller.HandleInput;

// TODO: Auto-generated Javadoc
/**
 * The Class UserInfo.
 */
public class UserInfo {

	/** The socket. */
	private Socket socket = null; // 动态更新房间信息

	/** The voice socket. */
	private Socket voiceSocket = null; // 声音信息

	/** The voice output. */
	private BufferedOutputStream voiceOutput = null; // 声音传出

	/** The output. */
	private ObjectOutputStream output;

	/** The user. */
	private User user;

	/** The input handler. */
	private HandleInput inputHandler;

	/** The room id. */
	private long roomID = 0;

	/**
	 * Instantiates a new user info.
	 * 
	 * @param user
	 *            the user
	 * @param socket
	 *            the socket
	 * @param output
	 *            the output
	 * @param inputHandler
	 *            the input handler
	 */
	public UserInfo(User user, Socket socket, ObjectOutputStream output,
			HandleInput inputHandler) {
		this.socket = socket;
		this.output = output;
		this.user = user;
		this.inputHandler = inputHandler;
		inputHandler.start();
	}

	/**
	 * Close socket.
	 */
	public void closeSocket() {
		try {
			writeObject(new LoginMsg(LoginMsg.PUSH_OUT_LINE, null));
			inputHandler.stop();
			this.socket.close();
			this.voiceSocket.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Gets the room id.
	 * 
	 * @return the room id
	 */
	public long getRoomID() {
		return roomID;
	}

	/**
	 * Gets the socket.
	 * 
	 * @return the socket
	 */
	public Socket getsocket() {
		return socket;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Gets the voice socket.
	 * 
	 * @return the voice socket
	 */
	public Socket getVoiceSocket() {
		return voiceSocket;
	}

	/**
	 * Sets the room id.
	 * 
	 * @param roomID
	 *            the new room id
	 */
	public void setRoomID(long roomID) {
		this.roomID = roomID;
	}

	/**
	 * Sets the socket.
	 * 
	 * @param socket
	 *            the new socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Sets the voice socket.
	 * 
	 * @param socket
	 *            the socket
	 * @param voiceOutput
	 *            the voice output
	 */
	public void setVoiceSocket(Socket socket, BufferedOutputStream voiceOutput) {
		this.voiceSocket = socket;
		this.voiceOutput = voiceOutput;
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 */
	public void UpdateUser(User user) {
		this.user = user;
	}

	/**
	 * Write object.
	 * 
	 * @param obj
	 *            the obj
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public synchronized void writeObject(final Object obj){
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					write(obj);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private synchronized void write(Object obj) throws IOException{
		System.out.print("Output:" + obj);
		output.writeObject(obj);
		output.flush();
		output.reset();
	}

	/**
	 * Write voice data.
	 * 
	 * @param data
	 *            the data
	 */
	public void writeVoiceData(final byte[] data) {
		new Thread(new Runnable(){
			@Override
			public void run() {
				writeVoice(data);
			}
		}).start();
	}
	
	private synchronized void writeVoice(byte[] data){
		try {
			if (voiceOutput != null)
				voiceOutput.write(data, 0, 1024);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
