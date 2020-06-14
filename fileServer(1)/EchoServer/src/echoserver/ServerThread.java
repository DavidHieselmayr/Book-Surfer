/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabia
 */
public class ServerThread implements Runnable{
    
    private Socket newSocket;
    
    public ServerThread(Socket socket) {
        this.newSocket = socket;
    }

    @Override
    public void run() {
        newSocket();
    }
    
    public void newSocket() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(newSocket.getInputStream());
            OutputStream os = newSocket.getOutputStream();
            String path = null;
            try {
                path = (String) ois.readObject();
                System.out.println(path);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            FileInputStream fr = new FileInputStream(path);
            byte b[] = new byte[100];
            while (fr.read(b) >= 0) {
                os.write(b);
            }
            os.flush();
            os.close();
            System.out.println("File übermittelt");
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
