package carProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login {
	private JFrame frame;
	private JTextField user;
	private JPasswordField password;
	private JLabel username;
	private JLabel userpassword;
	private JButton loginbutton;

	public Login() {
	}

	public void login() {
		createLoginFrame();
	}

	/**
	 * 
	 *	Creates the LoginFrame, that is used for user input
	 *	of username and password
	 */
	private void createLoginFrame() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.fill = GridBagConstraints.HORIZONTAL;

		username = new JLabel("Username: ");
		gb.gridx = 0;
		gb.gridy = 0;
		gb.gridwidth = 1;
		panel.add(username, gb);

		user = new JTextField(20);
		gb.gridx = 1;
		gb.gridy = 0;
		gb.gridwidth = 2;
		panel.add(user, gb);

		userpassword = new JLabel("Password: ");
		gb.gridx = 0;
		gb.gridy = 1;
		gb.gridwidth = 1;
		panel.add(userpassword, gb);

		password = new JPasswordField(20);
		gb.gridx = 1;
		gb.gridy = 1;
		gb.gridwidth = 2;
		panel.add(password, gb);

		loginbutton = new JButton("Login");
		
		// An action listener is added to react when a user is done
		// with their input and presses enter or the login button
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkCredentials(getUsername(), getPassword())) {
					System.out.println("Correct Password!");
					closeLogin();
				} else {
					System.out.println("Wrong Password! Try Again");
				}
			}
		});
		
		JPanel panel2 = new JPanel();
		panel2.add(loginbutton);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(panel);
		panel3.add(panel2);
		frame = new JFrame("Login to application");
		frame.add(panel3);
		frame.getRootPane().setDefaultButton(loginbutton);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * 
	 * @return username as a String
	 */

	private String getUsername() {
		return user.getText().trim();
	}

	/**
	 * 
	 * @return password as a String
	 */
	private String getPassword() {
		return new String(password.getPassword());
	}

	/**
	 *
	 * @param user
	 * @param password
	 * @return the result of the credentials check
	 */
	private boolean checkCredentials(String user, String password) {
		return (user.equals("user") && password.equals("password"));
		
	}
	/**
	 * Closes the frame window
	 */
	public void closeLogin() {
		frame.setVisible(false);
	}
	
}
