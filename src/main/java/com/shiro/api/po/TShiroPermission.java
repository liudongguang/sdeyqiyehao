package com.shiro.api.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_shiro_permission")
public class TShiroPermission {
    @Id
    private Integer uid;

    /**
     * 权限名
     */
    private String permissionname;

    /**
     * 权限描述
     */
    private String description;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取权限名
     *
     * @return permissionname - 权限名
     */
    public String getPermissionname() {
        return permissionname;
    }

    /**
     * 设置权限名
     *
     * @param permissionname 权限名
     */
    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    /**
     * 获取权限描述
     *
     * @return description - 权限描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限描述
     *
     * @param description 权限描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TShiroPermission{" +
                "uid=" + uid +
                ", permissionname='" + permissionname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}