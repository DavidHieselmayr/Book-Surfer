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
public class ServerThread extends Thread {

    private Socket newSocket;
    private ObjectInputStream ois;
    private OutputStream os;

    public ServerThread(Socket socket, ObjectInputStream ois, OutputStream os) {
        this.newSocket = socket;
        this.ois = ois;
        this.os = os;
    }

    @Override
    public void run() {

        
    }

    public void newSocket() {

    }
}
