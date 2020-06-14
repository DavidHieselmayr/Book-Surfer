/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelBookSurfer.Buch;
import modelBookSurfer.Kapitel;

/**
 *
 * @author fabia
 */
public class ClientThread implements Runnable {
    
    private static String hostName = "127.0.0.1";
    private static int portNumber = 6014;
    private Kapitel k;
    

    public ClientThread(Kapitel k) {
        this.k =k;
    }

    @Override
    public void run() {
        ObjectOutputStream outputStream = null;
        Socket echoServerSocket = null;
                try {
                    echoServerSocket = new Socket(hostName, portNumber);
                    outputStream = new ObjectOutputStream(echoServerSocket.getOutputStream());
                    try {
                        outputStream.writeObject("booksTxt/" + k.getTextdateiurl());
                        outputStream.flush();
                        byte[] b = new byte[100];
                        InputStream is = echoServerSocket.getInputStream();
                        FileOutputStream fr = null;
                        
                        
                        
                        String[] parts = k.getTextdateiurl().split("/");
                        String before = "data/buecher/";
                        for(int i = 0; i < parts.length-1; i++){
                            File file2 = new File(before + parts[i]);
                            file2.mkdir();
                            before += parts[i];
                        }
                        System.out.println(k.getTextdateiurl());
                        File file = new File("data/buecher/" + k.getTextdateiurl());
                        file.createNewFile();
                        fr = new FileOutputStream("data/buecher/" + k.getTextdateiurl());
                        while (is.read(b) >= 0) {
                            fr.write(b);
                        }
                        fr.flush();
                        fr.close();
                        echoServerSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Buch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                   
            try {
                echoServerSocket.close();
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
