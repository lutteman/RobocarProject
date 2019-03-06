package car.project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class keyPanel extends JPanel {
CarModule CarV;
KeyboardListener key;
JPanel panel;
JButton wheels;
JButton manual;
public keyPanel(CarModule CarV, KeyboardListener key) {
	this.CarV = CarV;
	this.key = key;
}

/**
 * Creates a Panel with GridBagLayout for displaying and handling the steering
 * keys
 * 
 * @return a JPanel
 */
public JPanel createKeyPanel() {

	JPanel keyPane = new JPanel();

	keyPane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	wheels = new JButton("Forward");
	c.weightx = 0.5;
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 1;
	c.gridy = 0;
	keyPane.add(wheels, c);
	wheels.addActionListener(e -> keyListener(e));
	
	manual = key.createButton();
	c.weightx = 0.5;
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 2;
	c.gridy = 0;
	keyPane.add(manual, c);

	wheels = new JButton("Back");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
	keyPane.add(wheels, c);
	wheels.addActionListener(e -> keyListener(e));

	wheels = new JButton("Left");
	c.weightx = 0.5;
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 1;
	keyPane.add(wheels, c);
	wheels.addActionListener(e -> keyListener(e));

	wheels = new JButton("Right");
	c.weightx = 0.5;
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 2;
	c.gridy = 1;
	keyPane.add(wheels, c);
	wheels.addActionListener(e -> keyListener(e));

	return keyPane;
}
/**
 * Method for sending event from button pressed to CarModule class.
 * @param e the buttons actionevent
 */
public void keyListener(ActionEvent e) {
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

}
