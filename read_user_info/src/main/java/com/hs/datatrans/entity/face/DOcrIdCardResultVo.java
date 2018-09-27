package com.hs.datatrans.entity.face;

import java.util.Map;

public class DOcrIdCardResultVo {
    /*地址  string*/
    private String address;
    /*生日  日期*/
    private DDateVo birthday;
    /*性别  string*/
    private String gender;
    /*身份证中人脸框的位置，分别包含左上、右上、右下、左下四个角点。可能会超过图本身 身份证头像位置*/
    private DIdentityHeadPositionVo head_rect;
    /*身份证号码   string*/
    private String id_card_number;
    /*签发机关    string*/
    private String issued_by;

    private Map<String,String> legality;

    /*名字  string*/
    private String name;
    /*民族  string*/
    private String race;
    /*请求唯一标识  string*/
    private String request_id;
    /*正反面 string*/
    private String side;
    /*请求时间    number (float)*/
    private double time_used;
    /*有效日期    string*/
    private String valid_date;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DDateVo getBirthday() {
        return birthday;
    }

    public void setBirthday(DDateVo birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public DIdentityHeadPositionVo getHead_rect() {
        return head_rect;
    }

    public void setHead_rect(DIdentityHeadPositionVo head_rect) {
        this.head_rect = head_rect;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public Map<String, String> getLegality() {
        return legality;
    }

    public void setLegality(Map<String, String> legality) {
        this.legality = legality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public double getTime_used() {
        return time_used;
    }

    public void setTime_used(double time_used) {
        this.time_used = time_used;
    }

    public String getValid_date() {
        return valid_date;
    }

    public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
    }

    @Override
    public String toString() {
        return "DOcrIdCardResultVo{" +
                "address='" + address + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", head_rect=" + head_rect +
                ", id_card_number='" + id_card_number + '\'' +
                ", issued_by='" + issued_by + '\'' +
                ", legality=" + legality +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", request_id='" + request_id + '\'' +
                ", side='" + side + '\'' +
                ", time_used=" + time_used +
                ", valid_date='" + valid_date + '\'' +
                '}';
    }
}
