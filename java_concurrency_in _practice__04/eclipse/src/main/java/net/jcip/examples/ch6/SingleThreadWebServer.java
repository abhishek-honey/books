package net.jcip.examples.ch6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SingleThreadWebServer
 * <p/>
 * Sequential web server
 *
 * @author Brian Goetz and Tim Peierls
 */

public class SingleThreadWebServer {
	public static void main(String[] args) {
		try (ServerSocket socket = new ServerSocket(80)) {
			while (true) {
				Socket connection = socket.accept();
				handleRequest(connection);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void handleRequest(Socket connection) {
		System.out.println(connection);
	}
}
