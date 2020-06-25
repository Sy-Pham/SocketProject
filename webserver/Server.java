/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy Pham
 */
public class Server extends Thread {
    private static int port = 9000;
    private static String address = "localhost";
    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(port);
            
            System.out.println(String.format("Server is running under address: %s and port: %d\n", address, port));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try{
            while(server.isBound() && !server.isClosed()){
                Socket client = server.accept();
                
                System.out.println(String.format("client whose port is %d has been connected", client.getPort()));
                
                Thread newClient = new Client(client);
                newClient.start();
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(server != null)
                try {
                    server.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   
    
}
