package com.gaox.encrypt.example.messageDigest.mac;

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
 * MAC 算法的 Bouncy Castle 实现
 */
public class MACEncryptDemo2 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String str = "HMAC加密算法消息摘要";
        byte[] hmacMD2key = initHmacMD2key();
        byte[] hmacMD4key = initHmacMD4key();
        byte[] hmacSHA224key = initHmacSHA224key();
        String hmacMD2 = encodeHmacMD2Hex(str.getBytes(), hmacMD2key);
        String hmacMD4 = encodeHmacMD4Hex(str.getBytes(), hmacMD4key);
        String hmacSHA224 = encodeHmacSHA224Hex(str.getBytes(), hmacSHA224key);
        byte[] hmacMD2_ = encodeHmacMD2(str.getBytes(), hmacMD2key);
        byte[] hmacMD4_ = encodeHmacMD4(str.getBytes(), hmacMD4key);
        byte[] hmacSHA224_ = encodeHmacSHA224(str.getBytes(), hmacSHA224key);
        System.out.println("hmacMD2:"+hmacMD2);
        System.out.println("hmacMD4:"+hmacMD4);
        System.out.println("hmacSHA:"+hmacSHA224);
        System.out.println("hmacMD2_:"+hmacMD2_);
        System.out.println("hmacMD4_:"+hmacMD4_);
        System.out.println("hmacSHA224_:"+hmacSHA224_);
    }


    /**
     * 初始化HmacMD2密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacMD2key() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator hmacMD5 = KeyGenerator.getInstance("HmacMD2");
        SecretKey secretKey = hmacMD5.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacMD2消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacMD2(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        //还原密钥
        SecretKeySpec hmacMD2Key = new SecretKeySpec(key, "HmacMD2");
        Mac mac = Mac.getInstance(hmacMD2Key.getAlgorithm());
        mac.init(hmacMD2Key);
        return mac.doFinal(data);
    }

    /**
     * HmacMD2Hex消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static String encodeHmacMD2Hex(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] bytes = encodeHmacMD2(data, key);
        return new String(Hex.encode(bytes));
    }

    /**
     * 初始化HmacMD4密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacMD4key() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator hmacMD4 = KeyGenerator.getInstance("HmacMD4");
        SecretKey secretKey = hmacMD4.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacMD4消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacMD4(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        //还原密钥
        SecretKeySpec hmacMD4Key = new SecretKeySpec(key, "HmacMD4");
        Mac mac = Mac.getInstance(hmacMD4Key.getAlgorithm());
        mac.init(hmacMD4Key);
        return mac.doFinal(data);
    }

    /**
     * HmacMD4Hex消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static String encodeHmacMD4Hex(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] bytes = encodeHmacMD4(data, key);
        return new String(Hex.encode(bytes));
    }

    /**
     * 初始化SHA-224密钥
     * @return 密钥
     * @throws NoSuchAlgorithmException 找不到加密方式
     */
    private static byte[] initHmacSHA224key() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator hmacSHA224 = KeyGenerator.getInstance("HmacSHA224");
        SecretKey secretKey = hmacSHA224.generateKey();
        return  secretKey.getEncoded();
    }

    /**
     * HmacSHA-224消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static byte[] encodeHmacSHA224(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        //还原密钥
        SecretKeySpec hmacSHA1Key = new SecretKeySpec(key, "HmacSHA224");
        Mac mac = Mac.getInstance(hmacSHA1Key.getAlgorithm());
        mac.init(hmacSHA1Key);
        return mac.doFinal(data);
    }

    /**
     * HmacMD224Hex消息摘要
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static String encodeHmacSHA224Hex(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] bytes = encodeHmacSHA224(data, key);
        return new String(Hex.encode(bytes));
    }

}
