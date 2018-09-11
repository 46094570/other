package com.gaox.encrypt.example.ripe;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * HmacRipeMD系列消息组件摘要 bouncy castle 实现
 */
public class HmacRipeMDDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String str = "HmacRipeMD系列消息组件摘要";
        byte[] hmacRipeMD128key = initHmacRipeMD128key();
        byte[] hmacRipeMD160key = initHmacRipeMD160key();
        String hmacRipeMD128_1 = encodeHmacRipeMD128Hex(str.getBytes(), hmacRipeMD128key);
        String hmacRipeMD128_2 = encodeHmacRipeMD128Hex(str.getBytes(), hmacRipeMD128key);
        String hmacRipeMD160_1 = encodeHmacRipeMD160Hex(str.getBytes(), hmacRipeMD160key);
        String hmacRipeMD160_2 = encodeHmacRipeMD160Hex(str.getBytes(), hmacRipeMD160key);
        System.out.println("原文：\t" + str);
        System.out.println("hmacRipeMD128_1：\t" + hmacRipeMD128_1);
        System.out.println("hmacRipeMD128_2：\t" + hmacRipeMD128_2);
        System.out.println("hmacRipeMD160_1：\t" + hmacRipeMD160_1);
        System.out.println("hmacRipeMD160_2：\t" + hmacRipeMD160_2);
    }

    /**
     * 初始化HmacRipeMD128密钥
     * @return byte[] 密钥
     * @throws NoSuchAlgorithmException
     */
    private static byte[] initHmacRipeMD128key() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator hmacRipeMD128 = KeyGenerator.getInstance("HmacRipeMD128");
        SecretKey secretKey = hmacRipeMD128.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * HmacRipeMD128消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacRipeMD128(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        SecretKeySpec hmacRipeMD128Key = new SecretKeySpec(key, "HmacRipeMD128");
        Mac mac = Mac.getInstance(hmacRipeMD128Key.getAlgorithm());
        mac.init(hmacRipeMD128Key);
        return mac.doFinal(data);
    }

    /**
     * HmacRipeMD128Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static String encodeHmacRipeMD128Hex(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] bytes = encodeHmacRipeMD128(data, key);
        return new String(Hex.encode(bytes));
    }
    /**
     * 初始化HmacRipeMD160密钥
     * @return byte[] 密钥
     * @throws NoSuchAlgorithmException
     */
    private static byte[] initHmacRipeMD160key() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator hmacRipeMD160 = KeyGenerator.getInstance("HmacRipeMD160");
        SecretKey secretKey = hmacRipeMD160.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * HmacRipeMD160消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacRipeMD160(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        SecretKeySpec hmacRipeMD160Key = new SecretKeySpec(key, "HmacRipeMD160");
        Mac mac = Mac.getInstance(hmacRipeMD160Key.getAlgorithm());
        mac.init(hmacRipeMD160Key);
        return mac.doFinal(data);
    }

    /**
     * HmacRipeMD160Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static String encodeHmacRipeMD160Hex(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] bytes = encodeHmacRipeMD160(data, key);
        return new String(Hex.encode(bytes));
    }


}
