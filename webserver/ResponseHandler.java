import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ResponseHandler {
    public String getResponse(String htmlContent, int responseCode) {
        final String CRLF = "\r\n";
        return "HTTP/1.1 " + responseCode    + CRLF +   // status line
                        "Content-Length: " + htmlContent.getBytes().length + CRLF + //header
                        //"Location: /infor.html" + CRLF +
                        CRLF +
                        htmlContent;
    }
    
    //doc file index.html
    public String getIndex(){
        String content = readFileHTML("src/index.html");
        return getResponse(content, 200);
        
    }
    
    //doc file infor.html
    public String getInformation(){
        //303
        String content = readFileHTML("src/info.html");
        //return getResponse(content,303);
        final String CRLF = "\r\n";
        return "HTTP/1.1 303"     + CRLF +   // status line
                        "Content-Length: " + content.getBytes().length + CRLF + //header
                        "Location: /infor.html" + CRLF +
                        CRLF +
                        content;
    }
    
    public String getInformation(boolean  b){
        //303
        String content = readFileHTML("src/info.html");
        return getResponse(content,200);
        
    }
    
    //doc file 404.html
    public String getError(){
        //404
        String content = readFileHTML("src/404.html");
         final String CRLF = "\r\n";
        return "HTTP/1.1 303"     + CRLF +   // status line
                        "Content-Length: " + content.getBytes().length + CRLF + //header
                        "Location: /404.html" + CRLF +
                        CRLF +
                        content;
    }
    
    public String getError(boolean  b){
        //404
        String content = readFileHTML("src/404.html");
        return getResponse(content,200);
    }
    
    //doc file html
    public String readFileHTML(String fileName){
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
