package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCoupon;
import com.baidu.mall.bean.CskaoyanMallCouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallCouponMapper {
    long countByExample(CskaoyanMallCouponExample example);

    int deleteByExample(CskaoyanMallCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallCoupon record);

    int insertSelective(CskaoyanMallCoupon record);

    List<CskaoyanMallCoupon> selectByExample(CskaoyanMallCouponExample example);

    CskaoyanMallCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallCoupon record, @Param("example") CskaoyanMallCouponExample example);

    int updateByExample(@Param("record") CskaoyanMallCoupon record, @Param("example") CskaoyanMallCouponExample example);

    int updateByPrimaryKeySelective(CskaoyanMallCoupon record);

    int updateByPrimaryKey(CskaoyanMallCoupon record);

    CskaoyanMallCoupon selectById(@Param("id") Integer id);
}
