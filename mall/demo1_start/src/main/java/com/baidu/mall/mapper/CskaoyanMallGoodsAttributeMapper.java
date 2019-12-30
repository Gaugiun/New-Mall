package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoodsAttribute;

import java.util.List;

public interface CskaoyanMallGoodsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoodsAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsAttribute record);

    int updateByPrimaryKey(CskaoyanMallGoodsAttribute record);

    List<CskaoyanMallGoodsAttribute> selectAttributeByGoodsId(Integer id);
}
