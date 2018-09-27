package com.hs.datatrans.entity.base;

public class DReferenceLinkman {
    /*手机号码*/
    private  String name;
    /*姓名*/
    private  int orderNum;
    /*排序*/
    private  String relation;
    /*关系*/
    private  String mobilePhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "DReferenceLinkman{" +
                "\nname='" + name + '\'' +
                ", \norderNum=" + orderNum +
                ", \nrelation='" + relation + '\'' +
                ", \nmobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
