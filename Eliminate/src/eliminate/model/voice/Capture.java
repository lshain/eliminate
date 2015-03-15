/*
 * 
 */
package eliminate.model.voice;

//��Ƶ���񲿷֣�
//Capture.java
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

// TODO: Auto-generated Javadoc
/**
 * Title: VoiceChat Description: ��Ƶ��׽��¼������ Copyright: Copyright (c) 2001
 * Company:.
 * 
 * @author
 * @version 1.0
 */
public class Capture implements Runnable {

	/** The line. */
	TargetDataLine line;

	/** The thread. */
	Thread thread;

	/** The s. */
	Socket s;

	/** The captrue output stream. */
	BufferedOutputStream captrueOutputStream;

	/**
	 * Instantiates a new capture.
	 * 
	 * @param s
	 *            the s
	 */
	public Capture(Socket s) {// ������ ȡ��socket�Ի�����������
		this.s = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			captrueOutputStream = new BufferedOutputStream(s.getOutputStream());// ���������
																				// �˴����Լ���ѹ��������ѹ������
		} catch (IOException ex) {
			return;
		}
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
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, line.getBufferSize());
		} catch (Exception ex) {
			return;
		}
		byte[] data = new byte[1024];// �˴���1024����������е�����Ӧ�������1024Ӧ����һ��
		int numBytesRead = 0;
		line.start();
		while (thread != null) {
			numBytesRead = line.read(data, 0, 16);// ȡ���ݣ�1024���Ĵ�Сֱ�ӹ�ϵ��������ٶȣ�һ��ԽСԽ�죬
			try {
				captrueOutputStream.write(data, 0, numBytesRead);// д��������
			} catch (Exception ex) {
				break;
			}
		}
		line.stop();
		line.close();
		line = null;
		try {
			captrueOutputStream.flush();
			captrueOutputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Start.
	 */
	public void start() {
		thread = new Thread(this);
		thread.setName("Capture");
		thread.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		thread = null;
	}

}
