package com.gaox.encrypt.example.digitalSignature.ecdsa;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class ECDSASignature {
    private static final String KEY_ALGORITHM = "EC";
    /**
     * JDK支持
     * <pre>
     * NONEwithECDSA
     * SHA1withECDSA
     * SHA224withECDSA
     * SHA256withECDSA
     * SHA384withECDSA
     * SHA512withECDSA
     * </pre>
     * BC支持
     * RIPEMD160withECDSA
     */
    private static final String SIGNATURE_ALGORITHM = "SHA512withECDSA";
    private static final String PUBLIC_KEY = "ECPublicKey";
    private static final String PRIVATE_KEY = "ECPrivateKey";

    /**
     * 执行签名
     * @param data  待签名数据
     * @param privateKey  私钥
     * @return  byte[]  数字签名
     * @throws Exception
     */
    public static byte[] sign(byte[] data,byte[] privateKey) throws Exception{
        //若需要实现RIPEMD160withECDSA需要加入BC
        //Security.addProvider(new BouncyCastleProvider());
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
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        Map<String, Object> map = new HashMap<>();
        map.put(PUBLIC_KEY,publicKey);
        map.put(PRIVATE_KEY,privateKey);
        return map;
    }

}
