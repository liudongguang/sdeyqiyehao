package com.yzcx.controller;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping(value = "/yzcxdata")
public class YZCXHandlerController {
    @Autowired
    private YZCXscheduleService yzcXscheduleService;

    /**
     * 日处理
     *
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/menzhenDayHandler")
    @ResponseBody
    public ResultMsg2 menzhenDayHandler(YZCXSearchParam param) throws IOException, ParseException {
        System.out.println("-------------");
        ResultMsg2 msg = new ResultMsg2();
        yzcXscheduleService.menzhenDayHandler();
        return msg;
    }

    /**
     * 日归档
     *
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/daysGuiDang")
    @ResponseBody
    public ResultMsg2 daysGuiDang(YZCXSearchParam param) throws IOException, ParseException {
        System.out.println("-------------");
        ResultMsg2 msg = new ResultMsg2();
        if (param != null) {
            param.setStart(LdgDateUtil.get000000Time(param.getStart()));
            param.setEnd(LdgDateUtil.get235959Time(param.getEnd()));
        } else {
            LocalDate localDate = LocalDate.now();
            localDate = localDate.minus(1, ChronoUnit.DAYS);//前一天
            String start = localDate.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_00_00_00);
            String end = localDate.format(LdgDateUtil.newDateFormat_yyyy_mm_dd_23_59_59);
            param.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(start));
            param.setEnd(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate(end));
        }
        YZCXHandlerData handlerData = yzcXscheduleService.getmzinfo(param);
        if (handlerData == null) {
            msg.setErrmsg("已导入！");
            return msg;
        }
        yzcXscheduleService.saveYZCXData(handlerData, param);
        return msg;
    }

    /**
     * 月归档
     *
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/testmzMonth")
    @ResponseBody
    public ResultMsg2 testmzMonth(YZCXSearchParam param) throws IOException, ParseException {
        System.out.println("-------------");
        param.setStart(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-09-01 00:00:00"));
        param.setEnd(LdgDateUtil.getYyyy_mm_dd_hh_mm_ssDate("2017-09-30 23:59:59"));
        ResultMsg2 msg = yzcXscheduleService.montho_mzinfo(param);
        return msg;
    }
}
