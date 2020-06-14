package echoserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        int portNumber = 6014;
        Socket clientSocket = null;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Server started on port " + portNumber);

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Connection established with  " + clientSocket.getRemoteSocketAddress());
                Thread t = new Thread(new ServerThread(clientSocket));
                t.start();
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                e.printStackTrace();
            } finally {
                if (clientSocket != null) {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    
}
