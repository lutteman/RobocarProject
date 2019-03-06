package car.project;
/**
 * @author Mehdi Vahid
 * @version 2019-02-28
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class KeyboardListener extends Observable implements KeyListener{
	private JButton button;
	private int left;
	private int right;
	public KeyboardListener() {
		
	}
	
	/**
	 * The method for setting up and returning the button for manual driving
	 * 
	 * @return A JButton with a keyListener connected to it
	 */
	
	public JButton createButton(){
		button = new JButton("Key control");
		button.addKeyListener(this);
		return button;
	}
	/**
	 * Metod that handles key press
	 * 
	 * @param arg0 is a key
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int id = arg0.getKeyCode();
		StartID(id);
		
	}
	/**
	 * Metod that handles key release
	 * 
	 * @param arg0 is a key
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getKeyCode();
		EndID(id);
	}

	
	/**
	 * Default method, does nothing in our case
	 */
	@Override
    public void keyTyped(KeyEvent arg0) {
	}
	/**
	 * Method for checking what key is being pressed
	 * 
	 * @param i is the number of a key
	 */
	private void StartID(int i) {
	
		if (i == 37 && (right != 7 && left !=3)) {
			this.right = 7;
			this.left = 3;
			SetUpdated();
		}
		else if (i == 38 && (right != 8 && left !=8)) {
			this.right = 8;
			this.left = 8;
			SetUpdated();
		}
		else if (i == 39  && (right != 3 && left !=7)) {
			this.right = 3;
			this.left = 7;	
			SetUpdated();
		}
		else if (i == 40  && (right != 2 && left !=2)) {
			this.right = 2;
			this.left = 2;
			SetUpdated();
		}
	}
	/**
	 * Method for checking the key to release it
	 * @param i is a number of a key
	 */
	private void EndID(int i) {
		if (i == 37 || i == 38 || i == 39 || i == 40) {
			this.right = 5;
			this.left = 5;
		}
		SetUpdated();
	}
	
	/**
	 * Method for notifying observers
	 */
	private void SetUpdated() {
		setChanged();
		notifyObservers(this);
	}
	/**
	 * Method for returning right value
	 * @return right value
	 */
	public int getRightValue() {
		return this.right;
	}
	/**
	 * Method for returning left value
	 * @return left value
	 */
	public int getLeftValue() {
		return this.left;
	}
}