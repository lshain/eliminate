package chat;

import java.io.BufferedInputStream;

import login.OnlineUser;
import room.RoomList;
import user.User;

// TODO: Auto-generated Javadoc
/**
 * The Class InputVoice.
 */
public class InputVoice implements Runnable {

	/** The input. */
	BufferedInputStream input;

	/** The user. */
	User user;

	/** The num bytes read. */
	int numBytesRead;

	/** The data. */
	byte[] data = new byte[1024];

	/**
	 * Instantiates a new input voice.
	 * 
	 * @param user
	 *            the user
	 * @param input
	 *            the input
	 */
	public InputVoice(User user, BufferedInputStream input) {
		this.user = user;
		this.input = input;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				numBytesRead = input.read(data);
				writeToOthers();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	/**
	 * Write to others.
	 */
	public void writeToOthers() {
		long roomID = OnlineUser.getSockets(user.getUserId()).getRoomID();
		if (roomID != 0) {
			for (String temp : RoomList.getRoom(roomID).getUserList()) {
				if (!temp.equals(user.getUserId())) {
					OnlineUser.getSockets(temp).writeVoiceData(data);
				}
			}
		}
	}

}
