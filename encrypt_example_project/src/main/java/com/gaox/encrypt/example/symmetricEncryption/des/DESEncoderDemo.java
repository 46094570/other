package com.gaox.encrypt.example.symmetricEncryption.des;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * DES安全编码组件 jdk 实现
 */
public class DESEncoderDemo {

    public static void main(String[] args) throws Exception {
        String str = "DES";
        byte[] inputData = str.getBytes();
        System.out.println("原文：\t"+str);
        byte[] key = initKey();
        System.out.println("密钥：\t"+ Base64.encodeBase64String(key));
        inputData = encrypt(inputData, key);
        System.out.println("加密后：\t"+Base64.encodeBase64String(inputData));
        byte[] outputData = decrypt(inputData, key);
        System.out.println("解密后：\t"+new String(outputData));
    }

    /**
     * 密钥算法
     * Java 支持 56 位密钥
     * Bouncy Castle 支持 64 位密钥
     */
    private static final String KEY_ALGORITHM = "DES";

    /**
     * 加密/解密算法 工作模式 填充方式
     */
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return Key 密钥
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static Key toKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        return secretKey;
    }

    /**
     * 解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密数据
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private static byte[] encrypt(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    private static byte[] initKey() throws Exception{
        //若要使用64位密钥，使用 KeyGenerator.getInstance(KEY_ALGORITHM,"BC") BC = Bouncy Castle
        KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
        //若要使用64位密钥，使用 generator.init(64)
        //可以使用generator.init(new SecureRandom());创建默认长度密钥
        generator.init(56);
        SecretKey secretKey = generator.generateKey();
        return secretKey.getEncoded();
    }
}
