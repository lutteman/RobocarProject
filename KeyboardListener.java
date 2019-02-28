package car.project;

/**
 * 
 * @author Mehdi Vahid
 * @author Anton Lutteman
 * @version 2019-02-28
 *
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
	JButton button;
	int left;
	int right;
	public KeyboardListener() {
		
	}
	
	/**
	 * The method for setting up and returning the button for manual driving
	 * 
	 * @return A JButton with a keyListener connected to it
	 */
	
	public JButton createButton(){
		button = new JButton("Manual");
		button.addKeyListener(this);
		return button;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getKeyCode();
		StartID(id);
		//Vänster37 upp höger ner
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getKeyCode();
		EndID(id);
	}

	
	
	
	@Override
    public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
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
	
	private void EndID(int i) {
		if (i == 37 || i == 38 || i == 39 || i == 40) {
			this.right = 5;
			this.left = 5;
		}
		SetUpdated();
	}
	
	private void SetUpdated() {
		setChanged();
		notifyObservers(this);
	}
	public int getRightValue() {
		return this.right;
	}
	public int getLeftValue() {
		return this.left;
	}
}