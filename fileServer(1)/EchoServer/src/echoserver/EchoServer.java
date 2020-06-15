package echoserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        int portNumber = 6014;
        Socket newSocket = null;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Server started on port " + portNumber);

        while (true) {
            try {
                newSocket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(newSocket.getInputStream());
                OutputStream os = newSocket.getOutputStream();
                System.out.println("Connection established with  " + newSocket.getRemoteSocketAddress());
                try {
            
                
                String path = null;
                try {
                    path = (String) ois.readObject();
                    System.out.println(path);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                FileInputStream fr = new FileInputStream(path);
                byte b[] = new byte[100];
                int index;
                while ((index = fr.read()) != -1) {
                    os.write(index);
                }
                os.flush();
                os.close();
                System.out.println("File übermittelt");
               
            
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (!newSocket.isClosed()) {
                    newSocket.close();
                }
                os.close();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                e.printStackTrace();
            }
        }
    }

    
}
