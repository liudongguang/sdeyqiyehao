package com.shiro.bo;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/7/31.
 */
public class UserRolePermissonInfo_role {
    private Integer roleid;
    private String rolename;
    private List<UserRolePermissonInfo_permission> permissionList;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<UserRolePermissonInfo_permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<UserRolePermissonInfo_permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "UserRolePermissonInfo_role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}
