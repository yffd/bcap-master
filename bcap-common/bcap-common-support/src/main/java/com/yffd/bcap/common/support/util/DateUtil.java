package com.yffd.bcap.common.support.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DateUtil {
    public static final SimpleDateFormat DATETIME_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIME_FMT = new SimpleDateFormat("HH:mm:ss");
    public static final long DAY_MILLI = 24 * 60 * 60 * 1000; // 一天的MilliSecond
    public final static int LEFT_OPEN_RIGHT_OPEN = 1;
    public final static int LEFT_CLOSE_RIGHT_OPEN = 2;
    public final static int LEFT_OPEN_RIGHT_CLOSE = 3;
    public final static int LEFT_CLOSE_RIGHT_CLOSE = 4;
    /**
     * 比较日期的模式 --只比较日期，不比较时间
     */
    public final static int COMP_MODEL_DATE = 1;
    /**
     * 比较日期的模式 --只比较时间，不比较日期
     */
    public final static int COMP_MODEL_TIME = 2;
    /**
     * 比较日期的模式 --比较日期，也比较时间
     */
    public final static int COMP_MODEL_DATETIME = 3;

    private static Map<String, String> DATE_REG_FORMAT = new HashMap<String, String>();
    static {
        DATE_REG_FORMAT.put(
                "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,3}\\D*$",
                "yyyy-MM-dd-HH-mm-ss.SSS");//2017年12月18日 11时46分34秒，2017-12-18 11:18:34 ，2017/12/18 11:46:34
        DATE_REG_FORMAT.put(
                "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
                "yyyy-MM-dd-HH-mm-ss");//2017年12月18日 11时46分34秒，2017-12-18 11:18:34 ，2017/12/18 11:46:34
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
                "yyyy-MM-dd-HH-mm");//2017-12-18 11:18
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
                "yyyy-MM-dd-HH");//2017-12-18 11
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");//2017-12-18
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");//2017-12
        DATE_REG_FORMAT.put("^\\d{4}$", "yyyy");//2017
        DATE_REG_FORMAT.put("^\\d{14}$", "yyyyMMddHHmmss");//20171218111834
        DATE_REG_FORMAT.put("^\\d{12}$", "yyyyMMddHHmm");//201712181118
        DATE_REG_FORMAT.put("^\\d{10}$", "yyyyMMddHH");//2017121811
        DATE_REG_FORMAT.put("^\\d{8}$", "yyyyMMdd");//20171218
        DATE_REG_FORMAT.put("^\\d{6}$", "yyyyMM");//201712
        DATE_REG_FORMAT.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$",
                "yyyy-MM-dd-HH-mm-ss");//11:18:34 拼接当前时间
        DATE_REG_FORMAT.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//11:34 拼接当前时间
        DATE_REG_FORMAT.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//17.12.18(年.月.日)
        DATE_REG_FORMAT.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//18.12(日.月) 拼接当前日期
        DATE_REG_FORMAT.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.18.2017(日.月.年)

    }

    /**
     * 私有构造方法，将该工具类设为单例模式
     */
    private DateUtil() {}

    /**
     * 将任意的日期格式字符串转换成日期类型
     * @param dateStr
     * @return
     */
    public static Date parseToDate(String dateStr) {
        DateFormat tmpFmt;
        for(String key : DATE_REG_FORMAT.keySet()) {
            if(Pattern.compile(key).matcher(dateStr).matches()) {
                try {
                    if(key.equals("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,3}\\D*$")) {
                        tmpFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        return tmpFmt.parse(dateStr);
                    }
                    tmpFmt = new SimpleDateFormat(DATE_REG_FORMAT.get(key));
                    if(key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$")
                            || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {//11:18:34 或 11:18 拼接当前日期
                        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        dateStr = curDate + "-" + dateStr;
                    } else if(key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//18.12 (日.月) 拼接当前年份
                        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        dateStr = curDate.substring(0, 4) + "-" + dateStr;
                    }
                    String dateReplace = dateStr.replaceAll("\\D+", "-");
                    return tmpFmt.parse(dateReplace);
                } catch (ParseException e) {
                    throw new RuntimeException("日期格式无效：【" + dateStr + "】", e);
                }
            }
        }
        return null;
    }

    /**
     * 字符转日期. <br/>
     * yyyy-MM-dd <br/>
     * yyyy-MM-dd HH:mm <br/>
     * yyyy-MM-dd HH:mm:ss <br/>
     * @param dateStr
     * @return
     */
    public static Date parse2Date(String dateStr) {
        SimpleDateFormat formatter = null;
        if (dateStr == null) {
            return null;
        } else if (dateStr.length() == 10) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        } else if (dateStr.length() == 16) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (dateStr.length() == 19) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (dateStr.length() > 19) {
            dateStr = dateStr.substring(0, 19);
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败：【" + dateStr + "】", e);
        }
    }

    /**
     * 根据单位字段比较两个时间
     * @param date
     * @param otherDate
     * @param withUnit  单位字段，从Calendar field取值
     * @return  等于返回0值, 大于返回大于0的值 小于返回小于0的值
     */
    public static int compareTime(Date date, Date otherDate, int withUnit) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        Calendar otherDateCal = Calendar.getInstance();
        otherDateCal.setTime(otherDate);

        switch (withUnit) {
            case Calendar.YEAR:
                dateCal.clear(Calendar.MONTH);
                otherDateCal.clear(Calendar.MONTH);
            case Calendar.MONTH:
                dateCal.set(Calendar.DATE, 1);
                otherDateCal.set(Calendar.DATE, 1);
            case Calendar.DATE:
                dateCal.set(Calendar.HOUR_OF_DAY, 0);
                otherDateCal.set(Calendar.HOUR_OF_DAY, 0);
            case Calendar.HOUR:
                dateCal.clear(Calendar.MINUTE);
                otherDateCal.clear(Calendar.MINUTE);
            case Calendar.MINUTE:
                dateCal.clear(Calendar.SECOND);
                otherDateCal.clear(Calendar.SECOND);
            case Calendar.SECOND:
                dateCal.clear(Calendar.MILLISECOND);
                otherDateCal.clear(Calendar.MILLISECOND);
            case Calendar.MILLISECOND:
                break;
            default:
                throw new IllegalArgumentException("withUnit 单位字段 " + withUnit + " 不合法！！");
        }
        return dateCal.compareTo(otherDateCal);
    }

    /**
     * 获得当前的日期毫秒
     * @return
     */
    public static long nowTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获得当前的时间戳
     * @return
     */
    public static Timestamp nowTimeStamp() {
        return new Timestamp(nowTimeMillis());
    }

    /**
     * 计算 second 秒后的时间
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 计算 minute 分钟后的时间
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 计算 hour 小时后的时间
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 计算 day 天后的时间
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 计算 month 月后的时间
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 计算 year 年后的时间
     * @param date
     * @param year
     * @return
     */
    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 365 * year);
        return calendar.getTime();
    }

    /**
     * 判断一个时间是否在某个时间区间内--开区间.<br/>
     * @param now       目标时间
     * @param start     时间区间开始
     * @param end       时间区间结束
     * @param compModel 比较的模式 <pre>
     *                      取值：
     *                      COMP_MODEL_DATE     只比较日期，不比较时间
     *                      COMP_MODEL_TIME     只比较时间，不比较日期
     *                      COMP_MODEL_DATETIME 比较日期，也比较时间
     *                      </pre>
     * @return          是否在区间内
     */
    public static boolean isBetween(Date now, Date start, Date end, int compModel) {
        return isBetween(now, start, end, LEFT_OPEN_RIGHT_OPEN, compModel);
    }

    /**
     * 判断一个时间是否在某个时间区间内. <br/>
     * @param date          需要判断的时间
     * @param start         时间段的起始时间
     * @param end           时间段的截止时间
     * @param rangeModel    区间模式<pre>
     *                      取值：
     *                      LEFT_OPEN_RIGHT_OPEN	左开右开
     *                      LEFT_CLOSE_RIGHT_OPEN	左闭右开
     *                      LEFT_OPEN_RIGHT_CLOSE	左开右闭
     *                      LEFT_CLOSE_RIGHT_CLOSE	左闭右闭
     *                      </pre>
     * @param compModel     比较的模式 <pre>
     *                      取值：
     *                      COMP_MODEL_DATE     只比较日期，不比较时间
     *                      COMP_MODEL_TIME     只比较时间，不比较日期
     *                      COMP_MODEL_DATETIME 比较日期，也比较时间
     *                      </pre>
     * </pre>
     * @return
     */
    public static boolean isBetween(Date date, Date start, Date end, int rangeModel, int compModel) {
        if (date == null || start == null || end == null) {
            throw new IllegalArgumentException("日期不能为空");
        }
        SimpleDateFormat format = null;
        switch (compModel) {
            case COMP_MODEL_DATE: {
                format = new SimpleDateFormat("yyyyMMdd");
                break;
            }
            case COMP_MODEL_TIME: {
                format = new SimpleDateFormat("HHmmss");
                break;
            }
            case COMP_MODEL_DATETIME: {
                format = new SimpleDateFormat("yyyyMMddHHmmss");
                break;
            }
            default: {
                throw new IllegalArgumentException(String.format("日期的比较模式[%d]有误", compModel));
            }
        }
        long dateNumber = Long.parseLong(format.format(date));
        long startNumber = Long.parseLong(format.format(start));
        long endNumber = Long.parseLong(format.format(end));
        switch (rangeModel) {
            case LEFT_OPEN_RIGHT_OPEN: {
                if (dateNumber <= startNumber || dateNumber >= endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            case LEFT_CLOSE_RIGHT_OPEN: {
                if (dateNumber < startNumber || dateNumber >= endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            case LEFT_OPEN_RIGHT_CLOSE: {
                if (dateNumber <= startNumber || dateNumber > endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            case LEFT_CLOSE_RIGHT_CLOSE: {
                if (dateNumber < startNumber || dateNumber > endNumber) {
                    return false;
                } else {
                    return true;
                }
            }
            default: {
                throw new IllegalArgumentException(String.format("日期的区间模式[%d]有误", rangeModel));
            }
        }
    }

    /**
     * 判断给定的日期是否在当前日期减去间隔后的日期之前，如果是返回true，否则返回false. <br/>
     * date < (currentDate - interval)
     * @param date
     * @param interval		间隔数
     * @param dateUnit		单位(如：月，日),参照Calendar的时间单位
     * @return
     */
    public static boolean isOverIntervalLimit(Date date, int interval, int dateUnit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(dateUnit, interval * (-1));
        Date curDate = getDayBegin(cal.getTime());
        return getDayBegin(date).compareTo(curDate) < 0;
    }

    /**
     * 判断给定的日期是否在当前日期减去间隔后的日期之前，精确到日（yyyy-MM-dd），如果是返回true，否则返回false. <br/>
     * date < (currentDate - interval)
     * @param date
     * @param interval		间隔数
     * @return
     */
    public static boolean isOverIntervalLimit(Date date, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, interval * (-1));
        Date curDate = getDayBegin(cal.getTime());
        return getDayBegin(date).compareTo(curDate) < 0;
    }


    /**
     * 判断某个时间是不是与当前时间在同一天中. <br/>
     * @param date
     * @return
     */
    public static boolean isSameDayWithToday(Date date) {
        if (date == null) return false;
        Calendar todayCal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();
        todayCal.setTime(new Date());
        dateCal.setTime(date);
        int subYear = todayCal.get(Calendar.YEAR) - dateCal.get(Calendar.YEAR);
        int subMouth = todayCal.get(Calendar.MONTH) - dateCal.get(Calendar.MONTH);
        int subDay = todayCal.get(Calendar.DAY_OF_MONTH) - dateCal.get(Calendar.DAY_OF_MONTH);
        if (subYear == 0 && subMouth == 0 && subDay == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断某个时间是不是与当前时间在同一周中. <br/>
     * @param date
     * @return
     */
    public static boolean isSameWeekWithToday(Date date) {
        if (date == null) return false;
        Calendar todayCal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();
        todayCal.setTime(new Date());
        dateCal.setTime(date);
        int subYear = todayCal.get(Calendar.YEAR) - dateCal.get(Calendar.YEAR);
        // subYear==0,说明是同一年
        if (subYear == 0) {
            if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == 1 && dateCal.get(Calendar.MONTH) == 11 && todayCal.get(Calendar.MONTH) == 0) {
            if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == -1 && todayCal.get(Calendar.MONTH) == 11 && dateCal.get(Calendar.MONTH) == 0) {
            if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 取得两个日期之间的天数. <br/>
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long daysBetween(String beginDate, String endDate) {
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(beginDate);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式无效：【" + beginDate + "】", e);
        }
        Date date2;
        try {
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式无效：【" + endDate + "】", e);
        }
        long diff = date1.getTime() - date2.getTime();
        return Math.abs(diff) / DAY_MILLI;
    }

    /**
     * 取得两个日期之间的毫秒数. <br/>
     * @param date1
     * @param date2
     * @return
     */
    public static long millisecondsBetween(Date date1, Date date2){
        long diff = date1.getTime() - date2.getTime();
        return Math.abs(diff);
    }

    /**
     * 取得两个日期之间的天数. <br/>
     * @param date1
     * @param date2
     * @return
     */
    public static long daysBetween(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        return Math.abs(diff) / DAY_MILLI;
    }

    /**
     * 取得两个日期之间的年数. <br/>
     * @param date1
     * @param date2
     * @return
     */
    public static int yearBetween(Date date1, Date date2){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        int year1 = calendar1.get(Calendar.YEAR);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int year2 = calendar2.get(Calendar.YEAR);

        return Math.abs(year1 - year2);
    }

    /**
     * 取得某个日期所在月的月天数. <br/>
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断输入日期是一个星期中的第几天(星期天为一个星期第一天). <br/>
     * @param date
     * @return
     */
    public static int getDayIndexOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 日期最大时间，精确到秒. <br/>
     * @param date
     * @return
     */
    public static Date getMaxTime(Date date) {

        Date tmp = null;
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, 1);
        tmp = ca.getTime();
        tmp = DateUtil.getMinTime(tmp);
        ca.setTime(tmp);
        ca.add(Calendar.SECOND, -1);
        tmp = ca.getTime();
        return tmp;
    }

    /**
     * 日期最小时间，精确到秒. <br/>
     * @param date
     * @return
     */
    public static Date getMinTime(Date date) {
        Date tmp = null;
        tmp = DateUtil.parse2Date(DateUtil.DATE_FMT.format(date));
        return tmp;
    }

    /**
     * 取得月第一天. <br/>
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得月最后一天. <br/>
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得上月第一天. <br/>
     * @return
     */
    public static Date getPreviousMonthFirstDay() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
        return getMinTime(lastDate.getTime());
    }

    /**
     * 取得上月最后一天. <br/>
     * @return
     */
    public static Date getPreviousMonthLastDay() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.DATE, -1);
        return getMinTime(lastDate.getTime());
    }

    /**
     * 得到day的起始时间点. <br/>
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到day的终止时间点. <br/>
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 得到当月起始时间. <br/>
     * @param date
     * @return
     */
    public static Date getMonthBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到month的终止时间点. <br/>
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     *
     * getWeekStart:得到当前周起始时间. <br/>
     * @param date
     * @return
     */
    public static Date getWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.WEEK_OF_YEAR);
        int firstDay = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, firstDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     *
     * getWeekEnd:得到当前周截止时间. <br/>
     * @param date
     * @return
     */
    public static Date getWeekEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.WEEK_OF_YEAR);
        int firstDay = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, 8 - firstDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }



    /**
     * 得到当前年起始时间. <br/>
     * @param date
     * @return
     */
    public static Date getYearBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到当前年最后一天. <br/>
     * @param date
     * @return
     */
    public static Date getYearEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得季度第一天. <br/>
     * @param date
     * @return
     */
    public static Date getSeasonStart(Date date) {
        return getDayBegin(getFirstDateOfMonth(getSeasonDate(date)[0]));
    }

    /**
     * 取得季度最后一天. <br/>
     * @param date
     * @return
     */
    public static Date getSeasonEnd(Date date) {
        return getDayEnd(getLastDateOfMonth(getSeasonDate(date)[2]));
    }

    /**
     * 取得季度月. <br/>
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 1:第一季度、2:第二季度、3:第三季度、4:第四季度. <br/>
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    public static void main(String[] args) throws Exception {
        String dateStr1 = "2018-01-28 09:40:11";

        Date date1 = DATETIME_FMT.parse(dateStr1);

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date1);

//		dateCal.clear(Calendar.MONTH);
//		System.out.println(DATETIME_FMT.format(dateCal.getTime()));

//		dateCal.set(Calendar.DATE, 1);
//		System.out.println(DATETIME_FMT.format(dateCal.getTime()));

//        System.out.println(DATETIME_FMT.format(addMonth(date1, -1)));

        String[] dateStrArray = new String[]{
                "2017-03-12 12:05:34.123",
                "2017-03-12 12:05:34",
                "2017-03-12 12:05",
                "2017-03-12 12",
                "2017-03-12",
                "2017-03",
                "2017",
                "20140312120534",
                "2017/03/12 12:05:34",
                "2017/3/12 12:5:34",
                "2014年3月12日 13时5分34秒",
                "201403121205",
                "1234567890",
                "20140312",
                "201403",
                "2000 13 33 13 13 13",
                "30.12.2013",
                "12.21.2013",
                "21.1",
                "13:05:34",
                "12:05",
                "14.1.8",
                "14.10.18"
        };
        for(int i=0;i<dateStrArray.length;i++){
            System.out.println(dateStrArray[i] +"------------------------------".substring(1,30-dateStrArray[i].length())+ parseToDate(dateStrArray[i]));
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date date = fmt.parse("2017-11-29 17:57:52.12");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
