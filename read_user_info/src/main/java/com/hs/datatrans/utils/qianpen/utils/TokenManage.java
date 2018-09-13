package com.hs.datatrans.utils.qianpen.utils;

import com.alibaba.fastjson.JSONObject;
import com.hs.datatrans.entity.qianpen.vo.HandshakeOne;
import com.hs.datatrans.entity.qianpen.vo.MessageVo;
import com.hs.datatrans.entity.qianpen.vo.TokenVo;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27.
 */
public class TokenManage
{
    /**
     * 握手并得到token
     */
    @SuppressWarnings("rawtypes")
    private static MessageVo<TokenVo> handshake(String appCode, String riskRequestUrl)
    {
        MessageVo<TokenVo> msg = new MessageVo<TokenVo>();
        try
        {
            String message = HttpTools.doFormPost(riskRequestUrl + "/auth/handshakeOne", new HashMap<String, String>());
            if (StringUtils.isBlank(message))
            {
                msg.setErrMsg("服务器异常...");
                return msg;
            }
            MessageVo messageVo = JSONObject.parseObject(message, MessageVo.class);
            if (messageVo.isSuccess())
            {
                // 1.第一次握手获取服务端的公钥和appId
                HandshakeOne handshakeOne =
                    JSONObject.parseObject(JSONObject.toJSONString(messageVo.getData()), HandshakeOne.class);
                String servicePublicKey = handshakeOne.getServicePublicKey();
                String appId = handshakeOne.getAppId();
                
                // 2.1生成自己的公钥和私钥，
                // Map<String, String> key = SecurityUtil.getRsaKey();
                // String cPublicKey = key.get("publicKey");
                // String cPrivateKey = key.get("privateKey");
                String cPublicKey =
                    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCK5vE4LjKam7Q6w/EIx009o8cIxBaBIDFsz9FhCylGD+9hb+e3CE4LWqXWAK3iDmiOJyKNG/BAPDX0gf9unGaqXZcJSZrGRLJk9+6j3LeauJSzlgSftTWPkTXqZ4hkeGMaC+dQnNrH/p3T2KbpLwQU4T31V0NQb4ddyEK/uWKpCwIDAQAB";
                String cPrivateKey =
                    "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIrm8TguMpqbtDrD8QjHTT2jxwjEFoEgMWzP0WELKUYP72Fv57cITgtapdYAreIOaI4nIo0b8EA8NfSB/26cZqpdlwlJmsZEsmT37qPct5q4lLOWBJ+1NY+RNepniGR4YxoL51Cc2sf+ndPYpukvBBThPfVXQ1Bvh13IQr+5YqkLAgMBAAECgYBTaBOm0Y7PafnSY8BDrjGCdurYJ67/wI4NMckfha02BfGPR7wTGyiKBteX5naDTHWaDoPfycv9NmqLtNfl2oKGl2mp77mo8sgeTyj78BxHasjFlUx0odgkCWZC1Lhy9QvFZkrrgIUhQu4JwP/G8L5P0SKCV7NjLgVqQxl3kf5+6QJBAPAfzAJ6ozruakPgLOPxj0WsP/T1hRurlRaRkVgYS4nld6YQ1SAuqDNAj6c9RjlSecx1R0hiyXAMrPE5BaPfGYcCQQCUFetB6TV6QomuB9/Sg9Zv+RHz6MTij8MpYiE2jU0qUjCJV1vRFHt92kd/V3zFTL7EE/ffGr5L08Io+RheI0VdAkBKbfC0jALwj1oM8/22IfgA5qVkbn3117d8qV5d6hnwAlqV9P263ftw94APnKSZvADY6TlRLJ23x5YLp1siMlMdAkA8lNcAyOJK2QuwOaJ0YeEJP/D7Qr/BHCUwOefyhE4MQUF//lAese1vaCwVyXnO0X29g5nFGRC+vqvQzU8NvAo1AkBNm0Q9ftK1EM6w5cHHDnQALx+M+F6b/QfP/ybkHOiCUxT5il8FcUl49j5AWYugmsoTiI6eX8iax7yKlkuvojd8";
                
                // 2.2并用服务端的公钥加密自己的公钥
                Map<String, String> paramMap = new HashMap<String, String>();
                paramMap.put("key", SecurityUtil.rsaEncryptByPublicKey(cPublicKey, servicePublicKey));
                paramMap.put("appId", appId);
                
                // 2.3发给服务端,服务端返回一个token
                String handshakeTwoMsg = HttpTools.doFormPost(riskRequestUrl + "/auth/handshakeTwo", paramMap);
                if (StringUtils.isBlank(handshakeTwoMsg))
                {
                    msg.setErrMsg("服务器异常...");
                    return msg;
                }
                // 3.该token是用客户端的公钥加密的，有效时间2个小时
                MessageVo tokenMsg = JSONObject.parseObject(handshakeTwoMsg, MessageVo.class);
                if (tokenMsg.isSuccess())
                {
                    // 3.1得到服务端用cPublicKey加密后的token，先用cPrivateKey解密得到token然后把token放入缓存中
                    String tokenDecode = String.valueOf(tokenMsg.getData());
                    String tokenJson = SecurityUtil.rsaDecodeByPrivateKey(tokenDecode, cPrivateKey);
                    
                    TokenVo token = JSONObject.parseObject(tokenJson, TokenVo.class);
                    msg.setData(token);
                    return msg;
                }
                else
                {
                    return msg;
                }
                
            }
            else
            {
                msg.setErrMsg(messageVo.getErrMsg());
                return msg;
            }
        }
        catch (Exception e)
        {
            msg.setErrMsg("握手失败....");
            return msg;
        }
    }
    
