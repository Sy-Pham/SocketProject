public class SubStr {
    public static void main(String[] args) {
        String data = "username=abc&password=123";
        int beginIndexUsn = data.indexOf("=") + 1;
        int endIndexUsn = data.indexOf("&");
        String username = data.substring(beginIndexUsn, endIndexUsn);
        int beginIndexPw = data.indexOf("=", endIndexUsn) + 1;
        int endIndexPw = data.length();
        String password = data.substring(beginIndexPw, endIndexPw);
    }
}
