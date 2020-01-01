package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCollect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallCollectMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCollect record);

    int updateByPrimaryKey(CskaoyanMallCollect record);

    List<CskaoyanMallCollect> selectCollect(Integer userId, Integer valueId);

    List<CskaoyanMallCollect> selectAllCollectsByUserId(Integer userId);

    CskaoyanMallCollect selectCollectByUserIdTypeValueId(Integer userId, Byte type, Integer valueId);

    void insertByUserIdTypeValueId(Integer userId, Byte type, Integer valueId);

    void deleteByUserIdTypeValueId(Integer userId, Byte type, Integer valueId);
}
