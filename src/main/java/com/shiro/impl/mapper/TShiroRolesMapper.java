package com.shiro.impl.mapper;

import com.shiro.api.po.TShiroRoles;
import com.shiro.vo.RoleAndPermissionList;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TShiroRolesMapper extends Mapper<TShiroRoles> {
    List<TShiroRoles> getRolePageInfo();

    Integer selectRoleNameByName(TShiroRoles param);

    List<RoleAndPermissionList> getRoleAndPermissionPageInfo();

    List<RoleAndPermissionList> getRoleAndPermissionListPageInfo();

    List<TShiroRoles> selectRoleNameByUserID(Integer uid);
}