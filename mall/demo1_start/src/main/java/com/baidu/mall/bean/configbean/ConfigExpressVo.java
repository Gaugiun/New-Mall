package com.baidu.mall.bean.configbean;

import lombok.Data;

@Data
public class ConfigExpressVo {

    String cskaoyan_mall_express_freight_min;

    String cskaoyan_mall_express_freight_value;

    public ConfigExpressVo() {
    }

    public ConfigExpressVo(String cskaoyan_mall_express_freight_min, String cskaoyan_mall_express_freight_value) {
        this.cskaoyan_mall_express_freight_min = cskaoyan_mall_express_freight_min;
        this.cskaoyan_mall_express_freight_value = cskaoyan_mall_express_freight_value;
    }
}
