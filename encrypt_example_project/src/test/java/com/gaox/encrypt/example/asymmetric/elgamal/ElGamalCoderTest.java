package com.gaox.encrypt.example.asymmetric.elgamal;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ElGamalCoderTest {
    private byte[] publicKey;
    private byte[] privateKey;

    @Before
    public void initKey() throws Exception {
        Map<String, Object> keyMap = ElGamalCoder.initKey();
        publicKey = ElGamalCoder.getPublicKey(keyMap);
        privateKey = ElGamalCoder.getPrivateKey(keyMap);
        System.out.println("公钥:\t"+ Base64.encodeBase64String(publicKey));
        System.out.println("私钥:\t"+Base64.encodeBase64String(privateKey));
    }

    @Test
    public void test() throws Exception {
        String str = "ElGamal加密算法工具";
        byte[] data1 = str.getBytes();
        System.out.println("原文:\t"+str);
        byte[] encode = ElGamalCoder.encryptByPublicKey(data1, publicKey);
        System.out.println("加密后:\t"+Base64.encodeBase64String(encode));
        System.out.println("---------发送至对端---------");
        Thread.sleep(1000);
        System.out.println("接收到消息，进行解密");
        byte[] decode = ElGamalCoder.decryptByPrivateKey(encode, privateKey);
        System.out.println("解密后:\t"+new String(decode));
    }
}
