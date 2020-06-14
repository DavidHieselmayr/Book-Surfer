package echoclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class EchoClient {
    public static void main(String[] args) throws IOException {
         
        Socket echoServerSocket = null;
        String hostName = "127.0.0.1";
        int portNumber = 6014;
 
        try {
            echoServerSocket = new Socket(hostName, portNumber);
           
            byte []b= new byte[20000];
            InputStream is = echoServerSocket.getInputStream();
            FileOutputStream fr = new FileOutputStream("ankunft.txt");
            
            
            while(is.read(b) >= 0){
                fr.write(b);
            }
            fr.flush();
            fr.close();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } finally {
            if (echoServerSocket != null) {
                try {
                    echoServerSocket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
