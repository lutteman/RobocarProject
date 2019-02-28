package car.project;

/**
 * 
 * 
 * @author Anton Lutteman
 * @version 2019-02-28
 *
 */

import java.net.SocketException;
import java.net.UnknownHostException;

public class Program {
	CarModule carMod;
	PiClient piClient;
	Display runDisplay;
	KeyboardListener key;
	public Program() throws UnknownHostException, SocketException {
		this.carMod = new CarModule();
		this.piClient = new PiClient();
		this.key = new KeyboardListener();
		this.runDisplay = new Display(carMod,piClient,key);
	}
	/**
	 * The run method for the program.
	 *
	 */
	
	public void run() {
	carMod.addObserver(runDisplay);
	key.addObserver(runDisplay);
	runDisplay.mainFrame();
	}
}
