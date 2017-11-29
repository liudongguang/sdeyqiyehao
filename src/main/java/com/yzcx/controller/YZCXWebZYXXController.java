package com.yzcx.controller;

import com.yzcx.api.service.YZCXZhuYuanSearchService;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

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



}
