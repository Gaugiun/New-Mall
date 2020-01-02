package com.baidu.mall.controller;

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
        String password = map.get("password");
            MallToken adminToken = new MallToken(username, password, "admin");
            subject.login(adminToken);

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
        HashMap<String, Object> map = new HashMap<>();

        //adminService.select

        map.put("name","admin123");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        ArrayList<String> roleList = new ArrayList<>();
        roleList.add("超级管理员");
        ArrayList<String> permList = new ArrayList<>();
        permList.add("*");

        map.put("roles",roleList);
        map.put("perms",permList);
        baseRespVo.setData(map);
        return baseRespVo;
    }


}
