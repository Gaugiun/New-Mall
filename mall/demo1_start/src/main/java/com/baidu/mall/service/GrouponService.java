package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallGrouponRules;

import java.util.List;

public interface GrouponService {
    List<CskaoyanMallGrouponRules> grouponList(Integer goodsId);

    boolean deleteGroupon(CskaoyanMallGrouponRules cskaoyanMallGrouponRules);

    boolean updateGroupon(CskaoyanMallGrouponRules cskaoyanMallGrouponRules);
}
