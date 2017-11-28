package com.sankaheyi.controller;

import com.ldg.api.sms.LdgMD5Util;
import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.service.YZCXscheduleService;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.util.YZCXControllerUtil;
import com.yzcx.api.vo.YZCXHandlerData;
import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.FeiYongHuiZong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/sankaheyi")
public class SKHYHandlerController {
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,String jobnum) throws Exception {
        StringBuilder jiami=new StringBuilder();
        jiami.append(LdgDateUtil.getYyyy_mm_ddDateStr(new Date())).append(jobnum).append("sdey");
        String sign=LdgMD5Util.MD5(jiami.toString(),32).toUpperCase();
        return "redirect:http://192.192.192.253:808//SDEY/ReceiveInfo.htm?sign="+sign+"&jobnum="+jobnum;
    }

    public static void main(String[] args) throws Exception {
        String s = LdgMD5Util.MD5("1999-01-01123sdey", 32);
        System.out.println(s);
        String s1 = LdgMD5Util.MD5("1999-01-01123sdey", 16);
        System.out.println(s1);
    }
}
