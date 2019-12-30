package com.baidu.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseWxGoodsListVo {
    private List<CskaoyanMallGoods> goodsList;
    private Integer count;
    private List<CskaoyanMallCategory> filterCategoryList;
}
