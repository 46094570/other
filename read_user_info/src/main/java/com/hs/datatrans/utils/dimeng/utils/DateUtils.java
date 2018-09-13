package com.hs.datatrans.utils.dimeng.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtils {
    private static final Logger logger = LogManager.getLogger(DateUtils.class);

    private DateUtils() {
    }

    public static String getCurrentDate(String format) {
        return (new SimpleDateFormat(format)).format(new Date());
    }

    public static String getFormatedDate(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    public static boolean isBefore(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        return c1.before(c2);
    }

    public static Date parseDateFromString(String date) throws ParseException {
        return (new SimpleDateFormat("yyyy-MM-dd")).parse(date);
    }

    public static Date parseDateFromString(String date, String pattern) throws ParseException {
        return StringUtils.isEmpty(pattern) ? (new SimpleDateFormat("yyyy-MM-dd")).parse(date) : (new SimpleDateFormat(pattern)).parse(date);
    }

    public static Date lastDayOfMonth(Date date, String date1) throws ParseException {
        Date _date = null;
        if (null != date) {
            _date = date;
        }

        if (null == date && null != date1) {
            _date = parseDateFromString(date1);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(_date);
        cal.add(2, 1);
        cal.set(5, 1);
        cal.add(5, -1);
        return cal.getTime();
    }

    public static boolean isLeapYear(int year) {
        return (new GregorianCalendar()).isLeapYear(year);
    }

    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;

        try {
            date = (new SimpleDateFormat("yy-MM-dd")).parse(specifiedDay);
            c.setTime(date);
        } catch (ParseException var4) {
            logger.error("DateUtils.getSpecifiedDayBefore ", var4);
        }

        c.set(5, c.get(5) - 1);
        return (new SimpleDateFormat("yyyy-MM-dd")).format(c.getTime());
    }

    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;

        try {
            date = (new SimpleDateFormat("yy-MM-dd")).parse(specifiedDay);
            c.setTime(date);
            c.set(5, c.get(5) + 1);
            return (new SimpleDateFormat("yyyy-MM-dd")).format(c.getTime());
        } catch (ParseException var4) {
            logger.error("DateUtils.getSpecifiedDayAfter ", var4);
            return null;
        }
    }

    public static String getDay(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        return String.valueOf(c.get(5));
    }

    public static String getMonth(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        return String.valueOf(c.get(2));
    }

    public static String getYear(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        return String.valueOf(c.get(1));
    }

    public static String getHour(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        return String.valueOf(c.get(11));
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(getYear(date));
        System.out.println(getMonth(date));
        System.out.println(getDay(date));
        System.out.println(getHour(date));
    }
}
