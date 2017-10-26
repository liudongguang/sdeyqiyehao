package com.shiro.api.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_shiro_roles")
public class TShiroRoles {
    @Id
    private Integer uid;

    /**
     * 角色名
     */
    private String rolename;

    /**
     * 说明
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
     * 获取角色名
     *
     * @return rolename - 角色名
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置角色名
     *
     * @param rolename 角色名
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * 获取说明
     *
     * @return description - 说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置说明
     *
     * @param description 说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TShiroRoles{" +
                "uid=" + uid +
                ", rolename='" + rolename + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}