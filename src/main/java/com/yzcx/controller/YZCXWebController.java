package com.yzcx.controller;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.service.YZCXSearchService;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping(value = "/webyzcx")
public class YZCXWebController {
    @Autowired
    private YZCXSearchService yzcxSearchService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, YZCXSearchParam param) throws IOException, ParseException {
        QyglVo qygl = yzcxSearchService.getQygl_ri();
        request.setAttribute(YZCXConstant.obj, qygl);
        return "/yzcx/index.jsp";
    }

    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public HighchartsConfig_arr indexChart(HttpServletRequest request) throws IOException, ParseException {
        HighchartsConfig_arr mzChart = yzcxSearchService.getQygl_riChart(1);
        return mzChart;
    }

    @RequestMapping(value = "/menzhen")
    public String menzhen(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        QyglVo qygl = yzcxSearchService.getQygl_ri();
        request.setAttribute(YZCXConstant.obj, qygl);
        return "/yzcx/menzhen.jsp";
    }
    @RequestMapping(value = "/menzhenChart")
    @ResponseBody
    public HighchartsConfig_arr menzhen(HttpServletRequest request) throws IOException, ParseException {
        HighchartsConfig_arr mzChart = yzcxSearchService.getQygl_riChart(1);
        return mzChart;
    }

}
