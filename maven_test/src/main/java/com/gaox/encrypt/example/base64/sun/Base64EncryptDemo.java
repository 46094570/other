package com.gaox.encrypt.example.base64.sun;


import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static sun.net.www.ParseUtil.decode;

/**
 * Sun Base64 实现
 */
public class Base64EncryptDemo {

    private static final String ENCODEING = "UTF-8";

    public static void main(String[] args) throws IOException {
        String str = "Java加密与解密的艺术";
        System.err.println("原文：\t"+str);
        byte[] bytes = str.getBytes();
        //进行Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(bytes);
        System.err.println("编码后：\t"+encode);
        //进行Base64解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodeBytes = decoder.decodeBuffer(encode);
        System.err.println("解码后：\t"+new String(decodeBytes));
    }
}
