package com.baidu.mall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CskaoyanMallCommentContentMapper {
    void insertContent(@Param("commentId") Integer commentId, @Param("content")String content);
}
