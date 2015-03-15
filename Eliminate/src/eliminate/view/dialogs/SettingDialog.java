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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.single.GamePanel;

@SuppressWarnings("serial")
public class SettingDialog {
	class SettingPanel extends JPanel implements ActionListener {
		public SettingPanel() {
			setLayout(null);

			try {

				jbExit = MyButton.makeButton(exit, exit_down, exit_hover);
				add(jbExit);
				jbExit.setBounds(759, 103, 45, 45);
				jbExit.addActionListener(this);

				musicVolume = new JSlider();

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
						// TODO Auto-generated method stub
						int volume = ((JSlider) arg0.getSource()).getValue();
						musicdb = (float) (volume / 50.0);
						AudioPlayer.setMusicVolume(musicdb);
					}

				});

				effectVolume = new JSlider();
				effectVolume.setBorder(null);

				effectVolume.setOpaque(false);
				effectVolume.setMinimum(0);
				effectVolume.setMaximum(100);
				effectVolume.setValue((int) (effectdb * 50));
				add(effectVolume);
				effectVolume.setEnabled(true);
				effectVolume.setPaintTrack(false);

				effectVolume.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						int volume = ((JSlider) arg0.getSource()).getValue();
						effectdb = (float) (volume / 50.0);
						AudioPlayer.setEffects(effectdb);
					}

				});

				peopleVolume = new JSlider();
				peopleVolume.setBorder(null);

				peopleVolume.setOpaque(false);
				peopleVolume.setMinimum(0);
				peopleVolume.setMaximum(100);
				peopleVolume
						.setValue((int) (LoginController.getChatVoice() * 50));
				add(peopleVolume);
				peopleVolume.setEnabled(true);
				peopleVolume.setPaintTrack(false);

				peopleVolume.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						int volume = ((JSlider) arg0.getSource()).getValue();
						LoginController.setChatVoice((float) (volume / 50.0));
					}

				});

				direction = new JLabel(new ImageIcon(switchDirection));
				add(direction);
				direction.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						AudioPlayer.playButtonOne();
						if (direction.getX() == 464) {
							direction.setBounds(534, 373, 148, 60);
							GamePanel.setDirectDropDown(true);
							dropDown = true;
						} else {
							direction.setBounds(464, 373, 148, 60);
							dropDown = false;
							GamePanel.setDirectDropDown(false);
						}
					}
				});

				if (single == true) {
					musicVolume.setBounds(464, 235, 225, 50);
					effectVolume.setBounds(464, 162, 225, 55);
					peopleVolume.setBounds(464, 305, 225, 55);
					if(dropDown){
						direction.setBounds(534, 373, 148, 60);
					} else{
						direction.setBounds(464, 373, 148, 60);
					}
				}

				if (single == false) {
					musicVolume.setBounds(464, 263, 225, 50);
					effectVolume.setBounds(464, 190, 225, 55);
					peopleVolume.setBounds(464, 333, 225, 55);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AudioPlayer.playButtonOne();
			if (e.getSource() == jbExit) {
				jd.dispose();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (single == true)
				g.drawImage(new ImageIcon(background_single).getImage(), 0, 0,
						this.getWidth(), this.getHeight(), this);
			else
				g.drawImage(new ImageIcon(background_multi).getImage(), 0, 0,
						this.getWidth(), this.getHeight(), this);
		}
	}
	JDialog jd;

	boolean single;

	private static boolean dropDown = false;
	
	private String background_single = "media/image/dialog/settings/settings_single.png";

	private String background_multi = "media/image/dialog/settings/settings_multi.png";
	private String switchDirection = "media/image/dialog/settings/bt2.png";
	private String thumb = "media/image/dialog/settings/bt.png";
	// ÍË³ö°´Å¥
	private String exit = "media/image/dialog/exit.png";

	private String exit_down = "media/image/dialog/exit_down.png";
	private String exit_hover = "media/image/dialog/exit_hover.png";
	JButton jbExit;
	private JSlider musicVolume;

	private JSlider effectVolume;
	private JSlider peopleVolume;
	private JLabel direction;
	private static float musicdb = 1f;

	private static float effectdb = 1f;
	public SettingDialog(JFrame owner, boolean modal, boolean single) {
		jd = new JDialog(owner, modal);
		this.single = single;
		jd.setContentPane(new SettingPanel());
		jd.setUndecorated(true);
		jd.setBackground(new Color(0f, 0f, 0f, 0f));
		jd.setSize(1103, 618);
		jd.setLocation(owner.getLocation());
		jd.setVisible(true);
	}
}