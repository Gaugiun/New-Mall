package com.baidu.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseWxGoodsList {

    private List<BaseWxGoods> goodsList;
    private Integer count;
    private List<CskaoyanMallCategory> filterCategoryList;
}
