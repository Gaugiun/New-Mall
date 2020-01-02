package com.baidu.mall.realm;

import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxRealm extends AuthorizingRealm {

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token =(UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = cskaoyanMallUserMapper.selectPasswordByUsername(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,this.getName());
        return authenticationInfo;
    }

    /**
     * 授权 目前还没写
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
