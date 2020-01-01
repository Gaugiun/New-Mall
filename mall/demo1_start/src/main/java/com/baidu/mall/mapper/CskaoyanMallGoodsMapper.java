package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGoods record);

    int updateByPrimaryKeyWithBLOBs(CskaoyanMallGoods record);

    int updateByPrimaryKey(CskaoyanMallGoods record);

    List<CskaoyanMallGoods> selectByCategoryId(@Param("categoryId") Integer categoryId);

    List<CskaoyanMallGoods> selectNewGoods();

    List<CskaoyanMallGoods> selectHotGoods();

    int countGoods();

    String selectShareUrlById(Integer id);

    List<CskaoyanMallGoods> selectGoodsListByCategoryId(Integer categoryId);


    List<BaseWxGoods> selectGoodsListByKeywords(String keywords);
}
