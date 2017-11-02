package com.shiro;

import com.shiro.api.service.ShiroService;
import com.shiro.bo.TShiroUsersExt;
import com.shiro.bo.UserRolePermissonInfo;
import com.shiro.bo.UserRolePermissonInfo_permission;
import com.shiro.bo.UserRolePermissonInfo_role;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by LiuDongguang on 2017/7/24.
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    select * from sdeyqiyehao.t_shiro_users ur
    left join sdeyqiyehao.t_shiro_users_roles user_role on ur.uid=user_role.userid
    left join sdeyqiyehao.t_shiro_roles role on user_role.roleid=role.uid
    left join sdeyqiyehao.t_shiro_roles_permission role_permission on role.uid=role_permission.roleid
    left join sdeyqiyehao.t_shiro_permission permission on permission.uid=role_permission.permissionid
    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        UserRolePermissonInfo urp = shiroService.selectRoleAndPermisssionByUserName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户名查询当前用户拥有的角色
        Set<String> roleNames = new HashSet<>();
        // 根据用户名查询当前用户权限
        Set<String> permissionNames = new HashSet<>();
        for (UserRolePermissonInfo_role item : urp.getRoleList()) {
            String rolename = item.getRolename();
            roleNames.add(rolename);
            for (UserRolePermissonInfo_permission permission : item.getPermissionList()) {
                permissionNames.add(permission.getPermissionname());
            }
        }
        // 将角色名称提供给info
        authorizationInfo.setRoles(roleNames);
        //将权限提供给info
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }

    /**
     * 登陆认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        TShiroUsersExt user = shiroService.findUserByUsername(username);
        if (user == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
        /**单一登陆*/
        //处理session
//        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
//        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
//        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
//        for(Session session:sessions){
//           //清除该用户以前登录时保存的session
//            if(username.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
//                sessionManager.getSessionDAO().delete(session);
//                break;
//            }
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,
                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return authenticationInfo;
    }

    /**
     * 清除自己
     */
    public void clearAuthz() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    /**
     * 清除其他人
     *
     * @param principal
     */
    public void clearAuthzForUser(String principal) {
        System.out.println(getAuthorizationCache().size() + "+++++++++++");
        this.getAuthorizationCache().remove(principal);
    }

    public void clearAllAuthz() {
        this.getAuthorizationCache().clear();
    }
}
