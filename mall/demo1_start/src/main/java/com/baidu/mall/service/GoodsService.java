package com.baidu.mall.service;

import com.baidu.mall.bean.goodsbean.GoodsBrand;
import com.baidu.mall.bean.goodsbean.GoodsCategory;
import com.baidu.mall.bean.goodsbean.GoodsComment;
import com.baidu.mall.bean.goodsbean.GoodsFirstBean;

import java.util.List;

public interface GoodsService {
    List<GoodsFirstBean> queryGoods(Integer goodsSn, String name);

    List<GoodsBrand> catBrand();

    List<GoodsCategory> queryCatery();

    List<GoodsComment> queryGoodscomment(Integer userId, Integer valueId);

    void deleteComment(Integer id);
}
