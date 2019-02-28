package car.project;

import java.net.*;
import java.io.*;
import java.util.*;

public class PiClient {

	private static final String ADDRESS = "192.168.137.103";
	private static final String PORT = "9400";
	private BufferedReader in;
	private InetAddress toAddr;
	private int toPort;
	private DatagramSocket socket;
	
	/**
	 * Constructs the PiClient to be used for sending information the the pi
	 * 
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public PiClient() throws UnknownHostException, SocketException {
		in = new BufferedReader(new InputStreamReader(System.in));
		toAddr = InetAddress.getByName(ADDRESS);
		toPort = Integer.parseInt(PORT);
		socket = new DatagramSocket();
	}
	
	/**
	 * Creates a string with the information to be sent to the pi
	 * 
	 * @param i a value of speed to be sent to the pi
	 * @param j a value of speed to be sent to the pi
	 * @throws IOException
	 */
	public void sendIntPair(int i, int j) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(i));
		sb.append(",");
		sb.append(Integer.toString(j));
		//test string to the what values are about to be sent
		System.out.println("Int pair: " + (i) + " " + (j));
		String message = sb.toString();
		byte[] data = message.getBytes();
		socket.send(new DatagramPacket(data, data.length, toAddr, toPort));
	}
}
