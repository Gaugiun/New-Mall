package com.baidu.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseWxIndexFloorGoods {

    String name;

    List<BaseWxGoods> goodsList;
}
