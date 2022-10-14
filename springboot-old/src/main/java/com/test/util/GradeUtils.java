package com.test.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @className:
 * @Description:
 * @auther:ynhj
 * @date:18:42 2018-08-14
 * @version: ver 1.0
 */
public class GradeUtils {
    public enum RealGrade {
        ONE(1, "一年级", "one"),
        TWO(2, "二年级", "two"),
        THREE(3, "三年级", "three"),
        FOUR(4, "四年级", "four"),
        FIVE(5, "五年级", "five"),
        SIX(6, "六年级", "six"),
        SEVEN(7, "七年级", "seven"),
        EIGHT(8, "八年级", "eight"),
        NINE(9, "九年级", "nine"),
        TEN(10, "十年级", "ten"),
        ELEVEN(11, "十一年级", "eleven"),
        TWELVE(12, "十二年级", "twelve"),
        TEACHER(13, "教师组", "teacher"),
        GRADUATED(-1, "已毕业", "graduation");

        Integer id;
        String name;
        String value;

        RealGrade(Integer id, String name, String value) {
            this.id = id;
            this.name = name;
            this.value = value;
        }

        public static RealGrade getInstance(Integer id) {
            final RealGrade[] realGrades = RealGrade.values();
            for (RealGrade realGrade : realGrades) {
                if (realGrade.getId() == id) {
                    return realGrade;
                }
            }
            return GRADUATED;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * @Description: 判断用户年级，9月算升级
     * @author: ynhj
     * @Date: 2018-09-19 17:59:02
     * @param: gradeName
     * @param: ageType
     * @return: com.chaoxing.basicedu.readactivity.platform.util.GradeUtils.RealGrade
     */
    public static GradeUtils.RealGrade getGrade(String gradeName, Integer ageType) throws Exception {
        String substring = DateUtils.getYear(gradeName);
        substring += "-09";
        Date orginDate = DateUtils.YYYYMM.parse(substring);
        int orgGrade;
        if (ageType == 1) {
            orgGrade = 1;
            RealGrade realGrade = RealGrade.getInstance(orgGrade + DateUtils.yearCompare(orginDate, Calendar.getInstance().getTime()));
            return realGrade.getId() <= 6 ? realGrade : RealGrade.GRADUATED;
        } else if (ageType == 2) {
            orgGrade = 7;
            RealGrade realGrade = RealGrade.getInstance(orgGrade + DateUtils.yearCompare(orginDate, Calendar.getInstance().getTime()));
            return realGrade.getId() <= 9 ? realGrade : RealGrade.GRADUATED;
        } else if (ageType == 3) {
            orgGrade = 10;
            RealGrade realGrade = RealGrade.getInstance(orgGrade + DateUtils.yearCompare(orginDate, Calendar.getInstance().getTime()));
            return realGrade.getId() <= 12 ? realGrade : RealGrade.GRADUATED;
        }
        return RealGrade.GRADUATED;
    }
    /**
     * @Description: 用于计算在某个时间用户是否毕业，一般按7月算毕业
     * @author: ynhj
     * @Date: 2018-09-19 17:59:27
     * @param: gradeName
     * @param: ageType
     * @param: endTime
     * @return: com.chaoxing.basicedu.readactivity.platform.util.GradeUtils.RealGrade
     */
    public static GradeUtils.RealGrade getGrade(String gradeName, Integer ageType,Date endTime) throws Exception {
        String substring = DateUtils.getYear(gradeName);
        substring += "-07";
        Date orginDate = DateUtils.YYYYMM.parse(substring);
        int orgGrade;
        if (ageType == 1) {
            orgGrade = 1;
            RealGrade realGrade = RealGrade.getInstance(orgGrade + DateUtils.yearCompare(orginDate, endTime));
            return realGrade.getId() <= 6 ? realGrade : RealGrade.GRADUATED;
        } else if (ageType == 2) {
            orgGrade = 7;
            RealGrade realGrade = RealGrade.getInstance(orgGrade + DateUtils.yearCompare(orginDate, endTime));
            return realGrade.getId() <= 9 ? realGrade : RealGrade.GRADUATED;
        } else if (ageType == 3) {
            orgGrade = 10;
            RealGrade realGrade = RealGrade.getInstance(orgGrade + DateUtils.yearCompare(orginDate, endTime));
            return realGrade.getId() <= 12 ? realGrade : RealGrade.GRADUATED;
        }
        return RealGrade.GRADUATED;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getGrade("莆田2011级", 1).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
