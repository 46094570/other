package com.hs.datatrans.utils.qianpen.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtil
{

    public static Date strToDate(String dateString)
    {
        if (null == dateString)
            return new Date();

        try
        {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        }
        catch (ParseException e)
        {
            return new Date();
        }
    }

    /**
     * 得到某年某周的第一天和最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static String getWeekTimeString(String yearWeekStr)
    {

        int year = Integer.parseInt(yearWeekStr.substring(0, 4));
        int week = Integer.parseInt(yearWeekStr.substring(4, yearWeekStr.length()));
        Date firstDayOfWeekDate = getFirstDayOfWeek(year, week);
        Date lastDayOfWeekDate = getLastDayOfWeek(year, week);
        //String firstDayOfWeekStr = DateUtils.getFormatedDate(firstDayOfWeekDate, "yyyy/MM/dd");
        //String lastDayOfWeekStr = DateUtils.getFormatedDate(lastDayOfWeekDate, "yyyy/MM/dd");
        //return firstDayOfWeekStr + "~" + lastDayOfWeekStr;
        return null;
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week)
    {
        //week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar)calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week)
    {
        //week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar)calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

    /**
     * 获取年月
     *
     * @param String
     * @return
     */
    public static String getYearMonth(String yearMonth)
    {
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4, yearMonth.length()));
        return year + "/" + month;
    }

    /**
     * 获取年季度
     *
     * @param String
     * @return
     */
    public static String getYearQuarter(String yearQuarter)
    {
        int year = Integer.parseInt(yearQuarter.substring(0, 4));
        int quarter = Integer.parseInt(yearQuarter.substring(4, yearQuarter.length()));
        return year + "年第" + quarter + "季度";
    }

    /**
     * 获取上月日期第一天
     *
     * @return Date
     */
    public static Date getFirstDayOfMonth()
    {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return cal.getTime();
    }

    /**
     * 获取上月日期最后一天
     *
     * @return Date
     */
    public static Date getLastDayOfMonth()
    {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        return cal.getTime();
    }

    /**
     * 获取昨天日期
     *
     * @return Date
     */
    public static Date getYesterday()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取上个月年份
     *
     * @return int
     */
    public static int getLastMonthForYear()
    {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.add(Calendar.MONTH, -1);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取上个月月份
     *
     * @return Date
     */
    public static int getLastMonth()
    {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * 获取当前时间所属季度的开始/结束日期
     *
     * @return
     * @throws ParseException
     * @author huxingwei
     */
    public static Map<String, Object> getQuarterNow()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取当前日期
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int startMouth = 0;
        int endMouth = 0;
        if (month >= 1 && month <= 3)
        {
            map.put("quarter", 1);
            startMouth = 1;
            endMouth = 3;
        }
        else if (month >= 4 && month <= 6)
        {
            map.put("quarter", 2);
            startMouth = 4;
            endMouth = 6;
        }
        else if (month >= 7 && month <= 9)
        {
            map.put("quarter", 3);
            startMouth = 7;
            endMouth = 9;
        }
        else
        {
            map.put("quarter", 4);
            startMouth = 10;
            endMouth = 12;
        }
        calendar.set(Calendar.MONTH, startMouth - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //map.put("startTime", DateUtils.getFormatedDate(calendar.getTime(), "yyyy-MM-dd"));
        calendar.set(Calendar.MONTH, endMouth);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        //map.put("endTime", DateUtils.getFormatedDate(calendar.getTime(), "yyyy-MM-dd"));
        return map;
    }

    /**
     * 获取还款日期
     * <功能详细描述>
     *
     * @param date（起息日）
     * @param monthes(第几期)
     * @param monthes
     * @return
     */
    public static final long rollMonth(long date, int monthes)
    {
        if (monthes == 0)
        {
            return date;
        }
        else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date);
            int i;
            if (monthes > 0)
            {
                i = calendar.get(2);

                for (int i1 = 0; i1 < monthes; ++i1)
                {
                    if (i == 11)
                    {
                        calendar.roll(1, 1);
                        i = -1;
                    }

                    ++i;
                }

                calendar.roll(2, monthes);
                return calendar.getTimeInMillis();
            }
            else
            {
                for (i = monthes; i < 0; ++i)
                {
                    if (calendar.get(2) == 0)
                    {
                        calendar.roll(1, -1);
                    }

                    calendar.roll(2, 1);
                }

                return calendar.getTimeInMillis();
            }
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    /*public static int daysBetween(Date smdate, Date bdate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }*/

    public static String getDateDiff(String startTime)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        String diffD = "";

        try
        {
            one = df.parse(startTime);
            two = df.parse(df.format(new Date()));
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff = time2 - time1;
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            diffD = day + "天" + hour + "小时" + min + "分钟";
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return diffD;
    }

    /**
     * 生成当前系统时间
     */
    public static final String sysTime()
    {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Calendar.getInstance().getTime());
    }

    /**
     * 时间转换为yyyy-MM-dd HH:mm:ss格式的字符串
     * @param date
     * @return
     */
    public static String dateToString(Date date)
    {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String dateToString1(Date date)
    {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String dateToString2(Date date)
    {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }

    public static String simple(Date date)
    {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static String simple2(Date date)
    {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    /* public static Date strToDate(String dateString)
    {
        if (null == dateString)
            return new Date();

        try
        {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        }
        catch (ParseException e)
        {
            return new Date();
        }
    }*/

    public static Date strToYYMMDDDate(String dateString)
    {
        if (null == dateString)
            return new Date();

        try
        {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        }
        catch (ParseException e)
        {
            return new Date();
        }
    }

    //获取系统时间并返回时间格式
    public static Date currentDate()
    {
        DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try
        {
            String currentDates = YYYY_MM_DD_MM_HH_SS.format(new Date());

            return YYYY_MM_DD_MM_HH_SS.parse(currentDates);
        }
        catch (ParseException e)
        {
            return new Date();
        }
    }

    /**
     * 得到当前时间距2013-11-01 00:00:00的小时数
     * @return
     * @throws ParseException
     */
    public long getHours()
    {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try
        {
            date = simple.parse("2013-11-01 00:00:00");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        long millisecond = System.currentTimeMillis() - date.getTime();
        long temp = 1000 * 60 * 60;
        return millisecond / temp;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(smdate);
        String s1 = sdf.format(bdate);
        try
        {
            smdate = sdf.parse(sdf.format(smdate));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            bdate = sdf.parse(sdf.format(bdate));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个时间之间相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDays(Date startDate, Date endDate)
    {
        long days = 0;
        long start = startDate.getTime();
        long end = endDate.getTime();
        //一天的毫秒数1000 * 60 * 60 * 24=86400000
        days = (end - start) / 86400000;
        return days;
    }

    /**
     * 计算两个时间之间相差的分钟数
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffMinutes(Date startDate, Date endDate)
    {
        return (endDate.getTime() - startDate.getTime()) / 60000;
    }

    /**
     * 日期加上月数的时间
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddMonth(Date date, int month)
    {
        return add(date, Calendar.MONTH, month);
    }

    /**
     * 日期加上天数的时间
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddDay(Date date, int day)
    {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * 日期加上年数的时间
     * @param date
     * @param year
     * @return
     */
    public static Date dateAddYear(Date date, int year)
    {
        return add(date, Calendar.YEAR, year);
    }

    /**
     * 计算剩余时间 (多少天多少时多少分)
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static String remainDateToString(Date startDate, Date endDate)
    {
        StringBuilder result = new StringBuilder();
        if (endDate == null)
        {
            return "过期";
        }
        long times = endDate.getTime() - startDate.getTime();
        if (times < -1)
        {
            result.append("过期");
        }
        else
        {
            long temp = 1000 * 60 * 60 * 24;
            //天数
            long d = times / temp;

            //小时数
            times %= temp;
            temp /= 24;
            long m = times / temp;
            //分钟数
            times %= temp;
            temp /= 60;
            long s = times / temp;

            result.append(d);
            result.append("天");
            result.append(m);
            result.append("小时");
            result.append(s);
            result.append("分");
        }
        return result.toString();
    }

    private static Date add(Date date, int type, int value)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }

    /**
     * @MethodName: getLinkUrl
     * @Param: DateUtil
     * flag ： true 转换  false 不转换
     * @Author: gang.lv
     * @Date: 2013-5-8 下午02:52:44
     * @Return:
     * @Descb:
     * @Throws:
     */
    public static String getLinkUrl(boolean flag, String content, String id)
    {
        if (flag)
        {
            content = "<a href='finance.do?id=" + id + "'>" + content + "</a>";
        }
        return content;
    }

    /**
     * 时间转换为时间戳
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getTimeCur(String format, String date)
            throws ParseException
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    /**
     * 时间转换为时间戳
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getTimeCur(String format, Date date)
            throws ParseException
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    /**
     * 将时间戳转为字符串
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time)
    {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    public static int getAge(Date birthday)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        Calendar nowDate = Calendar.getInstance();

        return nowDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
    }

    /**
     * 当前时间减去分钟数得到的时间
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static String getDateMinusMinutes(int minutes)
            throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date();
        String datetest = formatter.format(nowTime);
        Date date = formatter.parse(datetest);
        long Time1 = (date.getTime() / 1000) - 60 * minutes;//减去30分
        date.setTime(Time1 * 1000);
        return formatter.format(date);
    }

    /**
     * 和当前时间比较是否在给定的时长内
     * @param validTime 给定的时间
     * @param time 给定的时长（s）
     * @return true 有效 false 无效
     */
    public static boolean inValidTime(Date validTime, int time)
    {
        long currTime = System.currentTimeMillis();
        long valid = validTime.getTime();

        return ((currTime - valid) < time * 1000);
    }

    /**
     * 获取年
     * @param time
     * @return
     */
    public static int getYear(Date time)
    {
        if (time == null)
        {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     * @param time
     * @return
     */
    public static int getMonth(Date time)
    {
        if (time == null)
        {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     * @param time
     * @return
     */
    public static int getDay(Date time)
    {
        if (time == null)
        {
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取年
     * @return
     */
    public static int getYear()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     * @return
     */
    public static int getMonth()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     * @return
     */
    public static int getDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     * @param startDateStr
     * @return
     */
    public static Date strDateToStartDate(String startDateStr)
    {

        return DateUtil.strToDate(startDateStr + " 00:00:00");
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     * @param startDateStr
     * @return
     */
    public static Date strDateToEndDate(String endDateStr)
    {

        return DateUtil.strToDate(endDateStr + " 23:59:59");
    }

    /**
     * 当前时间减去天数得到的时间
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static Date getDateMinusDay(int day)
    {
        Date time = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
        return c.getTime();
    }

    /**
     * 当前时间加上天数得到的时间
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static Date getDateAddDay(int day)
    {
        Date time = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + day);
        return c.getTime();
    }

    public static String getDate()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    public static long dateTime(Date date, int val, int type)
    {
        Date now = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        /**
         * gc.add(1,-1)表示年份减一.
         *gc.add(2,-1)表示月份减一.
         *gc.add(3.-1)表示周减一.
         *gc.add(5,-1)表示天减一.
         */
        if (-1 == type)
        {
            gc.add(1, val);
        }
        else if (0 == type)
        {
            gc.add(2, val);
        }
        else if (1 == type)
        {
            gc.add(5, val);
        }
        long time = (gc.getTime().getTime() - now.getTime());
        return time;
    }

}

