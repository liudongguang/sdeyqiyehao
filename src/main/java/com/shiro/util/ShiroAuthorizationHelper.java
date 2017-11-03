package com.shiro.util;

import com.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroAuthorizationHelper {
    private static Logger log = LoggerFactory.getLogger(ShiroAuthorizationHelper.class);

    /**
     * 清除用户的授权信息
     */
    public static void clearAuthorizationInfoForUser(String userName) {
        RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm)rsm.getRealms().iterator().next();
        realm.clearAuthzForUser(userName);
    }

    public static void clearAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm)rsm.getRealms().iterator().next();
        realm.clearAllAuthz();
    }
}  