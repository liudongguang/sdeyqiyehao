package com.shiro.impl.mapper;

import com.shiro.api.po.TShiroRoles;
import com.shiro.api.po.TShiroUsersRoles;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TShiroUsersRolesMapper extends Mapper<TShiroUsersRoles> {
    int deleteByUserID(Integer userID);

    void batchInsertUserRoles(List<TShiroUsersRoles> rmList);

    /**
     * 根据用户id获取拥有的角色
     * @param userid
     * @return
     */
    List<TShiroRoles> getOwnRoleByUserID(Integer userid);
   //根据角色id删除信息
    int deleteByRoleID(Integer roleid);
}