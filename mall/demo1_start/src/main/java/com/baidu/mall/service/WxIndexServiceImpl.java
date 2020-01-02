package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
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
    @Autowired
    CskaoyanMallGrouponRulesMapper cskaoyanMallGrouponRulesMapper;
    @Autowired
    CskaoyanMallTopicMapper cskaoyanMallTopicMapper;

    @Override
    public BaseWxIndexVo selectIndex(BaseWxIndexVo baseWxIndexVo) {
        baseWxIndexVo.setNewGoodsList(cskaoyanMallGoodsMapper.selectNewGoods());
        baseWxIndexVo.setCouponList(cskaoyanMallCouponMapper.selectCoupon());
        baseWxIndexVo.setChannel(cskaoyanMallCategoryMapper.selectCategoryLevel1ForIdNameIconUrl());
        //团购列表
        List<BaseWxIndexGroupon> baseWxIndexGrouponList = new LinkedList<>();
        List<CskaoyanMallGrouponRules> grouponRulesList = cskaoyanMallGrouponRulesMapper.selectAllGroupon();
        for (CskaoyanMallGrouponRules cskaoyanMallGrouponRules : grouponRulesList) {
            BaseWxIndexGroupon baseWxIndexGroupon = new BaseWxIndexGroupon();
            CskaoyanMallGoods cskaoyanMallGoods = cskaoyanMallGoodsMapper.selectByPrimaryKey(cskaoyanMallGrouponRules.getGoodsId());

            BaseWxIndexGoods baseWxIndexGoods = new BaseWxIndexGoods();
            baseWxIndexGoods.setId(cskaoyanMallGoods.getId());
            baseWxIndexGoods.setName(cskaoyanMallGoods.getName());
            baseWxIndexGoods.setBrief(cskaoyanMallGoods.getBrief());
            baseWxIndexGoods.setPicUrl(cskaoyanMallGoods.getPicUrl());
            baseWxIndexGoods.setCounterPrice(cskaoyanMallGoods.getCounterPrice());
            baseWxIndexGoods.setRetailPrice(cskaoyanMallGoods.getRetailPrice());

            baseWxIndexGroupon.setGroupon_price(cskaoyanMallGoods.getRetailPrice().subtract(cskaoyanMallGrouponRules.getDiscount()));
            baseWxIndexGroupon.setGoods(baseWxIndexGoods);
            baseWxIndexGroupon.setGroupon_member(cskaoyanMallGrouponRules.getDiscountMember());
            baseWxIndexGrouponList.add(baseWxIndexGroupon);
        }
        baseWxIndexVo.setGrouponList(baseWxIndexGrouponList);
        baseWxIndexVo.setBanner(cskaoyanMallAdMapper.selectAd());
        baseWxIndexVo.setBrandList(cskaoyanMallBrandMapper.selectAllBrand());
        baseWxIndexVo.setHotGoodsList(cskaoyanMallGoodsMapper.selectHotGoods());

        //专题列表
        List<BaseWxIndexTopic> baseWxIndexTopicList = new LinkedList<>();
        List<CskaoyanMallTopic> cskaoyanMallTopicList = cskaoyanMallTopicMapper.selectAllTopics();
        for (CskaoyanMallTopic cskaoyanMallTopic :cskaoyanMallTopicList) {
            BaseWxIndexTopic baseWxIndexTopic = new BaseWxIndexTopic();
            baseWxIndexTopic.setId(cskaoyanMallTopic.getId());
            baseWxIndexTopic.setTitle(cskaoyanMallTopic.getTitle());
            baseWxIndexTopic.setSubtitle(cskaoyanMallTopic.getSubtitle());
            baseWxIndexTopic.setPrice(cskaoyanMallTopic.getPrice());
            baseWxIndexTopic.setReadCount(cskaoyanMallTopic.getReadCount());
            baseWxIndexTopic.setPicUrl(cskaoyanMallTopic.getPicUrl());
            baseWxIndexTopicList.add(baseWxIndexTopic);
        }
        baseWxIndexVo.setTopicList(baseWxIndexTopicList);

        //商品层级内容列表
        List<CskaoyanMallCategoryByLevel> cskaoyanMallCategoryList = cskaoyanMallCategoryMapper.selectCategoryByLevel(1);
        List<BaseWxIndexFloorGoods> baseWxIndexFloorGoodsList = new LinkedList<>();
        for (CskaoyanMallCategoryByLevel cskaoyanMallCategory : cskaoyanMallCategoryList) {
            BaseWxIndexFloorGoods baseWxIndexFloorGoods = new BaseWxIndexFloorGoods();
            List<BaseWxGoods> baseWxGoodsList = new LinkedList<>();
            List<CskaoyanMallGoods> cskaoyanMallGoodsList = cskaoyanMallGoodsMapper.selectGoodsListByCategoryId(cskaoyanMallCategory.getValue());
            for (CskaoyanMallGoods cskaoyanMallGoods : cskaoyanMallGoodsList) {
                BaseWxGoods baseWxGoods = new BaseWxGoods();
                baseWxGoods.setId(cskaoyanMallGoods.getId());
                baseWxGoods.setName(cskaoyanMallGoods.getName());
                baseWxGoods.setBrief(cskaoyanMallGoods.getBrief());
                baseWxGoods.setPicUrl(cskaoyanMallGoods.getPicUrl());
                baseWxGoods.setIsNew(cskaoyanMallGoods.getIsNew());
                baseWxGoods.setIsHot(cskaoyanMallGoods.getIsHot());
                baseWxGoods.setCounterPrice(cskaoyanMallGoods.getCounterPrice());
                baseWxGoods.setRetailPrice(cskaoyanMallGoods.getRetailPrice());
                baseWxGoodsList.add(baseWxGoods);
            }
            baseWxIndexFloorGoods.setName(cskaoyanMallCategory.getLabel());
            baseWxIndexFloorGoods.setGoodsList(baseWxGoodsList);
            baseWxIndexFloorGoodsList.add(baseWxIndexFloorGoods);
        }
        baseWxIndexVo.setFloorGoodsList(baseWxIndexFloorGoodsList);
        return baseWxIndexVo;
    }

    @Override
    public int countGoods() {
        return cskaoyanMallGoodsMapper.countGoods();
    }
}
