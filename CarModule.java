

import java.util.Observable;

public class CarModule extends Observable{
	int left;
	int right;
	int step;
	int limit;
	int identifier;

	public CarModule() {
		this.left = 0;
		this.right = 0;
		this.step = 1;
		this.limit = 10;
		//Identifier so that server will know what type of class it is
		this.identifier = 1;
	}
	

	/**
	 * 
	 * @return value to be sent to the left motor
	 */
	public int getLeftValue() {
		return this.left;
	}
	/**
	 * 
	 * @return value to be sent to the right motor
	 */
	public int getRightValue() {
		return this.right;
	}
	
	/**
	 * Updates values to represent a forward movement
	 */
	public void forward() {
		this.left += step;
		this.right += step;
		if (this.left > limit)
			this.left = limit;
		if (this.right >= limit)
			this.right = limit;
		
		changeIsGonnaCome();
	}
	/**
	 * Updates values to represent a backward movement
	 */
	public void backward() {
		this.left += -step;
		this.right += -step;
		if (this.left < -limit)
			this.left = -limit;
		if (this.right <= -limit)
			this.right = -limit;
		
		changeIsGonnaCome();
	}
	/**
	 * Updates values to represent a right movement
	 */
	public void right() {
		this.left += -step;
		this.right += step;
		if(this.left < -limit)
			this.left = -limit;
		if(this.right > limit)
			this.right = limit;
		
		changeIsGonnaCome();
	}
	/**
	 * Updates values to represent a left movement
	 */
	public void left() {
		this.left += step;
		this.right += -step;
		if(this.left > limit)
			this.left = limit;
		if(this.right < -limit)
			this.right = -limit;
		
		changeIsGonnaCome();
	}
	public void changeIsGonnaCome() {
		setChanged();
		notifyObservers(this);
		
	}
}