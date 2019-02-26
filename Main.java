

public class Main {
	public static void main(String[] args) {
		
		CarModule carmod = new CarModule();
		Display runDisplay = new Display(carmod);
		carmod.addObserver(runDisplay);
		runDisplay.mainFrame();
	
	
	
	}

}
