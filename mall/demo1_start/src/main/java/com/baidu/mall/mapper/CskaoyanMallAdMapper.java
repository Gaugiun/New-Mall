package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAd;
import com.baidu.mall.bean.CskaoyanMallCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallAdMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallAd record);

    int updateByPrimaryKey(CskaoyanMallAd record);

    List<CskaoyanMallAd> selectAd();
}
