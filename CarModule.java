package car.project;

/**
 * 
 * @author Anton Lutteman
 * @version 2019-02-28
 *
 */



import java.util.Observable;
/**
 * CarModule Object extends observable.
 * Is used for handling values of movement of the motors.
 * Notifies it observer after a change has been made.
 */
public class CarModule extends Observable {
	private int left;
	private int right;
	
	/**
	 * Setting up a CarModule object with default values
	 * 5 being the default value for not moving
	 */
	public CarModule() {
		this.left = 5;
		this.right = 5;
	}

	/**
	 * Method for accessing left motor value
	 * @return value to be sent to the left motor
	 */
	public int getLeftValue() {
		return this.left;
	}

	/**
	 * Method for accessing right motor value
	 * @return value to be sent to the right motor
	 */
	public int getRightValue() {
		return this.right;
	}

	/**
	 * Updates values to represent a forward movement
	 */
	public void forward() {
		if (this.left == 6 && this.left == 6) {
			this.left = 9;
			this.right = 9;
		} else if (this.left == 5 && this.right == 5) {
			this.left = 6;
			this.right = 6;
		} else {
			this.left = 5;
			this.right = 5;
		}
		sendValues();
	}

	/**
	 * Updates values to represent a backward movement
	 */
	public void backward() {
		if (this.left == 5 && this.right == 5) {
			this.left = 3;
			this.right = 3;
		} else if (this.left == 3 && this.right == 3) {
			this.left = 1;
			this.right = 1;
		} else {
			this.left = 5;
			this.right = 5;
		}
		sendValues();

	}

	/**
	 * Updates values to represent a right movement
	 */
	public void right() {
		this.left = 8;
		this.right = 3;
		sendValues();
		this.left = 5;
		this.right = 5;
	}

	/**
	 * Updates values to represent a left movement
	 */
	public void left() {
		this.right = 8;
		this.left = 3;
		sendValues();
		this.right = 5;
		this.left = 5;

	}

	/**
	 * Notifies Display that the car module has been changed
	 */
	private void sendValues() {
		setChanged();
		notifyObservers(this);

	}
}