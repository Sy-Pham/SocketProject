/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sy Pham
 */
public class RequestHandler {

    private final String data;

    public RequestHandler(String data) {
        this.data = data;
    }
    
    public String doGet() {
       
        String response = null;
        String method = data.split(" ", 0)[1];
        if(method.equals("/index.html"))
            response =  new ResponseHandler().getIndex();
        else if(method.equals("/infor.html"))
            response = new ResponseHandler().getInformation();
        else if(method.equals("/404.html"))
            response = new ResponseHandler().getError();
        return response;
    }

    //Xử lý mật khẩu ở đây
    public String doPost() {
        String response = null;
        
        boolean isAuthenticated = false;
        
        // Xử Lý
        int beginIndexUsn = data.indexOf("=") + 1;
        int endIndexUsn = data.indexOf("&");
        String username = data.substring(beginIndexUsn, endIndexUsn);
        int beginIndexPw = data.indexOf("=", endIndexUsn) + 1;
        int endIndexPw = data.length();
        String password = data.substring(beginIndexPw, endIndexPw);

        isAuthenticated = login(username,password);
        if(isAuthenticated){
            response = new ResponseHandler().getRedirectedInformation();
        }else {
            response = new ResponseHandler().getRedirectedError();
        }
        return response;
    }

    boolean login(String username, String password){
        return username.equals("admin") && password.equals("admin");
    }
   
}
