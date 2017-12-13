package com.yzcx.controller;

import com.yzcx.api.bo.PageParam;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.pageinfo.SSXXDisplayModle;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.pageinfo.YJHLXXDisplayModle;
import com.yzcx.impl.service.handler.YzcxHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping(value = "/webyzcxyjhlxx")
public class YZCXWebYJHLController {
    @RequestMapping(value = "/pageinfo")
    @ResponseBody
    public YJHLXXDisplayModle shoushuList(HttpServletRequest request,PageParam pageParam) throws IOException, ParseException {
        YZCXSearchParam param = YZCXControllerUtil.getSearchParamForDay();
        final YJHLXXDisplayModle yjhlPageInfo = YzcxHttpRequest.getYJHLPageInfo(param, pageParam);
        return yjhlPageInfo;
    }
}
