

public class Main {
	public static void main(String[] args)throws Exception {
		
		CarModule carmod = new CarModule();
		Display runDisplay = new Display(carmod);
		
		carmod.addObserver(runDisplay);
		
	
	
	
	}

}
