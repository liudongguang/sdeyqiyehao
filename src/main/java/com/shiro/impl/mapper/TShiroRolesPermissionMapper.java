package com.shiro.impl.mapper;

import com.shiro.api.po.TShiroPermission;
import com.shiro.api.po.TShiroRolesPermission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TShiroRolesPermissionMapper extends Mapper<TShiroRolesPermission> {
    int deleteByRoleID(Integer roleID);

    void batchInsertRolePermissions(List<TShiroRolesPermission> rmList);

    /**
     * 获取已存在的权限根据角色id
     * @param roleid
     * @return
     */
    List<TShiroPermission> getOwnPermissionByRoleID(Integer roleid);

    /**
     * 根据权限id删除
     * @param permissionid
     * @return
     */
    int deleteByPermissionID(Integer permissionid);
}