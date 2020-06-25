import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       Thread server = new Server();
       server.start();
    }
    
}
