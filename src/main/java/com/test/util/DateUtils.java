package com.test.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: DateUtils
 * @description:
 * @author: wwb
 * @date: 2017-10-30 16:22:37
 * @version: ver 1.0
 */
public class DateUtils {

    public static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat YYYYMM = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static final SimpleDateFormat HH = new SimpleDateFormat("HH");

    public static final SimpleDateFormat DEFAULT_FORMAT = YYYYMMDDHHMMSS;

    /** 将日期字符串根据默认的格式转换为日期
     * @Description:
     * @author: 王文彬
     * @version: 2016年4月21日 上午10:40:30
     * @param dateStr
     * @return Date
     */
    public static Date parse(String dateStr){
        return parse(dateStr, DEFAULT_FORMAT);
    }
    /** 将日期字符串根据指定的格式转换为日期
     * @Description:
     * @author: 王文彬
     * @version: 2015年12月3日 下午9:58:29
     * @param dateStr
     * @param sdf
     * @return Date
     */
    public static Date parse(String dateStr, SimpleDateFormat sdf){
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 将日期根据给定的格式转换为字符串
     * @Description:
     * @author: 王文彬
     * @version: 2015年12月3日 下午10:00:27
     * @param date
     * @param sdf
     * @return String
     */
    public static String format(Date date, SimpleDateFormat sdf){
        return sdf.format(date);
    }
    /** 将日期根据默认的格式转换为字符串
     * @Description:
     * @author: 王文彬
     * @version: 2016年4月21日 上午10:42:07
     * @param date
     * @return String
     */
    public static String format(Date date){
        return format(date, DEFAULT_FORMAT);
    }
    /** 获取上下午
     * @Description: 
     * @author:
     * @Date: 2018-01-08 11:06:11
     * @param: date
     * @return: java.lang.String
     */
    public static String getMorningOrAfternoon(Date date) {
        int hour = Integer.valueOf(HH.format(date));
        return hour > 12 ? "下午" : "上午";
    }
    /**
     * @Description时间内部类
     * @author 杨牛浩江
     * @date 2018-08-15
     */
    private static class DayCompare{
        private int year;
        private int month;
        private int day;

        private DayCompare() {
        }

        private DayCompare(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public static DayCompare builder() {
            return new DayCompare() ;
        }


    }
   /**
    * @Description: grade截取年份
    * @author: ynhj
    * @Date: 2018-08-15 18:16:13
    * @param: str
    * @return: java.lang.String
    */
    public static String getYear(String grade){
        String year = null;
        String pattern = "\\d{4}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(grade);
        if (m.find( )) {
            year = m.group();
        }
        return year;
    }
    /**
     * @Description:  比较2个时间，数据组装成功和不过dayCompare(时间相差转为年月日)
     * @author: ynhj
     * @Date: 2018-08-15 18:16:32
     * @param: fromDate
     * @param: toDate
     * @return: com.chaoxing.basicedu.readactivity.platform.util.DateUtils.DayCompare
     */
    private static DayCompare dayComparePrecise(Date fromDate,Date toDate){
        Calendar from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);

        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        return new DayCompare(year,month,day);
    }
    /**
     * @Description: 时间差（年份）2个时间相差的年份，不足1年按0年算
     * @author: ynhj
     * @Date: 2018-08-15 18:15:57
     * @param: fromDate
     * @param: toDate
     * @return: java.lang.Integer
     */
    public static Integer yearCompare(Date fromDate,Date toDate){
        DayCompare result = dayComparePrecise(fromDate, toDate);
        double month = result.getMonth();
        double year = result.getYear();
        //返回2位小数，并且四舍五入
        DecimalFormat df = new DecimalFormat("######0.0");
        return Double.valueOf(df.format(year + month / 12)).intValue();
    }
    /**
     * @Description: 时间差（天数）
     * @author: ynhj
     * @Date: 2018-08-15 18:15:57
     * @param: fromDate
     * @param: toDate
     * @return: java.lang.Integer
     */
    public static Integer dayCompare(Date fromDate,Date toDate){
        if (fromDate == null || toDate == null){
            return null;
        }
        Integer days = Double.valueOf(Math.ceil((double)(toDate.getTime() - fromDate.getTime()) / (1000 * 3600 * 24))).intValue();
        return days;
    }


    public static void main(String[] args) {
        Calendar calendar  = Calendar.getInstance();
        calendar.set(2018,8,4);
        Integer integer = dayCompare(Calendar.getInstance().getTime(), calendar.getTime());
        System.out.println(Calendar.getInstance().getTime().before(calendar.getTime()));
        System.out.println(integer);
    }
}
