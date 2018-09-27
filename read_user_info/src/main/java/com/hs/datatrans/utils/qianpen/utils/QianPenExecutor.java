package com.hs.datatrans.utils.qianpen.utils;

import com.alibaba.fastjson.JSONObject;
import com.hs.datatrans.entity.qianpen.vo.MessageVo;
import com.hs.datatrans.entity.qianpen.vo.TokenVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 钱盆任务执行者
 */
@Component
public class QianPenExecutor {

	private static final Logger log = LoggerFactory.getLogger(QianPenExecutor.class);

	public static ResourceBundle resource = null;

	public static String riskAppCode = null;
	public static String riskAppKey = null;
	public static String riskRequestUrl = null;
	public static String nodeId = null;
	public static TokenVo token = null;

	static {
		resource = ResourceBundle.getBundle("qianpen");
		riskAppCode = resource.getString("riskAppCode");
		riskAppKey = resource.getString("riskAppKey");
		riskRequestUrl = resource.getString("riskRequestUrl");
		nodeId = resource.getString("nodeId");
	}

	/**
	 * 获取钱盆token
	 */
	public static TokenVo getToken() {
		try {
			if (token == null) {
				token = TokenManage.getToken(riskAppCode, riskAppKey, riskRequestUrl, false);
			} else {
				// 钱盆的token 12小时失效,在10-12小时的时候更新token
				long minutes = DateUtil.diffMinutes(token.getCreateDatetime(), new Date());
				if (minutes >= 600 && minutes < 720) {
					MessageVo<TokenVo> messageVo = TokenManage.refreshToken(token.getAccessToken(), token.getAppId(),
							null, riskRequestUrl);
					token = messageVo.getData();
				}
				// 超过12小时再次获取token
				else if (minutes >= 720) {
					token = TokenManage.getToken(riskAppCode, riskAppKey, riskRequestUrl, false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取token失败，原因：" + e.getMessage(), e);
		}
		return token;
	}

	/**
	 * 钱盆信息交互工具
	 * 
	 * @param token
	 * @param param
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public static MessageVo send(TokenVo token, Map<String, String> param, String uri) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sign", AesUtil.aesEncode(JSONObject.toJSONString(param), token.getAccessToken()));
		paramMap.put("appId", token.getAppId());
		String result = HttpTools.doFormPost(uri, paramMap);
		if (StringUtils.isBlank(result)) {
			// System.err.println("========================接口异常==接口名：" + uri +
			// ",参数:" + JsonUtil.objToJson(param));
			throw new Exception("服务器繁忙...");
		}
		// MessageVo messageVo = JsonUtil.jsonToObj(result, MessageVo.class);
		MessageVo messageVo = JSONObject.parseObject(result, MessageVo.class);
		if (!messageVo.isSuccess()) {
			log.error("========================接口异常==接口名：" + uri + ",错误码为:" + messageVo.getErrMsg());
		}
		messageVo.setSecurityKey(token.getAccessToken());
		return messageVo;
	}

	/**
	 * 发送http请求给钱盆获取加密后的结果
	 * 
	 * @param token
	 * @param json
	 * @param uri
	 *            接口地址
	 */
	@SuppressWarnings({ "rawtypes" })
	public static MessageVo send(TokenVo token, String json, String uri) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sign", AesUtil.aesEncode(json, token.getAccessToken()));
		paramMap.put("appId", token.getAppId());
		String result = HttpTools.doFormPost(uri, paramMap);
		if (StringUtils.isBlank(result)) {
			throw new Exception("服务器繁忙...");
		}
		MessageVo messageVo = JSONObject.parseObject(result, MessageVo.class);
		if (!messageVo.isSuccess()) {
			log.error("========================接口异常==接口名：" + uri + ",参数:" + json + ",错误码为:" + messageVo.getErrMsg());
		}
		messageVo.setSecurityKey(token.getAccessToken());
		return messageVo;
	}

}
