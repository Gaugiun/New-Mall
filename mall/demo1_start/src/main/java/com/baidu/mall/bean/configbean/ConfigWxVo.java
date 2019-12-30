package com.baidu.mall.bean.configbean;

import lombok.Data;

@Data
public class ConfigWxVo {

    String cskaoyan_mall_wx_index_new;

    String cskaoyan_mall_wx_index_hot;

    String cskaoyan_mall_wx_index_brand;

    String cskaoyan_mall_wx_index_topic;

    String cskaoyan_mall_wx_catlog_list;

    String cskaoyan_mall_wx_catlog_goods;

    String cskaoyan_mall_wx_share;

    public ConfigWxVo() {
    }

    public ConfigWxVo(String cskaoyan_mall_wx_index_new, String cskaoyan_mall_wx_index_hot, String cskaoyan_mall_wx_index_brand, String cskaoyan_mall_wx_index_topic, String cskaoyan_mall_wx_catlog_list, String cskaoyan_mall_wx_catlog_goods, String cskaoyan_mall_wx_share) {
        this.cskaoyan_mall_wx_index_new = cskaoyan_mall_wx_index_new;
        this.cskaoyan_mall_wx_index_hot = cskaoyan_mall_wx_index_hot;
        this.cskaoyan_mall_wx_index_brand = cskaoyan_mall_wx_index_brand;
        this.cskaoyan_mall_wx_index_topic = cskaoyan_mall_wx_index_topic;
        this.cskaoyan_mall_wx_catlog_list = cskaoyan_mall_wx_catlog_list;
        this.cskaoyan_mall_wx_catlog_goods = cskaoyan_mall_wx_catlog_goods;
        this.cskaoyan_mall_wx_share = cskaoyan_mall_wx_share;
    }
}
