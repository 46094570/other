package com.gaox.encrypt.example.ripe;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * bouncy castle 实现的 RipeMD 加密算法
 */
public class RipeMDEncryptDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "RipeMD加密算法消息摘要";
        String ripeMD128Hex = encodeRipeMD128Hex(str.getBytes());
        String ripeMD160Hex = encodeRipeMD160Hex(str.getBytes());
        String ripeMD256Hex = encodeRipeMD256Hex(str.getBytes());
        String ripeMD320Hex = encodeRipeMD320Hex(str.getBytes());
        System.out.println("ripeMD128Hex:"+ripeMD128Hex);
        System.out.println("ripeMD160Hex:"+ripeMD160Hex);
        System.out.println("ripeMD256Hex:"+ripeMD256Hex);
        System.out.println("ripeMD320Hex:"+ripeMD320Hex);
    }

    /**
     * RipeMD128消息摘要
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encodeRipeMD128(byte[] data) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest ripeMD128 = MessageDigest.getInstance("RipeMD128");
        return ripeMD128.digest(data);
    }

    /**
     * RipeMD128Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @return String 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static String encodeRipeMD128Hex(byte[] data) throws NoSuchAlgorithmException {
        byte[] bytes = encodeRipeMD128(data);
        return new String(Hex.encode(bytes));
    }
    /**
     * RipeMD160消息摘要
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encodeRipeMD160(byte[] data) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest ripeMD160 = MessageDigest.getInstance("RipeMD160");
        return ripeMD160.digest(data);
    }

    /**
     * RipeMD160Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static String encodeRipeMD160Hex(byte[] data) throws NoSuchAlgorithmException {
        byte[] bytes = encodeRipeMD160(data);
        return new String(Hex.encode(bytes));
    }
    /**
     * RipeMD256消息摘要
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encodeRipeMD256(byte[] data) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest ripeMD256 = MessageDigest.getInstance("RipeMD256");
        return ripeMD256.digest(data);
    }

    /**
     * RipeMD256Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static String encodeRipeMD256Hex(byte[] data) throws NoSuchAlgorithmException {
        byte[] bytes = encodeRipeMD256(data);
        return new String(Hex.encode(bytes));
    }
    /**
     * RipeMD320消息摘要
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encodeRipeMD320(byte[] data) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest ripeMD320 = MessageDigest.getInstance("RipeMD320");
        return ripeMD320.digest(data);
    }

    /**
     * RipeMD320Hex消息摘要
     * @param data 待做消息摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     */
    private static String encodeRipeMD320Hex(byte[] data) throws NoSuchAlgorithmException {
        byte[] bytes = encodeRipeMD320(data);
        return new String(Hex.encode(bytes));
    }


}
