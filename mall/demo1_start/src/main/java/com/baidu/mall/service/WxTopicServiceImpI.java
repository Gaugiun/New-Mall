package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallGoods;
import com.baidu.mall.bean.CskaoyanMallTopic;
import com.baidu.mall.bean.wx.BaseWxComment;
import com.baidu.mall.bean.wx.BaseWxCommentListVo;
import com.baidu.mall.bean.wx.BaseWxTopicComment;
import com.baidu.mall.mapper.CskaoyanMallCommentMapper;
import com.baidu.mall.mapper.CskaoyanMallTopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxTopicServiceImpI implements WxTopicService {


    @Autowired
    CskaoyanMallTopicMapper cskaoyanMallTopicMapper;

    @Autowired
    CskaoyanMallCommentMapper cskaoyanMallCommentMapper;

    @Override
    public List<CskaoyanMallTopic> selectAllTopic() {
        return cskaoyanMallTopicMapper.selectAllTopic();
    }

    @Override
    public Integer selectTotalNumber() {
        return cskaoyanMallTopicMapper.selectTotalNumber();
    }

    @Override
    public CskaoyanMallTopic selectByPrimaryKey(Integer id) {
        return cskaoyanMallTopicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CskaoyanMallGoods> selectGoodsById(Integer id) {
        return cskaoyanMallTopicMapper.selectGoodsById(id);
    }

    @Override
    public Map queryCommentByValueId(BaseWxCommentListVo commentList) {
        PageHelper.startPage(commentList.getPage(),commentList.getSize());
        List<BaseWxTopicComment> comments = cskaoyanMallCommentMapper.selectTopicComments(commentList.getValueId(),commentList.getType());
        PageInfo<BaseWxTopicComment> pageInfo = new PageInfo<>(comments);
        Map map = new HashMap();
        map.put("count",pageInfo.getTotal());
        map.put("currentPage",commentList.getPage());
        map.put("data",comments);
        return map;
    }

    @Override
    public BaseWxComment insertComment(BaseWxComment comment) {
        comment.setAddTime(new Date());
        comment.setDeleted(false);
        //暂时备用的，等session的username
        comment.setUserId(1);
        cskaoyanMallCommentMapper.insert(comment);
        return comment;
    }

    @Override
    public Map getCommentCountAndHasPicCount(int valueId, int type) {
        Map map = new HashMap();
        //查询allCount
        int count = cskaoyanMallCommentMapper.selectCountById(valueId);
        map.put("allCount",count);
        //查询hasPicCount
        int hasPicCount = cskaoyanMallCommentMapper.selectPicCountById(valueId);
        map.put("hasPicCount",hasPicCount);
        return map;
    }
}
