package eliminate.model.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class Audio {
	private final int SIZE = 1024 * 64;

	private String url;

	private boolean loop;

	private SourceDataLine sdl;

	private boolean isPlaying = false;

	private AudioInputStream ais;

	private FloatControl fc;

	private float db = 1f;

	public Audio() {
	}

	private void init() {
		try {
			ais = AudioSystem.getAudioInputStream(new File(url));
			AudioFormat aif = ais.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play(String url, boolean l) {
		this.url = url;
		this.loop = l;
		stop();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		isPlaying = true;
		playProcess();

		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (loop && isPlaying) {
					playProcess();
				}
				isPlaying = false;
			}

		}).start();
	}

	private void playProcess() {
		init();
		sdl.start();
		readFile();
	}

	private void readFile() {
		fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
		fc.setValue(db);
		int nByte = 0;
		try {
			byte[] buffer = new byte[SIZE];

			while (nByte != -1 && isPlaying) {
				try {
					nByte = ais.read(buffer, 0, SIZE);
					if (nByte != -1) {
						sdl.write(buffer, 0, nByte);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sdl.drain();
			sdl.stop();
			ais.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setVolume(float value) {
		// value可以用来设置音量，从0-2.0
		float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value)
				/ Math.log(10.0) * 20.0);
		db = dB;
		if (fc != null) {
			fc.setValue(db);
		}
	}

	public void stop() {
		if (isPlaying) {
			isPlaying = false;
		} else {
			System.out.println("The music is not playing!");
		}
	}
}