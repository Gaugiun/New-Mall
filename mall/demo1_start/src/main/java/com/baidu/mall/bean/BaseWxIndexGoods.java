package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseWxIndexGoods {

    Integer id;

    String name;

    String brief;

    String picUrl;

    BigDecimal counterPrice;

    BigDecimal retailPrice;
}
