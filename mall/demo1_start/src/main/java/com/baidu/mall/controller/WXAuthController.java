package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.service.AuthRegisterService;
import com.baidu.mall.utils.GetIpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 微信小程序 登陆 页面
 */
@RequestMapping("wx")
@RestController
public class WXAuthController {

    Map<String, String> verrifyCodes = new HashMap<>();

    @Autowired
    AuthRegisterService authRegisterService;


    @RequestMapping("auth/register")
    public BaseRespVo<Object> registerUser(@RequestBody Map<String, String> json, HttpServletRequest request) {
        String username = json.get("username");
        String password = json.get("password");
        String mobile = json.get("mobile");
        String code = json.get("code");
        System.out.println(username + password + mobile +code);
        String ip = GetIpUtil.getIpByRequest(request);
        authRegisterService.registerUser(username, password, mobile, ip);

        String verrifyCode = verrifyCodes.get("verrifyCode");
        System.out.println(verrifyCode);
        /*if (verrifyCode == code) {
            BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
            baseRespVo.setData(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }*/
        if (verrifyCode == "123456") {
            BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
            baseRespVo.setData(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(100);
        baseRespVo.setErrmsg("失败");
        return baseRespVo;
    }

    @RequestMapping("auth/regCaptcha")
    public BaseRespVo<Object> sentRegCaptcha(@RequestBody Map<String, String> json) {
        String mobile = json.get("mobile");
        System.out.println(mobile);
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        String verrifyCode = str.toString();

//        ALiSMSUtils.sentSMS(mobile, verrifyCode);  //此处应该try catch

//        verrifyCodes.put("verrifyCode",verrifyCode);
        verrifyCodes.put("verrifyCode","123456");
        return null;
    }

    @RequestMapping("auth/logout")
    public BaseRespVo<Object> logOut() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
