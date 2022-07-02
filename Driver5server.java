package driver;

import server.DefaultServerSocket;

public class Driver5server {

	public static void main(String[] args) {

		DefaultServerSocket s1 = new DefaultServerSocket(4444);
		s1.start();
	}
}
