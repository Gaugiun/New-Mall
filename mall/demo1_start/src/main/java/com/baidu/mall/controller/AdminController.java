package com.baidu.mall.controller;

import com.baidu.mall.bean.AuthInfoBean;
import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.service.AdminService;
import com.baidu.mall.shiro.MallToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("admin")
public class AdminController {
    private String userName;

    @Autowired
    AdminService adminService;

    /**
     * 登录后台管理页面的API
     * @param map
     * @return
     */

    @RequestMapping("auth/login")
    public BaseRespVo login(@RequestBody HashMap<String, String> map){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String username = map.get("username");
        userName = username;
        String password = map.get("password");
        Subject subject = SecurityUtils.getSubject();
        MallToken adminToken = new MallToken(username, password, "admin");
        subject.login(adminToken);
        try {
            Serializable id = subject.getSession().getId();
            baseRespVo.setErrno(0);
            baseRespVo.setData(id);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        } catch (AuthenticationException e) {
            baseRespVo.setErrno(10000);
            baseRespVo.setErrmsg("失败");
            return baseRespVo;
        }
    }

    @RequestMapping("auth/info")
    public BaseRespVo info(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
//        HashMap<String, Object> map = new HashMap<>();

        AuthInfoBean authInfoBean = adminService.selectByUserName(userName);

/*        map.put("name",authInfoBean.getName());
        map.put("avatar",authInfoBean.getAvatar());
        map.put("roles",authInfoBean.getRoles());
        map.put("perms",authInfoBean.getPerms());*/
        baseRespVo.setData(authInfoBean);
        return baseRespVo;
    }

    @RequestMapping("auth/unAuthc")
    public String unAuthc(){
        return "http://localhost:9527/#/login?redirect=%2Fdashboard";
    }


}
