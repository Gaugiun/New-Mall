package com.baidu.mall.service;

import com.baidu.mall.bean.BaseWxIndexVo;

import java.util.List;

public interface WxIndexService {

    BaseWxIndexVo selectIndex(BaseWxIndexVo baseWxIndexVo);

    int countGoods();
}
