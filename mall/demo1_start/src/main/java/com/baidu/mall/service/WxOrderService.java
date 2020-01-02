package com.baidu.mall.service;

import com.baidu.mall.bean.BaseWxOrderVo;
import com.baidu.mall.bean.CskaoyanMallOrder;

import java.util.List;

public interface WxOrderService {
    List<BaseWxOrderVo> selectOrderListByType(Integer showType);

    CskaoyanMallOrder selectOrderById(Integer orderId);

    void deleteOrderById(Integer orderId);

    void cancelOrderById(Integer orderId);

    void prepayOrderById(Integer orderId);

    void refundOrderById(Integer orderId);
}
