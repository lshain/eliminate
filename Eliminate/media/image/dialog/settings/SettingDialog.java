/*
 * 
 */
package eliminate.view.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eliminate.model.sound.Audio;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;

// TODO: Auto-generated Javadoc
/**
 * The Class SettingDialog.
 */
@SuppressWarnings("serial")
public class SettingDialog {

	/**
	 * The Class SettingPanel.
	 */
	class SettingPanel extends JPanel implements ActionListener {

		/**
		 * Instantiates a new setting panel.
		 */
		public SettingPanel() {
			setLayout(null);
			try {
				this.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println(e.getX() + "   " + e.getY());
					}
				});

				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(565, 8, 50, 50);
				jbExit.addActionListener(this);

				musicVolume = new JSlider();
				musicVolume.setBounds(180, 235, 305, 50);
				musicVolume.setBackground(null);
				musicVolume.setMinimum(0);
				musicVolume.setMaximum(100);
				musicVolume.setOpaque(false);
				musicVolume.setValue((int) (musicdb * 50));
				add(musicVolume);
				ImageIcon icon = new ImageIcon(thumb);
				UIDefaults defaults = UIManager.getDefaults();
				defaults.put("Slider.horizontalThumbIcon", icon);
				musicVolume.setEnabled(true);
				musicVolume.setPaintTrack(false);

				// new Thread(new Runnable(){
				//
				// @Override
				// public void run() {
				// music.play("media/music/main.wav",true);
				// }
				//
				// }).start();

				musicVolume.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						int volume = ((JSlider) arg0.getSource()).getValue();
						musicdb = (float) (volume / 50.0);
						AudioPlayer.setMusicVolume(musicdb);
					}

				});

				effectVolume = new JSlider();
				effectVolume.setBorder(null);
				effectVolume.setBounds(180, 140, 305, 55);
				effectVolume.setOpaque(false);
				effectVolume.setMinimum(0);
				effectVolume.setMaximum(100);
				effectVolume.setValue((int) (effectdb * 50));
				;
				add(effectVolume);
				effectVolume.setEnabled(true);
				effectVolume.setPaintTrack(false);

				effectVolume.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						// TODO Auto-generated method stub

						int volume = ((JSlider) arg0.getSource()).getValue();
						effectdb = (float) (volume / 50.0);
						AudioPlayer.setEffects(effectdb);
					}

				});

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbExit) {
				jd.dispose();
				music.stop();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon(background_pic).getImage(), 0, 0,
					this.getWidth(), this.getHeight(), this);
		}
	}

	/** The jd. */
	JDialog jd;

	/** The background_pic. */
	private String background_pic = "media/image/dialog/settings/settings.png";

	/** The thumb. */
	private String thumb = "media/image/dialog/settings/bt.png";

	// ÍË³ö°´Å¥
	/** The exit. */
	private String exit = "media/image/dialog/exit.png";

	/** The exit_down. */
	private String exit_down = "media/image/dialog/exit_down.png";

	/** The exit_hover. */
	private String exit_hover = "media/image/dialog/exit_hover.png";

	/** The jb exit. */
	JButton jbExit;

	/** The music volume. */
	private JSlider musicVolume;

	/** The effect volume. */
	private JSlider effectVolume;

	/** The effect. */
	private Audio effect = new Audio();

	/** The music. */
	private Audio music = new Audio();

	/** The musicdb. */
	private static float musicdb = 1f;

	/** The effectdb. */
	private static float effectdb = 1f;

	/**
	 * Instantiates a new setting dialog.
	 * 
	 * @param owner
	 *            the owner
	 * @param modal
	 *            the modal
	 */
	public SettingDialog(JFrame owner, boolean modal) {
		jd = new JDialog(owner, modal);
		jd.setContentPane(new SettingPanel());
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(654, 431);
		jd.setLocationRelativeTo(owner);
		jd.setVisible(true);

	}
}