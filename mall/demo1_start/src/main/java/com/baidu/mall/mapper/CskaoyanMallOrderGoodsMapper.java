package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallOrderGoods;
import com.baidu.mall.bean.StatisticsGoodsRow;

import java.util.List;

public interface CskaoyanMallOrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallOrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallOrderGoods record);

    int updateByPrimaryKey(CskaoyanMallOrderGoods record);

    List<CskaoyanMallOrderGoods> selectByOrderId(Integer orderId);

    List<CskaoyanMallOrderGoods> selectById(Integer id);

    List<StatisticsGoodsRow> selectAll();
}
