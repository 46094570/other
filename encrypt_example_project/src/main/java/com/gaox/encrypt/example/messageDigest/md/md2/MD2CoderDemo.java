package com.gaox.encrypt.example.messageDigest.md.md2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD2消息摘要算法 jdk 实现
 */
public class MD2CoderDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md2="MD2消息摘要";
        byte[] bytes1 = encodeMD2(md2.getBytes());
        byte[] bytes2 = encodeMD2(md2.getBytes());
        System.out.println(Arrays.toString(bytes1));
        System.out.println(Arrays.toString(bytes2));
    }

    /**
     * MD2消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到该算法异常
     */
    private static byte[] encodeMD2(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md2 = MessageDigest.getInstance("MD2");
        return md2.digest(data);
    }
}
