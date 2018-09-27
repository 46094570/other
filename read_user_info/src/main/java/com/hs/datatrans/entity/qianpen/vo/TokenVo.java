package com.hs.datatrans.entity.qianpen.vo;

import java.util.*;

public class TokenVo {
	private static final long serialVersionUID = -7765191329684499609L;

	private String accessToken;

	private Date createDatetime;

	private int expiresIn = 7200;

	/**
	 * 是否已经登录
	 */
	private boolean isLogin;

	/**
	 * 是否是单点登录
	 */
	private boolean isSingle;

	/**
	 * 登录的id 用户id或者
	 */
	private Long id;

	/**
	 * 1.用户类型的token 2.app外部接口类型的token
	 */
	private int type;

	/**
	 * 权限码
	 */
	private List<String> authorCodeList = new ArrayList<String>();

	/**
	 * 自定义扩展的参数
	 */
	private Map<String, Object> extendMap = new HashMap<String, Object>();

	/**
	 * 每次握手产生的appId
	 */
	private String appId;

	public boolean isSingle() {
		return isSingle;
	}

	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<String> getAuthorCodeList() {
		return authorCodeList;
	}

	public void setAuthorCodeList(List<String> authorCodeList) {
		this.authorCodeList = authorCodeList;
	}

	public Map<String, Object> getExtendMap() {
		return extendMap;
	}

	/**
	 * 从自定义对象中取回对应的值
	 * 
	 * @param key
	 * @return
	 */
	public Object getExtendMap(String key) {
		// if (ValidateUtil.isBlank(extendMap))
		// {
		// return null;
		// }
		if (null == extendMap) {
			return null;
		}
		return extendMap.get(key);
	}

	public void setExtendMap(Map<String, Object> extendMap) {
		this.extendMap = extendMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * 是否超时
	 *
	 * @return
	 */
	public boolean timeout() {
		long currentTime = System.currentTimeMillis() / 1000;
		long createTime = this.createDatetime.getTime() / 1000;
		if (currentTime - createTime > expiresIn) {
			return true;
		}
		return false;
	}

	/**
	 * 差多少秒超时
	 * 
	 * @param second
	 * @return
	 */
	public boolean timeout(int second) {
		long currentTime = System.currentTimeMillis() / 1000;
		long createTime = this.createDatetime.getTime() / 1000;
		if ((currentTime - createTime) > (expiresIn - second)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "TokenVo{" +
				"accessToken='" + accessToken + '\'' +
				", createDatetime=" + createDatetime +
				", expiresIn=" + expiresIn +
				", isLogin=" + isLogin +
				", isSingle=" + isSingle +
				", id=" + id +
				", type=" + type +
				", authorCodeList=" + authorCodeList +
				", extendMap=" + extendMap +
				", appId='" + appId + '\'' +
				'}';
	}
}
