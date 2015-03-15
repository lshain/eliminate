/*
 * 
 */
package eliminate.model.voice;

//��������Ƶ����Ĵ��룺
//Playback.java
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import user.User;

// TODO: Auto-generated Javadoc
/**
 * 
 * Title: VoiceChat Description: �����Ƶ���������� Copyright: Copyright (c) 2001
 * Company: .
 * 
 * @author
 * @version 1.0
 */

public class Playback implements Runnable {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Socket voiceSocket;
		try {
			voiceSocket = new Socket("127.0.0.1", 8003);
			ObjectOutputStream writer = new ObjectOutputStream(
					voiceSocket.getOutputStream());
			writer.writeObject(new User("wuzhao", "password"));
			new Playback(voiceSocket).start();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/** The buf size. */
	final int bufSize = 16384;

	/** The line. */
	SourceDataLine line;

	/** The thread. */
	Thread thread;

	/** The s. */
	Socket s;

	float db = 1f;;

	/**
	 * Instantiates a new playback.
	 * 
	 * @param s
	 *            the s
	 */
	public Playback(Socket s) {// ������ ȡ��socket�Ի������������
		this.s = s;
	}

	public float getDB() {
		return db;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		AudioFormat format = new AudioFormat(8000, 16, 2, true, true);// AudioFormat(float
																		// sampleRate,
																		// int
																		// sampleSizeInBits,
																		// int
																		// channels,
																		// boolean
																		// signed,
																		// boolean
																		// bigEndian��
		BufferedInputStream playbackInputStream;
		try {
			playbackInputStream = new BufferedInputStream(new AudioInputStream(
					s.getInputStream(), format, 2147483647));// ��װ����Ƶ�����������������Ǿ���ѹ�������ڴ˼��׽�ѹ��
		} catch (IOException ex) {
			return;
		}
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format, bufSize);
		} catch (LineUnavailableException ex) {
			return;
		}
		byte[] data = new byte[1024];// �˴�����Ĵ�С��ʵʱ�Թ�ϵ���󣬿ɸ���������е���
		int numBytesRead = 0;
		line.start();
		while (thread != null) {
			try {
				numBytesRead = playbackInputStream.read(data);
				line.write(data, 0, numBytesRead);
			} catch (IOException e) {
				break;
			}
		}
		if (thread != null) {
			line.drain();
		}
		line.stop();
		line.close();
		line = null;
	}

	public void setDB(float value) {
		// value��������������������0-2.0
		float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value)
				/ Math.log(10.0) * 20.0);
		db = dB;
		((FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN))
				.setValue(db);
	}

	/**
	 * Start.
	 */
	public void start() {
		thread = new Thread(this);
		thread.setName("Playback");
		thread.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		thread = null;
	}
}