package car.project;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class PiClientStream {
	private IplImage iPimg;
	private BufferedImage img;
	private ImageIcon icon;
	private OpenCVFrameGrabber frameGrabber;
	
	public PiClientStream() {
		frameGrabber = new OpenCVFrameGrabber("http://192.168.137.103:8160");
		frameGrabber.setFormat("mjpeg");
		icon = new ImageIcon();
		try {
			frameGrabber.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public ImageIcon getImage() throws Exception{
		if((iPimg = frameGrabber.grab())!= null) {
			img = iPimg.getBufferedImage();
			icon.setImage(img);
			System.out.println("pic");
			return icon;
		}else {
			System.out.println("no pic");
		}
		
		return null;
	}

}
