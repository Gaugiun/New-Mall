package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAllauth;
import com.baidu.mall.bean.CskaoyanMallAllauthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallAllauthMapper {
    int countByExample(CskaoyanMallAllauthExample example);

    int deleteByExample(CskaoyanMallAllauthExample example);

    int deleteByPrimaryKey(Integer primaryId);

    int insert(CskaoyanMallAllauth record);

    int insertSelective(CskaoyanMallAllauth record);

    List<CskaoyanMallAllauth> selectByExample(CskaoyanMallAllauthExample example);

    CskaoyanMallAllauth selectByPrimaryKey(Integer primaryId);

    int updateByExampleSelective(@Param("record") CskaoyanMallAllauth record, @Param("example") CskaoyanMallAllauthExample example);

    int updateByExample(@Param("record") CskaoyanMallAllauth record, @Param("example") CskaoyanMallAllauthExample example);

    int updateByPrimaryKeySelective(CskaoyanMallAllauth record);

    int updateByPrimaryKey(CskaoyanMallAllauth record);
}