package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGrouponRules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallGrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallGrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallGrouponRules record);

    int updateByPrimaryKey(CskaoyanMallGrouponRules record);

    List<CskaoyanMallGrouponRules> select(@Param("goodsId") Integer goodsId);
}
