package com.gaox.encrypt.example.asymmetric.http;

import com.gaox.encrypt.example.asymmetric.http.utils.MessageUtils;
import com.gaox.encrypt.example.asymmetric.http.utils.RSACoder;
import com.gaox.encrypt.example.asymmetric.http.utils.HttpUtils;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;


public class DataServletTest {
//    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJGXdi42S/Fquw68iuU/PD9fAMVYMtFBYFat9YuDSxiQq4hw9sRBWL7eYPY4DwIBGCd/NKsbPzbxJ+Ww3BOI8p0CAwEAAQ==";
    private static final String PUBLIC_KEY = "305c300d06092a864886f70d0101010500034b003048024100d1069fb346614ee961a02bdf415ab8671dce9532638e639552db39ab2d41b3c0b00664baef26be1f30a53a4e079b535394d2c7abbf0770fa4059123d3eaafe350203010001";
    private static final String URL = "http://localhost:8080/dataserver";

    @Test
    public final void test() throws Exception {
        String str = MessageUtils.generateMessage();
        byte[] data = str.getBytes();
        byte[] input = HttpUtils.postRequest(URL, RSACoder.encryptByPublicKey(data, PUBLIC_KEY));
        System.out.println("Message:\t" + new String(input));
        System.out.println("Message length:\t" + input.length);
        input = RSACoder.decryptByPublicKey(input, PUBLIC_KEY);
        System.out.println("result decrypt:\t" + new String(input));
//        assertEquals("OK", new String(input));

    }
}
