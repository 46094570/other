package com.gaox.encrypt.example.symmetric.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AESEncoderDemo {
    /**
     * 密钥算法
     * Java 7 支持密钥长度为112位和168位
     * Bouncy Castle 支持密钥长度为128位和192位
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法 工作模式 填充方式
     * Java 7 支持PKCS5Padding填充
     * Bouncy Castle 支持PKCS7Padding
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 生成密钥
     * @return byte[] 二进制密钥
     * @throws Exception
     */
    private static byte[] initKey() throws Exception {
        /**
         * 实例化
         * 使用128位或者192位长度密钥，替换如下代码实现：
         * KeyGenerator.getInstance(KEY_ALGORITHM,"BC");
         */
        KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
        /**
         * 初始化
         * AES要求密钥长度为 128 、192、256
         * generator.init(128);
         * 或
         * generator.init(192);
         */
        generator.init(256);
        //生成密钥
        SecretKey key = generator.generateKey();
        //获得密钥的二进制编码形式
        return key.getEncoded();
    }

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return Key 密钥
     */
    private static Key toKey(byte[] key) throws Exception {
        SecretKey keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        return keySpec;
    }

    /**
     * 加密
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        /**
         * 使用PKCS7Padding填充方式，使用如下代码
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
         */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }


    /**
     * 解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception{
        Key k = toKey(key);
        /**
         * 使用PKCS7Padding填充方式，使用如下代码
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
         */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {

        String s = "This is AES test demo";
        byte[] inputData = s.getBytes();
        System.err.println("原文：\t"+s);
        byte[] key = initKey();
        System.err.println("密钥：\t" + Base64.encodeBase64String(key));
        inputData = encrypt(inputData, key);
        System.err.println("加密后：\t" + Base64.encodeBase64String(inputData));
        byte[] outputData = decrypt(inputData, key);
        System.err.println("解密后：\t"+new String(outputData));


    }
}
