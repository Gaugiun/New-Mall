package com.baidu.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseWxIndexVo<T> {
    private List<CskaoyanMallGoods> newGoodsList;
    private List<CskaoyanMallCoupon> couponList;
    private List<CskaoyanMallCategoryToIdNameIconUrl> channel;
    private List<CskaoyanMallAd> banner;
    private List<CskaoyanMallBrand> brandList;
    private List<CskaoyanMallGoods> hotGoodsList;
}
