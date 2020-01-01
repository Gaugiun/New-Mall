package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallLog;

import java.util.List;

public interface CskaoyanMallLogMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallLog record);

    int updateByPrimaryKey(CskaoyanMallLog record);

    List<CskaoyanMallLog> selectAllByName(String name);
}