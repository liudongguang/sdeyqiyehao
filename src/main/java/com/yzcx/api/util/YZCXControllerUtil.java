package com.yzcx.api.util;

import com.yzcx.api.vo.YZCXSearchParam;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class YZCXControllerUtil {
    /**
     * 返回上一个月  1.1号到1.31    月开始到月结束
     * @return
     */
    public static YZCXSearchParam getBeforeOneMonth() throws ParseException {
        LocalDateTime now=LocalDateTime.now();
        now=now.minus(1, ChronoUnit.MONTHS);
        YZCXSearchParam param=new YZCXSearchParam();
        param.setStart(LdgDateUtil.getDayZeroTime(now.with(TemporalAdjusters.firstDayOfMonth())));
        param.setEnd(LdgDateUtil.getDayLastTime(now.with(TemporalAdjusters.lastDayOfMonth())));
        return param;
    }

    /**
     * 获取当天的零点到23：59：59
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamForDay() throws ParseException {
        YZCXSearchParam yzcxSearchParam=new YZCXSearchParam();
        Date now=new Date();
        yzcxSearchParam.setStart(LdgDateUtil.get000000Time(now));
        yzcxSearchParam.setEnd(LdgDateUtil.get235959Time(now));
        return yzcxSearchParam;
    }
    /**
     * 根据传入的日期来返回查询时间段,时间段为一个月的第一天和这一个月的最后一天，若没有传入值则返回上个月的开始与结尾
     * @param cparam
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamForMonth(YZCXSearchParam cparam) throws ParseException {
        Date startDate=cparam.getStart();
        if(startDate!=null){
            LocalDateTime localDateTime = LdgDateUtil.parseDateToLocalDateTime(startDate);
            cparam.setStart(LdgDateUtil.getDayZeroTime(localDateTime.with(TemporalAdjusters.firstDayOfMonth())));
            cparam.setEnd(LdgDateUtil.getDayLastTime(localDateTime.with(TemporalAdjusters.lastDayOfMonth())));
        }else{
            return getBeforeOneMonth();
        }
        return cparam;
    }

    /**
     * 开始时间与结束时间都减去一年，即为前一年的月份日期
     * @param cparam
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamBeforeOneYear(YZCXSearchParam cparam) throws ParseException {
        LocalDateTime localDateTimeStart = LdgDateUtil.parseDateToLocalDateTime(cparam.getStart());
        LocalDateTime localDateTimeEnd = LdgDateUtil.parseDateToLocalDateTime(cparam.getEnd());
        localDateTimeStart=localDateTimeStart.minus(1,ChronoUnit.YEARS);
        localDateTimeEnd=localDateTimeEnd.minus(1,ChronoUnit.YEARS);
        cparam.setStart(LdgDateUtil.getDayZeroTime(localDateTimeStart));
        cparam.setEnd(LdgDateUtil.getDayLastTime(localDateTimeEnd));
        return cparam;
    }

    /**
     * 获取一年的开始与结尾
     * @param param
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParamForYear(YZCXSearchParam param) throws ParseException {
        Date startDate=param.getStart();
        LocalDateTime localDateTime =null;
        if(startDate==null){
            localDateTime=LocalDateTime.now();
        }else{
            localDateTime = LdgDateUtil.parseDateToLocalDateTime(startDate);
        }
        param.setStart(LdgDateUtil.getDayZeroTime(localDateTime.with(TemporalAdjusters.firstDayOfYear())));
        param.setEnd(LdgDateUtil.getDayLastTime(localDateTime.with(TemporalAdjusters.lastDayOfYear())));
        return param;
    }
}
