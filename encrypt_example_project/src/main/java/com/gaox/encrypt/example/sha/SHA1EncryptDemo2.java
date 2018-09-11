package com.gaox.encrypt.example.sha;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * SHA消息摘要 commons codec 实现
 */
public class SHA1EncryptDemo2 {
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
        System.out.println("encodeSHA1:"+ encodeSHAHex(str.getBytes()));
        System.out.println("encodeSHA2561:"+Arrays.toString(encodeSHA2561));
        System.out.println("encodeSHA2562:"+Arrays.toString(encodeSHA2562));
        System.out.println("encodeSHA256:"+encodeSHA256Hex(str.getBytes()));
        System.out.println("encodeSHA3841:"+Arrays.toString(encodeSHA3841));
        System.out.println("encodeSHA3842:"+Arrays.toString(encodeSHA3842));
        System.out.println("encodeSHA384:"+encodeSHA384Hex(str.getBytes()));
        System.out.println("encodeSHA5121:"+Arrays.toString(encodeSHA5121));
        System.out.println("encodeSHA5122:"+Arrays.toString(encodeSHA5122));
        System.out.println("encodeSHA512:"+encodeSHA512Hex(str.getBytes()));
    }

    /**
     * SHA-1消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static byte[] encodeSHA(byte[] data){
        return DigestUtils.sha(data);
    }

    /**
     * SHAHex消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static String encodeSHAHex(byte[] data){
        return DigestUtils.sha1Hex(data);
    }

    /**
     * SHA-256消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static byte[] encodeSHA256(byte[] data) {
        return DigestUtils.sha256(data);
    }
    /**
     * SHA-256消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static String encodeSHA256Hex(byte[] data) {
        return DigestUtils.sha256Hex(data);
    }

    /**
     * SHA-384消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static byte[] encodeSHA384(byte[] data){
        return DigestUtils.sha384(data);
    }

    /**
     * SHA-384消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static String encodeSHA384Hex(byte[] data){
        return DigestUtils.sha384Hex(data);
    }

    /**
     * SHA-512消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static byte[] encodeSHA512(byte[] data) {
        return DigestUtils.sha512(data);
    }

    /**
     * SHA-512消息摘要
     *
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     */
    private static String encodeSHA512Hex(byte[] data) {
        return DigestUtils.sha512Hex(data);
    }

}
