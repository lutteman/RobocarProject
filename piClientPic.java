package piServerPic;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.net.*;

public class piClientPic {
	public static void main(String[] args) throws UnknownHostException,
	SocketException, IOException{
		JFrame frame = new JFrame("WHOS HOUSE? RONS HOUSE!");
		ImageIcon icon = new ImageIcon();
		JPanel panel = new JPanel();
		JLabel label = new JLabel(icon);
		frame.add(panel.add(label));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//File file = new File("C:/Users/Kay/Desktop/Output.jpg");
		DatagramSocket recieveSocket = new DatagramSocket(Integer.parseInt("9600"));
		byte[] dataReceive = new byte[262000];
			DatagramPacket packet = new DatagramPacket(dataReceive, dataReceive.length);
			recieveSocket.receive(packet);
			System.out.println("Packet SIze: " + packet.getLength());
			
			InputStream in = new ByteArrayInputStream(packet.getData());
			BufferedImage image = ImageIO.read(in);
			//för att spara på datorn
			//ImageIO.write(image, "jpg", new File("C:/Users/Kay/Desktop/output.jpg"));
			//Visas i fönster
			
			icon.setImage(image);
			label.setLocation(null);
			
		
		
	}

}
