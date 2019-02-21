package piServerPic;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.*;
 
public class piServerPic{   
    public static void main(String[] args) throws IOException,UnknownHostException,
    SocketException
    {   
    	
        File file = new File("C:/Users/Kay/Desktop/Kayed.jpg");
         
        BufferedImage image = null;
     
        image = ImageIO.read(file);
                
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg",baos);
     
        
        baos.flush();
        byte[] buffer = baos.toByteArray();
        
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("192.168.137.103");
        System.out.println(buffer.length);
        
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length,
        		IPAddress, 9450);
        clientSocket.send(packet);
    	
         
   
    }
}

