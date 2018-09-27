package com.hs.datatrans.http;

import com.alibaba.fastjson.JSONObject;
import com.hs.datatrans.config.DxiangInfoBasicConfig;
import com.hs.datatrans.entity.qianpen.vo.MessageVo;
import com.hs.datatrans.utils.qianpen.utils.AesUtil;
import com.hs.datatrans.utils.qianpen.utils.HttpTools;

import java.util.HashMap;
import java.util.Map;

public class InterConnection {
    private static String appId = DxiangInfoBasicConfig.getAppId();
    private static String token = DxiangInfoBasicConfig.getToken();

    public static MessageVo sendPostRequest(String requetUrl, Map<String,String> paramMap) throws Exception{
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("sign", AesUtil.aesEncode(JSONObject.toJSONString(paramMap), token));
        requestMap.put("appId", appId);
        String resultBd = HttpTools.doFormPost(requetUrl, requestMap);
        MessageVo msg = JSONObject.parseObject(resultBd, MessageVo.class);
        return msg;
    }

    public static String decryptMessage(String message){
        return AesUtil.aesDecode(message, token);
    }
}
