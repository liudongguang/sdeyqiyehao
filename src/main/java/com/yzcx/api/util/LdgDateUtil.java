package com.yzcx.api.util;

import com.yzcx.api.vo.YZCXSearchParam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class LdgDateUtil {
    private final static String yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";
    private final static String yyyy_mm_dd_hh = "yyyy-MM-dd HH";
    private final static String yyyy_mm_dd = "yyyy-MM-dd";
    private final static ZoneId zoneId = ZoneId.systemDefault();
    private final static String yyyy_mm_dd_00_00_00 = "yyyy-MM-dd 00:00:00";
    private final static String yyyy_mm_dd_23_59_59 = "yyyy-MM-dd 23:59:59";
    private final static String yyyy_mm_dd_HH_00_00 = "yyyy-MM-dd HH:00:00";
    private final static String  zhongwen_yyyyMM="yyyy年MM月";
    private final static String  zhongwen_MM="MM月";
    private final static String HH = "HH";
    public final static DateTimeFormatter newDateFormat_yyyy_mm_dd_HH_00_00 = DateTimeFormatter.ofPattern(yyyy_mm_dd_HH_00_00);
    public final static DateTimeFormatter newDateFormat_yyyy_mm_dd_HH_mm_ss = DateTimeFormatter.ofPattern(yyyy_mm_dd_hh_mm_ss);
    public final static DateTimeFormatter newDateFormat_yyyy_mm_dd_00_00_00 = DateTimeFormatter.ofPattern(yyyy_mm_dd_00_00_00);
    public final static DateTimeFormatter newDateFormat_yyyy_mm_dd_23_59_59 = DateTimeFormatter.ofPattern(yyyy_mm_dd_23_59_59);

    public static String getyyyy_mm_dd_HH_00_00String(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd_HH_00_00);
    }

    public static String getyyyy_mm_dd_00_00_00String(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd_00_00_00);
    }

    public static Date getyyyy_mm_dd_00_00_00Date(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, yyyy_mm_dd_00_00_00);
    }

    public static String getYyyy_mm_dd_hh_mm_ssString(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd_hh_mm_ss);
    }

    public static Date getYyyy_mm_dd_hh_mm_ssDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, yyyy_mm_dd_hh_mm_ss);
    }

    public static String getYyyy_mm_ddString(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd);
    }

    public static Date getYyyy_mm_ddDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, yyyy_mm_dd);
    }


    public static String getyyyy_mm_dd_hhString(Date date) {
        return DateFormatUtils.format(date, yyyy_mm_dd_hh);
    }

    public static Date getyyyy_mm_dd_hhDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, yyyy_mm_dd_hh);
    }

    public static Date parseLocalDateToDate(LocalDate localDate) {
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static LocalDate parseDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    public static LocalDateTime parseDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDate = instant.atZone(zoneId).toLocalDateTime();
        return localDate;
    }

    public static List<Date> getDateByBetween(Date start, Date end) {
        List<Date> listDate = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(end);
        LocalDate newstart = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate newend = LocalDate.of(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH) + 1, calendar2.get(Calendar.DAY_OF_MONTH));
        if (newstart.isAfter(newend)) {
            System.out.println("开始时间不能在结束时间之后");
            return null;
        }
        while (true) {
            if (newstart.isBefore(newend)) {
                listDate.add(parseLocalDateToDate(newstart));
                newstart = newstart.plusDays(1);
            } else {
                break;
            }
        }
        listDate.add(parseLocalDateToDate(newstart));//包含结尾时间
        return listDate;
    }

    public static Date getDayZeroTime(Date date) throws ParseException {
        String str = DateFormatUtils.format(date, yyyy_mm_dd_00_00_00);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }

    public static Date getDayLastTime(Date date) throws ParseException {
        String str = DateFormatUtils.format(date, yyyy_mm_dd_23_59_59);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }
    public static Date getDayZeroTime(LocalDateTime date) throws ParseException {
        String str = date.format(newDateFormat_yyyy_mm_dd_00_00_00);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }

    public static Date getDayLastTime(LocalDateTime date) throws ParseException {
        String str = date.format(newDateFormat_yyyy_mm_dd_23_59_59);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }
    public static Date get000000Time(Date date) throws ParseException {
        String str = DateFormatUtils.format(date, yyyy_mm_dd_00_00_00);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }

    public static Date get235959Time(Date date) throws ParseException {
        String str = DateFormatUtils.format(date, yyyy_mm_dd_23_59_59);
        return DateUtils.parseDate(str, yyyy_mm_dd_hh_mm_ss);
    }

    /**
     * 获取给定时间，小时的数字
     *
     * @param handledate
     * @return
     */
    public static Integer getHourNum(Date handledate) {
        String hh = DateFormatUtils.format(handledate, HH);
        return Integer.valueOf(hh);
    }

    /**
     * 给定小时，返回当前时间 0分 0秒的时间
     *
     * @param h
     * @return
     */
    public static Long getTimeByHH(Integer h) {
        LocalDateTime lct = LocalDateTime.now();
        LocalDateTime getlct = LocalDateTime.of(lct.getYear(), lct.getMonth(), lct.getDayOfMonth(), h, 0, 0);
        return getlct.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * 根据给定的数字往前推多个月，返回区间集合
     *
     * @param jiangeYueNum
     * @return
     */
    public static List<YZCXSearchParam> getMonthJiangeByNum(int jiangeYueNum) throws ParseException {
        List<YZCXSearchParam> rs = new ArrayList<>();
        LocalDate nowDate = LocalDate.now();
        while (jiangeYueNum > 0) {
            LocalDate newLocalDate=nowDate.minusMonths(jiangeYueNum);
            jiangeYueNum--;
            YZCXSearchParam nobj=new YZCXSearchParam();
            nobj.setStart(get000000Time(parseLocalDateToDate(newLocalDate.with(TemporalAdjusters.firstDayOfMonth()))));  //一月的开始
            nobj.setEnd(get235959Time(parseLocalDateToDate(newLocalDate.with(TemporalAdjusters.lastDayOfMonth()))));//一月的结束
            rs.add(nobj);
        }
        return rs;
    }

    /**
     * 获取前一个月到去年年初的月份,跨度为1月
     * @return
     */
    public static List<YZCXSearchParam> getQianyinianStartUntilBeforeMonth() throws ParseException {
        LocalDate nowDate = LocalDate.now();
        LocalDate beforeYearDate=LocalDate.of(nowDate.getYear()-1,1,1);//前一年的1月1号
        LocalDate beforeMonthDate=nowDate.minus(1, ChronoUnit.MONTHS).with(TemporalAdjusters.lastDayOfMonth());//减一个月
        List<YZCXSearchParam> listDate=new ArrayList<>();
        while (true) {
            if (beforeYearDate.isBefore(beforeMonthDate)) {
                YZCXSearchParam  yzcxSearchParam=new YZCXSearchParam();
                yzcxSearchParam.setStart(parseLocalDateToDate(beforeYearDate));
                yzcxSearchParam.setEnd(get235959Time(parseLocalDateToDate(beforeYearDate.with(TemporalAdjusters.lastDayOfMonth()))));
                listDate.add(yzcxSearchParam);
                beforeYearDate = beforeYearDate.plusMonths(1);
            } else {
                break;
            }
        }
        return listDate;
    }

    /**
     * yyyy年mm月
     * @param date
     * @return
     */
    public static String get_zhongwen_yyyyMM(Date date) {
        return DateFormatUtils.format(date, zhongwen_yyyyMM);
    }

    /**
     * mm月
     * @param handledate
     * @return
     */
    public static String getMonthHanzi(Date handledate) {
        return DateFormatUtils.format(handledate, zhongwen_MM);
    }
}
