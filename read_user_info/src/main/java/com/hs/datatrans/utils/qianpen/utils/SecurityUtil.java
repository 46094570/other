package com.hs.datatrans.utils.qianpen.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

public class SecurityUtil
{
    static Map<String, String> mKeyMap = null;
    
    /**
     * <生成密钥对>
     * <生成RSA公钥和私钥>
     * @return
     * @throws Exception
     */
    public static Map<String, String> getRsaKey()
        throws Exception
    {
        Map<String, Object> keyMap = null;
        if (null == mKeyMap)
        {
            mKeyMap = new HashMap<String, String>();
            keyMap = RSAUtils.genKeyPair();
        }
        String publicKey = RSAUtils.getPublicKey(keyMap);
        String privateKey = RSAUtils.getPrivateKey(keyMap);
        mKeyMap.put("publicKey", publicKey);
        mKeyMap.put("privateKey", privateKey);
        return mKeyMap;
    }
    
    /**
     * <加密客户端公钥>
     * <rsa算法加密客户端公钥并通过第一次我手中获取到的服务端公钥进行加密>
     * @param cPublicKey
     * @param servicePublicKey
     * @return
     */
    public static String rsaEncryptByPublicKey(String cPublicKey, String servicePublicKey)
    {
        byte[] encodedData = null;
        try
        {
            encodedData = RSAUtils.encryptByPublicKey(cPublicKey.getBytes(), servicePublicKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //        return new String(encodedData);
        return Base64.encodeBase64String(encodedData);
    }
    
    public static String rsaDecodeByPrivateKey(String tokenDecode, String cPrivateKey)
    {
        byte[] encodedData = null;
        try
        {
            encodedData = RSAUtils.decryptByPrivateKey(Base64.decodeBase64(tokenDecode.getBytes()), cPrivateKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //        return new String(encodedData);
        return new String(encodedData);
    }
    
}
