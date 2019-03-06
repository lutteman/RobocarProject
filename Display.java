package car.project;

/**
 * 
 * @author Christopher Höglind
 * @author Anton Lutteman
 * @version 2019-02-28
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Display Object implements observer Used to create and update the GUI Observes.
 * objects and handles changes of the observed objects. Changes made from user.
 * input, pictures received from the server.
 */
public class Display implements Observer {
	private boolean loggedin;

	private Login loginObj;
	private CarModule CarV;
	private KeyboardListener key;
	private JLabel rSpeed;
	private JLabel lSpeed;
	private JButton wheels;
	private JButton manual;
	private JLabel lblLed;
	private PiClient piclient;
	// nya classer för att rensa i displayklassen
	private keyPanel keyP;

	private PictureModule picMod;
	private JPanel imagePanel;
	private JLabel imageLabel;

	/**
	 * Connects the objects observed by and called upon by the display object
	 * Creates a keyPanel object, and login object
	 * 
	 * @param c  (required) CarModule Object connected to the display
	 * @param p  (required) PiClient Object connected to the display
	 * @param k  (required) KeyboardListener Object connected to the display
	 * @param pm (required) PictureModule Object connected to the display
	 */
	public Display(CarModule c, PiClient p, KeyboardListener k, PictureModule pm) {
		this.CarV = c;
		this.piclient = p;
		this.key = k;
		this.picMod = pm;
		this.keyP = new keyPanel(this.CarV, this.key);
		loginObj = new Login();
		loggedin = false;
	}

	/**
	 * Creating the main frame, BorderLayout witch will contain 3 panels.
	 * 
	 */
	public void mainFrame() {
		JFrame frame = new JFrame();
		JPanel mainPane = new JPanel(new BorderLayout());
		frame.getContentPane().add(mainPane);

		frame.setLocationRelativeTo(null);

		mainPane.add(imagePanel(), BorderLayout.CENTER);
		mainPane.add(keyP.createKeyPanel(), BorderLayout.SOUTH);
		mainPane.add(infoPanel1(), BorderLayout.WEST);

		// meny test 1 start
		JMenuBar menuBar = new JMenuBar();
		mainPane.add(menuBar, BorderLayout.NORTH);
		JMenu menu = new JMenu("Login");
		menuBar.add(menu);
		JMenuItem loginScreen = new JMenuItem("Login");
		menu.add(loginScreen);
		loginScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createLoginFrame();
			}
		});
		menuBar.add(createInfoMenu());
		
		// test 1 end

		frame.pack();
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setResizable(true);
		try {
			picMod.setupStream();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Creates a Panel with FlowLayout for displaying the camera image
	 * 
	 * @return a Panel
	 */
	private JPanel imagePanel() {
		imagePanel = new JPanel();
		imageLabel = new JLabel();
		imagePanel.add(imageLabel);

		return imagePanel;
	}

	/**
	 * 
	 * @return a menu item with info option
	 */
	public JMenu createInfoMenu() {
		JMenu menu = new JMenu("Info");
		JMenuItem readme = new JMenuItem("Read me");
		menu.add(readme);
		readme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Click “login” in the menu and enter your credentials. \r\n" + 
						"You will then be able to see the live stream from the robot and operate it using the mouse or by clicking “Key control” use the arrow keys.",
						"Read me",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		return menu;
	}

	/**
	 * Creates a Panel with GridLayout for displaying the information about
	 * Speed/connection
	 * 
	 * @return a Panel
	 */
	private JPanel infoPanel1() {
		JPanel infoPane = new JPanel(new GridLayout(3, 2));

		JLabel connectText = new JLabel("Connected: ");
		infoPane.add(connectText);

		lblLed = new JLabel("•");
		lblLed.setForeground(Color.RED);
		infoPane.add(lblLed);

		JLabel rSpeedText = new JLabel("R.Speed :");
		infoPane.add(rSpeedText);

		rSpeed = new JLabel("0");
		infoPane.add(rSpeed);

		JLabel lSpeedText = new JLabel("L.Speed :");
		infoPane.add(lSpeedText);

		lSpeed = new JLabel("0");
		infoPane.add(lSpeed);

		return infoPane;
	}

	/**
	 * Creating a login frame
	 * 
	 */
	private void createLoginFrame() {

		JFrame loginFrame = new JFrame("Login to application");
		JTextField user;
		JPasswordField password;
		JLabel username;
		JLabel userpassword;
		JButton loginbutton;
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
			@Override
			public void actionPerformed(ActionEvent e) {

				String userInput = user.getText().trim();
				String pwInput = new String(password.getText());
				String pwFile = "";
				try {
					pwFile = loginObj.getPwforUser(userInput);
				} catch (IOException IOerror) {
					IOerror.printStackTrace();
				}

				if (loginObj.checkCredentials(pwInput, pwFile)) {
					lblLed.setForeground(Color.GREEN);
					loginFrame.dispose();
					loggedin = true;

				} else {
					JOptionPane errorPane = new JOptionPane();
					errorPane.showMessageDialog(null, "Wrong username or password", "Invalide Login",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel panel2 = new JPanel();
		panel2.add(loginbutton);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(panel);
		panel3.add(panel2);
		loginFrame.add(panel3);
		loginFrame.getRootPane().setDefaultButton(loginbutton);

		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);
		loginFrame.pack();
		loginFrame.setVisible(true);
	}

	/**
	 * Method for sending event from button pressed to CarModule class.
	 * 
	 * @param e the buttons actionevent
	 */
	private void keyListener(ActionEvent e) {
		if (e.getActionCommand() == "Forward") {
			CarV.forward();

		}
		if (e.getActionCommand() == "Back") {
			CarV.backward();
		}
		if (e.getActionCommand() == "Left") {
			CarV.left();
		}
		if (e.getActionCommand() == "Right") {
			CarV.right();
		}

	}

	/**
	 * Update method for the Observer Handles changes on the CarModule object by
	 * sending values to the PiClient and updating the GUI Handles changes on the
	 * KeyboardListener object by sending values to the PiClient and updating the
	 * GUI Handles changes on the PictureModule object by updating the GUI with a
	 * new picture
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (loggedin == true) {
			if (arg0 instanceof CarModule) {
				CarModule c1 = (CarModule) arg1;
				try {
					piclient.sendIntPair((c1.getLeftValue()), c1.getRightValue());
				} catch (Exception e) {
					e.printStackTrace();

				}

				rSpeed.setText(Integer.toString(c1.getRightValue()));
				lSpeed.setText(Integer.toString(c1.getLeftValue()));

			} else if (arg0 instanceof KeyboardListener) {
				KeyboardListener k1 = (KeyboardListener) arg1;
				try {
					piclient.sendIntPair((k1.getLeftValue()), k1.getRightValue());
				} catch (Exception e) {
					e.printStackTrace();

				}
				rSpeed.setText(Integer.toString(k1.getRightValue()));
				lSpeed.setText(Integer.toString(k1.getLeftValue()));
			} else if (arg0 instanceof PictureModule) {
				PictureModule pm = (PictureModule) arg1;
				imageLabel.setIcon(pm.getIcon());
				imagePanel.revalidate();
				imagePanel.repaint();
			}

		}
	}

}