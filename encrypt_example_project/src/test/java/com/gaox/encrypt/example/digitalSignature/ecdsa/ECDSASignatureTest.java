package com.gaox.encrypt.example.digitalSignature.ecdsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ECDSASignatureTest {
    private byte[] publicKey;
    private byte[] privateKey;

    @Before
    public void initKey() throws Exception {
        Map<String, Object> keyMap = ECDSASignature.initKey();
        publicKey = ECDSASignature.getPublicKey(keyMap);
        privateKey = ECDSASignature.getPrivateKey(keyMap);
        System.out.println("公钥:\t" + Base64.encodeBase64String(publicKey));
        System.out.println("私钥:\t" + Base64.encodeBase64String(privateKey));
    }

    @Test
    public void testSign() throws Exception {
        String str = "ECDSA签名测试";
        byte[] data = str.getBytes();

        //执行签名
        byte[] sign = ECDSASignature.sign(data, privateKey);
        System.out.println("签名:\t"+ Hex.encodeHexString(sign));
        System.out.println("签名:\t"+ Base64.encodeBase64String(sign));

        //验证签名
        boolean verify = ECDSASignature.verify(data, publicKey, sign);
        System.out.println("验证:\t"+verify);
    }
}
