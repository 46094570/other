package com.hs.datatrans.entity.base;

import java.util.Date;

public class TUserAddr {
    /*NOT NULL COMMENT '主键',*/
    private String id;
    /*NOT NULL COMMENT '用户ID',*/
    private String userId;
    /*DEFAULT NULL COMMENT '户籍地址省',*/
    private int nativePlaceProvince;
    /*DEFAULT NULL COMMENT '户籍地址市',*/
    private int nativePlaceCity;
    /*DEFAULT NULL COMMENT '户籍地址县',*/
    private int nativePlaceCounty;
    /*DEFAULT NULL COMMENT '户籍详细地址',*/
    private String nativePlaceDetailedAddress;
    /*DEFAULT NULL COMMENT '户籍地址邮编',*/
    private String nativePlaceZipCode;
    /*DEFAULT NULL COMMENT '居住地址省',*/
    private int liveProvince;
    /*DEFAULT NULL COMMENT '居住地址是市',*/
    private int liveCity;
    /*DEFAULT NULL COMMENT '居住地址县',*/
    private int liveCounty;
    /*DEFAULT NULL COMMENT '居住详细地址',*/
    private String liveDetailedAddress;
    /*DEFAULT NULL COMMENT '居住地址邮编',*/
    private String liveZipCode;
    /*NOT NULL COMMENT '创建时间',*/
    private Date createTime;
    /*DEFAULT NULL COMMENT '更新时间'*/
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNativePlaceProvince() {
        return nativePlaceProvince;
    }

    public void setNativePlaceProvince(int nativePlaceProvince) {
        this.nativePlaceProvince = nativePlaceProvince;
    }

    public int getNativePlaceCity() {
        return nativePlaceCity;
    }

    public void setNativePlaceCity(int nativePlaceCity) {
        this.nativePlaceCity = nativePlaceCity;
    }

    public int getNativePlaceCounty() {
        return nativePlaceCounty;
    }

    public void setNativePlaceCounty(int nativePlaceCounty) {
        this.nativePlaceCounty = nativePlaceCounty;
    }

    public String getNativePlaceDetailedAddress() {
        return nativePlaceDetailedAddress;
    }

    public void setNativePlaceDetailedAddress(String nativePlaceDetailedAddress) {
        this.nativePlaceDetailedAddress = nativePlaceDetailedAddress;
    }

    public String getNativePlaceZipCode() {
        return nativePlaceZipCode;
    }

    public void setNativePlaceZipCode(String nativePlaceZipCode) {
        this.nativePlaceZipCode = nativePlaceZipCode;
    }

    public int getLiveProvince() {
        return liveProvince;
    }

    public void setLiveProvince(int liveProvince) {
        this.liveProvince = liveProvince;
    }

    public int getLiveCity() {
        return liveCity;
    }

    public void setLiveCity(int liveCity) {
        this.liveCity = liveCity;
    }

    public int getLiveCounty() {
        return liveCounty;
    }

    public void setLiveCounty(int liveCounty) {
        this.liveCounty = liveCounty;
    }

    public String getLiveDetailedAddress() {
        return liveDetailedAddress;
    }

    public void setLiveDetailedAddress(String liveDetailedAddress) {
        this.liveDetailedAddress = liveDetailedAddress;
    }

    public String getLiveZipCode() {
        return liveZipCode;
    }

    public void setLiveZipCode(String liveZipCode) {
        this.liveZipCode = liveZipCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TUserAddr{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", nativePlaceProvince=" + nativePlaceProvince +
                ", nativePlaceCity=" + nativePlaceCity +
                ", nativePlaceCounty=" + nativePlaceCounty +
                ", nativePlaceDetailedAddress='" + nativePlaceDetailedAddress + '\'' +
                ", nativePlaceZipCode='" + nativePlaceZipCode + '\'' +
                ", liveProvince=" + liveProvince +
                ", liveCity=" + liveCity +
                ", liveCounty=" + liveCounty +
                ", liveDetailedAddress='" + liveDetailedAddress + '\'' +
                ", liveZipCode='" + liveZipCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
