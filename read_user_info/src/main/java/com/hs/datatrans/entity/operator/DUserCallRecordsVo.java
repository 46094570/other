package com.hs.datatrans.entity.operator;

import java.util.List;

public class DUserCallRecordsVo {

    /*基础信息	通讯详单基础信息*/
    private DCallRecordBasicInfoVo baseInfoVo;
    /*套餐信息	< 通话详单订单信息 > array*/
    private List<DCallRecordPackageInfoVo> billInfoList;
    /*通话记录信息	< 通话详单通话记录 > array*/
    private List<DCallRecordDetailVo> callInfoList;
    /*编码	string*/
    private String code;
    /*创建时间	integer (int64)*/
    private String createDatetime;
    /*身份证号	string*/
    private String idCard;
    /*入网日期	string*/
    private String innetDate;
    /*消息	string*/
    private String message;
    /*手机号码	string*/
    private String mobilePhone;
    /*姓名	string*/
    private String name;

    public DCallRecordBasicInfoVo getBaseInfoVo() {
        return baseInfoVo;
    }

    public void setBaseInfoVo(DCallRecordBasicInfoVo baseInfoVo) {
        this.baseInfoVo = baseInfoVo;
    }

    public List<DCallRecordPackageInfoVo> getBillInfoList() {
        return billInfoList;
    }

    public void setBillInfoList(List<DCallRecordPackageInfoVo> billInfoList) {
        this.billInfoList = billInfoList;
    }

    public List<DCallRecordDetailVo> getCallInfoList() {
        return callInfoList;
    }

    public void setCallInfoList(List<DCallRecordDetailVo> callInfoList) {
        this.callInfoList = callInfoList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getInnetDate() {
        return innetDate;
    }

    public void setInnetDate(String innetDate) {
        this.innetDate = innetDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DUserCallRecordsVo{" +
                "\ncode='" + code + '\'' +
                ", \ncreateDatetime='" + createDatetime + '\'' +
                ", \nidCard='" + idCard + '\'' +
                ", \ninnetDate='" + innetDate + '\'' +
                ", \nmessage='" + message + '\'' +
                ", \nmobilePhone='" + mobilePhone + '\'' +
                ", \nname='" + name + '\'' +
                ", \nbaseInfoVo=" + baseInfoVo +
                ", \nbillInfoList=" + billInfoList +
                ", \ncallInfoList=" + callInfoList +
                '}';
    }
}
