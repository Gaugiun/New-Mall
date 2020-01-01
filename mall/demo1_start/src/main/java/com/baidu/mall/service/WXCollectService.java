package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallCollect;
import com.baidu.mall.bean.WxCollectGoods;

import java.util.List;

public interface WXCollectService {

    List<WxCollectGoods> collectList(Byte type);

    String addOrDeleteCollect(Byte type, Integer valueId);
}
