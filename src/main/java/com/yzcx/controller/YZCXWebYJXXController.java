package com.yzcx.controller;

import com.yzcx.api.service.YZCXYiJiSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.YiJiMonthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping(value = "/webyzcxyijixx")
public class YZCXWebYJXXController {
    @Autowired
    private YZCXYiJiSearchService yzcxYiJiSearchService;

    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public Map<String,Object> indexChart(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getSearchParamForDay();
        Map<String,Object> rs= yzcxYiJiSearchService.getIndexChart(param);
        return rs;
    }
    @RequestMapping(value = "/monthChartPage")
    public String monthChartPage(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForThisMonth(param);
        YiJiMonthData yiJiMonthData=yzcxYiJiSearchService.getMonthChartData(yzcxSearchParam);
        request.setAttribute(YZCXConstant.obj, yiJiMonthData);
        return "/yzcx/yiji/month.jsp";
    }

    @RequestMapping(value = "/monthChartPage_chart")
    @ResponseBody
    public Map<String,Object> monthChartPage_chart(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForThisMonth(param);
        Map<String,Object> rs= yzcxYiJiSearchService.monthChartPage_chart(yzcxSearchParam);
        return rs;
    }
}
