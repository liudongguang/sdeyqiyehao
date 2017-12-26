package com.yzcx.controller;

import com.yzcx.api.service.YZCXZhuYuanSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping(value = "/webyzcxZyxx")
public class YZCXWebZYXXController {
    @Autowired
    private YZCXZhuYuanSearchService yzcxZhuYuanSearchService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param= YZCXControllerUtil.getBeforeDayByNum(0);
        ZyxxIndex zyxxIndex=yzcxZhuYuanSearchService.getIndexZhuYuanForDay(param);
        request.setAttribute(YZCXConstant.obj, zyxxIndex);
        return "/yzcx/zhuyuan/index.jsp";
    }

    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public Map<String,Object> indexChart(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param= YZCXControllerUtil.getBeforeDayByNum(0);
        Map<String,Object> rs= yzcxZhuYuanSearchService.getIndexChart(param);
        return rs;
    }


    @RequestMapping(value = "/zhuyuan_yue_page")
    public String zhuyuan_yue_page(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForThisMonth(param);
        ZyxxIndex zyxxIndex=yzcxZhuYuanSearchService.getIndexZhuYuanForYue(yzcxSearchParam);
        request.setAttribute(YZCXConstant.obj, zyxxIndex);
        return "/yzcx/zhuyuan/zy_month.jsp";
    }

    @RequestMapping(value = "/zhuyuan_yue_chart")
    @ResponseBody
    public Map<String,Object> zhuyuan_yue_chart(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForThisMonth(param);
        Map<String,Object> rs= yzcxZhuYuanSearchService.zhuyuan_yue_chart(yzcxSearchParam);
        return rs;
    }


    @RequestMapping(value = "/zhuyuan_year")
    public String zhuyuan_year(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForYear(param);
        request.setAttribute(YZCXConstant.obj, yzcxSearchParam);
        return "/yzcx/zhuyuan/zy_year.jsp";
    }
    @RequestMapping(value = "/zhuyuan_year_chart")
    @ResponseBody
    public Map<String,Object> zhuyuan_year_chart(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForYear(param);
        Map<String,Object> rs= yzcxZhuYuanSearchService.zhuyuan_year(yzcxSearchParam);
        return rs;
    }


}
