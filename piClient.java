package piCommunication;
import java.net.*;
import java.io.*;
import java.util.*;



public class piClient extends Thread{

		private static final String ADDRESS = "127.0.0.1";
		private static final String PORT = "9400";
		private BufferedReader in;
		private InetAddress toAddr;
		private int toPort;
		private DatagramSocket socket;
		private Random rand;
		
		public piClient() throws UnknownHostException, SocketException {
			in = new BufferedReader(new InputStreamReader(System.in));
			toAddr = InetAddress.getByName(ADDRESS);
			toPort = Integer.parseInt(PORT);
			socket = new DatagramSocket();
			rand = new Random();
			
			
		}
		
		
	public void sendIntPair(int i, int j) throws IOException{
			StringBuilder sb = new StringBuilder();
			sb.append(Integer.toString(i));
			sb.append(",");
			sb.append(Integer.toString(j));

			
			System.out.println("Int pair:");
			String message = sb.toString();
			byte[] data = message.getBytes();
			socket.send(new DatagramPacket(data, data.length, toAddr, toPort));	
		}
	
	public void run(){
		while(true) {
			try {
				sleep(3000);
				sendIntPair(rand.nextInt(100),rand.nextInt(100));
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
			
			
		}
		
		
	}


}
