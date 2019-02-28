package project;

import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) throws UnknownHostException, SocketException {

		Program program = new Program();
		try {
			program.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
