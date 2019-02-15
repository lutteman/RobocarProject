package piCommunication;

import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args) throws UnknownHostException, SocketException {
		piClient client = new piClient();
		client.run();
	}

}
