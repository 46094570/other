package com.gaox.encrypt.example.digitalSignature.http;

import com.gaox.encrypt.example.digitalSignature.http.utils.HttpUtils;
import com.gaox.encrypt.example.digitalSignature.http.utils.MessageUtils;
import com.gaox.encrypt.example.digitalSignature.http.utils.RSAHttpSignature;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class DataServletTest {
//    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJGXdi42S/Fquw68iuU/PD9fAMVYMtFBYFat9YuDSxiQq4hw9sRBWL7eYPY4DwIBGCd/NKsbPzbxJ+Ww3BOI8p0CAwEAAQ==";
    private static final String PUBLIC_KEY = "30819f300d06092a864886f70d010101050003818d0030818902818100c535627ca25851ab88122a53c9f84b267f86357c180ee46ecb532c9458f049da044b79e7c25ab29ec542e1607e10fa636a9da89566c7464ab86af1ccd1e26338a32f257db0eb8a58782dacefb17d255a0bb171936f563d1e1efda9ef1ac02bc1df879625ff5c75138dc841e58aee228a3abe969dfc78b93f70cf0d36f1ac7e7b0203010001";
    private static final String URL = "http://localhost:8080/dataserver";

    @Test
    public final void test() throws Exception {
        String str = MessageUtils.generateMessage();
        byte[] data = str.getBytes();
        byte[] input = HttpUtils.postRequest(URL, RSAHttpSignature.encryptByPublicKey(data, PUBLIC_KEY));
        System.out.println("Message:\t" + new String(input));
        System.out.println("Message length:\t" + input.length);
        input = RSAHttpSignature.decryptByPublicKey(input, PUBLIC_KEY);
        String result = new String(input);
        String sign = result.substring(0,256);
        result = result.substring(256);
        System.out.println("sign:\r\n" + sign+"\r\nsign length:\r\n"+sign.length());
        System.out.println("result:\r\n" + result);
//        assertTrue(RSAHttpSignature.verify(result.getBytes(),PUBLIC_KEY,sign));
        System.out.println("verify:\r\n"+RSAHttpSignature.verify(result.getBytes(),PUBLIC_KEY,sign));
    }
}
