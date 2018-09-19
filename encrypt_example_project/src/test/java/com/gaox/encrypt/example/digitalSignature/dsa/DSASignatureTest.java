package com.gaox.encrypt.example.digitalSignature.dsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class DSASignatureTest {
    private byte[] publicKey;
    private byte[] privateKey;

    @Before
    public void initKey() throws Exception {
        Map<String, Object> keyMap = DSASignature.initKey();
        publicKey = DSASignature.getPublicKey(keyMap);
        privateKey = DSASignature.getPrivateKey(keyMap);
        System.out.println("公钥:\t" + Base64.encodeBase64String(publicKey));
        System.out.println("私钥:\t" + Base64.encodeBase64String(privateKey));
    }

    @Test
    public void testSign() throws Exception {
        String str = "DSA签名测试";
        byte[] data = str.getBytes();

        //执行签名
        byte[] sign = DSASignature.sign(data, privateKey);
        System.out.println("签名:\t"+ Hex.encodeHexString(sign));

        //验证签名
        boolean verify = DSASignature.verify(data, publicKey, sign);
        System.out.println("验证:\t"+verify);
    }
}
