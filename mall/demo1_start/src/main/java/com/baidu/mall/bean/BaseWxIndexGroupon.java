package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseWxIndexGroupon {

    BigDecimal groupon_price;

    BaseWxIndexGoods goods;

    Integer groupon_member;
}
