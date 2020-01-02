package com.baidu.mall.controller;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.CskaoyanMallOrderMapper;
import com.baidu.mall.service.WxGoodsServiceImpl;
import com.baidu.mall.service.WxOrderServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("wx")
@RestController
public class WXOrderController {
    @Autowired
    WxOrderServiceImpl wxOrderServiceImpl;

    // 还差一个 groupin
    @RequestMapping("order/list") //wx/order/list?showType=2&page=1&size=10
    public BaseRespVo OrderList(@RequestParam Integer showType,@RequestParam Integer page,
                               @RequestParam Integer size){
        BaseWxOrderListVo baseWxOrderListVo = new BaseWxOrderListVo();

        PageHelper.startPage(page,size);
        List<BaseWxOrderVo> orderListByType = wxOrderServiceImpl.selectOrderListByType(showType);
        PageInfo<BaseWxOrderVo> orderVoPageInfo = new PageInfo<>(orderListByType);

        baseWxOrderListVo.setData(orderListByType);
        baseWxOrderListVo.setCount((int)orderVoPageInfo.getTotal());
        baseWxOrderListVo.setTotalPages((int) Math.ceil(orderVoPageInfo.getTotal()));

        BaseRespVo resp = new BaseRespVo();
        resp.setData(baseWxOrderListVo);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @Autowired
    WxGoodsServiceImpl wxGoodsServiceImpl;

    @RequestMapping("order/detail")
    public BaseRespVo OrderDetail(Integer orderId){
        CskaoyanMallOrder order = wxOrderServiceImpl.selectOrderById(orderId);
        List<CskaoyanMallOrderGoods> goodsList = wxGoodsServiceImpl.selectOrderGoodsListByOrderId(orderId);

        Map<Object, Object> map = new HashMap<>();
        map.put("orderInfo",order);
        map.put("orderGoods",goodsList);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(map);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("order/delete")
    public BaseRespVo OrderDelete(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        wxOrderServiceImpl.deleteOrderById(orderId);

        BaseRespVo resp = new BaseRespVo();
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("order/cancel")
    public BaseRespVo OrderCancel(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        wxOrderServiceImpl.cancelOrderById(orderId);

        BaseRespVo resp = new BaseRespVo();
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("order/prepay")
    public BaseRespVo OrderPrepay(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        wxOrderServiceImpl.prepayOrderById(orderId);

        BaseRespVo resp = new BaseRespVo();
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 退款
     * order-order_status置-->202
     */
    @RequestMapping("/order/refund")
    public BaseRespVo refund(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");

        wxOrderServiceImpl.refundOrderById(orderId);

        BaseRespVo resp = new BaseRespVo();
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

}


