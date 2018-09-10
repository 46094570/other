package com.gaox.encrypt.example.md.md4;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * MD4消息摘要算法 bouncy castle 实现
 */
public class MD4CoderDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md4="MD4Hex消息摘要";
        String md4Hex1 = encodeMD4Hex(md4.getBytes());
        String md4Hex2 = encodeMD4Hex(md4.getBytes());
        byte[] bytes1 = encodeMD4(md4.getBytes());
        byte[] bytes2 = encodeMD4(md4.getBytes());
        System.out.println("原文：\t" + md4);
        System.out.println("MD4Hex-1:"+md4Hex1);
        System.out.println(Arrays.toString(bytes1));
        System.out.println(Arrays.toString(bytes2));
        System.out.println("MD4Hex-2:"+md4Hex2);

    }

    /**
     * MD4消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到该算法异常
     */
    private static byte[] encodeMD4(byte[] data) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md4 = MessageDigest.getInstance("MD4");
        return md4.digest(data);
    }
    /**
     * MD4消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到该算法异常
     */
    private static String encodeMD4Hex(byte[] data) throws NoSuchAlgorithmException {
        byte[] bytes = encodeMD4(data);
        return new String(Hex.encode(bytes));
    }
}
