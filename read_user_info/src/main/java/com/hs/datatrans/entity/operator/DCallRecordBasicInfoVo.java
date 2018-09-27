package com.hs.datatrans.entity.operator;

public class DCallRecordBasicInfoVo {

    /*查询保存缓存时间	integer (int64)*/
    private long createDateTime;
    /*身份证号	string*/
    private String idCard;
    /*入网日期	string*/
    private String innetDate;
    /*手机号码	string*/
    private String mobilePhone;
    /*姓名	string*/
    private String name;

    public long getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(long createDateTime) {
        this.createDateTime = createDateTime;
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
        return "DCallRecordBasicInfoVo{" +
                "\n\tcreateDateTime=" + createDateTime +
                ", \n\tidCard='" + idCard + '\'' +
                ", \n\tinnetDate='" + innetDate + '\'' +
                ", \n\tmobilePhone='" + mobilePhone + '\'' +
                ", \n\tname='" + name + '\'' +
                '}';
    }
}
