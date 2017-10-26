package com.shiro.bo;

/**
 * Created by LiuDongguang on 2017/7/31.
 */
public class UserRolePermissonInfo_permission {
    private Integer permissionid;
    private String permissionname;

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    @Override
    public String toString() {
        return "UserRolePermissonInfo_permission{" +
                "permissionid=" + permissionid +
                ", permissionname='" + permissionname + '\'' +
                '}';
    }
}
