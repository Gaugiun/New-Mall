package com.baidu.mall.service;

import com.baidu.mall.bean.CSkaoyanMallCommentContent;
import com.baidu.mall.mapper.CskaoyanMallCommentContentMapper;
import com.baidu.mall.mapper.CskaoyanMallCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsCommentReplyServiceImp implements GoodsCommentReplyService {
    @Autowired
    CskaoyanMallCommentMapper cskaoyanMallCommentMapper;

    @Autowired
    CskaoyanMallCommentContentMapper cSkaoyanMallCommentContentMapper;


    @Override
    public Boolean isEmptyType(Integer commentId) {
        Boolean flag = cskaoyanMallCommentMapper.selectTypeByPrimaryKey(commentId);
        cskaoyanMallCommentMapper.updateTypeByPrimaryKey(commentId);
        return flag;
    }

    @Override
    public void replyComment(Integer commentId, String content) {
        cSkaoyanMallCommentContentMapper.insertContent(commentId, content);
    }
}
