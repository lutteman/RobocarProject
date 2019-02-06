package carProject;

public class CarClient extends ClientClass {
	float left;
	float right;
	float step;
	float limit;
	int identifier;

	public CarClient() {
		this.left = 0;
		this.right = 0;
		this.step = 0.10f;
		this.limit = 1.00f;
		//Identifier so that server will know what type of class it is
		this.identifier = 1;
	}
	/**
	 * 
	 * @return value to be sent to the left motor
	 */
	public float getLeftValue() {
		return this.left;
	}
	/**
	 * 
	 * @return value to be sent to the right motor
	 */
	public float getRightValue() {
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
	}
}
