package com.gaox.encrypt.example.asymmetric.rsa;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class RSACoderTest {
    private byte[] publicKey;
    private byte[] privateKey;

    /**
     * 初始化密钥
     * @throws Exception
     */
    @Before
    public void initKey() throws Exception{
        Map<String, Object> keyMap = RSACoder.initKey();
        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);
        System.out.println("公钥:\t"+ Base64.encodeBase64String(publicKey));
        System.out.println("私钥:\t"+ Base64.encodeBase64String(privateKey));
    }

    @Test
    public void test1() throws Exception{
        System.out.println("\n-----私钥加密，公钥解密-----");
        String input1 = "RSA算法验证工具";
        byte[] data1 = input1.getBytes();
        System.out.println("原文:\t"+input1);
        byte[] encode1 = RSACoder.encryptByPrivateKey(data1, privateKey);
        System.out.println("加密后:\t"+Base64.encodeBase64String(encode1));
        System.out.println("\n------发送至对端-------");
        Thread.sleep(1000);
        System.out.println("\n------对方解密中-------");
        byte[] decode1 = RSACoder.decryptByPublicKey(encode1, publicKey);
        String output1 = new String(decode1);
        System.out.println("解密后:\t"+output1);
    }
    @Test
    public void test2() throws Exception{
        System.out.println("\n-----公钥加密，私钥解密-----");
        String input1 = "RSA算法验证工具";
        byte[] data1 = input1.getBytes();
        System.out.println("原文:\t"+input1);
        byte[] encode1 = RSACoder.encryptByPublicKey(data1, publicKey);
        System.out.println("加密后:\t"+Base64.encodeBase64String(encode1));
        System.out.println("\n------发送至对端-------");
        Thread.sleep(1000);
        System.out.println("\n------对方解密中-------");
        byte[] decode1 = RSACoder.decryptByPrivateKey(encode1, privateKey);
        String output1 = new String(decode1);
        System.out.println("解密后:\t"+output1);
    }
}
