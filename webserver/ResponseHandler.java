/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

/**
 *
 * @author Sy Pham
 */
public class ResponseHandler {
    
    public String getResponse(){
        String htmlContent = "<html><body>"
                + "<h1>Login</h1>"
                + "<form method=\"POST\"><input name=\"username\" action=\"/\"><input name=\"password\" type=\"password\"/><input type=\"submit\" value=\"Login\"></form>"
                + "</body></html>";
        final String CRLF = "\r\n";
        String response = 
            "HTTP/1.1 200 OK" + CRLF +   // status line
             "Content-Length: " + htmlContent.getBytes().length + CRLF + //header
                //"Location: /infor.html" + CRLF + 
             CRLF +
                htmlContent;
                
        return response;
    }
    
    public String getIndex(){
        String response = getResponse();
        
        return response;
    }
}
