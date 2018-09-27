package com.hs.datatrans.entity.tongdun;

public class DOverdueDetails {

    /*逾期金额*/
    private double overdue_amount;
    /*逾期笔数*/
    private int overdue_count;
    /*逾期天数*/
    private int overdue_day;

    public double getOverdue_amount() {
        return overdue_amount;
    }

    public void setOverdue_amount(double overdue_amount) {
        this.overdue_amount = overdue_amount;
    }

    public int getOverdue_count() {
        return overdue_count;
    }

    public void setOverdue_count(int overdue_count) {
        this.overdue_count = overdue_count;
    }

    public int getOverdue_day() {
        return overdue_day;
    }

    public void setOverdue_day(int overdue_day) {
        this.overdue_day = overdue_day;
    }

    @Override
    public String toString() {
        return "DPlatformDetailVo{" +
                "overdue_amount=" + overdue_amount +
                ", overdue_count=" + overdue_count +
                ", overdue_day=" + overdue_day +
                '}';
    }

}
