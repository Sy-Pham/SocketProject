
public class RequestHandler {

    private final String data;
    private static boolean isAccess = false;

    public RequestHandler(String data) {
        this.data = data;
    }

    public String doGet() {

        String response = null;
        String path = data.split(" ", 0)[1];
        switch (path) {
            case "/index.html":
                response = ResponseHandler.getResponse("index.html",200, "index.html");
                break;
            case "/info.html":
                if (isAccess) {
                    response = ResponseHandler.getResponse("info.html",200, "info.html");
                }
                break;
            case "/404.html":
                response = ResponseHandler.getResponse("404.html",200, "404.html");
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
            response = ResponseHandler.getResponse("info.html", 303, "info.html");
        } else {
            response = ResponseHandler.getResponse("404.html", 303, "404.html");
        }
        return response;
    }

    boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

}
