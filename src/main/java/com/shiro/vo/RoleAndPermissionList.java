package com.shiro.vo;

import com.shiro.api.po.TShiroPermission;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/7/27.
 */
public class RoleAndPermissionList {
    private Integer roleid;
    private String rolename;
    private String roledescription;
    private List<TShiroPermission> permissions;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledescription() {
        return roledescription;
    }

    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription;
    }

    public List<TShiroPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TShiroPermission> permissions) {
        this.permissions = permissions;
    }

    public Integer getRoleid() {

        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}
