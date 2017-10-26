package com.shiro.vo;

import com.shiro.api.po.TShiroPermission;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/7/27.
 */
public class RoleAndPermissionList {
    private Integer uid;
    private String rolename;
    private String description;
    private List<TShiroPermission> permissions;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TShiroPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TShiroPermission> permissions) {
        this.permissions = permissions;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "RoleAndPermissionList{" +
                "uid=" + uid +
                ", rolename='" + rolename + '\'' +
                ", description='" + description + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
