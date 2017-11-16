package com.yzcx.api.util;

import com.yzcx.api.vo.YZCXSearchParam;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

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
}
