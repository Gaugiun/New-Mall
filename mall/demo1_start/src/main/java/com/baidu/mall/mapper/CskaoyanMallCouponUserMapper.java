package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCouponUser;
import com.baidu.mall.bean.CskaoyanMallCouponUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallCouponUserMapper {
    long countByExample(CskaoyanMallCouponUserExample example);

    int deleteByExample(CskaoyanMallCouponUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CskaoyanMallCouponUser record);

    int insertSelective(CskaoyanMallCouponUser record);

    List<CskaoyanMallCouponUser> selectByExample(CskaoyanMallCouponUserExample example);

    CskaoyanMallCouponUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CskaoyanMallCouponUser record, @Param("example") CskaoyanMallCouponUserExample example);

    int updateByExample(@Param("record") CskaoyanMallCouponUser record, @Param("example") CskaoyanMallCouponUserExample example);

    int updateByPrimaryKeySelective(CskaoyanMallCouponUser record);

    int updateByPrimaryKey(CskaoyanMallCouponUser record);
}
