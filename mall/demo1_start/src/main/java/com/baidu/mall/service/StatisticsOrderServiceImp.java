package com.baidu.mall.service;

import com.baidu.mall.bean.StatisticsOrderRow;
import com.baidu.mall.mapper.CskaoyanMallOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsOrderServiceImp implements StatisticsOrderService {

    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;

    @Override
    public List<StatisticsOrderRow> querryDateNumble() {
        List<StatisticsOrderRow> statisticsOrderRows = cskaoyanMallOrderMapper.selectAll();
        return statisticsOrderRows;
    }
}
