package com.gaox.encrypt.example.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * SHA消息摘要 jdk 实现
 */
public class SHA1EncryptDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "SHA消息摘要算法";
        byte[] encodeSHA11 = encodeSHA(str.getBytes());
        byte[] encodeSHA12 = encodeSHA(str.getBytes());
        byte[] encodeSHA2561 = encodeSHA256(str.getBytes());
        byte[] encodeSHA2562 = encodeSHA256(str.getBytes());
        byte[] encodeSHA3841 = encodeSHA384(str.getBytes());
        byte[] encodeSHA3842 = encodeSHA384(str.getBytes());
        byte[] encodeSHA5121 = encodeSHA512(str.getBytes());
        byte[] encodeSHA5122 = encodeSHA512(str.getBytes());

        System.out.println("encodeSHA11:"+ Arrays.toString(encodeSHA11));
        System.out.println("encodeSHA12:"+Arrays.toString(encodeSHA12));
        System.out.println("encodeSHA2561:"+Arrays.toString(encodeSHA2561));
        System.out.println("encodeSHA2562:"+Arrays.toString(encodeSHA2562));
        System.out.println("encodeSHA3841:"+Arrays.toString(encodeSHA3841));
        System.out.println("encodeSHA3842:"+Arrays.toString(encodeSHA3842));
        System.out.println("encodeSHA5121:"+Arrays.toString(encodeSHA5121));
        System.out.println("encodeSHA5122:"+Arrays.toString(encodeSHA5122));
    }

    /**
     * SHA-1消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到算法异常
     */
    private static byte[] encodeSHA(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        return sha.digest(data);
    }

    /**
     * SHA-256消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到算法异常
     */
    private static byte[] encodeSHA256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        return sha.digest(data);
    }

    /**
     * SHA-384消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到算法异常
     */
    private static byte[] encodeSHA384(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-384");
        return sha.digest(data);
    }

    /**
     * SHA-512消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到算法异常
     */
    private static byte[] encodeSHA512(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-512");
        return sha.digest(data);
    }

}
