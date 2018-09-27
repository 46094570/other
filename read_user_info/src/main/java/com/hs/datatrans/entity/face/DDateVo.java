package com.hs.datatrans.entity.face;

public class DDateVo {
    /*日*/
    private int hour;
    /*时*/
    private int minute;
    /*分*/
    private int month;
    /*月*/
    private int second;
    /*秒*/
    private int year;
    /*年*/
    private int day;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "DDateVo{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", month=" + month +
                ", second=" + second +
                ", year=" + year +
                ", day=" + day +
                '}';
    }
}
