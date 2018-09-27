package com.hs.datatrans.entity.base;

import java.util.List;

public class DUserBase {

    /*设备类型*/
    private String clientType;

    /*公司详细地址*/
    private String companyAddress;

    /*公司地址*/
    private String companyAddSuffix;

    /*公司地址经纬度*/
    private String deviceFingerprinting;

    /*设备指纹*/
    private String companyLngLat;

    /*邮箱地址*/
    private String emailAdress;

    /*家庭详细地址*/
    private String homeAddress;

    /*家庭住址*/
    private String homeAddSuffix;

    /*家庭经纬度*/
    private String homeLngLat;

    /*职位*/
    private String jobTitle;

    /*选择联系人*/
    private List<DReferenceLinkman> linkmans;

    /*收入*/
    private String monthlyIncome;

    /*现在的经纬度*/
    private String purposeOfLoan;

    /*借款用途*/
    private String nowLngLat;

    /*工作单位*/
    private String workUnit;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddSuffix() {
        return companyAddSuffix;
    }

    public void setCompanyAddSuffix(String companyAddSuffix) {
        this.companyAddSuffix = companyAddSuffix;
    }

    public String getDeviceFingerprinting() {
        return deviceFingerprinting;
    }

    public void setDeviceFingerprinting(String deviceFingerprinting) {
        this.deviceFingerprinting = deviceFingerprinting;
    }

    public String getCompanyLngLat() {
        return companyLngLat;
    }

    public void setCompanyLngLat(String companyLngLat) {
        this.companyLngLat = companyLngLat;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddSuffix() {
        return homeAddSuffix;
    }

    public void setHomeAddSuffix(String homeAddSuffix) {
        this.homeAddSuffix = homeAddSuffix;
    }

    public String getHomeLngLat() {
        return homeLngLat;
    }

    public void setHomeLngLat(String homeLngLat) {
        this.homeLngLat = homeLngLat;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<DReferenceLinkman> getLinkmans() {
        return linkmans;
    }

    public void setLinkmans(List<DReferenceLinkman> linkmans) {
        this.linkmans = linkmans;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public void setPurposeOfLoan(String purposeOfLoan) {
        this.purposeOfLoan = purposeOfLoan;
    }

    public String getNowLngLat() {
        return nowLngLat;
    }

    public void setNowLngLat(String nowLngLat) {
        this.nowLngLat = nowLngLat;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    @Override
    public String toString() {
        return "DUserBase{" +
                "\nclientType='" + clientType + '\'' +
                ",\ncompanyAddress='" + companyAddress + '\'' +
                ",\ncompanyAddSuffix='" + companyAddSuffix + '\'' +
                ",\ndeviceFingerprinting='" + deviceFingerprinting + '\'' +
                ",\ncompanyLngLat='" + companyLngLat + '\'' +
                ",\nemailAdress='" + emailAdress + '\'' +
                ",\nhomeAddress='" + homeAddress + '\'' +
                ",\nhomeAddSuffix='" + homeAddSuffix + '\'' +
                ",\nhomeLngLat='" + homeLngLat + '\'' +
                ",\njobTitle='" + jobTitle + '\'' +
                ",\nmonthlyIncome='" + monthlyIncome + '\'' +
                ",\npurposeOfLoan='" + purposeOfLoan + '\'' +
                ",\nnowLngLat='" + nowLngLat + '\'' +
                ",\nworkUnit='" + workUnit + '\'' +
                ",\nlinkmans=" + linkmans +
                '}';
    }
}
