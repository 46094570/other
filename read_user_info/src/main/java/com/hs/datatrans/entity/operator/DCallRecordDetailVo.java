package com.hs.datatrans.entity.operator;

public class DCallRecordDetailVo {

    /*通话的电话号*/
    private String callAnotherNumber;
    /*通话时长*/
    private String callDuration;
    /*通话费用*/
    private String callFee;
    /*通话接通时间*/
    private String callStartTime;
    /*月份*/
    private String month;

    public String getCallAnotherNumber() {
        return callAnotherNumber;
    }

    public void setCallAnotherNumber(String callAnotherNumber) {
        this.callAnotherNumber = callAnotherNumber;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getCallFee() {
        return callFee;
    }

    public void setCallFee(String callFee) {
        this.callFee = callFee;
    }

    public String getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(String callStartTime) {
        this.callStartTime = callStartTime;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "DCallRecordDetailVo{" +
                "\n\t\t\tcallAnotherNumber='" + callAnotherNumber + '\'' +
                ", \n\t\t\tcallDuration='" + callDuration + '\'' +
                ", \n\t\t\tcallFee='" + callFee + '\'' +
                ", \n\t\t\tcallStartTime='" + callStartTime + '\'' +
                ", \n\t\t\tmonth='" + month + '\'' +
                '}';
    }
}
