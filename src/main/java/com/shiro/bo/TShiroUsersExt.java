package com.shiro.bo;

import com.shiro.api.po.TShiroUsers;

import java.io.Serializable;

/**
 * Created by LiuDongguang on 2017/7/24.
 */
public class TShiroUsersExt extends TShiroUsers implements Serializable{
    public String getCredentialsSalt(){
        return this.getUsername()+this.getSalt();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
