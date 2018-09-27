package com.hs.datatrans.utils.dimeng.utils;

import java.sql.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.UnixCrypt;
import org.apache.commons.lang3.StringUtils;

public class EncryptUtil {
    private static final byte[] ROW_BYTES = "80e36e39f34e678c".getBytes();
    private static SecretKeySpec keySpec;
    private static final String prefix = "dimeng";
    private static final String salt;

    public EncryptUtil() {
    }

    public static String md5(String input) {
        return DigestUtils.md5Hex(input.getBytes());
    }

    public static String md5(String input, String key) {
        return DigestUtils.md5Hex((input + key).getBytes());
    }

    public static String sha(String input) {
        return DigestUtils.shaHex(input.getBytes());
    }

    public static String sha256(String input) {
        return DigestUtils.sha256Hex(input.getBytes());
    }

    public static String sha384(String input) {
        return DigestUtils.sha384Hex(input.getBytes());
    }

    public static String sha512(String input) {
        return DigestUtils.sha512Hex(input.getBytes());
    }

    public static String base64Encode(String input) {
        return Base64.encodeBase64String(input.getBytes()).trim();
    }

    public static String base64Decode(String input) {
        return new String(Base64.decodeBase64(input));
    }

    public static String encode(String input) {
        long millis = System.currentTimeMillis();
        input = base64Encode("dimeng" + input + millis);
        byte[] bytes = input.getBytes();
        StringBuilder sb = new StringBuilder();
        String s = "";

        for(int i = 0; i < bytes.length; ++i) {
            s = Integer.toHexString(bytes[i] + 18);
            sb.append(s.charAt(1) + "" + s.charAt(0));
        }

        return reverse(sb.toString());
    }

    private static String reverse(String input) {
        byte[] bytes1 = input.getBytes();
        byte[] bytes2 = new byte[bytes1.length];

        for(int i = 0; i < bytes1.length; ++i) {
            bytes2[i] = bytes1[bytes1.length - 1 - i];
        }

        byte byte0 = bytes2[0];
        byte byte1 = bytes2[1];
        bytes2[0] = bytes2[bytes2.length - 1];
        bytes2[1] = bytes2[bytes2.length - 2];
        bytes2[bytes2.length - 1] = byte0;
        bytes2[bytes2.length - 2] = byte1;
        return new String(bytes2);
    }

    public static String[] decode(String input) {
        try {
            if (input.length() % 2 != 0) {
                return null;
            } else {
                input = reverse(input);
                byte[] bytes = new byte[input.length() / 2];
                String hexString = "";

                for(int i = 0; i <= input.length() - 2; i += 2) {
                    hexString = input.substring(i + 1, i + 2) + input.substring(i, i + 1);
                    if (!hexString.matches("[0-9a-fA-F]+")) {
                        return null;
                    }

                    bytes[i / 2] = (byte)(Integer.parseInt(hexString, 16) - 18);
                }

                String s = base64Decode(new String(bytes));
                String s1 = s.substring(0, "dimeng".length());
                String s3 = s.substring(s.length() - 13);
                if (s1.equals("dimeng") && s3.matches("\\d+")) {
                    return new String[]{s.substring("dimeng".length(), s.length() - 13), s3};
                } else {
                    return null;
                }
            }
        } catch (Exception var6) {
            return null;
        }
    }

    public static String aesEencode(String content) throws Exception {
        if (StringUtils.isEmpty(content)) {
            return content;
        } else {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, keySpec);
            return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
        }
    }

    public static String aesDecode(String content) throws Exception {
        if (StringUtils.isEmpty(content)) {
            return content;
        } else {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, keySpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)));
        }
    }

    public static String passWord(String passWord) {
        return UnixCrypt.crypt(passWord, salt);
    }

    public static void main(String[] args) {
        System.out.println(new Date(9999999999999L));
        System.out.println(base64Decode("Ac/6L9OYytU2H1drQ9KIGy9sjP236g=="));
        System.out.println(encode("1"));
        System.out.println(decode("c64f89608a677c5f465f56608a7d565f8c63665f858a6976614b5968425c6a6c444744747c437c75887e7f767a7a696c556869748264686c42585964885c6a6c486c656c1c1f435c5a764b67596c886069738768686c8c684562426b5a758b53455e44538c5f8c8156608a46555f87797c608a467b5f47578c5e888156748a606a7786828c6b7b647f738047696c867e59f4")[0]);
    }

    static {
        keySpec = new SecretKeySpec(ROW_BYTES, "AES");
        salt = DigestUtils.sha256Hex("dimeng");
    }
}
