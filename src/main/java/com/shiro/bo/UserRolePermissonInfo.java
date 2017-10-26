package com.shiro.bo;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/7/31.
 */
public class UserRolePermissonInfo {
    private Integer userid;
    private String username;
    private List<UserRolePermissonInfo_role> roleList;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserRolePermissonInfo_role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRolePermissonInfo_role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserRolePermissonInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
