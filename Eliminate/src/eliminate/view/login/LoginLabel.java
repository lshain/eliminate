/*
 * 
 */
package eliminate.view.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eliminate.controller.MainFrame;
import eliminate.controller.login.LoginController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyPasswordField;
import eliminate.view.MyTextField;
import eliminate.view.start.MainPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginLabel.
 */
@SuppressWarnings("serial")
public class LoginLabel extends JLabel implements ActionListener {

	/** The Pword_ field. */
	JPasswordField Pword_Field;

	/** The I d_ field. */
	JTextField ID_Field;

	/** The background. */
	private static String background = "media/image/login/login.png";

	/** The log_in. */
	private String log_in = "media/image/login/bt/loginBT.png";

	/** The log_in_hover. */
	private String log_in_hover = "media/image/login/bt/loginBT_hover.png";

	/** The log_in_down. */
	private String log_in_down = "media/image/login/bt/loginBT_down.png";

	/** The log_in_bt. */
	JButton log_in_bt;

	/** The register. */
	private String register = "media/image/login/bt/registerBT.png";

	/** The register_hover. */
	private String register_hover = "media/image/login/bt/registerBT_hover.png";

	/** The register_down. */
	private String register_down = "media/image/login/bt/registerBT_hover.png";

	/** The register_bt. */
	JButton register_bt;

	/**
	 * Instantiates a new login label.
	 */
	public LoginLabel() {
		super(new ImageIcon(background));
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == register_bt) {
			AudioPlayer.playButtonOne();
			TogglePanel.changeTo(false);
			clearText();
		} else if (e.getSource() == log_in_bt) {
			AudioPlayer.playButtonOne();
			login();
		}
	}

	/**
	 * Clear text.
	 */
	private void clearText() {
		ID_Field.setText("");
		Pword_Field.setText("");
	}

	/**
	 * Inits the.
	 */
	private void init() {
		setOpaque(false);
		setLayout(null);
		try {
			// set_login_in_Button
			log_in_bt = MyButton.makeButton(log_in, log_in_down, log_in_hover);
			add(log_in_bt);
			log_in_bt.setBounds(20, 302, 292, 78);
			log_in_bt.addActionListener(this);

			// set_register_Button
			register_bt = MyButton.makeButton(register, register_down,
					register_hover);
			add(register_bt);
			register_bt.setBounds(26, 405, 307, 27);
			register_bt.addActionListener(this);

			// set_ID_TextField
			ID_Field = MyTextField.makeTextField(10, 108, 240, 34);
			add(ID_Field);
			ID_Field.addActionListener(this);

			// set_Password_TextField
			Pword_Field = MyPasswordField.makePasswordField(10, 199, 240, 34);
			add(Pword_Field);
			Pword_Field.addActionListener(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Login.
	 */
	private void login() {
		final String userName = ID_Field.getText().trim();
		final String password = String.valueOf(Pword_Field.getPassword())
				.trim();
		if (userName.length() == 0 || password.length() == 0) {
			MainFrame.showMsgDialog("用户名密码不为空！", "用户登录");
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (LoginController.Login(userName, password)) {
					MainPanel panel = new MainPanel();
					MainFrame.setContentPanel(panel);
					clearText();
				}
			}
		}).start();
		MainFrame.showLoadingDialog();
	}
}