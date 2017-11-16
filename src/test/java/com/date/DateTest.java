package com.date;

import com.yzcx.api.util.LdgDateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;

public class DateTest {
    @Test
    public void t1() throws ParseException {
        LdgDateUtil.getQianyinianStartUntilBeforeMonth().forEach(it->{
            System.out.println(it);
        });
    }

    @Test
    public void t2() throws ParseException {
        LocalDateTime nowTime=LocalDateTime.now();
        String nowDateTime_Str= nowTime.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_HH_mm_ss);
        System.out.println(nowDateTime_Str);
    }

}
