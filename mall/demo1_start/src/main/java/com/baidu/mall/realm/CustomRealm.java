package com.baidu.mall.realm;

import com.baidu.mall.bean.CskaoyanMallAdmin;
import com.baidu.mall.mapper.CskaoyanMallAdminMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    CskaoyanMallAdminMapper cskaoyanMallAdminMapper;

    /**
     * 自定义认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        CskaoyanMallAdmin cskaoyanMallAdmin = cskaoyanMallAdminMapper.selectByName(userName);
        String credential = cskaoyanMallAdmin.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, credential, this.getName());
        return authenticationInfo;
    }

    /**
     * 自定义授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        String permission = cskaoyanMallAdminMapper.selectPermissionByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(permission);
        return simpleAuthorizationInfo;
    }


}
