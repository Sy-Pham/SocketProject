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
    private static boolean isAccess = false;

    /*
    public RequestHandler(String data, boolean isAuthenticated) {
        this.data = data;
        this.isAuthenticated = isAuthenticated;
    }*/
    public RequestHandler(String data) {
        this.data = data;
    }

    public String doGet() {

        String response = null;
        String method = data.split(" ", 0)[1];

        if (method.equals("/index.html")) {
            response = new ResponseHandler().getIndex();

        } else if (method.equals("/infor.html") && isAccess) {
            response = new ResponseHandler().getInformation();
            isAccess = false;

        } else if (method.equals("/404.html") && isAccess) {
            response = new ResponseHandler().getError();
            isAccess = false;
        }

        return response;
    }

    //Xử lý mật khẩu ở đây
    public String doPost() {
        String response = null;

        boolean isAuthenticated = false;

        // Xử Lý
        /*
        int beginIndexUsn = data.indexOf("=") + 1;
        int endIndexUsn = data.indexOf("&");
        String username = data.substring(beginIndexUsn, endIndexUsn);
        int beginIndexPw = data.indexOf("=", endIndexUsn) + 1;
        int endIndexPw = data.length();
        String password = data.substring(beginIndexPw, endIndexPw);*/
        
        String[] sep = data.split("&", 0);
        String username = sep[0].split("=", 0)[1];
        String password = sep[1].split("=", 0)[1];

        isAuthenticated = login(username, password);
        if (isAuthenticated) {
            isAccess = true;
            response = new ResponseHandler().getRedirectedInformation();
        } else {
            isAccess = true;
            response = new ResponseHandler().getRedirectedError();
        }
        return response;
    }

    boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

}
