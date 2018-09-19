package com.gaox.encrypt.example.digitalSignature.rsa;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA算法签名组件
 */
public class RSASignature {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final int KEY_SIZE = 512;

    /**
     * 执行签名
     * @param data  待签名数据
     * @param privateKey  私钥
     * @return  byte[]  数字签名
     * @throws Exception
     */
    public static byte[] sign(byte[] data,byte[] privateKey) throws Exception{
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privKey = factory.generatePrivate(privateKeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 校验
     * @param data  待校验数据
     * @param publicKey  公钥
     * @param sign  数字签名
     * @return  boolean  校验结果
     * @throws Exception
     */
    public static boolean verify(byte[] data,byte[] publicKey,byte[] sign) throws Exception{
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = factory.generatePublic(pubKeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(sign);
    }

    /**
     * 获取私钥
     * @param keyMap  密钥对map
     * @return  byte[]  私钥
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key privateKey = (Key)keyMap.get(PRIVATE_KEY);
        return privateKey.getEncoded();
    }

    /**
     * 获取公钥
     * @param keyMap  密钥对map
     * @return  byte[]  公钥
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
        Key publicKey = (Key)keyMap.get(PUBLIC_KEY);
        return publicKey.getEncoded();
    }

    /**
     * 初始化密钥
     * @return  密钥map
     * @throws Exception
     */
    public static Map<String,Object> initKey() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> map = new HashMap<>();
        map.put(PUBLIC_KEY,publicKey);
        map.put(PRIVATE_KEY,privateKey);
        return map;
    }

}
