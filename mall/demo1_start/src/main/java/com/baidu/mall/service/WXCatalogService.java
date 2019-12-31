package com.baidu.mall.service;

import com.baidu.mall.bean.*;

import java.util.HashMap;
import java.util.List;

public interface WXCatalogService {

    HashMap catalogIndex();

    Integer count();

    HashMap currenIndex(Integer id);

    HashMap categoryIndex(Integer id);

    List<CskaoyanMallGoods> goodsList(Integer categoryId);

    boolean couponReceive(Integer userId, Integer couponId);

    CskaoyanMallUser authLogin(String username, String password);

    List<CskaoyanMallCouponUser> couponMylist(Integer userId, Short status);

    List<CskaoyanMallCoupon> couponExchange(String code);

    List<CskaoyanMallAddress> addressList();

    Integer addressSave(CskaoyanMallAddress cskaoyanMallAddress);

    CskaoyanMallAddress addressDetail(Integer id);

    boolean addressDelete(Integer id);

    List<CskaoyanMallRegion> regionList(Integer pid);

    boolean feedbackSubmit(Integer userId, CskaoyanMallFeedback cskaoyanMallFeedback);
}
