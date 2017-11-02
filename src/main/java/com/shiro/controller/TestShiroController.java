package com.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.ldg.api.constant.CommConstant;
import com.shiro.api.po.TShiroPermission;
import com.weixin.vo.PageParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LiuDongguang on 2017/11/2.
 */
@Controller
@RequestMapping(value = "/shirotest")
public class TestShiroController {
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, PageParam pageParam) {
        System.out.println("login.........");
        return "/index.jsp";
    }

}
