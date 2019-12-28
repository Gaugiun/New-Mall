package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallGrouponRules;
import com.baidu.mall.mapper.CskaoyanMallGrouponRulesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponServiceImpl implements GrouponService {
    @Autowired
    CskaoyanMallGrouponRulesMapper cskaoyanMallGrouponRulesMapper;

    @Override
    public List<CskaoyanMallGrouponRules> grouponList(Integer goodsId) {
        List<CskaoyanMallGrouponRules> cskaoyanMallGrouponRules = cskaoyanMallGrouponRulesMapper.select(goodsId);
        return cskaoyanMallGrouponRules;
    }

    @Override
    public boolean deleteGroupon(CskaoyanMallGrouponRules cskaoyanMallGrouponRules) {
        int i = cskaoyanMallGrouponRulesMapper.deleteByPrimaryKey(cskaoyanMallGrouponRules.getId());
        return i > 0;
    }

    @Override
    public boolean updateGroupon(CskaoyanMallGrouponRules cskaoyanMallGrouponRules) {
        int i = cskaoyanMallGrouponRulesMapper.updateByPrimaryKey(cskaoyanMallGrouponRules);
        return i > 0;
    }
}
