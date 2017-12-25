package com.yzcx.controller;

import com.yzcx.api.service.YZCXFeiYongSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.FeiYongHuiZong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
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
    /**
     *  跳转月费用页面
     */
    @RequestMapping(value = "/feiyong_yue_page")
    public String feiyong_yue_page(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam cparam = YZCXControllerUtil.getSearchParamForThisMonth(param);
        FeiYongHuiZong feiYongHuiZong = yzcxFeiYongSearchService.getFeiYong_Month_pagedata(cparam);
        request.setAttribute(YZCXConstant.obj, feiYongHuiZong);
        return "/yzcx/feiyong/feiyong_yue.jsp";
    }

    /**
     * 月费用图表
     * @param request
     * @param param
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/feiyong_yue_chart")
    @ResponseBody
    public Map<String,Object> feiyong_yue_chart(HttpServletRequest request,YZCXSearchParam param) throws IOException, ParseException {
        YZCXSearchParam yzcxSearchParam = YZCXControllerUtil.getSearchParamForThisMonth(param);
        Map<String,Object> rs= yzcxFeiYongSearchService.getfeiyong_yue(yzcxSearchParam);
        return rs;
    }

}
