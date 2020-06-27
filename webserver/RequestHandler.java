
public class RequestHandler {

    private final String data;
    private static boolean isAccess = false;

    public RequestHandler(String data) {
        this.data = data;
    }

    public String doGet() {

        String response = null;
        String method = data.split(" ", 0)[1];
        switch (method) {
            case "/index.html":
                response = new ResponseHandler().getIndex();
                break;
            case "/info.html":
                if (isAccess) {
                    response = new ResponseHandler().getInformation();                   
                }
                break;
            case "/404.html":
                response = new ResponseHandler().getError();
                break;
        }
        return response;
    }

    public String doPost() {
        String response = null;

        boolean isAuthenticated = false;

        int beginIndexUsn = data.indexOf("=") + 1;
        int endIndexUsn = data.indexOf("&");
        int beginIndexPw = data.indexOf("=", endIndexUsn) + 1;
        int endIndexPw = data.length();
        if (beginIndexUsn == 0 || endIndexUsn == -1 || beginIndexPw == 0) {
            return null;
        }
        String username = data.substring(beginIndexUsn, endIndexUsn);
        String password = data.substring(beginIndexPw, endIndexPw);

        isAuthenticated = login(username, password);
        if (isAuthenticated) {
            isAccess = true;
            response = new ResponseHandler().getRedirectedInformation();
        } else {
            response = new ResponseHandler().getRedirectedError();
        }
        return response;
    }

    boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

}
