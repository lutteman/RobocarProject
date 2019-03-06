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
	private CarModule carMod;
	private PiClient piClient;
	private Display runDisplay;
	private KeyboardListener key;
	private PictureModule picMod;
	public Program() throws UnknownHostException, SocketException{
		this.carMod = new CarModule();
		this.piClient = new PiClient();
		this.key = new KeyboardListener();
		try {
		this.picMod = new PictureModule();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.runDisplay = new Display(carMod,piClient,key,picMod);
	}
	/**
	 * The run method for the program.
	 * @throws Exception 
	 *
	 */
	
	public void run() throws Exception {
	carMod.addObserver(runDisplay);
	key.addObserver(runDisplay);
	picMod.addObserver(runDisplay);
	runDisplay.mainFrame();
	}
}
