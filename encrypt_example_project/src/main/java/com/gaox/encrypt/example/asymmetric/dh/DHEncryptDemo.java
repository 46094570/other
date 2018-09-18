package com.gaox.encrypt.example.asymmetric.dh;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * DH安全编码组件 jdk 实现
 * 测试案例见test，案例复杂无法main方法测试
 */
public class DHEncryptDemo {
    //非对称加密密钥算法
    private static final String KEY_ALGORITHM = "DH";

    /**
     * 本地密钥算法，即对称加密密钥算法
     * 可选DES、DESege、AES 算法
     * Java 8 update 161版本以后就会出现此问题，根本原因还是DH密钥长度至少为512位，而AES算法密钥没有这么长，密钥长度不一致引起的
     * 配置JVM的系统变量：-Djdk.crypto.KeyAgreement.legacyKDF=true
     */
    private static final String SECRET_ALGORITHM = "AES";

    /**
     * 密钥长度
     * DH 默认密钥长度1024
     * 密钥长度必须是 64 的整数倍，其范围在 512 和 1024 位之间
     */
    private static final int KEY_SIZE = 512;
    //公钥
    private static final String PUBLIC_KEY = "DHPublicKey";
    //私钥
    private static final String PRIVATE_KEY = "DHPrivateKey";

    /**
     * 初始化甲方密钥对
     * @return  Map  甲方密钥map
     * @throws Exception
     */
    public static Map<String,Object> initKey() throws Exception{
        //实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥对生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
        //甲方私钥
        DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
        //将密钥对存储在Map中
        HashMap<String, Object> map = new HashMap<>();
        map.put(PUBLIC_KEY,publicKey);
        map.put(PRIVATE_KEY,privateKey);
        return map;
    }

    /**
     * 初始化乙方密钥对
     * @param key  甲方公钥
     * @return  Map  乙方密钥map
     * @throws Exception
     */
    public static Map<String,Object> initKey(byte[] key) throws Exception{
        //解析甲方公钥，转换公钥材料
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
        //实例化密钥工厂
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成甲方公钥
        PublicKey pubKey = factory.generatePublic(x509EncodedKeySpec);
        //根据甲方公钥构建乙方密钥
        DHParameterSpec params = ((DHPublicKey) pubKey).getParams();

        //--------以下跟甲方构建密钥过程一致---------

        //实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(factory.getAlgorithm());
        //初始化密钥对生成器
        keyPairGenerator.initialize(params);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
        //甲方私钥
        DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
        //将密钥对存储在Map中
        HashMap<String, Object> map = new HashMap<>();
        map.put(PUBLIC_KEY,publicKey);
        map.put(PRIVATE_KEY,privateKey);
        return map;
    }

    /**
     *  AES 数据加密
     * @param data  待加密数据
     * @param key   密钥
     * @return  byte[]  加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        //生成本地密钥
        SecretKeySpec keySpec = new SecretKeySpec(key, SECRET_ALGORITHM);
        Cipher cipher = Cipher.getInstance(keySpec.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,keySpec);
        return cipher.doFinal(data);
    }

    /**
     *  AES 数据解密
     * @param data  待加密数据
     * @param key   密钥
     * @return  byte[]  加密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        //生成本地密钥
        SecretKeySpec keySpec = new SecretKeySpec(key, SECRET_ALGORITHM);
        Cipher cipher = Cipher.getInstance(keySpec.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,keySpec);
        return cipher.doFinal(data);
    }

    /**
     * 根据甲方公钥乙方私钥构建本地AES密钥
     * @param publicKey  公钥
     * @param privateKey  私钥
     * @return  byte[]  本地密钥
     * @throws Exception
     */
    public static byte[] getSecretKey(byte[] publicKey,byte[] privateKey) throws Exception{
        //实例化密钥工厂
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥转换材料
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey);
        //生成公钥
        PublicKey pubKey = factory.generatePublic(publicKeySpec);
        //初始化私钥转换材料
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey);
        //生成私钥
        PrivateKey privKey = factory.generatePrivate(privateKeySpec);
        //实例化密钥对协议
        KeyAgreement agreement = KeyAgreement.getInstance(factory.getAlgorithm());
        //根据密钥对协议生成本地AES密钥
        agreement.init(privKey);
        agreement.doPhase(pubKey,true);
        SecretKey aesKey = agreement.generateSecret(SECRET_ALGORITHM);
        return aesKey.getEncoded();
    }

    /**
     * 获得私钥
     * @param keyMap 密钥对map
     * @return  byte[] 私钥
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key k = (Key)keyMap.get(PRIVATE_KEY);
        return k.getEncoded();
    }

    /**
     * 获得公钥
     * @param keyMap 密钥对map
     * @return  byte[]  公钥
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
        Key k = (Key)keyMap.get(PUBLIC_KEY);
        return k.getEncoded();
    }

}
