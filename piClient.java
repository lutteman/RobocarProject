import java.net.*;
import java.io.*;



public class piClient {
	public static void main(String[] args) throws UnknownHostException,
	SocketException,IOException
	
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		InetAddress toAddr = InetAddress.getByName("192.168.137.103");
		int toPort = Integer.parseInt("9400");
		DatagramSocket socket = new DatagramSocket();
		
		while(true) {
			System.out.println("?");
			String message = in.readLine();
			if(message == null) {
				break;
			}
			byte[] data = message.getBytes();
			socket.send(new DatagramPacket(data, data.length, toAddr, toPort));
			
		}
		
		
		
	}

}
