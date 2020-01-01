package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCart;

import java.util.List;

public interface CskaoyanMallCartMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCart record);

    int updateByPrimaryKey(CskaoyanMallCart record);

    List<CskaoyanMallCart> selectCarts();

    void updateByProductId(Integer productId);

    void insert(CskaoyanMallCart cskaoyanMallCart);

    int updateByIdGoodsIdNumberProductId(Integer goodsId, Integer id, Short number, Integer productId);

    void deleteByProductId(Integer productId, Integer userId);

    CskaoyanMallCart selectByProductId(Integer productId);

    List<CskaoyanMallCart> selectByChecked();
}
