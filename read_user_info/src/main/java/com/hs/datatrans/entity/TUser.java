package com.hs.datatrans.entity;

import java.io.Serializable;
import java.util.Date;

public class TUser implements Serializable {
    private static final long serialVersionUID = -1933675337968978642L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户登录用户名
     */
    private String userName;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 交易密码
     */
    private String tradePwd;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册来源:1:PC前台2:PC后台3:安卓4:苹果5:微信
     */
    private String source;

    /**
     * 注册时间
     */
    private Date dateRegister;

    /**
     * 账号状态:0:正常1:拉黑2:锁定
     */
    private String userStatus;

    /**
     * 登录时间
     */
    private Date dateLogin;

    /**
     * 第一次登录时间
     */
    private Date firstLoginTime;
    /**
     * 最后一次登录时间
     */
    private Date dateLastLogin;

    /**
     * 用户类型，0：乘客
     */
    private String userType;

    /**
     * 当日登录失败次数
     */
    private Integer failLoginCount;

    /**
     * 当日交易密码错误次数
     */
    private Integer pwdErrorCount;

    /**
     * 初始密码状态(0:未修改 1:已修改)
     */
    private String initPasswordState;

    /**
     * 默认充值方式(0:微信 1:支付宝  2:信用卡 3:银行卡)
     */
    private String defaultRechargeWay;

    /**
     * 拉黑原因
     */
    private String blackReason;

    /**
     * 乘客信息token
     */
    private String token;

    private String opSource;

    /**
     * 渠道商唯一识别码
     */
    private String channelCore;
    /**
     * 注册的ip
     */
    private String ip;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getDateLogin() {
        return dateLogin;
    }

    public void setDateLogin(Date dateLogin) {
        this.dateLogin = dateLogin;
    }

    public Date getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Date firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public Date getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Date dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getFailLoginCount() {
        return failLoginCount;
    }

    public void setFailLoginCount(Integer failLoginCount) {
        this.failLoginCount = failLoginCount;
    }

    public Integer getPwdErrorCount() {
        return pwdErrorCount;
    }

    public void setPwdErrorCount(Integer pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    public String getInitPasswordState() {
        return initPasswordState;
    }

    public void setInitPasswordState(String initPasswordState) {
        this.initPasswordState = initPasswordState;
    }

    public String getDefaultRechargeWay() {
        return defaultRechargeWay;
    }

    public void setDefaultRechargeWay(String defaultRechargeWay) {
        this.defaultRechargeWay = defaultRechargeWay;
    }

    public String getBlackReason() {
        return blackReason;
    }

    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpSource() {
        return opSource;
    }

    public void setOpSource(String opSource) {
        this.opSource = opSource;
    }

    public String getChannelCore() {
        return channelCore;
    }

    public void setChannelCore(String channelCore) {
        this.channelCore = channelCore;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void clean(){
        this.setBlackReason(null);
        this.setChannelCore(null);
        this.setDateLastLogin(null);
        this.setDateLogin(null);
        this.setDateRegister(null);
        this.setDefaultRechargeWay(null);
        this.setEmail(null);
        this.setFailLoginCount(null);
        this.setFirstLoginTime(null);
        this.setInitPasswordState(null);
        this.setIp(null);
        this.setOpSource(null);
        this.setPassword(null);
        this.setPhone(null);
        this.setPwdErrorCount(null);
        this.setSource(null);
        this.setToken(null);
        this.setTradePwd(null);
        this.setUserId(null);
        this.setUserName(null);
        this.setUserStatus(null);
        this.setUserType(null);
    }

    @Override
    public String toString() {
        return "TUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", tradePwd='" + tradePwd + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", source='" + source + '\'' +
                ", dateRegister=" + dateRegister +
                ", userStatus='" + userStatus + '\'' +
                ", dateLogin=" + dateLogin +
                ", firstLoginTime=" + firstLoginTime +
                ", dateLastLogin=" + dateLastLogin +
                ", userType='" + userType + '\'' +
                ", failLoginCount=" + failLoginCount +
                ", pwdErrorCount=" + pwdErrorCount +
                ", initPasswordState='" + initPasswordState + '\'' +
                ", defaultRechargeWay='" + defaultRechargeWay + '\'' +
                ", blackReason='" + blackReason + '\'' +
                ", token='" + token + '\'' +
                ", opSource='" + opSource + '\'' +
                ", channelCore='" + channelCore + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
