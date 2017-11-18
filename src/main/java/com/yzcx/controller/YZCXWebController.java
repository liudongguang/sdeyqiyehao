package com.yzcx.controller;

import com.yzcx.api.service.YZCXSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.column.HighchartsConfig_column;
import com.yzcx.api.vo.highchat.pie.HighchartsConfig_pie;
import com.yzcx.api.vo.yzcxdisplay.Menzhen_Month_Yuyue;
import com.yzcx.api.vo.yzcxdisplay.QyglVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        HighchartsConfig jibingChart=yzcxSearchService.getJiBing_riChart();
        rs.put("menzhen",mzChart);
        rs.put("yuyue",yuyueChart);
        rs.put("jibingChart",jibingChart);
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
        YZCXSearchParam cparam = YZCXControllerUtil.getSearchParamForMonth(param);
        QyglVo qygl = yzcxSearchService.getQygl_month(cparam);
        request.setAttribute(YZCXConstant.obj, qygl);
        return "/yzcx/menzhen_yue.jsp";
    }

    /**
     * 月图表查询
     * @param request
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/menzhen_yueChart")
    @ResponseBody
    public Map<String,Object> menzhen_yueChart(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForMonth(param);
        Map<String,Object> rs=new HashMap<>();
        HighchartsConfig menzhenChart=yzcxSearchService.getQygl_yueChart(yzcxSearchParam);
        rs.put("menzhenChart",menzhenChart);
        HighchartsConfig tongqimenzhenChart =yzcxSearchService.getQygl_yueChart_tongqimenzhen(yzcxSearchParam);
        rs.put("tongqimenzhenChart",tongqimenzhenChart);
        HighchartsConfig jibingmenzhenChart=yzcxSearchService.getQygl_yueChart_jibing(yzcxSearchParam);
        rs.put("jibingChart",jibingmenzhenChart);
        return rs;
    }
    /**
     *  跳转月的门诊预约量页面
     */
    @RequestMapping(value = "/menzhen_yuyue_yue")
    public String menzhen_yuyue_yue(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam cparam = YZCXControllerUtil.getSearchParamForMonth(param);
        Menzhen_Month_Yuyue menzhen_month_yuyue = yzcxSearchService.getMenzhen_Month_Yuyue_month(cparam);
        request.setAttribute(YZCXConstant.obj, menzhen_month_yuyue);
        return "/yzcx/menzhen_yuyue_yue.jsp";
    }

    @RequestMapping(value = "/menzhen_yuyue_yueChart")
    @ResponseBody
    public Map<String,Object> menzhen_yuyue_yueChart(HttpServletRequest request,YZCXSearchParam param,Menzhen_Month_Yuyue menzhen_month_yuyue) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForMonth(param);
        Map<String,Object> rs=new HashMap<>();
        HighchartsConfig_pie yuyueMenzhenZhanbiMonthChart=yzcxSearchService.getMenzhenYuyueZhanbi_yueChart(menzhen_month_yuyue);
        rs.put("yuyueMenzhenZhanbiMonthChart",yuyueMenzhenZhanbiMonthChart);
        HighchartsConfig menzhenYYChart=yzcxSearchService.getMenzhenYuyue_yueChart(yzcxSearchParam);
        rs.put("menzhenYYChart",menzhenYYChart);
        HighchartsConfig tongqiyuyueChart =yzcxSearchService.getTongqiyuyueChart(yzcxSearchParam);
        rs.put("tongqiyuyueChart",tongqiyuyueChart);
        return rs;
    }


    @RequestMapping(value = "/menzhen_year")
    public String menzhen_year(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        return "/yzcx/menzhen_year.jsp";
    }
    @RequestMapping(value = "/menzhen_year_chart")
    @ResponseBody
    public Map<String,Object> menzhen_year_chart(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        Map<String,Object> rs=new HashMap<>();
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForYear(param);
        HighchartsConfig_column menzhe_year =yzcxSearchService.getMenzhen_year_chart(yzcxSearchParam);
        rs.put("menzhe_year",menzhe_year);
        return rs;
    }
}
