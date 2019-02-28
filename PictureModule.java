package project;


import java.util.Observable;

import javax.swing.ImageIcon;

public class PictureModule extends Observable{
	private piClientStream stream;
	ImageIcon icon;
	
	public PictureModule() throws Exception {
		ImageIcon icon = new ImageIcon();
		System.out.println("pm constructor");
		
	}
	
	private void updateStream() throws Exception {
		while(true) {
			System.out.println("Updatestream");
			icon = stream.getImage();	
			setChanged();
			notifyObservers(this);
		}
		
	}
	
	public void setupStream() throws Exception {
		this.stream = new piClientStream();
		updateStream();
	}
	public ImageIcon getIcon() {
		return icon;
	}

}
