package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * 
 * @author Christopher
 *
 */
public class Display implements Observer {
	private Login loginObj;
	private CarModule CarV;
	private PictureModule picMod;
	private KeyboardListener key;
	private JLabel rSpeed;
	private JLabel lSpeed;
	private JButton wheels;
	private JLabel lblLed;
	private PiClient piclient;
	
	private JPanel imagePanel;
	private JLabel imageLabel;
	

	public Display(CarModule c, PiClient p, KeyboardListener k, PictureModule pm) {
		this.CarV = c;
		this.piclient = p;
		this.key = k;
		this.picMod = pm;
		loginObj = new Login();
	}

	/**
	 * Creating the main frame, BorderLayout witch will contain 3 panels.
	 * @throws Exception 
	 * 
	 */
	public void mainFrame() throws Exception {
		JFrame frame = new JFrame();
		JPanel mainPane = new JPanel(new BorderLayout());
		frame.getContentPane().add(mainPane);

		frame.setLocationRelativeTo(null);
		frame.setResizable(true);

		mainPane.add(imagePanel(), BorderLayout.CENTER);
		mainPane.add(keyPanel(), BorderLayout.SOUTH);
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
				// loginPanel();
			}
		});
		// test 1 end

		frame.pack();
		frame.setVisible(true);
		picMod.setupStream();

	}

	/**
	 * Creates a Panel with FlowLayout for displaying the camera image
	 * 
	 * @return a Panel
	 */
	public JPanel imagePanel() {
		imagePanel = new JPanel();
		imageLabel = new JLabel();
		imagePanel.add(imageLabel);
		
		return imagePanel;
	}

	/**
	 * Creates a Panel with GridBagLayout for displaying and handling the steering
	 * keys
	 * 
	 * @return a Panel
	 */
	public JPanel keyPanel() {

		JPanel keyPane = new JPanel();

		keyPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		wheels = new JButton("w");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		keyPane.add(wheels, c);
		wheels.addActionListener(e -> keyListener(e));

		wheels = new JButton("s");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		keyPane.add(wheels, c);
		wheels.addActionListener(e -> keyListener(e));

		wheels = new JButton("a");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		keyPane.add(wheels, c);
		wheels.addActionListener(e -> keyListener(e));

		wheels = new JButton("d");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		keyPane.add(wheels, c);
		wheels.addActionListener(e -> keyListener(e));

		return keyPane;
	}

	/**
	 * Creates a Panel with GridLayout for displaying the information about
	 * Speed/connection
	 * 
	 * @return a Panel
	 */
	public JPanel infoPanel1() {
		JPanel infoPane = new JPanel(new GridLayout(3, 2));

		JLabel connectText = new JLabel("Connected: ");
		infoPane.add(connectText);

		lblLed = new JLabel("�");
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
	 */
	public void createLoginFrame() {

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
	 * @param e
	 * @throws Exception 
	 */
	public void keyListener(ActionEvent e) {
		if (e.getActionCommand() == "w") {
			CarV.forward();	
		}
		if (e.getActionCommand() == "s") {
			CarV.backward();
		}
		if (e.getActionCommand() == "a") {
			CarV.left();
		}
		if (e.getActionCommand() == "d") {
			CarV.right();
		}

	}

	/**
	 * Update method witch is called from CarModule class with a notifyObserver.
	 * It updates the speed values in the infoPane.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg0 instanceof CarModule) {
			CarModule c1 = (CarModule) arg1;
				try {
					piclient.sendIntPair((c1.getLeftValue()),c1.getRightValue());
				}catch(Exception e){
					e.printStackTrace();
					
				}			

			rSpeed.setText(Integer.toString(c1.getRightValue()));
			lSpeed.setText(Integer.toString(c1.getLeftValue()));

		}
		else if(arg0 instanceof KeyboardListener) {
			KeyboardListener k1 = (KeyboardListener) arg1;
			try {
				piclient.sendIntPair((k1.getLeftValue()),k1.getRightValue());
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		else if(arg0 instanceof PictureModule ) {
			PictureModule pm = (PictureModule) arg1;
			imageLabel.setIcon(pm.getIcon());
			imagePanel.revalidate();
			imagePanel.repaint();
			System.out.println("update method");
			
			
		}

	}

}