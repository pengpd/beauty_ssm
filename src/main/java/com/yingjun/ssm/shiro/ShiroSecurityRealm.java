package com.yingjun.ssm.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.SimpleAuthenticationInfo;  
import org.apache.shiro.authc.UsernamePasswordToken;  
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;  
import org.apache.shiro.authz.AuthorizationInfo;  
import org.apache.shiro.authz.SimpleAuthorizationInfo;  
import org.apache.shiro.realm.AuthorizingRealm;  
import org.apache.shiro.subject.PrincipalCollection;  
import org.springframework.stereotype.Component;

import com.yingjun.ssm.dao.UserDao;
import com.yingjun.ssm.entity.User;
import com.yingjun.ssm.service.UserService;  
  
  
/** 
 * 权限认证 
 * @author root 
 */  
@SuppressWarnings("deprecation")  
@Component  
public class ShiroSecurityRealm extends AuthorizingRealm {  
      
    @Resource  
    private UserService userService;  
  
    @Resource  
    private UserDao sysUserDao;  
  
    public ShiroSecurityRealm() {  
        setName("ShiroSecurityRealm"); // This name must match the name in the SysUser class's getPrincipals() method  
        setCredentialsMatcher(new Sha256CredentialsMatcher());  
    }  
  
    /** 
     * 登录认证 
     */  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        SysUser user = userService.getByProerties(new String[]{"loginAccount"}, new String[]{token.getUsername()},null);  
        if (user != null) {  
            return new SimpleAuthenticationInfo(user.getUserId(), user.getLoginPass(), getName());  
        } else {  
            return null;  
        }  
    }  
  
  
    /** 
     * 权限认证 
     */  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
        Long userId = (Long) principals.fromRealm(getName()).iterator().next();  
        User user = userService.get(userId);  
        if (user != null) {  
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
            for (SysRole role : user.getRoles()) {  
                info.addRole(role.getRoleKey());  
                info.addStringPermissions(role.getPermissions());  
            }  
            return info;  
        } else {  
            return null;  
        }  
    }  
  
}  
