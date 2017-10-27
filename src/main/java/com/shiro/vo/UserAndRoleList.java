package com.shiro.vo;

import com.shiro.api.po.TShiroRoles;

import java.util.Date;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/7/27.
 */
public class UserAndRoleList {
    private Integer userid;
    private String username;
    private Date createtime;
    private List<TShiroRoles> roles;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<TShiroRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<TShiroRoles> roles) {
        this.roles = roles;
    }
}