    /**
     * 
     * @param appId 握手AppId
     * @param appCode 账号
     * @param secret 密码
     * @param riskRequestUrl
     * @return
     */
    @SuppressWarnings("unchecked")
    private static MessageVo<TokenVo> login(TokenVo token, String appCode, String secret, String riskRequestUrl)
    {
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appCode", appCode);
        paramMap.put("appKey", secret);
        Map<String, String> loginMap = new HashMap<String, String>();
        
        loginMap.put("sign", AesUtil.aesEncode(JSONObject.toJSONString(paramMap), token.getAccessToken()));
        loginMap.put("appId", token.getAppId());
        
        MessageVo<TokenVo> tokenMsg = new MessageVo<TokenVo>();
        String msg = "";
        try
        {
            msg = HttpTools.doFormPost(riskRequestUrl + "/out/app/login", loginMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (StringUtils.isBlank(msg))
        {
            tokenMsg.setErrMsg("服务器异常...");
            return tokenMsg;
        }
        tokenMsg = JSONObject.parseObject(msg, MessageVo.class);
        
        if (tokenMsg.isSuccess())
        {
            String tokenSecurity = tokenMsg.getSecurityData();
            String tokenJson = AesUtil.aesDecode(tokenSecurity, token.getAccessToken());
            
            TokenVo token2 = JSONObject.parseObject(tokenJson, TokenVo.class);
            tokenMsg.setData(token2);
            return tokenMsg;
        }
        else
        {
            return tokenMsg;
        }
    }
    
    /**
     * 换token
     * 
     * @param accessTokenEncode
     * @param appId
     * @param riskRequestUrl
     * @throws IOException
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public static MessageVo<TokenVo> refreshToken(String accessToken, String appId, String appCode,
        String riskRequestUrl)
        throws ParseException, IOException
    {
        
        Map<String, String> refreshTokenMap = new HashMap<String, String>();
        
        refreshTokenMap.put("accessTokenEncode", AesUtil.aesEncode(accessToken, accessToken));
        refreshTokenMap.put("appId", appId);
        
        MessageVo<TokenVo> tokenMsg = new MessageVo<TokenVo>();
        String msg = HttpTools.doFormPost(riskRequestUrl + "/auth/refreshToken", refreshTokenMap);
        if (StringUtils.isBlank(msg))
        {
            tokenMsg.setErrMsg("服务器异常...");
            return tokenMsg;
        }
        tokenMsg = JSONObject.parseObject(msg, MessageVo.class);
        if (tokenMsg.isSuccess())
        {
            String tokenSecurity = tokenMsg.getSecurityData();
            String tokenJson = AesUtil.aesDecode(tokenSecurity, accessToken);
            
            TokenVo token2 = JSONObject.parseObject(tokenJson, TokenVo.class);
            
            tokenMsg.setData(token2);
            return tokenMsg;
        }
        else
        {
            return tokenMsg;
        }
    }
    
    /**
     * 强行获取一个新的token
     * @param appid
     * @param secret
     * @param riskRequestUrl
     * @return
     * @throws Exception
     */
    private static TokenVo getNewToken(String appCode, String secret, String riskRequestUrl)
        throws Exception
    {
        MessageVo<TokenVo> msg = handshake(appCode, riskRequestUrl);
        if (msg.isSuccess())
        {
            msg = login(msg.getData(), appCode, secret, riskRequestUrl);
        }
        if (msg.isSuccess())
        {
            return msg.getData();
        }
        else
        {
            throw new Exception("获取token失败....");
        }
    }
    
    /**
     * 获取一个可以用的token
     * @param appCode
     * @param secret
     * @param riskRequestUrl
     * @param isNew 是否是强行获取一个新的token
     * @throws Exception
     */
    @SuppressWarnings("null")
    public static synchronized TokenVo getToken(String appCode, String secret, String riskRequestUrl, Boolean isNew)
        throws Exception
    {
        String tokenJson = "";
        MessageVo<TokenVo> msg = null;
        if (StringUtils.isBlank(tokenJson))
        {
            // 握手得到token的消息
            msg = handshake(appCode, riskRequestUrl);
            if (msg.isSuccess())
            {
                // 登录
                msg = login(msg.getData(), appCode, secret, riskRequestUrl);
            }
        }
        else
        {
            TokenVo token = JSONObject.parseObject(tokenJson, TokenVo.class);
            Boolean falg = token.timeout(10);
            if (falg)
            {
                if (!msg.isSuccess() && msg.getErrCode() == 60019)
                {
                    TokenVo tokenVo = null;
                    try
                    {
                        tokenVo = getNewToken(appCode, secret, riskRequestUrl);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    msg.setSuccess(true);
                    msg.setData(tokenVo);
                }
            }
            else
            {
                if (token.isLogin())
                {// 已经登录了且未超时，直接返回
                    return token;
                }
                else
                {
                    msg = login(token, appCode, secret, riskRequestUrl);
                }
            }
        }
        if (msg == null)
        {
            throw new Exception("获取token失败.");
        }
        if (msg.isSuccess())
        {
            return msg.getData();
        }
        else
        {
            throw new Exception(msg.getErrMsg());
        }
        
    }
    
}
