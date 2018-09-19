package com.gaox.encrypt.example.asymmetric.http.utils;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA 安全编码组件 jdk 实现
 */
public class RSACoder {
    //非对称加密密钥算法
    private static final String KEY_ALGORITHM = "RSA";
    //公钥
    private static final String PUBLIC_KEY = "RSAPublicKey";
    //私钥
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA加密算法加密块大小
     * RSA根据不同密钥的长度加密块大小也不同
     * 本案例采用512密钥长度，加密块大小53byte，解密块大小64byte
     * 超过该长度的消息需要进行分块加密
     */
    private static final int MAX_ENCRYPT_BLOCK = 53;
    private static final int MAX_DECRYPT_BLOCK = 64;

    /**
     * RSA密钥长度
     * 默认1024位
     * 密钥长度必须是64的倍数
     * 范围在512~65536之间
     */
    private static final int KEY_SIZE = 512;

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[]  解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //构建私钥素材
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        //添加分段解密方法
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int dataLenth = data.length;
        System.out.println("data length:"+dataLenth);
        int offset = 0;
        byte[] cache;
        int i =0;
        while (dataLenth - offset >0) {
            if(dataLenth - offset > MAX_DECRYPT_BLOCK){
                cache = cipher.doFinal( data, offset, MAX_DECRYPT_BLOCK);
            }else {
                cache = cipher.doFinal(data, offset, dataLenth - offset);
            }
            out.write( cache, 0, cache. length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedDdeata = out.toByteArray();
        System.out.println("decryptedDdeata length:\t"+decryptedDdeata.length);
        out.close();
        return decryptedDdeata;
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return byte[]  解密数据
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
        //构建公钥素材
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(key);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = factory.generatePublic(publicKeySpec);
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        /**
         * 以下是RSA分段解密处理方法
         * 这里做测试用，并未所有方法都添加，实际上应该所有解密方法都添加
         */
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int dataLenth = data.length;
        System.out.println("data length:"+dataLenth);
        int offset = 0;
        byte[] cache;
        int i =0;
        while (dataLenth - offset >0) {
            if(dataLenth - offset > MAX_DECRYPT_BLOCK){
                cache = cipher.doFinal( data, offset, MAX_DECRYPT_BLOCK);
            }else {
                cache = cipher.doFinal(data, offset, dataLenth - offset);
            }
            out.write( cache, 0, cache. length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedDdeata = out.toByteArray();
        System.out.println("decryptedDdeata length:\t"+decryptedDdeata.length);
        out.close();
        return decryptedDdeata;
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[]  加密数据
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //构建私钥素材
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        /**
         * 以下是RSA分段加密处理方法
         * 这里做测试用，并未所有方法都添加，实际上应该所有加密方法都添加
         */
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int dataLenth = data.length;
        System.out.println("data length:"+dataLenth);
        int offset = 0;
        byte[] cache;
        int i =0;
        while (dataLenth - offset >0) {
            if(dataLenth - offset > MAX_ENCRYPT_BLOCK){
                cache = cipher.doFinal( data, offset, MAX_ENCRYPT_BLOCK);
            }else {
                cache = cipher.doFinal(data, offset, dataLenth - offset);
            }
            out.write( cache, 0, cache. length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        System.out.println("encryptedData length:\t"+encryptedData.length);
        out.close();
        return encryptedData;
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[]  加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        //构建公钥素材
//        Security.addProvider(new BouncyCastleProvider());
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(key);
//        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM,"BC");
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = factory.generatePublic(publicKeySpec);
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //添加分段加密
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int dataLenth = data.length;
        System.out.println("data length:"+dataLenth);
        int offset = 0;
        byte[] cache;
        int i =0;
        while (dataLenth - offset >0) {
            if(dataLenth - offset > MAX_ENCRYPT_BLOCK){
                cache = cipher.doFinal( data, offset, MAX_ENCRYPT_BLOCK);
            }else {
                cache = cipher.doFinal(data, offset, dataLenth - offset);
            }
            out.write( cache, 0, cache. length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        System.out.println("encryptedData length:\t"+encryptedData.length);
        out.close();
        return encryptedData;
    }

    /**
     * 获得私钥
     *
     * @param keyMap 密钥map
     * @return byte[]  私钥
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 获得公钥
     *
     * @param keyMap 密钥map
     * @return byte[]  公钥
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 获得密钥对
     *
     * @return 密钥对map
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        HashMap<String, Object> map = new HashMap<>();
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);
        return map;
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[]  加密数据
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        return encryptByPrivateKey(data, getKey(key));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[]  加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        return encryptByPublicKey(data, getKey(key));
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[]  解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        return decryptByPrivateKey(data, getKey(key));
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return byte[]  解密数据
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        return decryptByPublicKey(data, getKey(key));
    }

    /**
     * 获取私钥
     *
     * @param keyMap 密钥map
     * @return String  十六进制编码密钥
     * @throws Exception
     */
    public static String getPrivateKeyString(Map<String, Object> keyMap) throws Exception {
//        return Base64.encodeBase64String(getPrivateKey(keyMap));
        return Hex.encodeHexString(getPrivateKey(keyMap));
    }

    /**
     * 获取公钥
     *
     * @param keyMap 密钥map
     * @return String  十六进制编码密钥
     * @throws Exception
     */
    public static String getPublicKeyString(Map<String, Object> keyMap) throws Exception {
//        return Base64.encodeBase64String(getPublicKey(keyMap));
        return Hex.encodeHexString(getPublicKey(keyMap));
    }

    /**
     *  获取密钥
     * 十六进制方式或者Base64方式获取密钥都是一样的
     *
     * @param key 十六进制密钥
     * @return byte[]  二进制密钥
     * @throws DecoderException
     */
    public static byte[] getKey(String key) throws DecoderException {
//        return Base64.decodeBase64(key.getBytes());
        return Hex.decodeHex(key.toCharArray());
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> keyMap = initKey();
        String privateKeyString = getPrivateKeyString(keyMap);
        String publicKeyString = getPublicKeyString(keyMap);
        System.out.println("publicKey - \r\n" + publicKeyString);
        System.out.println("privateKey - \r\n" + privateKeyString);
    }
}
