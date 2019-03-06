package car.project;
/**
 * @author 
 * 
 */

import java.util.Observable;

import javax.swing.ImageIcon;

public class PictureModule extends Observable{
	private PiClientStream stream;
	ImageIcon icon;
	
	public PictureModule() throws Exception {
		ImageIcon icon = new ImageIcon();
		
	}
	
	private void updateStream() throws Exception {
		while(true) {
			icon = stream.getImage();	
			setChanged();
			notifyObservers(this);
		}
		
	}
	
	public void setupStream() throws Exception {
		this.stream = new PiClientStream();
		updateStream();
	}
	public ImageIcon getIcon() {
		return icon;
	}

}
