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
		byte[] data;
		while(true) {
			System.out.println("?");
			String message = in.readLine();
			if(message == null) {
				break;
			}
			
			switch(message) {
			case "w": data = ("9,9").getBytes();
			break;
			case "a": data = ("6,5").getBytes();
			break;
			case "s": data = ("4,4").getBytes();
			break;
			case "d": data = ("5,6").getBytes();
			break;
			case "e": data = ("9,1").getBytes();
			break;
			default: data = ("5,5").getBytes();
			}
			
			socket.send(new DatagramPacket(data, data.length, toAddr, toPort));
			
		}
		
		
		
	}

}
