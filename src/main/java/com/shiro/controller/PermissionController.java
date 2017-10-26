package com.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.ldg.api.constant.CommConstant;
import com.ldg.api.vo.PageParam;
import com.ldg.api.vo.ResultMsg;
import com.shiro.api.po.TShiroPermission;
import com.shiro.api.po.TShiroRoles;
import com.shiro.api.po.TShiroUsers;
import com.shiro.api.service.ShiroService;
import com.shiro.bo.TShiroUsersExt;
import com.shiro.vo.RoleAndPermission;
import com.shiro.vo.RoleAndPermissionList;
import com.shiro.vo.UserAndRole;
import com.shiro.vo.UserAndRoleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuDongguang on 2017/7/24.
 */
@Controller
@RequestMapping(value = "/permission_shiro")
public class PermissionController {

   @Autowired
   private ShiroService shiroService;
    /***********************************
     * 获取权限列表
     *
     * @return
     */
    @RequestMapping(value = "/getPermissionPageInfo")
    public String getPermissionPageInfo(HttpServletRequest request, PageParam pageParam) {
        PageInfo<TShiroPermission> permissions = shiroService.getPermissionPageInfo(pageParam);
        request.setAttribute(CommConstant.PAGE_REQUEST_ATTR, permissions);
        return "/pajaxapimain/permissions/permission/index.jsp";
    }

    /**
     * 保存权限
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/savePermission")
    public String savePermission(HttpServletRequest request, TShiroPermission param) {
        int i=shiroService.savePermission(param);
        return "/permission_shiro/getPermissionPageInfo";
    }

    /**
     * 权限名是否存在
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/checkPermissionName")
    @ResponseBody
    public ResultMsg checkPermissionName(HttpServletRequest request, TShiroPermission param) {
        ResultMsg rs=new ResultMsg();
        Integer id=shiroService.selectPermissionNameByName(param);
        if(id!=null){
            rs.setErrcode(1);
            rs.setErrmsg("权限名已存在");
        }
        return rs;
    }


    /**
     * 删除权限
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/deletePermission")
    public String deletePermission(HttpServletRequest request, TShiroPermission param) {
        int i=shiroService.deletePermission(param);
        return "/permission_shiro/getPermissionPageInfo";
    }
    /*******************角色*************************/
    /**
     * 获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/getRolePageInfo")
    public String getRolePageInfo(HttpServletRequest request, PageParam pageParam) {
        PageInfo<RoleAndPermissionList> permissions = shiroService.getRoleAndPermissionPageInfo(pageParam);
        request.setAttribute(CommConstant.PAGE_REQUEST_ATTR, permissions);
        return "/pajaxapimain/permissions/role/index.jsp";
    }

    /**
     * 保存角色
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/saveRole")
    public String saveRole(HttpServletRequest request, TShiroRoles param) {
        int i=shiroService.saveRole(param);
        return "/permission_shiro/getRolePageInfo";
    }

    /**
     * 角色名是否存在
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/checkRoleName")
    @ResponseBody
    public ResultMsg checkRoleName(HttpServletRequest request, TShiroRoles param) {
        ResultMsg rs=new ResultMsg();
        Integer id=shiroService.selectRoleNameByName(param);
        if(id!=null){
            rs.setErrcode(1);
            rs.setErrmsg("角色名已存在");
        }
        return rs;
    }


    /**
     * 删除角色
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/deleteRole")
    public String deleteRole(HttpServletRequest request, TShiroRoles param) {
        int i=shiroService.deleteRole(param);
        return "/permission_shiro/getRolePageInfo";
    }

    /**
     * 打开分配权限页面
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/distributionPermission")
    public String distributionPermission(HttpServletRequest request, TShiroRoles param) {
        return "/pajaxapimain/permissions/role/distribution_permission.jsp";
    }

    /**
     * 获取权限列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPermissionList")
    @ResponseBody
    public  Map<String,Object> getPermissionList(HttpServletRequest request, Integer roleid) {
        ///////////获取已拥有的权限
        Map<String,Object> rs=new HashMap<>();
        rs.put("allPermission",shiroService.getPermissionList());
        rs.put("ownPermission",shiroService.getOwnPermissionByRoleID(roleid));//根据roleid获取角色拥有的权限
        return rs;
    }

    /**
     * 保存角色与其权限
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/saveRoleAndPermission")
    public String saveRoleAndPermission(HttpServletRequest request, RoleAndPermission param) {
        shiroService.saveRoleAndPermission(param);
        return "/permission_shiro/getRolePageInfo";
    }
    /*******************用户*************************/
    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping(value = "/getUserPageInfo")
    public String getUserPageInfo(HttpServletRequest request, PageParam pageParam) {
        PageInfo<UserAndRoleList> userRole = shiroService.getUserAndRolePageInfo(pageParam);
        request.setAttribute(CommConstant.PAGE_REQUEST_ATTR, userRole);
        return "/pajaxapimain/permissions/user/index.jsp";
    }

    /**
     * 保存用户
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/saveUser")
    public String saveRole(HttpServletRequest request, TShiroUsersExt param) {
        int i=shiroService.addUser(param);
        return "/permission_shiro/getUserPageInfo";
    }

    /**
     * 用户名是否存在
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/checkUserName")
    @ResponseBody
    public ResultMsg checkUserName(HttpServletRequest request, TShiroUsers param) {
        ResultMsg rs=new ResultMsg();
        Integer id=shiroService.selectUserNameByName(param);
        if(id!=null){
            rs.setErrcode(1);
            rs.setErrmsg("用户名已存在");
        }
        return rs;
    }


    /**
     * 删除用户
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/deleteUser")
    public String deleteUser(HttpServletRequest request, TShiroUsers param) {
        int i=shiroService.deleteUser(param);
        return "/permission_shiro/getUserPageInfo";
    }

    /**
     * 打开分配角色页面
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/distributionRole")
    public String distributionRole(HttpServletRequest request, TShiroRoles param) {
        return "/pajaxapimain/permissions/user/distribution_role.jsp";
    }

    /**
     * 获取角色列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRoleList")
    @ResponseBody
    public Map<String,Object> getRoleList(HttpServletRequest request, Integer userid) {
        Map<String,Object> rs=new HashMap<>();
        rs.put("allRole",shiroService.getRoleList());
        rs.put("ownRole",shiroService.getOwnRoleByUserID(userid));//根据userid获取角色拥有的角色
        return rs;
    }

    /**
     * 保存用户与其角色
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/saveUserAndRole")
    public String saveUserAndRole(HttpServletRequest request, UserAndRole param) {
        shiroService.saveUserAndRole(param);
        return "/permission_shiro/getUserPageInfo";
    }
}
