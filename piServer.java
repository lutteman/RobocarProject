package piCommunication;
import java.net.*;
import java.io.*;

public class piServer {
	
	public static void main(String[] args) throws SocketException, IOException {
		int recievePort = Integer.parseInt("9400");
		DatagramSocket socket = new DatagramSocket(recievePort);
		byte[] data = new byte[1024];
		String[] values = new String[2];
		
		while(true) {
			DatagramPacket packet = new DatagramPacket(data,data.length);
			socket.receive(packet);
			System.out.println("Message from: " +packet.getAddress().getHostName());
			String message = new String(packet.getData(),0,packet.getLength());
			values = message.split(",");
			
			System.out.println("Left: " + values[0]);
			System.out.println("Right: " + values[1]);
			
		}
		
	}

}
