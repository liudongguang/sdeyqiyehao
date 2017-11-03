package com.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.ldg.api.constant.CommConstant;
import com.shiro.api.po.TShiroPermission;
import com.shiro.api.service.ShiroService;
import com.shiro.bo.LoginUser;
import com.weixin.vo.PageParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by LiuDongguang on 2017/11/2.
 */
@Controller
@RequestMapping(value = "/shirotest")
public class TestShiroController {
    @Autowired
    private ShiroService shiroService;
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, LoginUser loginUser) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            //token.setRememberMe(true);
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            request.setAttribute("message", "密码错误！");
            return "/error.jsp";
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            request.setAttribute("message", "未知的用户名！");
            return "/error.jsp";
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            request.setAttribute("message", "登录次数过多！");
            return "/error.jsp";
        }catch (LockedAccountException eae) {
            // 捕获错误登录过多的异常
            request.setAttribute("message", "帐户锁定！");
            return "/error.jsp";
        }
        subject.getSession().setAttribute("user",shiroService.findUserByUsername(loginUser.getUsername()));
        return "redirect:/index.jsp";
    }
    @RequestMapping(value = "/testQX")
    public String getPermissionPageInfo(HttpServletRequest request, PageParam pageParam) {
         ///
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.hasAllRoles(Arrays.asList("ddd")));
        ////
        return "/error.jsp";
    }
}
