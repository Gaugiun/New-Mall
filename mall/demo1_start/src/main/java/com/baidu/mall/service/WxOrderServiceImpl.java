package com.baidu.mall.service;

import com.baidu.mall.bean.BaseWxHandleOptionVo;
import com.baidu.mall.bean.BaseWxOrderVo;
import com.baidu.mall.bean.CskaoyanMallOrder;
import com.baidu.mall.bean.CskaoyanMallOrderGoods;
import com.baidu.mall.mapper.CskaoyanMallOrderGoodsMapper;
import com.baidu.mall.mapper.CskaoyanMallOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;
    @Autowired
    CskaoyanMallOrderGoodsMapper cskaoyanMallOrderGoodsMapper;
    @Override
    public List<BaseWxOrderVo> selectOrderListByType(Integer showType) {
        List<BaseWxOrderVo> baseWxOrderVos = cskaoyanMallOrderMapper.selectOrderListByType(showType);
        for (BaseWxOrderVo orderVo : baseWxOrderVos){
            orderVo.setOrderStatusText(cskaoyanMallOrderMapper.selectStatusText(orderVo.getOrderStatus()));
            orderVo.setGoodsList(cskaoyanMallOrderGoodsMapper.selectByOrderId(orderVo.getId()));

            BaseWxHandleOptionVo baseWxHandleOptionVo = new BaseWxHandleOptionVo();
            if ("102".equals(orderVo.getOrderStatus()) || "103".equals(orderVo.getOrderStatus())) {
                baseWxHandleOptionVo.setCancel(true);
            } else if ("201".equals(orderVo.getOrderStatus())) {
                baseWxHandleOptionVo.setPay(true);
            }
            orderVo.setHandleOption(baseWxHandleOptionVo);
        }
        return baseWxOrderVos;
    }

    @Override
    public CskaoyanMallOrder selectOrderById(Integer orderId) {
        return cskaoyanMallOrderMapper.selectByOrderId(orderId);
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        cskaoyanMallOrderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public void cancelOrderById(Integer orderId) {
        cskaoyanMallOrderMapper.cancelOrderById(orderId);
    }

    @Override
    public void prepayOrderById(Integer orderId) {
        cskaoyanMallOrderMapper.prepayOrderById(orderId);
    }

    @Override
    public void refundOrderById(Integer orderId) {
        cskaoyanMallOrderMapper.refundOrderById(orderId);
    }


}
