package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoodsSpecification;
import com.baidu.mall.bean.CskaoyanMallGoodsSpecificationToNameValueList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface CskaoyanMallGoodsSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoodsSpecification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoodsSpecification record);

    int updateByPrimaryKey(CskaoyanMallGoodsSpecification record);

    List<CskaoyanMallGoodsSpecificationToNameValueList> selectGoodsSpecificationToNameValueList(Integer id);

    java.util.List<String> selectGoodsSpecificationGroupByGoodsId(Integer id);

    List<CskaoyanMallGoodsSpecification> selectGoodsSpecificationByGoodsIdAndSpec(Integer id, String s);

    void addSpecification(@Param("specification") LinkedHashMap<String, Object> specifications);

    List<CskaoyanMallGoodsSpecification> selectGoodsSpecificationByGoodsId(@Param("goodsId") Integer goodsId);

    void updateSpecificationByPrimaryKey(@Param("specification") LinkedHashMap<String, Object> specifications);
}
