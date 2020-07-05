import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {

    private final Socket client;
    private InputStream input;
    private OutputStream output;
    private BufferedReader reader;
    private String information;

    public Client(Socket client) {
        this.client = client;
        
        try {
            input = client.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
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
                String response;
                RequestHandler requestHandler = new RequestHandler(information);
                
                if (isGetMethod) {
                    response = requestHandler.doGet();
                }else{
                    response = requestHandler.doPost();
                }

                if (response != null) {
                    output.write(response.getBytes());
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.printf("port %d Disconnect\n", client.getPort());
            try {
                input.close();
                reader.close();
                output.close();
                client.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    boolean getRequest() throws IOException {

        
        boolean isGetMethod = false;
        int length = 0;

        String data;
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
                }
                break;
            }

        }
        
        
        return isGetMethod;
    }

}
