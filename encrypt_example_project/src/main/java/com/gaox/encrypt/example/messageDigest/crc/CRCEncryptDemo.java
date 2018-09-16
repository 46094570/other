package com.gaox.encrypt.example.messageDigest.crc;

import java.util.zip.CRC32;

/**
 * CRC32 算法 sun 实现
 */
public class CRCEncryptDemo {

    public static void main(String[] args) {
        String str = "测试CRC-32";
        String encodeCRC32 = encodeCRC32(str.getBytes());
        System.out.println("encodeCRC32:"+encodeCRC32);
    }

    /**
     * CRC-32算法消息摘要
     * @param data 待做消息摘要处理的数据
     * @return String 消息摘要
     */
    private static String encodeCRC32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return Long.toHexString(crc32.getValue());
    }
}
