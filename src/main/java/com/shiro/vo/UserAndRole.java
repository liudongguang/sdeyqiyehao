package com.shiro.vo;

/**
 * Created by LiuDongguang on 2017/7/27.
 */
public class UserAndRole {
    private Integer userID;
    private String roleIDS;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getRoleIDS() {
        return roleIDS;
    }

    public void setRoleIDS(String roleIDS) {
        this.roleIDS = roleIDS;
    }

    @Override
    public String toString() {
        return "UserAndRole{" +
                "userID=" + userID +
                ", roleIDS='" + roleIDS + '\'' +
                '}';
    }
}
