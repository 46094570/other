package com.gaox.encrypt.example.mac;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * MAC 算法的 SUN 实现
 */
public class MACEncryptDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String str = "HMAC加密算法消息摘要";
        byte[] hmacMD5key = initHmacMD5key();
        byte[] hmacSHAkey = initHmacSHAkey();
        byte[] hmacSHA256key = initHmacSHA256key();
        byte[] hmacSHA384key = initHmacSHA384key();
        byte[] hmacSHA512key = initHmacSHA512key();
        byte[] hmacMD5 = encodeHmacMD5(str.getBytes(), hmacMD5key);
        byte[] hmacSHA = encodeHmacSHA(str.getBytes(), hmacSHAkey);
        byte[] hmacSHA256 = encodeHmacSHA256(str.getBytes(), hmacSHA256key);
        byte[] hmacSHA384 = encodeHmacSHA384(str.getBytes(), hmacSHA384key);
        byte[] hmacSHA512 = encodeHmacSHA512(str.getBytes(), hmacSHA512key);
        System.out.println("hmacMD5:"+hmacMD5);
        System.out.println("hmacSHA:"+hmacSHA);
        System.out.println("hmacSHA256:"+hmacSHA256);
        System.out.println("hmacSHA384:"+hmacSHA384);
        System.out.println("hmacSHA512:"+hmacSHA512);
    }

    /**
     * 初始化MD5密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacMD5key() throws NoSuchAlgorithmException {
        KeyGenerator hmacMD5 = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = hmacMD5.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacMD5消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacMD5(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        //还原密钥
        SecretKeySpec hmacMD5Key = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance(hmacMD5Key.getAlgorithm());
        mac.init(hmacMD5Key);
        return mac.doFinal(data);
    }

    /**
     * 初始化SHA-1密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacSHAkey() throws NoSuchAlgorithmException {
        KeyGenerator hmacSHA1 = KeyGenerator.getInstance("HmacSHA1");
        SecretKey secretKey = hmacSHA1.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacSHA-1消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacSHA(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        //还原密钥
        SecretKeySpec hmacSHA1Key = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance(hmacSHA1Key.getAlgorithm());
        mac.init(hmacSHA1Key);
        return mac.doFinal(data);
    }

    /**
     * 初始化SHA-256密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacSHA256key() throws NoSuchAlgorithmException {
        KeyGenerator hmacSHA256 = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = hmacSHA256.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacSHA-256消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        //还原密钥
        SecretKeySpec hmacSHA256Key = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance(hmacSHA256Key.getAlgorithm());
        mac.init(hmacSHA256Key);
        return mac.doFinal(data);
    }

    /**
     * 初始化SHA-384密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacSHA384key() throws NoSuchAlgorithmException {
        KeyGenerator hmacSHA384 = KeyGenerator.getInstance("HmacSHA384");
        SecretKey secretKey = hmacSHA384.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacSHA-384消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacSHA384(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        //还原密钥
        SecretKeySpec hmacSHA384Key = new SecretKeySpec(key, "HmacSHA384");
        Mac mac = Mac.getInstance(hmacSHA384Key.getAlgorithm());
        mac.init(hmacSHA384Key);
        return mac.doFinal(data);
    }

    /**
     * 初始化SHA-512密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacSHA512key() throws NoSuchAlgorithmException {
        KeyGenerator hmacSHA512 = KeyGenerator.getInstance("HmacSHA512");
        SecretKey secretKey = hmacSHA512.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacSHA-512消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacSHA512(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        //还原密钥
        SecretKeySpec hmacSHA512Key = new SecretKeySpec(key, "HmacSHA512");
        Mac mac = Mac.getInstance(hmacSHA512Key.getAlgorithm());
        mac.init(hmacSHA512Key);
        return mac.doFinal(data);
    }

}
