package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.CskaoyanMallCollectMapper;
import com.baidu.mall.mapper.CskaoyanMallGoodsMapper;
import com.baidu.mall.mapper.CskaoyanMallTopicMapper;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class WXCollectServiceImpl implements WXCollectService {

    @Autowired
    CskaoyanMallCollectMapper cskaoyanMallCollectMapper;

    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Autowired
    CskaoyanMallTopicMapper cskaoyanMallTopicMapper;

    @Override
    public List<WxCollectGoods> collectList(Byte type) {
        List<WxCollectGoods> collectGoodsList = new LinkedList<>();
        Integer userId = cskaoyanMallUserMapper.selectByUsername("wx").getId();
        List<CskaoyanMallCollect> cskaoyanMallCollectList = cskaoyanMallCollectMapper.selectAllCollectsByUserId(userId);
        for (CskaoyanMallCollect cskaoyanMallCollect :cskaoyanMallCollectList) {
            WxCollectGoods wxCollectGoods = new WxCollectGoods();
            if (type == 0) {
                Integer goodsId = cskaoyanMallCollect.getValueId();
                CskaoyanMallGoods cskaoyanMallGoods = cskaoyanMallGoodsMapper.selectByPrimaryKey(goodsId);
                wxCollectGoods.setBrief(cskaoyanMallGoods.getBrief());
                wxCollectGoods.setPicUrl(cskaoyanMallGoods.getPicUrl());
                wxCollectGoods.setValueId(goodsId);
                wxCollectGoods.setName(cskaoyanMallGoods.getName());
                wxCollectGoods.setId(cskaoyanMallCollect.getId());
                wxCollectGoods.setType(type);
                wxCollectGoods.setRetailPrice(cskaoyanMallGoods.getRetailPrice());
                collectGoodsList.add(wxCollectGoods);
            }
            else if (type == 1) {
                Integer topicId = cskaoyanMallCollect.getValueId();
                CskaoyanMallTopic cskaoyanMallTopic = cskaoyanMallTopicMapper.selectByPrimaryKey(topicId);
                wxCollectGoods.setBrief(cskaoyanMallTopic.getSubtitle());
                wxCollectGoods.setPicUrl(cskaoyanMallTopic.getPicUrl());
                wxCollectGoods.setValueId(topicId);
                wxCollectGoods.setName(cskaoyanMallTopic.getTitle());
                wxCollectGoods.setId(cskaoyanMallCollect.getId());
                wxCollectGoods.setType(type);
                wxCollectGoods.setRetailPrice(cskaoyanMallTopic.getPrice());
                collectGoodsList.add(wxCollectGoods);
            }
        }
        return collectGoodsList;
    }

    @Override
    public String addOrDeleteCollect(Byte type, Integer valueId) {
        Integer userId = cskaoyanMallUserMapper.selectByUsername("wx").getId();
        CskaoyanMallCollect cskaoyanMallCollect = cskaoyanMallCollectMapper.selectCollectByUserIdTypeValueId(userId, type, valueId);
        if (cskaoyanMallCollect == null) {
            cskaoyanMallCollectMapper.insertByUserIdTypeValueId(userId, type, valueId);
            return "add";
        }
        else {
            cskaoyanMallCollectMapper.deleteByUserIdTypeValueId(userId, type, valueId);
            return "delete";
        }
    }
}
