import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 *
 * 
 */
public class VLCtest {
    

public static void main(String[] args) throws Exception {
OpenCVFrameGrabber frameGrabber = new OpenCVFrameGrabber("http://192.168.137.103:8160"); 
    frameGrabber.setFormat("mjpeg");
    frameGrabber.start();
    IplImage iPimg = frameGrabber.grab();
    CanvasFrame canvasFrame = new CanvasFrame("Camera");
    canvasFrame.setCanvasSize(iPimg.width(), iPimg.height());
    boolean coolkid = true;
    while(coolkid) {
    	 try {
    	    	while (canvasFrame.isVisible() && (iPimg = frameGrabber.grab()) != null) {
    	            canvasFrame.showImage(iPimg);
    	        }
    	    }catch(Exception e) {
    	    System.out.println("catched");
    	    try {
    	    	  frameGrabber = new OpenCVFrameGrabber("http://192.168.137.103:8160"); 
    	    	    frameGrabber.setFormat("mjpeg");
    	    	    frameGrabber.start();
    	    	    iPimg = frameGrabber.grab();
    	    	    
    	    }catch(Exception e2) {
    	    	System.out.println("retrying");
    	    }
    	  
    	    }
    	
    }
   
    
    frameGrabber.stop();
    canvasFrame.dispose();
    System.exit(0);
}

public static void reconnect()throws Exception{
	OpenCVFrameGrabber frameGrabber = new OpenCVFrameGrabber("http://192.168.137.103:8160"); 
    frameGrabber.setFormat("mjpeg");
    frameGrabber.start();
    IplImage iPimg = frameGrabber.grab();
    CanvasFrame canvasFrame = new CanvasFrame("Camera");
    canvasFrame.setCanvasSize(iPimg.width(), iPimg.height());
}
}