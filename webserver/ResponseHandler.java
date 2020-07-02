import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResponseHandler {

    public static String getResponse(String fileName, int statusCode, String location) {
        String htmlContent = readHTMLFile(fileName);
        final String CRLF = "\r\n";
        return "HTTP/1.1 " + statusCode + CRLF
                + // status line
                "Content-Length: " + htmlContent.getBytes().length + CRLF
                + //header
                "Location: " + location + CRLF +
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
