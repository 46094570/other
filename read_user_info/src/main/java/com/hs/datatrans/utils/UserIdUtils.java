package com.hs.datatrans.utils;

import org.apache.logging.log4j.core.util.UuidUtil;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class UserIdUtils {
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static String single ="number";
    public static String node = "20";
    public static long point = 100L;
    public static SimpleDateFormat sdf;
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        if ("number".equals(single)) {
            shortBuffer.append(node).append(getSequenceNumber());
            return shortBuffer.toString();
        } else {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String str = "";

            for(int i = 0; i < 16; ++i) {
                str = uuid.substring(i * 2, i * 2 + 2);
                int x = Integer.parseInt(str, 16);
                shortBuffer.append(chars[x % 62]);
            }

            return shortBuffer.toString();
        }
    }
    private static synchronized String getSequenceNumber() {
        ++point;
        if (point == 999L) {
            point = 100L;
        }

        return "" + System.currentTimeMillis() + point;
    }

    static {
        if (node.length() < 4) {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < 4 - node.length(); ++i) {
                sb.append("0");
            }

            node = sb.append(node).toString();
        }

        sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
    }

    public static String general25UUID(){
        return UuidUtil.getTimeBasedUuid().toString().replace("-","").substring(0,25);
    }
}
