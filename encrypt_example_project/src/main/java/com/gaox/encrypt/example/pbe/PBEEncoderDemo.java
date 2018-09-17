package com.gaox.encrypt.example.pbe;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

public class PBEEncoderDemo {
    /**
     *  jdk 支持
     *  PBEWITHMD5AndDES
     *  PBEWITHMD5AndTripleDES
     *  PBEWITHSHA1AndDESede
     *  PBKDF2WITHHmacSHA1
     *
     */
    private static final String ALGORITHM = "PBEWITHMD5AndDES";
    private static final int ITERATION_COUNT = 100;

    /**
     * 初始化盐
     * 盐长度必须为 8 字节
     * @return
     * @throws Exception
     */
    private static byte[] initSalt() throws Exception{
        SecureRandom random = new SecureRandom();
        return random.generateSeed(8);
    }

    /**
     * 转换密钥
     * @param password 口令
     * @return Key 密钥
     * @throws Exception
     */
    private static Key toKey(String password) throws Exception{
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey key = factory.generateSecret(keySpec);
        return key;
    }

    /**
     * 加密
     * @param data 数据
     * @param password 口令
     * @param salt 盐
     * @return byte[] 加密数据
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data,String password,byte[] salt) throws Exception {
        Key key = toKey(password);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,key,parameterSpec);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param data 数据
     * @param password 口令
     * @param salt 盐
     * @return byte[] 解密数据
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data,String password,byte[] salt) throws Exception{
        Key key = toKey(password);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,key,parameterSpec);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String str = "this is PBE encrypt demo";
        System.out.println("原文：\t"+str);
        byte[] input = str.getBytes();
        String pwd = "abc@xyz.com";
        System.out.println("密码：\t"+pwd);
        byte[] salt = initSalt();
        System.out.println("盐：\t"+ Base64.encodeBase64String(salt));
        System.out.println("盐：\t"+new String(Hex.encodeHex(salt)));
        byte[] output = encrypt(input, pwd, salt);
        System.out.println("加密后：\t"+Base64.encodeBase64String(output));
        System.out.println("加密后：\t"+new String(Hex.encodeHex(output)));
        output=decrypt(output,pwd,salt);
        System.out.println("解密后：\t"+Base64.encodeBase64String(output));
        System.out.println("解密后：\t"+new String(output));
    }
}
