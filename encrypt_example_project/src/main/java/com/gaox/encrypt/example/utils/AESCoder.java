package com.gaox.encrypt.example.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AESCoder {
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

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
     * 初始化密钥
     * @return  String  Base64编码密钥
     * @throws Exception
     */
    public static String initKeyString() throws Exception {
        return Base64.encodeBase64String(initKey());
    }

    /**
     * 生成密钥
     * @return byte[] 二进制密钥
     * @throws Exception
     */
    private static byte[] initKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
        generator.init(256);
        SecretKey key = generator.generateKey();
        return key.getEncoded();
    }

    /**
     * 获取密钥
     * @param key   密钥字Base64字符串
     * @return  byte[]  二进制密钥字节数组
     */
    public static byte[] getKey(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     *  解密
     * @param data 待解密数据
     * @param key   Base64加密密钥
     * @return  byte[]  解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,String key) throws Exception{
        return decrypt(data,getKey(key));
    }

    /**
     * 解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     *  加密
     * @param data  待加密数据
     * @param key   Base64加密密钥
     * @return  byte[]  加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,String key) throws Exception{
        return encrypt(data,getKey(key));
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
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     *  摘要处理
     * @param data  待摘要数据
     * @return  String  摘要字符串
     */
    public static String shaHex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     *  验证
     * @param data  待摘要数据
     * @param messageDigest 摘要字符串
     * @return  String  验证结果
     */
    public static boolean validate(byte[] data, String messageDigest) {
        return messageDigest.equals(shaHex(data));
    }
}
