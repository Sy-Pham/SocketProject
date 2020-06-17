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
    
    //demo login
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
    
    //doc file index.html
    public String getIndex(){
        // demo login --> viet lai
        String response = getResponse();
        
        return response;
    }
    
    //doc file infor.html
    public String getInfor(){
        String response = null;
        
        return response;
    }
    
    //doc file 404.html
    public String getError(){
        String response = null;
        
        return response;
    }
    
    //doc file html
    public String readFileHTML(String fileName){
        String content = null;
        
        return content;
    }
}
