package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseWxGoods {

    Integer id;

    String name;

    String brief;

    String picUrl;

    Boolean isNew;

    Boolean isHot;

    BigDecimal counterPrice;

    BigDecimal retailPrice;
}
