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
     * 根据传入的日期来返回查询时间段
     * @param cparam
     * @return
     * @throws ParseException
     */
    public static YZCXSearchParam getSearchParam(YZCXSearchParam cparam) throws ParseException {
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
}
