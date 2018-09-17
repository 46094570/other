import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "eyJqdGkiOiJiMjI5YmJiMmFkYWE0OGU3YmE2MDAzMmE1MDM2YzQwZCIsImV4cCI6MTUxOTcwMzIwNjAwMCwicm9sZXMiOlsiUk9MRV9BRE1VQ19BRE1JTiJdLCJ1c2VyX2lkIjoxfQ==";
        byte[] bytes = Base64.decodeBase64(str.getBytes());
        String result = new String(bytes,"UTF-8");
        System.out.println("result:"+result);
    }
}
