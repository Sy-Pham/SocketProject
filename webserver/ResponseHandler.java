import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseHandler {

    public static String getResponse(String fileName, int statusCode, String location) {
        String htmlContent = readHTMLFile(fileName);
        final String CRLF = "\r\n";
        return "HTTP/1.1 " + statusCode + CRLF
                + // status line
                "Content-Length: " + htmlContent.getBytes().length + CRLF
                + 
                "Location: " + location + CRLF +
                //header
                CRLF
                + htmlContent;
    }

    private static String readHTMLFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
            String line = in.readLine();
            while (line != null) {
                content.append(line).append("\n");
                line = in.readLine();
            }
        } catch (IOException ex) {
             Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content.toString();
    }
}
