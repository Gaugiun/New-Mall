package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoodsProduct;

import java.util.List;

public interface CskaoyanMallGoodsProductMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoodsProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsProduct record);

    int updateByPrimaryKey(CskaoyanMallGoodsProduct record);

    List<CskaoyanMallGoodsProduct> selectByGoodsId(Integer id);
}
