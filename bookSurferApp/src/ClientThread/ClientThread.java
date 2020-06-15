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
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelBookSurfer.Buch;
import modelBookSurfer.Kapitel;

/**
 *
 * @author fabia
 */
public class ClientThread extends Thread {
    
    private static String hostName = "127.0.0.1";
    private static int portNumber = 6014;
    private int buchid;
    private Statement statement;
    

    public ClientThread(int buchid, Statement statement) {
        this.buchid =buchid;
        this.statement = statement;
    }

    @Override
    public void run() {
        List<Kapitel> kapitel = Kapitel.getKapitelToBuch(buchid, statement);
        Socket echoServerSocket = null;
        String hostName = "127.0.0.1";
        int portNumber = 6014;

        for (Kapitel k : kapitel) {
            try {
                echoServerSocket = new Socket(hostName, portNumber);
                ObjectOutputStream outputStream = new ObjectOutputStream(echoServerSocket.getOutputStream());
                InputStream is = echoServerSocket.getInputStream();
                is = echoServerSocket.getInputStream();
                outputStream.writeObject("booksTxt/" + k.getTextdateiurl());
                outputStream.flush();
                byte[] b = new byte[100];

                FileOutputStream fr = null;

                String[] parts = k.getTextdateiurl().split("/");
                String before = "data/buecher/";
                for (int i = 0; i < parts.length - 1; i++) {
                    File file2 = new File(before + parts[i]);
                    file2.mkdir();
                    before += parts[i];
                }
                System.out.println(k.getTextdateiurl());
                File file = new File("data/buecher/" + k.getTextdateiurl());
                file.createNewFile();
                fr = new FileOutputStream("data/buecher/" + k.getTextdateiurl());
                int by;
                while ((by = is.read()) != -1) {
                    fr.write(by);
                }
                fr.flush();
                fr.close();
                is.close();

            } catch (IOException ex) {
                Logger.getLogger(Buch.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
}
