package com.hs.datatrans.entity.bd;

import java.util.List;

public class DAccountBDList {
    private int bdId;
    private int qianpenId;
    private String billId;
    private double bdPrice;
    private String bdTitle;
    private String billTitle;
    private double realPrice;
    private String bdType;
    private int totalPeriods;
    private int surplusPeriods;
    private long loanDateTime;
    private String overdue;
    private String bdState;
    private double totalRepaymentMoney;
    private double unPaidMoney;
    private long finallyRepaymentDate;
    private long nextRepaymentDate;
    private double overdueAmount;
    private String refundWay;
    private double preRepaymentAmount;
    private List<DAccountBDDetail> bdDetailList;

    public int getBdId() {
        return bdId;
    }

    public void setBdId(int bdId) {
        this.bdId = bdId;
    }

    public int getQianpenId() {
        return qianpenId;
    }

    public void setQianpenId(int qianpenId) {
        this.qianpenId = qianpenId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public double getBdPrice() {
        return bdPrice;
    }

    public void setBdPrice(double bdPrice) {
        this.bdPrice = bdPrice;
    }

    public String getBdTitle() {
        return bdTitle;
    }

    public void setBdTitle(String bdTitle) {
        this.bdTitle = bdTitle;
    }

    public String getBillTitle() {
        return billTitle;
    }

    public void setBillTitle(String billTitle) {
        this.billTitle = billTitle;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public String getBdType() {
        return bdType;
    }

    public void setBdType(String bdType) {
        this.bdType = bdType;
    }

    public int getTotalPeriods() {
        return totalPeriods;
    }

    public void setTotalPeriods(int totalPeriods) {
        this.totalPeriods = totalPeriods;
    }

    public int getSurplusPeriods() {
        return surplusPeriods;
    }

    public void setSurplusPeriods(int surplusPeriods) {
        this.surplusPeriods = surplusPeriods;
    }

    public long getLoanDateTime() {
        return loanDateTime;
    }

    public void setLoanDateTime(long loanDateTime) {
        this.loanDateTime = loanDateTime;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public String getBdState() {
        return bdState;
    }

    public void setBdState(String bdState) {
        this.bdState = bdState;
    }

    public double getTotalRepaymentMoney() {
        return totalRepaymentMoney;
    }

    public void setTotalRepaymentMoney(double totalRepaymentMoney) {
        this.totalRepaymentMoney = totalRepaymentMoney;
    }

    public double getUnPaidMoney() {
        return unPaidMoney;
    }

    public void setUnPaidMoney(double unPaidMoney) {
        this.unPaidMoney = unPaidMoney;
    }

    public long getFinallyRepaymentDate() {
        return finallyRepaymentDate;
    }

    public void setFinallyRepaymentDate(long finallyRepaymentDate) {
        this.finallyRepaymentDate = finallyRepaymentDate;
    }

    public long getNextRepaymentDate() {
        return nextRepaymentDate;
    }

    public void setNextRepaymentDate(long nextRepaymentDate) {
        this.nextRepaymentDate = nextRepaymentDate;
    }

    public double getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(double overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay;
    }

    public double getPreRepaymentAmount() {
        return preRepaymentAmount;
    }

    public void setPreRepaymentAmount(double preRepaymentAmount) {
        this.preRepaymentAmount = preRepaymentAmount;
    }

    public List<DAccountBDDetail> getBdDetailList() {
        return bdDetailList;
    }

    public void setBdDetailList(List<DAccountBDDetail> bdDetailList) {
        this.bdDetailList = bdDetailList;
    }

    @Override
    public String toString() {
        return "DAccountBDList{" +
                " bdId=" + bdId +
                ", qianpenId=" + qianpenId +
                ", billId='" + billId + '\'' +
                ", bdPrice=" + bdPrice +
                ", bdTitle='" + bdTitle + '\'' +
                ", billTitle='" + billTitle + '\'' +
                ", realPrice=" + realPrice +
                ", bdType='" + bdType + '\'' +
                ", totalPeriods=" + totalPeriods +
                ", surplusPeriods=" + surplusPeriods +
                ", loanDateTime=" + loanDateTime +
                ", overdue='" + overdue + '\'' +
                ", bdState='" + bdState + '\'' +
                ", totalRepaymentMoney=" + totalRepaymentMoney +
                ", unPaidMoney=" + unPaidMoney +
                ", finallyRepaymentDate=" + finallyRepaymentDate +
                ", nextRepaymentDate=" + nextRepaymentDate +
                ", overdueAmount=" + overdueAmount +
                ", refundWay='" + refundWay + '\'' +
                ", preRepaymentAmount=" + preRepaymentAmount +
                ", bdDetailList=" + bdDetailList +
                '}';
    }
}
