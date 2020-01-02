package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallGoods;
import com.baidu.mall.bean.CskaoyanMallTopic;
import com.baidu.mall.bean.wx.BaseWxComment;
import com.baidu.mall.bean.wx.BaseWxCommentListVo;

import java.util.List;
import java.util.Map;

public interface WxTopicService {

    List<CskaoyanMallTopic> selectAllTopic();

    Integer selectTotalNumber();

    CskaoyanMallTopic selectByPrimaryKey(Integer id);

    List<CskaoyanMallGoods> selectGoodsById(Integer id);

    Map queryCommentByValueId(BaseWxCommentListVo commentList);

    BaseWxComment insertComment(BaseWxComment comment);

    Map getCommentCountAndHasPicCount(int valueId, int type);

}
