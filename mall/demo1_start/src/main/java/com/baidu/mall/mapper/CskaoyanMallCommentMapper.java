package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallComment;
import com.baidu.mall.bean.goodsbean.GoodsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallCommentMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallComment record);

    int updateByPrimaryKey(CskaoyanMallComment record);

    List<CskaoyanMallComment> selectByValueId(Integer id);

    List<GoodsComment> selectGoodsComment(@Param("userId") Integer userId, @Param("valueId") Integer valueId);

    Boolean selectTypeByPrimaryKey(@Param("commentId") Integer commentId);

    void updateTypeByPrimaryKey(Integer commentId);
}
