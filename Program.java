package car.project;

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
	public void run() {
	carMod.addObserver(runDisplay);
	key.addObserver(runDisplay);
	runDisplay.mainFrame();
	}
}