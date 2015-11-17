package org.academiadecodigo.chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Claudia Duarte
 * @version 27/10/15.
 */
public class TCPServer {
	public static void main(String[] args) throws IOException {

		// Get parameters from command line arguments
		int portNumber = Integer.parseInt(args[0]);

		// Bind to local port and block while waiting for client connections
		ServerSocket serverSocket = new ServerSocket(portNumber);

		while (true) {
			System.out.println("Waiting for client...");

			Socket clientSocket = serverSocket.accept();
			System.out.println("Client accepted");
			
			new TCPServer.SocketProcessor(clientSocket).start();
		}

	}

	static class SocketProcessor extends Thread {

		Socket socket;

		public SocketProcessor(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			// Client message
			BufferedReader clientMsg;
			try {
				clientMsg = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				while (!socket.isClosed()) {
					String clientMessage = clientMsg.readLine();

					if (!clientMessage.equals("quit")) {
						System.out.println(clientMessage);
					} else {
						clientMsg.close();
						socket.close();
						System.out.println("The connection is closed.");
						//System.exit(0);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
