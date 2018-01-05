package com.sankaheyi.controller;

import com.ldg.api.sms.LdgMD5Util;
import com.yzcx.api.util.LdgDateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/sankaheyi")
public class SKHYHandlerController {
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,String jobnum) throws Exception {
        StringBuilder jiami=new StringBuilder();
        jiami.append(LdgDateUtil.getYyyy_mm_ddDateStr(new Date())).append(jobnum).append("sdey");
        String sign=LdgMD5Util.MD5(jiami.toString(),32).toUpperCase();
        return "redirect:http://192.192.192.253:8886//SDEY/ReceiveInfo.htm?sign="+sign+"&jobnum="+jobnum;
    }

    public static void main(String[] args) throws Exception {
        String s = LdgMD5Util.MD5("1999-01-01123sdey", 32);
        System.out.println(s);
        String s1 = LdgMD5Util.MD5("1999-01-01123sdey", 16);
        System.out.println(s1);
    }
}
