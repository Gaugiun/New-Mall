package com.baidu.mall.service;

import com.baidu.mall.bean.BaseWxGoods;
import com.baidu.mall.bean.BaseWxGoodsVo;
import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallGoods;
import com.baidu.mall.bean.CskaoyanMallOrderGoods;

import java.util.List;

public interface WxGoodsService {
    BaseWxGoodsVo selectGoodsDetail(Integer id, BaseWxGoodsVo baseWxGoodsVo);

    List<CskaoyanMallGoods> selectGoodsListByCategoryId(Integer categoryId);

    List<CskaoyanMallCategory> selectFilterCategoryListByCategoryId(Integer categoryId);

    List<CskaoyanMallOrderGoods> selectOrderGoodsListByOrderId(Integer orderId);

    List<BaseWxGoods> selectGoodsListByKeywords(String keyword);
}
