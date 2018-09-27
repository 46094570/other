package com.hs.datatrans.entity.bd;

public class DAccountBDDetail {
    private int bdId;
    private long repaymentDate;
    private int period;
    private double money;
    private String state;
    private double unPaidMoney;
    private double paidMoney;
    private double overdue;
    private String types;
    private int overdueDay;
    private int overdueSign;
    private double principalMoney;
    private double manageInterest;
    private double rateInterest;
    private double overdueInterest;

    public int getBdId() {
        return bdId;
    }

    public void setBdId(int bdId) {
        this.bdId = bdId;
    }

    public long getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(long repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getUnPaidMoney() {
        return unPaidMoney;
    }

    public void setUnPaidMoney(double unPaidMoney) {
        this.unPaidMoney = unPaidMoney;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public double getOverdue() {
        return overdue;
    }

    public void setOverdue(double overdue) {
        this.overdue = overdue;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(int overdueDay) {
        this.overdueDay = overdueDay;
    }

    public int getOverdueSign() {
        return overdueSign;
    }

    public void setOverdueSign(int overdueSign) {
        this.overdueSign = overdueSign;
    }

    public double getPrincipalMoney() {
        return principalMoney;
    }

    public void setPrincipalMoney(double principalMoney) {
        this.principalMoney = principalMoney;
    }

    public double getManageInterest() {
        return manageInterest;
    }

    public void setManageInterest(double manageInterest) {
        this.manageInterest = manageInterest;
    }

    public double getRateInterest() {
        return rateInterest;
    }

    public void setRateInterest(double rateInterest) {
        this.rateInterest = rateInterest;
    }

    public double getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(double overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    @Override
    public String toString() {
        return "DAccountBDDetail{" +
                "bdId=" + bdId +
                ", repaymentDate=" + repaymentDate +
                ", period=" + period +
                ", money=" + money +
                ", state='" + state + '\'' +
                ", unPaidMoney=" + unPaidMoney +
                ", paidMoney=" + paidMoney +
                ", overdue=" + overdue +
                ", types='" + types + '\'' +
                ", overdueDay=" + overdueDay +
                ", overdueSign=" + overdueSign +
                ", principalMoney=" + principalMoney +
                ", manageInterest=" + manageInterest +
                ", rateInterest=" + rateInterest +
                ", overdueInterest=" + overdueInterest +
                '}';
    }
}
