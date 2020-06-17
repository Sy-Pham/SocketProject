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
       
    }

    
    public String doGet() {
       
        String response = null;
        String method = data.split(" ", 0)[1];
        if(method.equals("/index.html"))
            response =  new ResponseHandler().getIndex();
        
        return response;
    }

    //Xử lý mật khẩu ở đây
    public String doPost() {
        String response = null;
        
        boolean isAuthenticated = false;
        
        // Xử Lý 
        
        
        
        if(isAuthenticated){
            response = new ResponseHandler().getInfor();
        }else
            response = new ResponseHandler().getError();
        
        return response;
    }

   
}
