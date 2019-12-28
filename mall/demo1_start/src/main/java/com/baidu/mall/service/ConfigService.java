package com.baidu.mall.service;

import com.baidu.mall.bean.configbean.ConfigExpressVo;
import com.baidu.mall.bean.configbean.ConfigMallVo;
import com.baidu.mall.bean.configbean.ConfigOrderVo;
import com.baidu.mall.bean.configbean.ConfigWxVo;

public interface ConfigService {

    //获取商城配置信息
    ConfigMallVo queryByMall();

    //更改商城配置信息
    void updateMallConfig(ConfigMallVo configMallVo);

    //获取运费配置信息
    ConfigExpressVo queryByExpress();

    //更改运费配置信息
    void updateExpressConfig(ConfigExpressVo configExpressVo);

    //获取商城配置信息
    ConfigOrderVo queryByOrder();

    //更改运费配置信息
    void updateOrderConfig(ConfigOrderVo configOrderVo);

    //获取小程序配置信息
    ConfigWxVo queryByWx();

    //更改小程序配置信息
    void updateWxConfig(ConfigWxVo configWxVo);
}
