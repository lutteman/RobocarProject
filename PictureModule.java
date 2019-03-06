package car.project;
/**
 * @author Kayed Mahra
 * @version 2018-02-28
 * 
 */

import java.util.Observable;

import javax.swing.ImageIcon;

public class PictureModule extends Observable{
	private PiClientStream stream;
	private ImageIcon icon;
	
	public PictureModule() throws Exception {
		ImageIcon icon = new ImageIcon();
		
	}
	/**
	 * Continuesly calls the method getImage from the stream object
	 * and notifies the observer about an updated icon
	 * @throws Exception
	 */
	private void updateStream() throws Exception {
		while(true) {
			icon = stream.getImage();	
			setChanged();
			notifyObservers(this);
		}
		
	}
	/**
	 * Sets up connection between the client and server by creating a
	 * PiClientStream object
	 * @throws Exception
	 */
	
	public void setupStream() throws Exception {
		this.stream = new PiClientStream();
		updateStream();
	}
	/**
	 * Returns the image icon when called
	 * @return icon in form of an image
	 */
	public ImageIcon getIcon() {
		return icon;
	}

}
