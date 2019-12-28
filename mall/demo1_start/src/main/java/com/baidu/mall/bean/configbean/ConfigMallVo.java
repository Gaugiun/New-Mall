package com.baidu.mall.bean.configbean;


import lombok.Data;

@Data
public class ConfigMallVo {

    String cskaoyan_mall_mall_name;

    String cskaoyan_mall_mall_address;

    String cskaoyan_mall_mall_phone;

    String cskaoyan_mall_mall_qq;

    public ConfigMallVo() {
    }

    public ConfigMallVo(String cskaoyan_mall_mall_name, String cskaoyan_mall_mall_address, String cskaoyan_mall_mall_phone, String cskaoyan_mall_mall_qq) {
        this.cskaoyan_mall_mall_name = cskaoyan_mall_mall_name;
        this.cskaoyan_mall_mall_address = cskaoyan_mall_mall_address;
        this.cskaoyan_mall_mall_phone = cskaoyan_mall_mall_phone;
        this.cskaoyan_mall_mall_qq = cskaoyan_mall_mall_qq;
    }
}
