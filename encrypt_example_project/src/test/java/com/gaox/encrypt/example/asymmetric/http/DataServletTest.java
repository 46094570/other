package com.gaox.encrypt.example.asymmetric.http;

import com.gaox.encrypt.example.asymmetric.http.utils.RSACoder;
import com.gaox.encrypt.example.asymmetric.http.utils.HttpUtils;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;


public class DataServletTest {
    private static final String PUBLIC_KEY = "305c300d06092a864886f70d0101010500034b003048024100921214137517b0665ca414ac252c594175ca7e11ecae7902327b468a998ffe6ff3b8f42bc5a599ab1945bd70095ed08f494ae732658aa39075f92ba3d44135a50203010001";
    private static final String URL = "http://localhost:8080/dataserver";

    @Test
    public final void test() throws Exception {
        String str = "服务端你好，可以看到这条信息吗";
        byte[] data = str.getBytes();
        byte[] input = HttpUtils.postRequest(URL, RSACoder.encryptByPublicKey(data, PUBLIC_KEY));
        System.out.println("result:\t" + new String(input));
        input = RSACoder.decryptByPublicKey(input, PUBLIC_KEY);
        System.out.println("result decrypt:\t" + new String(input));
        assertEquals("OK", new String(input));

    }
}
