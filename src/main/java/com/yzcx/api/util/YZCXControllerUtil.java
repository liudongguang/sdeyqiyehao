package com.yzcx.api.util;

import com.yzcx.api.vo.YZCXSearchParam;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class YZCXControllerUtil {
    /**
     * 获取明天
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getNextOneDay() throws ParseException {
        YZCXSearchParam param = new YZCXSearchParam();
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plus(1, ChronoUnit.DAYS);//前一天
        param.setStart(LdgDateUtil.parseLocalDateToDate(localDate));
        param.setEnd(LdgDateUtil.get235959Time(LdgDateUtil.parseLocalDateToDate(localDate)));
        return param;
    }
    /**
     * 返回昨天
     *
     * @return
     */
    public static YZCXSearchParam getBeforeOneDay() throws ParseException {
        return getBeforeDayByNum(1);
    }

    /**
     * 根据天数返回提前多少天
     *
     * @param numofDay
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getBeforeDayByNum(int numofDay) throws ParseException {
        YZCXSearchParam param = new YZCXSearchParam();
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minus(numofDay, ChronoUnit.DAYS);//前一天
        param.setStart(LdgDateUtil.parseLocalDateToDate(localDate));
        param.setEnd(LdgDateUtil.get235959Time(LdgDateUtil.parseLocalDateToDate(localDate)));
        return param;
    }

    /**
     * 返回上一个月  1.1号到1.31    月开始到月结束
     *
     * @return
     */
    public static YZCXSearchParam getBeforeOneMonth() throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        now = now.minus(1, ChronoUnit.MONTHS);
        YZCXSearchParam param = new YZCXSearchParam();
        param.setStart(LdgDateUtil.getDayZeroTime(now.with(TemporalAdjusters.firstDayOfMonth())));
        param.setEnd(LdgDateUtil.getDayLastTime(now.with(TemporalAdjusters.lastDayOfMonth())));
        return param;
    }
    public static YZCXSearchParam getThisMonth() throws ParseException {
        LocalDateTime now = LocalDateTime.now();
        YZCXSearchParam param = new YZCXSearchParam();
        param.setStart(LdgDateUtil.getDayZeroTime(now.with(TemporalAdjusters.firstDayOfMonth())));
        param.setEnd(LdgDateUtil.getDayLastTime(now.with(TemporalAdjusters.lastDayOfMonth())));
        return param;
    }
    /**
     * 获取当天的零点到23：59：59
     *
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamForDay() throws ParseException {
        YZCXSearchParam yzcxSearchParam = new YZCXSearchParam();
        Date now = new Date();
        yzcxSearchParam.setStart(LdgDateUtil.get000000Time(now));
        yzcxSearchParam.setEnd(LdgDateUtil.get235959Time(now));
        return yzcxSearchParam;
    }

    /**
     * 根据传入的日期来返回查询时间段,时间段为一个月的第一天和这一个月的最后一天，若没有传入值则返回上个月的开始与结尾
     *
     * @param cparam
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamForMonth(YZCXSearchParam cparam) throws ParseException {
        Date startDate = cparam.getStart();
        YZCXSearchParam rsparam = new YZCXSearchParam();
        if (startDate != null) {
            LocalDateTime localDateTime = LdgDateUtil.parseDateToLocalDateTime(startDate);
            rsparam.setStart(LdgDateUtil.getDayZeroTime(localDateTime.with(TemporalAdjusters.firstDayOfMonth())));
            rsparam.setEnd(LdgDateUtil.getDayLastTime(localDateTime.with(TemporalAdjusters.lastDayOfMonth())));
        } else {
            return getBeforeOneMonth();
        }
        return rsparam;
    }

    public static YZCXSearchParam getSearchParamForThisMonth(YZCXSearchParam cparam) throws ParseException {
        Date startDate = cparam.getStart();
        YZCXSearchParam rsparam = new YZCXSearchParam();
        if (startDate != null) {
            LocalDateTime localDateTime = LdgDateUtil.parseDateToLocalDateTime(startDate);
            rsparam.setStart(LdgDateUtil.getDayZeroTime(localDateTime.with(TemporalAdjusters.firstDayOfMonth())));
            rsparam.setEnd(LdgDateUtil.getDayLastTime(localDateTime.with(TemporalAdjusters.lastDayOfMonth())));
        } else {
            return getThisMonth();
        }
        return rsparam;
    }
    /**
     * 开始时间与结束时间都减去一年，即为前一年的月份日期
     *
     * @param cparam
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamBeforeOneYear(YZCXSearchParam cparam) throws ParseException {
        YZCXSearchParam rsparam = new YZCXSearchParam();
        LocalDateTime localDateTimeStart = LdgDateUtil.parseDateToLocalDateTime(cparam.getStart());
        LocalDateTime localDateTimeEnd = LdgDateUtil.parseDateToLocalDateTime(cparam.getEnd());
        localDateTimeStart = localDateTimeStart.minus(1, ChronoUnit.YEARS);
        localDateTimeEnd = localDateTimeEnd.minus(1, ChronoUnit.YEARS);
        rsparam.setStart(LdgDateUtil.getDayZeroTime(localDateTimeStart));
        rsparam.setEnd(LdgDateUtil.getDayLastTime(localDateTimeEnd));
        return rsparam;
    }

    /**
     * 获取一年的开始与结尾
     *
     * @param param
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamForYear(YZCXSearchParam param) throws ParseException {
        YZCXSearchParam rsparam = new YZCXSearchParam();
        Date startDate = param.getStart();
        LocalDateTime localDateTime = null;
        if (startDate == null) {
            localDateTime = LocalDateTime.now();
        } else {
            localDateTime = LdgDateUtil.parseDateToLocalDateTime(startDate);
        }
        rsparam.setStart(LdgDateUtil.getDayZeroTime(localDateTime.with(TemporalAdjusters.firstDayOfYear())));
        rsparam.setEnd(LdgDateUtil.getDayLastTime(localDateTime.with(TemporalAdjusters.lastDayOfYear())));
        return rsparam;
    }
}
