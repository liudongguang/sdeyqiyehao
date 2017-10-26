package com.shiro.vo;

/**
 * Created by LiuDongguang on 2017/7/27.
 */
public class RoleAndPermission {
    private Integer roleID;
    private String permissionIDS;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getPermissionIDS() {
        return permissionIDS;
    }

    public void setPermissionIDS(String permissionIDS) {
        this.permissionIDS = permissionIDS;
    }

    @Override
    public String toString() {
        return "RoleAndPermission{" +
                "roleID=" + roleID +
                ", permissionIDS='" + permissionIDS + '\'' +
                '}';
    }
}
