package com.shiro.util;

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
    public static void clearAuthorizationInfo() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            RealmSecurityManager securityManager =
                    (RealmSecurityManager) SecurityUtils.getSecurityManager();
            Cache<Object, Object> AuthorizationCahce = securityManager.getCacheManager().getCache("AuthorizationCahce");
            AuthorizationCahce.clear();
        }
    }
}  