package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartTotal {

    //商品数量
    Integer goodsCount;

    //选中的商品数量
    Integer checkedGoodsCount;

    //商品价格
    BigDecimal goodsAmount;

    //选中的商品价格
    BigDecimal checkedGoodsAmount;
}
