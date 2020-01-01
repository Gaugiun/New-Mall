package com.baidu.mall.controller.wxcontroller;

import com.baidu.mall.bean.BaseRespVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx")
public class WxAdminController {

    /**
     * 登录小程序端页面的API
     */
   /* @RequestMapping("auth/login")
    public BaseRespVo login(@RequestBody HashMap<String, String> map){

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String username = map.get("username");
        String password = map.get("password");
        if ("wx".equals(username) && "admin123".equals(password)) {

            baseRespVo.setErrno(0);
            baseRespVo.setData("abc");
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        return baseRespVo;
    }*/
}
