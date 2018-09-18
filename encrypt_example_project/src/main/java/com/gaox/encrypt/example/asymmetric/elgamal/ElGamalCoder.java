package com.gaox.encrypt.example.asymmetric.elgamal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * ElGamal 安全编码组件 jdk 实现
 */
public class ElGamalCoder {
    //非对称加密密钥算法
    private static final String KEY_ALGORITHM = "ElGamal";
    //公钥
    private static final String PUBLIC_KEY = "ElGamalPublicKey";
    //私钥
    private static final String PRIVATE_KEY = "ElGamalPrivateKey";
    /**
     * ElGamal密钥长度
     * 默认1024位
     * 密钥长度必须是8的倍数
     * 范围在160~16384之间
     */
    private static final int KEY_SIZE = 256;

    /**
     * 私钥解密
     * @param data  待解密数据
     * @param key  私钥
     * @return  byte[]  解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data,byte[] key) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        //构建私钥素材
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     * @param data  待加密数据
     * @param key  公钥
     * @return  byte[]  加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data,byte[] key) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        //构建公钥素材
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(key);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = factory.generatePublic(publicKeySpec);
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 获得私钥
     * @param keyMap  密钥map
     * @return  byte[]  私钥
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key key = (Key)keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 获得公钥
     * @param keyMap  密钥map
     * @return  byte[]  公钥
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
        Key key = (Key)keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 获得密钥对
     * @return  密钥对map
     * @throws Exception
     */
    public static Map<String,Object> initKey() throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        AlgorithmParameterGenerator apg = AlgorithmParameterGenerator.getInstance(KEY_ALGORITHM);
        apg.init(KEY_SIZE);
        AlgorithmParameters parameters = apg.generateParameters();
        DHParameterSpec spec = parameters.getParameterSpec(DHParameterSpec.class);

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(spec,new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        HashMap<String, Object> map = new HashMap<>();
        map.put(PUBLIC_KEY,publicKey);
        map.put(PRIVATE_KEY,privateKey);
        return map;
    }
}
