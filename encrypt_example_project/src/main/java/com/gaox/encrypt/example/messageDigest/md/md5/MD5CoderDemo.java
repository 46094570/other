package com.gaox.encrypt.example.messageDigest.md.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5消息摘要算法 jdk 实现
 */
public class MD5CoderDemo {
    private static final char[] DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5="MD5消息摘要";
        byte[] bytes1 = encodeMD5(md5.getBytes());
        byte[] bytes2 = encodeMD5(md5.getBytes());
        String bytes3 = md5(md5.getBytes());
        System.out.println(Arrays.toString(bytes1));
        System.out.println(Arrays.toString(bytes2));
        System.out.println(Arrays.toString(bytes3.getBytes()));
        System.out.println(bytes3);
        System.out.println(bytes3.substring(8,24));

    }

    /**
     * MD5消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws NoSuchAlgorithmException 找不到该算法异常
     */
    private static byte[] encodeMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest(data);
    }

    private static String md5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        byte[] bytes = md5.digest(data);
        String md5str = new String(encodeHex(bytes));
        return md5str;
    }

    public static char[] encodeHex(byte[] data)
    {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++)
        {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }
}
