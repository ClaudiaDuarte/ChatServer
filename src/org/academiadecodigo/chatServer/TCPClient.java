package org.academiadecodigo.chatServer;

import java.io.*;
import java.net.Socket;

/**
 * @author Claudia Duarte
 * @version 27/10/15.
 */
public class TCPClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Get parameters from command line arguments
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        String clientMessage;

        // Block while connecting to the server
        Socket clientSocket = new Socket(hostName, portNumber);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            // Client message
            clientMessage = in.readLine();
            if(clientMessage.equals("quit")) {
            	out.write(clientMessage);
            	out.close();
                clientSocket.close();
                System.exit(0);
            } else {
                out.write(clientMessage);
                out.newLine();
                out.flush();
            }
        }
    }
}

