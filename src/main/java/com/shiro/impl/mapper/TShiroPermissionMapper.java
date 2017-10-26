package com.shiro.impl.mapper;

import com.shiro.api.po.TShiroPermission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TShiroPermissionMapper extends Mapper<TShiroPermission> {

    List<TShiroPermission> getPermissionPageInfo();

    Integer selectPermissionNameByName(TShiroPermission param);

    List<TShiroPermission> getPermissionByRoleID(Integer uid);
}