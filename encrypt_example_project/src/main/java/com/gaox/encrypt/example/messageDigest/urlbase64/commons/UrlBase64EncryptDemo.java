package com.gaox.encrypt.example.messageDigest.urlbase64.commons;


import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class UrlBase64EncryptDemo {

    private static final String ENCODING="UTF-8";

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "Java加密与解密的艺术";
        System.err.println("原文：\t"+str);
        //进行Base64编码
        String encode = encode(str);
        System.err.println("编码后：\t"+encode);
        //进行Base64解码
        String decode = decode(encode);
        System.err.println("解码后：\t"+decode);
        assert str == decode;
    }

    /**
     * Base64编码
     * @param data 待编码数据
     * @return String 编码数据
     * @throws UnsupportedEncodingException 编码格式异常
     */
    private static String encode(String data) throws UnsupportedEncodingException {
        byte[] bytes = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
        return new String(bytes,ENCODING);
    }

    /**
     * Base64解码
     * @param data 待解码数据
     * @return String 解码数据
     * @throws UnsupportedEncodingException 编码格式异常
     */
    private static String decode(String data) throws UnsupportedEncodingException {
        byte[] bytes = Base64.decodeBase64(data.getBytes(ENCODING));
        return new String(bytes,ENCODING);
    }
}
