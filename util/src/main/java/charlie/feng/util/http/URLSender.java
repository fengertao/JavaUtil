package charlie.feng.util.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLSender {

    public static void main(String[] args) {
        byte[] inputBytes = new byte[1000];
        try {
            URL url = new URL("http://webgedi.ssmb.com/webgedi/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            displayURLinfo(url);
            InputStream is = (InputStream) con.getContent();
//			int i;
            while ((is.read(inputBytes)) > 0) {
                String inputStr = new String(inputBytes);
                System.out.print(inputStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void displayURLinfo(URL url) {
        System.out.println("getAuthority:" + url.getAuthority());
        System.out.println("getFile:" + url.getFile());
        System.out.println("getHost:" + url.getHost());
        System.out.println("getPath:" + url.getPath());
        System.out.println("getPort:" + url.getPort());
        System.out.println("getProtocol:" + url.getProtocol());
        System.out.println("getQuery:" + url.getQuery());
        System.out.println("getRef:" + url.getRef());
        System.out.println("getUserInfo:" + url.getUserInfo());
        System.out.println("ended");
    }

}
