package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface CouponService {
    List<CskaoyanMallCoupon> selectFuzzy(String name, Short type, Short status);

    CskaoyanMallCoupon updateCoupon(CskaoyanMallCoupon cskaoyanMallCoupon);

    CskaoyanMallCoupon readCoupon(Integer id);

    HashMap listUser(Integer couponId);

    boolean deleteCoupon(CskaoyanMallCoupon cskaoyanMallCoupon);

    Integer createCoupon(Map<String, Object> map);

    CskaoyanMallCoupon selectById(@Param("id") Integer id);
}
