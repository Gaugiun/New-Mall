package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoodsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public interface CskaoyanMallGoodsProductMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoodsProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsProduct record);

    int updateByPrimaryKey(CskaoyanMallGoodsProduct record);

    List<CskaoyanMallGoodsProduct> selectById(Integer id);

    CskaoyanMallGoodsProduct selectByGoodsId(Integer goodsId);

    void addProduct(@Param("product") LinkedHashMap<String, Object> product);

    List<CskaoyanMallGoodsProduct> selectsByGoodsId(Integer goodsId);

    void updateProductByPrimaryId(@Param("product") LinkedHashMap<String, Object> stringObjectLinkedHashMap);
}
