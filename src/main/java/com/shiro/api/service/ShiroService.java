package com.shiro.api.service;

import com.github.pagehelper.PageInfo;
import com.ldg.api.vo.PageParam;
import com.shiro.api.po.TShiroPermission;
import com.shiro.api.po.TShiroRoles;
import com.shiro.api.po.TShiroUsers;
import com.shiro.bo.TShiroUsersExt;
import com.shiro.bo.UserRolePermissonInfo;
import com.shiro.vo.RoleAndPermission;
import com.shiro.vo.RoleAndPermissionList;
import com.shiro.vo.UserAndRole;
import com.shiro.vo.UserAndRoleList;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/7/24.
 */
public interface ShiroService {
    /**
     * 增加用户
     * @param user
     * @return
     */
    int addUser(TShiroUsersExt user);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    TShiroUsersExt findUserByUsername(String username);

    /**
     * 获取权限列表
     * @param pageParam
     * @return
     */
    PageInfo<TShiroPermission> getPermissionPageInfo(PageParam pageParam);

    /**
     * 保存权限
     * @param param
     * @return
     */
    int savePermission(TShiroPermission param);

    /**
     * 查询权限名是否存在
     * @param param
     * @return
     */
    Integer selectPermissionNameByName(TShiroPermission param);

    /**
     * 删除权限
     * @param param
     * @return
     */
    int deletePermission(TShiroPermission param);
    /**
     * 获取拥有的权限根据角色id
     * @param roleid
     * @return
     */
    List<TShiroPermission> getOwnPermissionByRoleID(Integer roleid);
    /*********************************************
     * 获取角色列表
     * @param pageParam
     * @return
     */
    PageInfo<TShiroRoles> getRolePageInfo(PageParam pageParam);

    /**
     * 保存角色
     * @param param
     * @return
     */
    int saveRole(TShiroRoles param);

    /**
     * 角色名是否存在
     * @param param
     * @return
     */
    Integer selectRoleNameByName(TShiroRoles param);

    /**
     * 删除角色
     * @param param
     * @return
     */
    int deleteRole(TShiroRoles param);

    /**
     * 获取所有权限
     * @return
     */
    List<TShiroPermission> getPermissionList();

    /**
     * 保存角色与其权限
     * @param param
     */
    void saveRoleAndPermission(RoleAndPermission param);

    /**
     * 获取角色与权限
     * @param pageParam
     * @return
     */
    PageInfo<RoleAndPermissionList> getRoleAndPermissionPageInfo(PageParam pageParam);

    /**
     * 获取用户与所拥有角色
     * @param pageParam
     * @return
     */
    PageInfo<UserAndRoleList> getUserAndRolePageInfo(PageParam pageParam);


    /**
     * 用户名是否存在
     * @param param
     * @return
     */
    Integer selectUserNameByName(TShiroUsers param);

    /**
     * 删除用户
     * @param param
     * @return
     */
    int deleteUser(TShiroUsers param);

    /**
     * 获取角色列表
     * @return
     */
    List<TShiroRoles> getRoleList();

    /**
     * 保存用户及其角色
     * @param param
     * @return
     */
    int saveUserAndRole(UserAndRole param);

    /**
     *
     * @param userid
     * @return
     */
    List<TShiroRoles> getOwnRoleByUserID(Integer userid);

    /**
     * 根据用户名获取角色及其权限信息
     * @param username
     * @return
     */
    UserRolePermissonInfo selectRoleAndPermisssionByUserName(String username);
}
