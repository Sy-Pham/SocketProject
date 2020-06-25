/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy Pham
 */
public class Client extends Thread {

    private final Socket client;
    private InputStream input = null;
    private OutputStream output = null;
    private String information;

    public Client(Socket client) {
        this.client = client;
        if (this.client != null) {
            System.out.print( client.getPort() + " Connected successfully\n");
        } else {
            return;
        }
        try {
            input = client.getInputStream();
            output = client.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {

        try {

            while (client.isBound() && !client.isClosed()) {
                boolean isGetMethod = getRequest();
                String response = null;
                RequestHandler requestHandler = new RequestHandler(information);
                
                if (isGetMethod) {
                    response = requestHandler.doGet();
                }else{
                    response = requestHandler.doPost();
                }

                if (response != null) {

                   
                    try(PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"))){
                      
                        writer.print(response);
                        
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.printf("port %d Disconnect\n", client.getPort());
            try {
                client.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

   

    boolean getRequest() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        boolean isGetMethod = false;
        boolean isPostMethod = false;
        int length = 0;

        String data = null;
        while ((data = reader.readLine()) != null) {
            System.out.println(data);
            String[] startLine = data.split(" /", 0);
            String method = startLine[0];
            if (method.equals("GET")) {
               
                information = data;
                isGetMethod = true;

            }

            if (!isGetMethod) {
                String[] str = data.split(": ", 0);
                if (str[0].equals("Content-Length")) {
                    length = Integer.parseInt(str[1]);
                }
            }

            if (data.isEmpty()) {
                if (!isGetMethod) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < length; i++) {
                        str.append((char) reader.read());
                    }
                    information = str.toString();
                    System.out.println(information);
                }
                break;
            }

        }

        return isGetMethod;
    }

}
