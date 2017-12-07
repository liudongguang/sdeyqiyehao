package com.yzcx.controller;

import com.yzcx.api.service.YZCXShoushuSearchService;
import com.yzcx.api.service.YZCXZhuYuanSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.SsxxIndex;
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
@RequestMapping(value = "/webyzcxSsxx")
public class YZCXWebSSXXController {
    @Autowired
    private YZCXShoushuSearchService yzcxShoushuSearchService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param= YZCXControllerUtil.getBeforeDayByNum(0);
        SsxxIndex zyxxIndex=yzcxShoushuSearchService.getIndexData(param);
        request.setAttribute(YZCXConstant.obj, zyxxIndex);
        return "/yzcx/shoushu/index.jsp";
    }

    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public Map<String,Object> indexChart(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getSearchParamForDay();
        Map<String,Object> rs= yzcxShoushuSearchService.getIndexChart(param);
        return rs;
    }

    @RequestMapping(value = "/shoushuList")
    @ResponseBody
    public Map<String,Object> shoushuList(HttpServletRequest request) throws IOException, ParseException {

        return null;
    }
}
