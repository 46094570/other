package com.gaox.encrypt.example.messageDigest.md.md5;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5消息摘要算法 commons codec 实现
 */
public class MD5CoderDemo2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5="MD5消息摘要";
        String md4Hex1 = encodeMD5Hex(md5.getBytes());
        String md4Hex2 = encodeMD5Hex(md5.getBytes());
        byte[] bytes1 = encodeMD5(md5.getBytes());
        byte[] bytes2 = encodeMD5(md5.getBytes());
        System.out.println("原文：\t" + md5);
        System.out.println("MD5Hex-1:"+md4Hex1);
        System.out.println(Arrays.toString(bytes1));
        System.out.println(Arrays.toString(bytes2));
        System.out.println("MD5Hex-2:"+md4Hex2);

    }

    /**
     * MD5消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到该算法异常
     */
    private static byte[] encodeMD5(byte[] data) throws NoSuchAlgorithmException {
        return DigestUtils.md5(data);
    }

    /**
     * MD5消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到该算法异常
     */
    private static String encodeMD5Hex(byte[] data) throws NoSuchAlgorithmException {
        return DigestUtils.md5Hex(data);
    }
}
