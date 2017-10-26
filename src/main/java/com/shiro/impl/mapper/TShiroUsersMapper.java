package com.shiro.impl.mapper;

import com.shiro.api.po.TShiroUsers;
import com.shiro.bo.TShiroUsersExt;
import com.shiro.bo.UserRolePermissonInfo;
import com.shiro.vo.UserAndRoleList;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TShiroUsersMapper extends Mapper<TShiroUsers> {
    int insertUser(TShiroUsersExt user);

    TShiroUsersExt findUserByUsername(String username);

    Integer selectUserNameByName(TShiroUsers param);

    List<UserAndRoleList> getUserAndRolePageInfo();

    UserRolePermissonInfo selectRoleAndPermisssionByUserName(String username);

    List<UserAndRoleList> getUserAndRoleListPageInfo();


}