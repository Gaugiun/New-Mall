package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallGoods;
import com.baidu.mall.bean.CskaoyanMallTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallTopicMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallTopic record);

    int updateByPrimaryKeyWithBLOBs(CskaoyanMallTopic record);

    int updateByPrimaryKey(CskaoyanMallTopic record);

    List<CskaoyanMallTopic> selectFuzzy(@Param("title") String title, @Param("subtitle") String subtitle);

    int insert(CskaoyanMallTopic cskaoyanMallTopic);

    List<CskaoyanMallTopic> selectAllTopics();

    List<CskaoyanMallTopic> selectAllTopic();

    Integer selectTotalNumber();

    List<CskaoyanMallGoods> selectGoodsById(Integer id);
}
