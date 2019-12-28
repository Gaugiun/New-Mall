package com.baidu.mall.controller;


import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.configbean.ConfigExpressVo;
import com.baidu.mall.bean.configbean.ConfigMallVo;
import com.baidu.mall.bean.configbean.ConfigOrderVo;
import com.baidu.mall.bean.configbean.ConfigWxVo;
import com.baidu.mall.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("config/mall")
    public BaseRespVo getConfigMall(){
        ConfigMallVo configMallVo = configService.queryByMall();
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setData(configMallVo);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @PostMapping("config/mall")
    public BaseRespVo changeConfigMall(@RequestBody ConfigMallVo configMallVo) {
        configService.updateMallConfig(configMallVo);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @GetMapping("config/express")
    public BaseRespVo getConfigExpress(){
        ConfigExpressVo configExpressVo = configService.queryByExpress();
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setData(configExpressVo);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @PostMapping("config/express")
    public BaseRespVo changeConfigExpress(@RequestBody ConfigExpressVo configExpressVo) {
        configService.updateExpressConfig(configExpressVo);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @GetMapping("config/order")
    public BaseRespVo getConfigOrder(){
        ConfigOrderVo configOrderVo = configService.queryByOrder();
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setData(configOrderVo);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @PostMapping("config/order")
    public BaseRespVo changeConfigOrder(@RequestBody ConfigOrderVo configOrderVo) {
        configService.updateOrderConfig(configOrderVo);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @GetMapping("config/wx")
    public BaseRespVo getConfigWx(){
        ConfigWxVo configWxVo = configService.queryByWx();
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setData(configWxVo);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @PostMapping("config/wx")
    public BaseRespVo changeConfigWx(@RequestBody ConfigWxVo configWxVo) {
        configService.updateWxConfig(configWxVo);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


}
