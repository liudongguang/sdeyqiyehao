package com.yzcx.controller;

import com.yzcx.api.service.YZCXHuanZhenSearchService;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping(value = "/webyzcxHuanzhexx")
public class YZCXWebHuanZhexxController {
    @Autowired
    private YZCXHuanZhenSearchService yzcxHuanZhenSearchService;

    @RequestMapping(value = "/indexChart")
    @ResponseBody
    public Map<String,Object> indexChart(HttpServletRequest request) throws IOException, ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getSearchParamForDay();
        Map<String,Object> rs= yzcxHuanZhenSearchService.getIndexChart(param);
        return rs;
    }
}
