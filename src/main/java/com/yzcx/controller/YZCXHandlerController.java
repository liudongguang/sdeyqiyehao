package com.yzcx.controller;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.service.YZCXscheduleImmediatelyService;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.impl.service.handler.YzcxEHcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
@RequestMapping(value = "/yzcxdata")
public class YZCXHandlerController {
    @Autowired
    private YZCXscheduleService yzcXscheduleService;
   @Autowired
    private YZCXscheduleImmediatelyService yzcXscheduleImmediatelyService;
    /**
     * 日处理---5分钟一更新
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/immediatelyHandler")
    @ResponseBody
    public ResultMsg2 immediatelyHandler() throws IOException, ParseException {
        //System.out.println("------日即时处理-------");
        ResultMsg2 msg = new ResultMsg2();
        yzcXscheduleImmediatelyService.ImmediatelyHandler();
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
        yzcXscheduleService.saveYZCXMenzhenData(param);//保存门诊记录
        System.out.println("保存了门诊记录..");
        yzcXscheduleService.handlerFeiyonginfo(param);//获取费用记录
        System.out.println("费用处理完毕！");
        yzcXscheduleService.handlerZhuYuanXinxiRiGuiDang(param);//住院处理
        System.out.println("住院处理完毕！");
        yzcXscheduleService.handlerHuizhenRiGuiDang(param);//会诊处理
        System.out.println("会诊处理完毕！");
        yzcXscheduleService.handlerYijiRiGuiDang(param);//医技处理
        System.out.println("医技处理完毕！");
        /////////////////////删除前一天的日归档信息
        param.setHandletype(null);
        int i = yzcXscheduleImmediatelyService.deleteMenzhenDayHandler(param);
        ////////////////////
        System.out.println(param+"daysGuiDang执行完成！");
        return msg;
    }

    /**
     * 日归档，自动执行于凌晨1点  只归档前一天的日处理
     *
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/excuteRiguidang")
    @ResponseBody
    public void excuteRiguidang() throws IOException, ParseException {
        System.out.println("------excuteRiguidang------");
//        LocalDateTime localDate=LocalDateTime.of(2017,1,1,0,0,0);
//        YZCXSearchParam param=new YZCXSearchParam();
//        param.setStart(LdgDateUtil.getDayZeroTime(localDate));
//        param.setEnd(LdgDateUtil.getDayLastTime(localDate));
//        daysGuiDang(param);
        daysGuiDang(YZCXControllerUtil.getBeforeOneDay());
        //daysGuiDang(YZCXControllerUtil.getBeforeOneMonth());
    }

    /**
     * 初始化
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/initYZCXSystem")
    @ResponseBody
    public void initYZCXSystem() throws IOException, ParseException {
        List<YZCXSearchParam> initDateList=  LdgDateUtil.getQianyinianStartUntilBeforeMonth();
        //List<YZCXSearchParam> initDateList=  LdgDateUtil.getMonthJiangeByNum(1);
        //日归档
        initDateList.forEach(it->{
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
        initDateList.forEach(it->{
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
     * 初始化一个月中没有初始化的，处理一月中，哪天没有日归档的
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/initYZCXMonthSystem")
    @ResponseBody
    public void initYZCXMonthSystem() throws Exception {
        List<YZCXSearchParam> initDateList=  LdgDateUtil.getStartAndEndTimeByTiQianYueNum(1);
        /*YZCXSearchParam searchParam=new YZCXSearchParam();
        searchParam.setStart(initDateList.get(0).getStart());
        searchParam.setEnd(initDateList.get(initDateList.size()-1).getEnd());
        List<YZCXSearchParam> existDays=yzcXscheduleService.getExistDaysFromGuiDangDays(searchParam);
        Map<Date, Date> existDaysMap = existDays.stream().collect(Collectors.toMap(YZCXSearchParam::getStart, YZCXSearchParam::getStart));
        List<YZCXSearchParam> initDate = initDateList.stream().filter(item -> {
            Date date = existDaysMap.get(item.getStart());
            System.out.println(item+"   "+date);
            if (date != null) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());*/
        //日归档
        initDateList.forEach(it->{
            System.out.println(it);
            try {
                daysGuiDang(it);
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
        System.out.println(msg);
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
