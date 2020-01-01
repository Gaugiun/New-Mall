package com.baidu.mall.config;

import com.baidu.mall.realm.CustomRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    // shiro过滤器生成工厂
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBea(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //认证失败后重定向的url
        shiroFilterFactoryBean.setLoginUrl("admin/auth/unAuthc");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        // 放开静态资源访问路径
        filterMap.put("classpath:/static/img","anon");
        // 放开登录的url过滤
        filterMap.put("/admin/auth/login","anon");
        filterMap.put("/admin/auth/info","anon");
        filterMap.put("/wx/**", "anon");
        // 需要认证才能访问的路径
        filterMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //shiro核心组件 SecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(CustomRealm customRealm, DefaultWebSessionManager webSessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
//        defaultWebSecurityManager.setSessionManager(webSessionManager());
        defaultWebSecurityManager.setSessionManager(webSessionManager);
        return defaultWebSecurityManager;
    }

    /**
     *  声明式鉴权 注解需要的组件
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultWebSessionManager webSessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        return customSessionManager;
    }


}
