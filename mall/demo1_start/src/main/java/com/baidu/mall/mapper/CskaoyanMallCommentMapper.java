package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallComment;

import java.util.List;

public interface CskaoyanMallCommentMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallComment record);

    int updateByPrimaryKey(CskaoyanMallComment record);

    List<CskaoyanMallComment> selectByValueId(Integer id);
}
