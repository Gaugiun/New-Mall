package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallComment;
import com.baidu.mall.bean.goodsbean.GoodsComment;
import com.baidu.mall.bean.wx.BaseWxComment;
import com.baidu.mall.bean.wx.BaseWxTopicComment;
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

    List<BaseWxTopicComment> selectTopicComments(@Param("valueId") Integer valueId, @Param("type") Byte type);

    int insert(BaseWxComment comment);

    int selectCountById(Integer valueId);

    int selectPicCountById(Integer valueId);
}
