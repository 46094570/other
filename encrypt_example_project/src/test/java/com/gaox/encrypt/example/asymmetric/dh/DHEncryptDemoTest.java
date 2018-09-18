package com.gaox.encrypt.example.asymmetric.dh;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DHEncryptDemoTest {
    //甲方公钥
    private byte[] publicKey1;
    //甲方私钥
    private byte[] privateKey1;
    //甲方本地密钥
    private byte[] key1;
    //乙方公钥
    private byte[] publicKey2;
    //乙方私钥
    private byte[] privateKey2;
    //乙方本地密钥
    private byte[] key2;

    /**
     * 初始化密钥
     *
     * @throws Exception
     */
    @Before
    public final void initKey() throws Exception {
        //生成甲方密钥对
        Map<String, Object> keyMap1 = DHEncryptDemo.initKey();
        publicKey1 = DHEncryptDemo.getPublicKey(keyMap1);
        privateKey1 = DHEncryptDemo.getPrivateKey(keyMap1);
        System.out.println("甲方公钥:\n" + Base64.encodeBase64String(publicKey1));
        System.out.println("甲方私钥:\n" + Base64.encodeBase64String(privateKey1));

        //根据甲方公钥生成乙方密钥对
        Map<String, Object> keyMap2 = DHEncryptDemo.initKey(publicKey1);
        publicKey2 = DHEncryptDemo.getPublicKey(keyMap2);
        privateKey2 = DHEncryptDemo.getPrivateKey(keyMap2);
        System.out.println("乙方公钥:\n" + Base64.encodeBase64String(publicKey2));
        System.out.println("乙方私钥:\n" + Base64.encodeBase64String(privateKey2));

        //根据甲方私钥乙方公钥生成甲方本地密钥
        key1 = DHEncryptDemo.getSecretKey(publicKey2, privateKey1);
        System.out.println("甲方本地密钥:\n" + Base64.encodeBase64String(key1));
        //根据甲方公钥乙方私钥生成乙方本地密钥
        key2 = DHEncryptDemo.getSecretKey(publicKey1, privateKey2);
        System.out.println("乙方本地密钥:\n" + Base64.encodeBase64String(key2));

//        assertEquals(key1, key2);
    }

    /**
     * 校验
     *
     * @throws Exception
     */
    @Test
    public final void test() throws Exception {
        System.out.println("\n========甲方向乙方发送加密数据=======");
        String input1 = "DH密码交换算法";
        System.out.println("原文:\t" + input1);
        System.out.println("----使用甲方本地密钥对数据加密----");
        byte[] code1 = DHEncryptDemo.encrypt(input1.getBytes(), key1);
        System.out.println("加密:\t" + Base64.encodeBase64String(code1));
        System.out.println("----乙方接收到加密数据，使用乙方本地密钥对数据解密----");
        byte[] decode1 = DHEncryptDemo.decrypt(code1, key2);
        String output1 = new String(decode1);
        System.out.println("乙方解密数据:\t" + output1);
//        assertEquals(input1, decode1);

        System.out.println("\n========乙方向甲方发送加密数据=======");
        String input2 = "正常收到信息";
        System.out.println("原文:\t" + input2);
        System.out.println("----使用乙方本地密钥对数据加密----");
        byte[] code2 = DHEncryptDemo.encrypt(input2.getBytes(), key2);
        System.out.println("加密:\t" + Base64.encodeBase64String(code2));
        System.out.println("----甲方接收到加密数据，使用甲方本地密钥对数据解密----");
        byte[] decode2 = DHEncryptDemo.decrypt(code2, key1);
        String output2 = new String(decode2);
        System.out.println("乙方解密数据:\t" + output2);
//        assertEquals(input2, decode2);
    }
}
