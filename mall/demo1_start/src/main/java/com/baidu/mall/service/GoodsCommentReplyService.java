package com.baidu.mall.service;

public interface GoodsCommentReplyService {
    Boolean isEmptyType(Integer commentId);

    void replyComment(Integer commentId, String content);

}
