package com.baidu.mall.bean.configbean;

import lombok.Data;

@Data
public class ConfigOrderVo {

    String cskaoyan_mall_order_unpaid;

    String cskaoyan_mall_order_unconfirm;

    String cskaoyan_mall_order_comment;

    public ConfigOrderVo() {
    }

    public ConfigOrderVo(String cskaoyan_mall_order_unpaid, String cskaoyan_mall_order_unconfirm, String cskaoyan_mall_order_comment) {
        this.cskaoyan_mall_order_unpaid = cskaoyan_mall_order_unpaid;
        this.cskaoyan_mall_order_unconfirm = cskaoyan_mall_order_unconfirm;
        this.cskaoyan_mall_order_comment = cskaoyan_mall_order_comment;
    }
}

