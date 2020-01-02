package com.baidu.mall.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;


public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest srequest, ServletResponse sresponse) {
        HttpServletRequest request = (HttpServletRequest)srequest;
        HttpServletResponse response = (HttpServletResponse) sresponse;

        String sessionId = request.getHeader("X-cskaoyan-mall-Admin-Token");
        String Id2 = request.getHeader("X-cskaoyanmall-Admin-Token");
        if (sessionId != null && !"".equals(sessionId)) {
            return sessionId;
        }
        if (Id2 != null && !"".equals(Id2)) {
            return Id2;
        }
        return super.getSessionId(request, response);
    }
}
