package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallSystem;
import com.baidu.mall.bean.configbean.ConfigExpressVo;
import com.baidu.mall.bean.configbean.ConfigMallVo;
import com.baidu.mall.bean.configbean.ConfigOrderVo;
import com.baidu.mall.bean.configbean.ConfigWxVo;
import org.apache.ibatis.annotations.Param;

public interface CskaoyanMallSystemMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallSystem record);

    int updateByPrimaryKey(CskaoyanMallSystem record);

    //显示商场配置
    String selectByName(int nameId);

    String selectByAddress(int addressId);

    String selectByPhone(int phoneId);

    String selectByQQ(int qqId);

    //更改商场配置信息
    int updateName(@Param("nameId") int nameId, @Param("configMallVo") ConfigMallVo configMallVo);

    int updateAddress(@Param("addressId") int addressId, @Param("configMallVo") ConfigMallVo configMallVo);

    int updatePhone(@Param("phoneId") int phoneId, @Param("configMallVo") ConfigMallVo configMallVo);

    int updateQQ(@Param("qqId") int qqId, @Param("configMallVo") ConfigMallVo configMallVo);

    //显示运费配置
    String selectExpressMinById(@Param("minId") int minId);

    String selectExpressValueById(@Param("valueId") int valueId);

    //更改运费配置信息
    int updateExpressMin(@Param("minId") int minId, @Param("configExpressVo") ConfigExpressVo configExpressVo);

    int updateExpressValue(@Param("valueId") int valueId, @Param("configExpressVo") ConfigExpressVo configExpressVo);

    //显示订单配置
    String selectOrderUnpaidById(@Param("unpaidId") int unpaidId);

    String selectOrderUnconfirmById(@Param("unconfirmId") int unconfirmId);

    String selectOrderCommentById(@Param("commentId") int commentId);

    //更改订单配置信息
    int updateOrderUnpaid(@Param("unpaidId") int unpaidId, @Param("configOrderVo") ConfigOrderVo configOrderVo);

    int updateOrderUnconfirm(@Param("unconfirmId") int unconfirmId, @Param("configOrderVo") ConfigOrderVo configOrderVo);

    int updateOrderComment(@Param("commentId") int commentId, @Param("configOrderVo") ConfigOrderVo configOrderVo);

    //显示小程序配置
    String selectWxIndexNewById(@Param("indexNewId") int indexNewId);

    String selectWxIndexHotById(@Param("indexHotId") int indexHotId);

    String selectWxIndexBrandById(@Param("indexBrandId") int indexBrandId);

    String selectWxIndexTopicById(@Param("indexTopicId") int indexTopicId);

    String selectWxCatlogListById(@Param("catlogListId") int catlogListId);

    String selectWxCatlogGoodsById(@Param("catlogGoodsId") int catlogGoodsId);

    String selectWxShareById(@Param("shareId") int shareId);

    //更改订单配置信息
    int updateWxIndexNew(@Param("indexNewId") int indexNewId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxIndexHot(@Param("indexHotId") int indexHotId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxIndexBrand(@Param("indexBrandId") int indexBrandId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxIndexTopic(@Param("indexTopicId") int indexTopicId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxCatlogList(@Param("catlogListId") int catlogListId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxCatlogGoods(@Param("catlogGoodsId") int catlogGoodsId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxShare(@Param("shareId") int shareId, @Param("configWxVo") ConfigWxVo configWxVo);

}
