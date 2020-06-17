/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy Pham
 */
public class Client extends Thread {

    private Socket client;
    private InputStream input = null;
    private OutputStream output = null;
    private boolean isGetMethod = false;
    private boolean isPostMethod = false;
    private String infor;

    public Client(Socket client) {
        this.client = client;
        if (this.client != null) {
            System.out.println("Connected successfully\n");
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
                isGetMethod = getRequest();
                String response = null;
                RequestHandler requestHandler = new RequestHandler(infor);
                
                if (isGetMethod) {
                    response = requestHandler.doGet();
                }else{
                    response = requestHandler.doPost();
                }

                if (response != null) {
                    output.write(response.getBytes());

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.printf("port %d Disconnect\n", client.getPort());
            if (client != null) {
                try {
                    client.close();

                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    void writeFile(int c) {
        String nameFile = "log.txt";
        try {
            FileWriter fw = new FileWriter(nameFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            out.print((char) c);

            out.close();
            bw.close();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
                infor = data;
                isGetMethod = true;
            }

            if (!isGetMethod) {
                String[] str = data.split(": ", 0);
                if (str[0].equals("Content-Length")) {
                    length = Integer.parseInt(str[1]);
                }
            }

            if (data.isEmpty()) {
                if (isGetMethod) {
                    break;
                } else {
                    
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < length; i++) {
                        str.append((char)reader.read());
                    }
                    infor = str.toString();
                    System.out.println(infor);
                    break;
                } 
            }

        }

        return isGetMethod;
    }

}
