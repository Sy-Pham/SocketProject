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
public class RequestHandler {

    private String data;

    public RequestHandler(String data) {
        this.data = data;
        //verifyRequest();
    }

    /*
    public String verifyRequest() {
        String methodInfo = request.split("\r\n", 0)[0];
        String method = methodInfo.split("\\/", 0)[0];

        return method;
    }*/

    
    public String doGet() {
       
        String response = null;
        String method = data.split(" ", 0)[1];
        if(method.equals("/index.html"))
            response =  new ResponseHandler().getIndex();
        
        return response;
    }

    public String doPost() {
        String response = null;
        
        return response;
    }

    /*
    public String handleRequest() {

        String response = null;

        //String method = verifyRequest();
        if (method.equals("GET ")) {
            response = doGet();
        } else if (method.equals("POST ")) {
            doPost(request);
        }

        return response;
    }*/
}
