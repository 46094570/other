package com.gaox.encrypt.example.messageDigest.sha;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * SHA-224消息摘要组件 bouncy castle 实现
 */
public class SHA224EncryptDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "SHA消息摘要算法";
        byte[] encodeSHA2241 = encodeSHA224(str.getBytes());
        byte[] encodeSHA2242 = encodeSHA224(str.getBytes());
        String encodeSHA224H = encodeSHA224Hex(str.getBytes());
        System.out.println("encodeSHA2241:" + Arrays.toString(encodeSHA2241));
        System.out.println("encodeSHA2242:" + Arrays.toString(encodeSHA2242));
        System.out.println("encodeSHA224H:" + encodeSHA224H);
    }

    /**
     * SHA-224消息摘要
     *
     * @param data 待编码数据
     * @return String 编码数据
     * @throws NoSuchAlgorithmException 获取算法异常
     */
    private static byte[] encodeSHA224(byte[] data) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest sha = MessageDigest.getInstance("SHA-224");
        return sha.digest(data);
    }

    /**
     * SHA-224消息摘要
     *
     * @param data 待解码数据
     * @return String 解码数据
     * @throws NoSuchAlgorithmException 获取算法异常
     */
    private static String encodeSHA224Hex(byte[] data) throws NoSuchAlgorithmException {
        byte[] bytes = encodeSHA224(data);
        return new String(Hex.encode(bytes));
    }

}
