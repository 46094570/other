package com.hs.datatrans.entity.operator;

public class DCallRecordPackageInfoVo {

    /*个人实际费用*/
    private double actualFee;
    /*基础费用*/
    private double baseFee;
    /*渠道信息*/
    private String channel;
    /*优惠费用*/
    private double discount;
    /*其它优惠*/
    private double extraDiscount;
    /*其他费用*/
    private double extraFee;
    /*增值业务费*/
    private double extraServiceFee;
    /*当月总费用*/
    private double fee;
    /*上期可用积分*/
    private int lastPoint;
    /*月份*/
    private String month;
    /*套餐名*/
    private String name;
    /*备注*/
    private String notes;
    /*本期已付费用*/
    private double paidFee;
    /*本期可用积分*/
    private int point;
    /*本手机关联号码, \n\t\t多个手机号以逗号分隔*/
    private String relatedMobiles;
    /*短彩信费用*/
    private double smsFee;
    /*本期总费用*/
    private double totalFee;
    /*本期未付费用*/
    private double unpaidFee;
    /*套餐值*/
    private String value;
    /*语音费*/
    private double voiceFee;
    /*网络流量费*/
    private double webFee;

    public double getActualFee() {
        return actualFee;
    }

    public void setActualFee(double actualFee) {
        this.actualFee = actualFee;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(double baseFee) {
        this.baseFee = baseFee;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getExtraDiscount() {
        return extraDiscount;
    }

    public void setExtraDiscount(double extraDiscount) {
        this.extraDiscount = extraDiscount;
    }

    public double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(double extraFee) {
        this.extraFee = extraFee;
    }

    public double getExtraServiceFee() {
        return extraServiceFee;
    }

    public void setExtraServiceFee(double extraServiceFee) {
        this.extraServiceFee = extraServiceFee;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(int lastPoint) {
        this.lastPoint = lastPoint;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(double paidFee) {
        this.paidFee = paidFee;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getRelatedMobiles() {
        return relatedMobiles;
    }

    public void setRelatedMobiles(String relatedMobiles) {
        this.relatedMobiles = relatedMobiles;
    }

    public double getSmsFee() {
        return smsFee;
    }

    public void setSmsFee(double smsFee) {
        this.smsFee = smsFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getUnpaidFee() {
        return unpaidFee;
    }

    public void setUnpaidFee(double unpaidFee) {
        this.unpaidFee = unpaidFee;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getVoiceFee() {
        return voiceFee;
    }

    public void setVoiceFee(double voiceFee) {
        this.voiceFee = voiceFee;
    }

    public double getWebFee() {
        return webFee;
    }

    public void setWebFee(double webFee) {
        this.webFee = webFee;
    }

    @Override
    public String toString() {
        return "DCallRecordPackageInfoVo{" +
                "\n\t\tactualFee=" + actualFee +
                ", \n\t\tbaseFee=" + baseFee +
                ", \n\t\tchannel='" + channel + '\'' +
                ", \n\t\tdiscount=" + discount +
                ", \n\t\textraDiscount=" + extraDiscount +
                ", \n\t\textraFee=" + extraFee +
                ", \n\t\textraServiceFee=" + extraServiceFee +
                ", \n\t\tfee=" + fee +
                ", \n\t\tlastPoint=" + lastPoint +
                ", \n\t\tmonth='" + month + '\'' +
                ", \n\t\tname='" + name + '\'' +
                ", \n\t\tnotes='" + notes + '\'' +
                ", \n\t\tpaidFee=" + paidFee +
                ", \n\t\tpoint=" + point +
                ", \n\t\trelatedMobiles='" + relatedMobiles + '\'' +
                ", \n\t\tsmsFee=" + smsFee +
                ", \n\t\ttotalFee=" + totalFee +
                ", \n\t\tunpaidFee=" + unpaidFee +
                ", \n\t\tvalue='" + value + '\'' +
                ", \n\t\tvoiceFee=" + voiceFee +
                ", \n\t\twebFee=" + webFee +
                '}';
    }
}
