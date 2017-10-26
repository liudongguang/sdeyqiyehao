package com.shiro.api.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_shiro_roles_permission")
public class TShiroRolesPermission {
    @Id
    private Integer uid;

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 权限id
     */
    private Integer permissionid;

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
     * 获取角色id
     *
     * @return roleid - 角色id
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 设置角色id
     *
     * @param roleid 角色id
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取权限id
     *
     * @return permissionid - 权限id
     */
    public Integer getPermissionid() {
        return permissionid;
    }

    /**
     * 设置权限id
     *
     * @param permissionid 权限id
     */
    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}