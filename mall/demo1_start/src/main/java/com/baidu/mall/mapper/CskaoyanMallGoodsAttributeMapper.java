package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoodsAttribute;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public interface CskaoyanMallGoodsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoodsAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsAttribute record);

    int updateByPrimaryKey(CskaoyanMallGoodsAttribute record);

    List<CskaoyanMallGoodsAttribute> selectAttributeByGoodsId(Integer id);

    void addGoodsAttribute(@Param("goodsAttribute") LinkedHashMap<String, Object> goodsAttribute);

    void updateAttributeByPrimary(@Param("goodsAttribute") LinkedHashMap<String, Object> goodsAttribute);
}
