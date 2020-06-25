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
        return response;
    }

    public String doPost() {
        String response = null;
        
        boolean isAuthenticated = false;
        System.out.println(data);
        int beginIndexUsn = data.indexOf("=") + 1;
        int endIndexUsn = data.indexOf("&");
        int beginIndexPw = data.indexOf("=", endIndexUsn) + 1;
        int endIndexPw = data.length();
//        if (beginIndexUsn == 0 || endIndexUsn == -1 || beginIndexPw == 0) {
//            return null;
//        }
        String username = data.substring(beginIndexUsn, endIndexUsn);
        String password = data.substring(beginIndexPw, endIndexPw);

        isAuthenticated = login(username,password);
        if(isAuthenticated){
            response = new ResponseHandler().getInformation();
        }else {
            response = new ResponseHandler().getError();
        }
        return response;
    }

    boolean login(String username, String password){
        return username.equals("admin") && password.equals("admin");
    }
   
}
