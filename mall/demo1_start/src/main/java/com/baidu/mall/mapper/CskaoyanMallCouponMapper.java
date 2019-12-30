package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallCouponMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCoupon record);

    int updateByPrimaryKey(CskaoyanMallCoupon record);

    List<CskaoyanMallCoupon> selectCoupon();
}
