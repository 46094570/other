package com.gaox.encrypt.example.symmetricEncryption.idea;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;

/**
 *  IDEA 安全编码组件 Bouncy Castle 实现
 */
public class IDEAEncoderDemo {

    /**
     *  密钥算法
     */
    private static final String KEY_ALGORITHM = "IDEA";

    /**
     *  加密（解密）算法 / 工作模式 / 填充方式
     */
    private static final String CIPHER_ALGORITHM = "IDEA/ECB/ISO10126Padding";

    /**
     * 生成密钥
     * @return byte[] 二进制密钥
     * @throws Exception
     */
    private static byte[] initKey() throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
        generator.init(128);
        SecretKey key = generator.generateKey();
        return key.getEncoded();
    }

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return Key 密钥
     */
    private static Key toKey(byte[] key){
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    private static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,k);
        return cipher.doFinal(data);

    }

    private static byte[] decrypt(byte[] data,byte[] key) throws  Exception{
        Security.addProvider(new BouncyCastleProvider());
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,k);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String s = "This is IDEA test demo";
        byte[] inputData = s.getBytes();
        System.err.println("原文：\t"+s);
        System.err.println("原文：\t"+Base64.encodeBase64String(inputData));
        System.err.println("原文：\t"+ Arrays.toString(inputData));
        System.err.println("原文：\t"+ Arrays.toString(Hex.encode(inputData)));
        byte[] key = initKey();
        System.err.println("密钥：\t" + Base64.encodeBase64String(key));
        System.err.println("密钥：\t" + Arrays.toString(key));
        System.err.println("密钥：\t" + Arrays.toString(Hex.encode(key)));
        System.err.println("密钥：\t" + new String(Hex.encode(key)));
        inputData = encrypt(inputData, key);
        System.err.println("加密后：\t" + Base64.encodeBase64String(inputData));
        byte[] outputData = decrypt(inputData, key);
        System.err.println("解密后：\t"+new String(outputData));
    }
}
