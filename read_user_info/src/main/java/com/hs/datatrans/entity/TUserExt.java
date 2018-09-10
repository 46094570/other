package com.hs.datatrans.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class TUserExt implements Serializable {
    private static final long serialVersionUID = 4800804439332064455L;
    private String userId;

    /**
     * 信用级别
     */
    private String creditLevel;

    /**
     * 信用分
     */
    private String creditScore;

    /**
     * 可用额度
     */
    private BigDecimal loanLimit;

    /**
     * 信用总额度
     */
    private BigDecimal creditLimit;

    private String updateId;

    private String updateTime;

    /**
     * 用户的额度对应的级别ID
     */
    private String creditGradeId;

    /**
     * 第三方返回的编码，作为银行卡发短信用
     */
    private String reqSn;

    private String sesameScore;

    /**
     * 芝麻信用的openID
     * @return
     */
    private String openId;

    /**
     * 人工调整的额度
     * @return
     */
    private BigDecimal adjustmentQuota;

    /**
     * 待还金额
     * @return
     */
    private BigDecimal stayAmount;

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 银行卡中文名
     */
    private String bankCardName;

    /**
     * 银行卡预留手机号
     */
    private String bankCardPhone;

    /**
     * 钱盆用户id
     */
    private String qianpenId;

    /**
     * 钱盆用户账号
     */
    private String qianpenAccountName;

    /**
     * 通联id
     */
    private String tonglianId;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getCreditLevel()
    {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel)
    {
        this.creditLevel = creditLevel;
    }

    public String getCreditScore()
    {
        return creditScore;
    }

    public void setCreditScore(String creditScore)
    {
        this.creditScore = creditScore;
    }

    public BigDecimal getLoanLimit()
    {
        return loanLimit;
    }

    public void setLoanLimit(BigDecimal loanLimit)
    {
        this.loanLimit = loanLimit;
    }

    public BigDecimal getCreditLimit()
    {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit)
    {
        this.creditLimit = creditLimit;
    }

    public String getUpdateId()
    {
        return updateId;
    }

    public void setUpdateId(String updateId)
    {
        this.updateId = updateId;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getCreditGradeId()
    {
        return creditGradeId;
    }

    public void setCreditGradeId(String creditGradeId)
    {
        this.creditGradeId = creditGradeId;
    }

    public String getReqSn()
    {
        return reqSn;
    }

    public void setReqSn(String reqSn)
    {
        this.reqSn = reqSn;
    }

    public String getSesameScore()
    {
        return sesameScore;
    }

    public void setSesameScore(String sesameScore)
    {
        this.sesameScore = sesameScore;
    }

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public BigDecimal getAdjustmentQuota()
    {
        return adjustmentQuota;
    }

    public void setAdjustmentQuota(BigDecimal adjustmentQuota)
    {
        this.adjustmentQuota = adjustmentQuota;
    }

    public BigDecimal getStayAmount()
    {
        return stayAmount;
    }

    public void setStayAmount(BigDecimal stayAmount)
    {
        this.stayAmount = stayAmount;
    }

    public String getBankCard()
    {
        return bankCard;
    }

    public void setBankCard(String bankCard)
    {
        this.bankCard = bankCard;
    }

    public String getBankCardName()
    {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName)
    {
        this.bankCardName = bankCardName;
    }

    public String getQianpenId()
    {
        return qianpenId;
    }

    public void setQianpenId(String qianpenId)
    {
        this.qianpenId = qianpenId;
    }

    public String getQianpenAccountName()
    {
        return qianpenAccountName;
    }

    public void setQianpenAccountName(String qianpenAccountName)
    {
        this.qianpenAccountName = qianpenAccountName;
    }

    public String getBankCardPhone()
    {
        return bankCardPhone;
    }

    public void setBankCardPhone(String bankCardPhone)
    {
        this.bankCardPhone = bankCardPhone;
    }

    public String getTonglianId()
    {
        return tonglianId;
    }

    public void setTonglianId(String tonglianId)
    {
        this.tonglianId = tonglianId;
    }

    public void clean(){
        this.setAdjustmentQuota(null);
        this.setBankCard(null);
        this.setBankCardName(null);
        this.setBankCardPhone(null);
        this.setCreditGradeId(null);
        this.setCreditLevel(null);
        this.setCreditLimit(null);
        this.setCreditScore(null);
        this.setLoanLimit(null);
        this.setOpenId(null);
        this.setQianpenAccountName(null);
        this.setQianpenId(null);
        this.setReqSn(null);
        this.setSesameScore(null);
        this.setStayAmount(null);
        this.setTonglianId(null);
        this.setUpdateId(null);
        this.setUpdateTime(null);
        this.setUserId(null);
    }

    @Override
    public String toString() {
        return "TUserExt{" +
                "userId='" + userId + '\'' +
                ", creditLevel='" + creditLevel + '\'' +
                ", creditScore='" + creditScore + '\'' +
                ", loanLimit=" + loanLimit +
                ", creditLimit=" + creditLimit +
                ", updateId='" + updateId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", creditGradeId='" + creditGradeId + '\'' +
                ", reqSn='" + reqSn + '\'' +
                ", sesameScore='" + sesameScore + '\'' +
                ", openId='" + openId + '\'' +
                ", adjustmentQuota=" + adjustmentQuota +
                ", stayAmount=" + stayAmount +
                ", bankCard='" + bankCard + '\'' +
                ", bankCardName='" + bankCardName + '\'' +
                ", bankCardPhone='" + bankCardPhone + '\'' +
                ", qianpenId='" + qianpenId + '\'' +
                ", qianpenAccountName='" + qianpenAccountName + '\'' +
                ", tonglianId='" + tonglianId + '\'' +
                '}';
    }
}
