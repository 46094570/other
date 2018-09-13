package com.hs.datatrans.utils.qianpen.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Aes加解密工具类
 * 
 * @author yi
 * @version [版本号, 2017年12月15日]
 */
public class AesUtil
{
    static final String algorithmStr = "AES/ECB/PKCS5Padding";
    
    private static KeyGenerator keyGen;
    
    /**
     * 字符编码
     */
    private static final String CHARSET_NAME = "UTF-8";
    
    @SuppressWarnings("unused")
    private static Cipher cipher;
    
    static
    {
        try
        {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // 128位的AES加密
            cipher = Cipher.getInstance(algorithmStr);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        
    }
    
    private static SecretKeySpec getSecretKeySpec(String password)
        throws Exception
    {
        byte[] rByte = null;
        if (password != null)
        {
            rByte = password.getBytes(CHARSET_NAME);
        }
        else
        {
            rByte = new byte[24];
        }
        return new SecretKeySpec(rByte, "AES");
    }
    
    /**
     * 
     * @param content
     * @param password 必须是16位长度
     * @return
     */
    private static byte[] encrypt(String content, String password)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(algorithmStr);
            byte[] byteContent = content.getBytes(CHARSET_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(password));
            byte[] result = cipher.doFinal(byteContent);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @param content
     * @param password 必须是16位长度
     * @return
     */
    private static byte[] decrypt(byte[] content, String password)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(algorithmStr);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(password));
            byte[] result = cipher.doFinal(content);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 加密
     * content：待加密内容
     * keyBytes：加密使用的key
     */
    public static String aesEncode(String content, String keyBytes)
    {
        String key = Md5Encrypt.md5(keyBytes).substring(8, 24);
        return Base64.encodeBase64String(encrypt(content, key));
    }
    
    /**
     * 解密
     * content：待解密内容
     * keyBytes：解密使用的key
     */
    public static String aesDecode(String content, String keyBytes)
    {
        String key = Md5Encrypt.md5(keyBytes).substring(8, 24);
        byte[] b = decrypt(Base64.decodeBase64(content), key);
        try
        {
            return new String(b, CHARSET_NAME);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 加密
     */
    public static String aesEncode2(String content, String keyBytes)
        throws Exception
    {
        if (StringUtils.isBlank(content) || StringUtils.isBlank(keyBytes))
        {
            return null;
        }
        if (keyBytes.length() != 16)
        {
            throw new Exception("秘钥必须是16位");
        }
        return Base64.encodeBase64String(encrypt(content, keyBytes));
    }
    
    /**
     * 解密
     */
    public static String aesDecode2(String content, String keyBytes)
        throws Exception
    {
        if (StringUtils.isBlank(content) || StringUtils.isBlank(keyBytes))
        {
            return null;
        }
        if (keyBytes.length() != 16)
        {
            throw new Exception("秘钥必须是16位");
        }
        
        byte[] b = decrypt(Base64.decodeBase64(content), keyBytes);
        try
        {
            return new String(b, CHARSET_NAME);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args)
    {
        /**
         * AES加密模式：ECB 填充：pkcs5padding 、pkcs7padding 数据块：128
         * 密码：fbd3c3445a1b7d23（micool） base64 字符集：utf-8
         */
        System.out.println(aesEncode("骚俊", "micool"));
        System.out.println(aesDecode("sM62hSUuOXrL8Y4+gZMsPw==", "micool"));
        if ("sM62hSUuOXrL8Y4+gZMsPw==".equals(aesEncode("骚俊", "micool")))
        {
            System.out.println("是的,就是这么骚");
        }
    }
    
}
