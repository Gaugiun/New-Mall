package com.baidu.mall.service;


import com.baidu.mall.bean.wx.GrouponRuleVo;
import com.baidu.mall.mapper.CskaoyanMallGoodsMapper;
import com.baidu.mall.mapper.CskaoyanMallGrouponMapper;
import com.baidu.mall.mapper.CskaoyanMallGrouponRulesMapper;
import com.baidu.mall.mapper.WxGrouponMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxGrouponServiceImpI implements WxGrouponService {


    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;

    @Autowired
    CskaoyanMallGrouponRulesMapper cskaoyanMallGrouponRulesMapper;

    @Autowired
    CskaoyanMallGrouponMapper cskaoyanMallGrouponMapper;

    @Autowired
    WxGrouponMapper wxGrouponMapper;

    @Override
    public Map queryGrouponList(int page, int size) {
        PageHelper.startPage(page, size);
        List<GrouponRuleVo> wxGroupons = wxGrouponMapper.queryGroupList();
        PageInfo<GrouponRuleVo> pageInfo = new PageInfo<>(wxGroupons);
        Map map = new HashMap();
        map.put("count", pageInfo.getTotal());
        map.put("data", wxGroupons);
        return map;
    }




}
