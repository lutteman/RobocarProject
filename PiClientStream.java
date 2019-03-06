package car.project;
/**
 * @author Kayed Mahra
 * @version 2019-02-28
 * 
 */

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
		frameGrabber = new OpenCVFrameGrabber("http://192.168.137.75:8160");
		frameGrabber.setFormat("mjpeg");
		icon = new ImageIcon();
		try {
			frameGrabber.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Uses the openCV library to grab an image of the format IP-image from the
	 * IP-stream. Converts it into a buffered image that is then set as an image of
	 * an icon that is then returned
	 * 
	 * @author Kayed Mahra
	 * @version 2019-02-25
	 * @return icon with an image or null
	 * @throws Exception
	 */
	public ImageIcon getImage() throws Exception {
		if ((iPimg = frameGrabber.grab()) != null) {
			img = iPimg.getBufferedImage();
			icon.setImage(img);
			return icon;
		}

		return null;
	}

}
