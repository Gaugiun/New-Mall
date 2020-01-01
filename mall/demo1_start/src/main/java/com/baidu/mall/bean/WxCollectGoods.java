package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WxCollectGoods {

    String brief;

    String picUrl;

    Integer valueId;

    String name;

    Integer id;

    Byte type;

    BigDecimal retailPrice;
}
