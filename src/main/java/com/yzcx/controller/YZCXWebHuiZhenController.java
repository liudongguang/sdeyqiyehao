package com.yzcx.controller;

import com.yzcx.api.service.YZCXHuiZhenSearchService;
import com.yzcx.api.service.YZCXZhuYuanSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.HzxxIndex;
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
@RequestMapping(value = "/webyzcxHuizhen")
public class YZCXWebHuiZhenController {
    @Autowired
    private YZCXHuiZhenSearchService yzcxHuiZhenSearchService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getBeforeDayByNum(0);
        HzxxIndex indexHuiZhenForDay = yzcxHuiZhenSearchService.getIndexHuiZhenForDay(param);
        request.setAttribute(YZCXConstant.obj, indexHuiZhenForDay);
        return "/yzcx/huizhen/index.jsp";
    }

    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public Map<String, Object> indexChart(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getBeforeDayByNum(0);
        Map<String, Object> rs = yzcxHuiZhenSearchService.getIndexChart(param);
        return rs;
    }

    @RequestMapping(value = "/monthChartPage")
    public String monthChartPage(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForThisMonth(param);
        request.setAttribute(YZCXConstant.obj, yzcxSearchParam);
        return "/yzcx/huizhen/month.jsp";
    }


    @RequestMapping(value = "/monthChart")
    @ResponseBody
    public Map<String, Object> monthChart(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getBeforeDayByNum(0);
        Map<String, Object> rs = yzcxHuiZhenSearchService.getMonthChart(param);
        return rs;
    }


}
