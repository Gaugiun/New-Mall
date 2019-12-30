package com.baidu.mall.service;

import com.baidu.mall.bean.StatisticsGoodsRow;
import com.baidu.mall.mapper.CskaoyanMallOrderGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsGoodServiceImp implements StatisticsGoodService {

    @Autowired
    CskaoyanMallOrderGoodsMapper cskaoyanMallOrderGoodsMapper;

    @Override
    public List<StatisticsGoodsRow> querryDateNumble() {
        List<StatisticsGoodsRow> statisticsGoodsRows = cskaoyanMallOrderGoodsMapper.selectAll();
        return statisticsGoodsRows;
    }
}
