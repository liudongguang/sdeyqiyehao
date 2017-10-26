package com.shiro.impl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldg.api.vo.PageParam;
import com.shiro.api.po.*;
import com.shiro.api.service.ShiroService;
import com.shiro.bo.TShiroUsersExt;
import com.shiro.bo.UserRolePermissonInfo;
import com.shiro.impl.mapper.*;
import com.shiro.util.PasswordHelper;
import com.shiro.util.ShiroAuthorizationHelper;
import com.shiro.vo.RoleAndPermission;
import com.shiro.vo.RoleAndPermissionList;
import com.shiro.vo.UserAndRole;
import com.shiro.vo.UserAndRoleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiuDongguang on 2017/7/24.
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private TShiroUsersMapper shiroUserDao;
    @Autowired
    private TShiroPermissionMapper permissionDao;
    @Autowired
    private TShiroRolesMapper roleDao;
    @Autowired
    private TShiroRolesPermissionMapper roleAndPermissionDao;
    @Autowired
    private TShiroUsersRolesMapper usersRoleDao;



    @Override
    public PageInfo<TShiroPermission> getPermissionPageInfo(PageParam pageParam) {
        PageInfo<TShiroPermission> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> permissionDao.getPermissionPageInfo());
        return pageInfo;
    }

    @Override
    public int savePermission(TShiroPermission param) {
        return permissionDao.insertSelective(param);
    }

    @Override
    public Integer selectPermissionNameByName(TShiroPermission param) {
        return permissionDao.selectPermissionNameByName(param);
    }

    @Override
    public int deletePermission(TShiroPermission param) {
        roleAndPermissionDao.deleteByPermissionID(param.getUid());
        return permissionDao.deleteByPrimaryKey(param.getUid());
    }

    /////////////////////////角色
    @Override
    public PageInfo<TShiroRoles> getRolePageInfo(PageParam pageParam) {
        PageInfo<TShiroRoles> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> roleDao.getRolePageInfo());
        return pageInfo;
    }

    @Override
    public List<TShiroRoles> getRoleList() {
        return roleDao.getRolePageInfo();
    }

    @Override
    public int saveRole(TShiroRoles param) {
        return roleDao.insertSelective(param);
    }

    @Override
    public Integer selectRoleNameByName(TShiroRoles param) {
        return roleDao.selectRoleNameByName(param);
    }

    @Override
    public int deleteRole(TShiroRoles param) {
        roleAndPermissionDao.deleteByRoleID(param.getUid());
        usersRoleDao.deleteByRoleID(param.getUid());
        return roleDao.deleteByPrimaryKey(param.getUid());
    }

    @Override
    public List<TShiroPermission> getPermissionList() {
        return permissionDao.getPermissionPageInfo();
    }

    ////////////////////////////角色与权限
    @Override
    public void saveRoleAndPermission(RoleAndPermission param) {
        String permisseions = param.getPermissionIDS();
        if (permisseions != null && permisseions.length() != 0) {
            int delNum = roleAndPermissionDao.deleteByRoleID(param.getRoleID());
            List<TShiroRolesPermission> rmList = Arrays.asList(param.getPermissionIDS().split(",")).stream().map(item -> {
                TShiroRolesPermission rm = new TShiroRolesPermission();
                rm.setRoleid(param.getRoleID());
                rm.setPermissionid(Integer.valueOf(item));
                return rm;
            }).collect(Collectors.toList());
            roleAndPermissionDao.batchInsertRolePermissions(rmList);
            ShiroAuthorizationHelper.clearAuthorizationInfo();//清除缓存
        }
    }

    @Override
    public PageInfo<RoleAndPermissionList> getRoleAndPermissionPageInfo(PageParam pageParam) {
       // PageInfo<RoleAndPermissionList> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> roleDao.getRoleAndPermissionPageInfo());
        PageInfo<RoleAndPermissionList> roles = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> roleDao.getRoleAndPermissionListPageInfo());
        roles.getList().stream().forEach(item->{
            item.setPermissions(permissionDao.getPermissionByRoleID(item.getUid()));
        });
        return roles;
    }

    @Override
    public List<TShiroPermission> getOwnPermissionByRoleID(Integer roleid) {
        return roleAndPermissionDao.getOwnPermissionByRoleID(roleid);
    }

    ///////////////////用户
    @Override
    public int addUser(TShiroUsersExt user) {
        PasswordHelper phr = new PasswordHelper();
        phr.encryptPassword(user); //密码处理
        user.setCreatetime(new Date());
        return shiroUserDao.insertUser(user);
    }

    @Override
    public TShiroUsersExt findUserByUsername(String username) {
        return shiroUserDao.findUserByUsername(username);
    }
    @Override
    public Integer selectUserNameByName(TShiroUsers param) {
        return shiroUserDao.selectUserNameByName(param);
    }

    @Override
    public int deleteUser(TShiroUsers param) {
        usersRoleDao.deleteByUserID(param.getUid());
        return shiroUserDao.deleteByPrimaryKey(param.getUid());
    }

    //////////////////////////////////////用户与角色
    @Override
    public PageInfo<UserAndRoleList> getUserAndRolePageInfo(PageParam pageParam) {
        PageInfo<UserAndRoleList> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> shiroUserDao.getUserAndRoleListPageInfo());
        pageInfo.getList().forEach(item->{
             item.setRoles(roleDao.selectRoleNameByUserID(item.getUid()));
        });
        return pageInfo;
    }


    @Override
    public int saveUserAndRole(UserAndRole param) {
        String roleids = param.getRoleIDS();
        if (roleids != null && roleids.length() != 0) {
            int delNum = usersRoleDao.deleteByUserID(param.getUserID());
            List<TShiroUsersRoles> rmList = Arrays.asList(param.getRoleIDS().split(",")).stream().map(item -> {
                TShiroUsersRoles rm = new TShiroUsersRoles();
                rm.setRoleid(Integer.valueOf(item));
                rm.setUserid(param.getUserID());
                return rm;
            }).collect(Collectors.toList());
            usersRoleDao.batchInsertUserRoles(rmList);
            ShiroAuthorizationHelper.clearAuthorizationInfo();//清除缓存
        }
        return 0;
    }

    @Override
    public List<TShiroRoles> getOwnRoleByUserID(Integer userid) {
        return usersRoleDao.getOwnRoleByUserID(userid);
    }

    @Override
    public UserRolePermissonInfo selectRoleAndPermisssionByUserName(String username) {
        return shiroUserDao.selectRoleAndPermisssionByUserName(username);
    }
}
