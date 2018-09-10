package com.gaox.encrypt.example.md.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5消息摘要算法 jdk 实现
 */
public class MD5CoderDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5="MD5消息摘要";
        byte[] bytes1 = encodeMD5(md5.getBytes());
        byte[] bytes2 = encodeMD5(md5.getBytes());
        System.out.println(Arrays.toString(bytes1));
        System.out.println(Arrays.toString(bytes2));

    }

    /**
     * MD5消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到该算法异常
     */
    private static byte[] encodeMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return md5.digest(data);
    }
}
