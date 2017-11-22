package com.yzcx.controller;

import com.yzcx.api.service.YZCXFeiYongSearchService;
import com.yzcx.api.service.YZCXSearchService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.highchat.HighchartsConfig;
import com.yzcx.api.vo.highchat.HighchartsConfig_arr;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.column.HighchartsConfig_column;
import com.yzcx.api.vo.highchat.pie.HighchartsConfig_pie;
import com.yzcx.api.vo.yzcxdisplay.FeiYongHuiZong;
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
@RequestMapping(value = "/webyzcxFeiYong")
public class YZCXWebFeiYongController {
    @Autowired
    private YZCXFeiYongSearchService yzcxFeiYongSearchService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param= YZCXControllerUtil.getBeforeOneDay();
        FeiYongHuiZong fyzong=yzcxFeiYongSearchService.getIndexFeiYongZong(param);
        request.setAttribute(YZCXConstant.obj, fyzong);
        return "/yzcx/feiyong/index.jsp";
    }

    /**
     * 获取昨天的费用图表
     * @param request
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public Map<String,Object> indexChart(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param= YZCXControllerUtil.getBeforeOneDay();
        Map<String,Object> rs= yzcxFeiYongSearchService.getIndexChart(param);
        return rs;
    }

}
