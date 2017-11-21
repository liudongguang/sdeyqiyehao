package com.yzcx.controller;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Controller
@RequestMapping(value = "/yzcxdata")
public class YZCXHandlerController {
    @Resource(name = "YZCXscheduleService")
    private YZCXscheduleService yzcXscheduleService;

    /**
     * 日处理---5分钟一更新
     *
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/menzhenDayHandler")
    @ResponseBody
    public ResultMsg2 menzhenDayHandler(YZCXSearchParam param) throws IOException, ParseException {
        System.out.println("------日处理-------");
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
        param.setStart(LdgDateUtil.get000000Time(param.getStart()));
        param.setEnd(LdgDateUtil.get235959Time(param.getEnd()));
        //YZCXHandlerData handlerData = yzcXscheduleService.getmzinfo(param);//获取门诊记录
        //yzcXscheduleService.saveYZCXMenzhenData(handlerData, param);//保存门诊记录
        YZCXHandlerData handlerFeiYongData = yzcXscheduleService.getFeiyonginfo(param);//获取费用记录
        yzcXscheduleService.saveYZCXFeiyongData(handlerFeiYongData, param);//保存费用记录
        System.out.println("daysGuiDang执行完成！");
        return msg;
    }

    /**
     * 日归档，自动执行于凌晨1点  只归档前一天的内容
     *
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/excuteRiguidang")
    @ResponseBody
    public void excuteRiguidang() throws IOException, ParseException {
        System.out.println("------excuteRiguidang------");
        daysGuiDang(YZCXControllerUtil.getBeforeOneDay());
        //daysGuiDang(YZCXControllerUtil.getBeforeOneMonth());
    }

    /**
     * 初始化去年1月到今年本月前一月的信息
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/initYZCXSystem")
    @ResponseBody
    public void initYZCXSystem() throws IOException, ParseException {
        //日归档
        LdgDateUtil.getQianyinianStartUntilBeforeMonth().forEach(it->{
            System.out.println(it);
            try {
                daysGuiDang(it);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        //月归档
        LdgDateUtil.getQianyinianStartUntilBeforeMonth().forEach(it->{
            System.out.println(it);
            try {
                monthGuidang(it);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * 月归档
     *
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/monthGuidang")
    @ResponseBody
    public ResultMsg2 monthGuidang(YZCXSearchParam param) throws IOException, ParseException {
        System.out.println("------月归档-------");
        param.setStart(LdgDateUtil.get000000Time(param.getStart()));
        param.setEnd(LdgDateUtil.get235959Time(param.getEnd()));
        ResultMsg2 msg = yzcXscheduleService.montho_mzinfo(param);
        return msg;
    }

    /**
     * 月归档，执行于每月的1日
     *
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/excutemonthGuidang")
    @ResponseBody
    public void excutemonthGuidang() throws IOException, ParseException {
        System.out.println("------excutemonthGuidang-------");
        LocalDate localDate=LocalDate.now();
        localDate=localDate.minusMonths(1);//获取上一个月
        LocalDate start=localDate.with(TemporalAdjusters.firstDayOfMonth());//一月开始的日期
        LocalDate end=localDate.with(TemporalAdjusters.lastDayOfMonth());//一月结束的日期
        YZCXSearchParam param = new YZCXSearchParam();
        param.setStart(LdgDateUtil.parseLocalDateToDate(start));
        param.setEnd(LdgDateUtil.parseLocalDateToDate(end));
        monthGuidang(param);
    }
}
