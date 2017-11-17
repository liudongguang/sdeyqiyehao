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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/webyzcx")
public class YZCXWebController {
    @Autowired
    private YZCXSearchService yzcxSearchService;

    /**
     * 进入医院概况页面
     * @param request
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, YZCXSearchParam param) throws IOException, ParseException {
        QyglVo qygl = yzcxSearchService.getQygl_ri();
        request.setAttribute(YZCXConstant.obj, qygl);
        return "/yzcx/index.jsp";
    }

    /**
     * 医院概况页面  图标数据
     * @param request
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public HighchartsConfig_arr indexChart(HttpServletRequest request) throws IOException, ParseException {
        HighchartsConfig_arr mzChart = yzcxSearchService.getQygl_riChart(1);
        return mzChart;
    }

    /**
     * 进入门诊页面
     * @param request
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/menzhen")
    public String menzhen(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        QyglVo qygl = yzcxSearchService.getQygl_ri();
        request.setAttribute(YZCXConstant.obj, qygl);
        return "/yzcx/menzhen.jsp";
    }

    /**
     * 获取门诊页面的图表数据
     * @param request
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/menzhenChart")
    @ResponseBody
    public Map<String,Object> menzhen(HttpServletRequest request) throws IOException, ParseException {
        Map<String,Object> rs=new HashMap<>();
        HighchartsConfig_arr mzChart = yzcxSearchService.getQygl_riChart(1);
        HighchartsConfig yuyueChart=yzcxSearchService.getYuyue_riChart();
        rs.put("menzhen",mzChart);
        rs.put("yuyue",yuyueChart);
        return rs;
    }

    /**
     * 跳转月的门诊量页面
     * @param request
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/menzhen_yue")
    public String menzhen_yue(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        QyglVo qygl = yzcxSearchService.getQygl_month(param);
        request.setAttribute(YZCXConstant.obj, qygl);
        return "/yzcx/menzhen_yue.jsp";
    }

    @RequestMapping(value = "/menzhen_yueChart")
    @ResponseBody
    public Map<String,Object> menzhen_yueChart(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        Map<String,Object> rs=new HashMap<>();
        HighchartsConfig menzhenChart=yzcxSearchService.getQygl_yueChart(param);
        rs.put("menzhenChart",menzhenChart);
        return rs;
    }
    /**
     *  跳转月的门诊预约量页面
     */
    @RequestMapping(value = "/menzhen_yuyue_yue")
    public String menzhen_yuyue_yue(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {

        return "/yzcx/menzhen_yuyue_yue.jsp";
    }
}
