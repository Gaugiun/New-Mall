package com.baidu.mall.service;

import com.baidu.mall.bean.BaseWxIndexVo;
import com.baidu.mall.bean.CskaoyanMallAd;
import com.baidu.mall.bean.CskaoyanMallBrand;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxIndexServiceImpl implements WxIndexService {

    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;
    @Autowired
    CskaoyanMallCouponMapper cskaoyanMallCouponMapper;
    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;
    @Autowired
    CskaoyanMallAdMapper cskaoyanMallAdMapper;
    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;

    @Override
    public BaseWxIndexVo selectIndex(BaseWxIndexVo baseWxIndexVo) {
        baseWxIndexVo.setNewGoodsList(cskaoyanMallGoodsMapper.selectNewGoods());
        baseWxIndexVo.setCouponList(cskaoyanMallCouponMapper.selectCoupon());
        baseWxIndexVo.setChannel(cskaoyanMallCategoryMapper.selectCategoryForIdNameIconUrl());
        baseWxIndexVo.setBanner(cskaoyanMallAdMapper.selectAd());
        baseWxIndexVo.setBrandList(cskaoyanMallBrandMapper.selectAllBrand());
        baseWxIndexVo.setHotGoodsList(cskaoyanMallGoodsMapper.selectHotGoods());

        return baseWxIndexVo;
    }

    @Override
    public int countGoods() {
        return cskaoyanMallGoodsMapper.countGoods();
    }
}
