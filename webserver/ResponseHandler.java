import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ResponseHandler {

    private String getResponse(String htmlContent, int statusCode) {
        final String CRLF = "\r\n";
        return "HTTP/1.1 " + statusCode + CRLF
                + // status line
                "Content-Length: " + htmlContent.getBytes().length + CRLF
                + //header
//                "Location: " + location + CRLF +
                CRLF
                + htmlContent;
    }
    private String getResponse(String htmlContent, int statusCode, String location) {
        final String CRLF = "\r\n";
        return "HTTP/1.1 " + statusCode + CRLF
                + // status line
                "Content-Length: " + htmlContent.getBytes().length + CRLF
                + //header
                "Location: " + location + CRLF +
                CRLF
                + htmlContent;
    }
    //doc file index.html
    public String getIndex() {
        String content = readHTMLFile("index.html");
        return getResponse(content, 200);

    }

    public String getRedirectedInformation() {
        //303
        String content = readHTMLFile("info.html");

        //return getResponse(content,303);
        final String CRLF = "\r\n";
        return getResponse(content, 303, "/info.html");
    }

    public String getInformation() {
        //303
        String content = readHTMLFile("info.html");
        return getResponse(content, 200);

    }

    //doc file 404.html
    public String getError() {
        //404
        String content = readHTMLFile("404.html");
        return getResponse(content, 404);
    }

       
    
    public String getRedirectedError() {

        String content = readHTMLFile("404.html");
        return getResponse(content, 303, "/404.html");
    }

    public String readHTMLFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
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
