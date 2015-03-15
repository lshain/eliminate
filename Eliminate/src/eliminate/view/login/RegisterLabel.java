/*
 * 
 */
package eliminate.view.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eliminate.controller.MainFrame;
import eliminate.controller.register.RegisterController;
import eliminate.model.sound.AudioPlayer;
import eliminate.view.MyButton;
import eliminate.view.MyPasswordField;
import eliminate.view.MyTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterLabel.
 */
@SuppressWarnings("serial")
public class RegisterLabel extends JLabel implements ActionListener {

	/** The set_ pword_ field. */
	JPasswordField set_Pword_Field;

	/** The set_ i d_ field. */
	JTextField set_ID_Field;

	/** The set_ pword2_ field. */
	JPasswordField set_Pword2_Field;

	/** The login_register. */
	private static String login_register = "media/image/login/login_register.png";

	/** The cancel. */
	private String cancel = "media/image/login/bt/cancelBT.png";

	/** The cancel_hover. */
	private String cancel_hover = "media/image/login/bt/cancel_hover.png";

	/** The cancel_down. */
	private String cancel_down = "media/image/login/bt/cancel_down.png";

	/** The cancel_bt. */
	JButton cancel_bt;

	/** The ok. */
	private String ok = "media/image/login/bt/okBT.png";

	/** The ok_hover. */
	private String ok_hover = "media/image/login/bt/ok_hover.png";

	/** The ok_down. */
	private String ok_down = "media/image/login/bt/ok_down.png";

	/** The ok_bt. */
	JButton ok_bt;

	/**
	 * Instantiates a new register label.
	 */
	public RegisterLabel() {
		super(new ImageIcon(login_register));
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
		// TODO Auto-generated method stub
		if (e.getSource() == cancel_bt) {
			AudioPlayer.playButtonOne();
			TogglePanel.changeTo(true);
			clearTextField();
		} else if (e.getSource() == ok_bt) {
			AudioPlayer.playButtonOne();
			register();
			clearTextField();
		}

	}

	/**
	 * Clear text field.
	 */
	private void clearTextField() {
		set_Pword_Field.setText("");
		set_ID_Field.setText("");
		set_Pword2_Field.setText("");
	}

	/**
	 * Inits the.
	 */
	private void init() {
		setLayout(null);
		setOpaque(false);
		try {

			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(e.getPoint());
				}
			});
			// set_ok_Button
			ok_bt = MyButton.makeButton(ok, ok_down, ok_hover);
			add(ok_bt);
			ok_bt.setBounds(34, 404, 131, 52);
			ok_bt.addActionListener(this);

			// set_cancel_Button
			cancel_bt = MyButton.makeButton(cancel, cancel_down, cancel_hover);
			add(cancel_bt);
			cancel_bt.setBounds(200, 404, 131, 52);
			cancel_bt.addActionListener(this);

			// set_setID_TextField
			set_ID_Field = MyTextField.makeTextField(35, 145, 280, 34);
			add(set_ID_Field);
			set_ID_Field.addActionListener(this);

			// set_setPassword_TextField
			set_Pword_Field = MyPasswordField.makePasswordField(35, 265, 280,
					34);
			add(set_Pword_Field);
			set_Pword_Field.addActionListener(this);

			// set_setPassword_again_TextField
			set_Pword2_Field = MyPasswordField.makePasswordField(35, 344, 280,
					34);
			add(set_Pword2_Field);
			set_Pword2_Field.addActionListener(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Register.
	 */
	private void register() {
		String userName = set_ID_Field.getText().trim();
		if (userName.equals("")) {
			MainFrame.showMsgDialog("用户名不为空！", "注册用户");
			return;
		}

		String password1 = String.valueOf(set_Pword_Field.getPassword()).trim();
		String password2 = String.valueOf(set_Pword2_Field.getPassword())
				.trim();
		if (!password1.equals(password2)) {
			MainFrame.showMsgDialog("您两次输入的密码不一致，请确认后输入！", "注册用户");
		} else if (password1.length() < 6 || password1.length() > 30) {
			MainFrame.showMsgDialog("密码应为6~30位！", "注册用户");
			return;
		} else {
			if (RegisterController.register(userName, password1)) {
				clearTextField();
			}
		}
	}
}