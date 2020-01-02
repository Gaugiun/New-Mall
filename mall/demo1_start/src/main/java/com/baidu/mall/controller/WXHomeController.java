package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.BaseWxIndexVo;
import com.baidu.mall.bean.CskaoyanMallOrder;
import com.baidu.mall.mapper.CskaoyanMallOrderMapper;
import com.baidu.mall.mapper.CskaoyanMallTokenMapper;
import com.baidu.mall.service.WxIndexService;
import com.baidu.mall.service.WxIndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("wx")
@RestController
public class WXHomeController {
    /**
     * 首页  wx/home/index
     */
    @Autowired
    WxIndexServiceImpl wxIndexServiceImpl;

    @RequestMapping("home/index")
    public BaseRespVo UserList(){
        BaseWxIndexVo baseWxIndexVo = new BaseWxIndexVo();
        baseWxIndexVo = wxIndexServiceImpl.selectIndex(baseWxIndexVo);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(baseWxIndexVo);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("goods/count")
    public BaseRespVo GoodsCount(){
        HashMap<Object, Object> goodsCount = new HashMap<>();
        goodsCount.put("goodsCount",wxIndexServiceImpl.countGoods());

        BaseRespVo resp = new BaseRespVo();
        resp.setData(goodsCount);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;
    @Autowired
    CskaoyanMallTokenMapper cskaoyanMallTokenMapper;

    @RequestMapping("user/index")
    public BaseRespVo UserIndex(HttpServletRequest servletRequest){
/*        "order":{
            "unrecv":0,
            "uncomment":0,
            "unpaid":0,
            "unship":0
        }*/
        String token = servletRequest.getHeader("X-cskaoyanmall-Admin-Token");
        System.out.println(token);
        Integer userId = cskaoyanMallTokenMapper.selectUserIdByToken(token);

        Map<Object, Integer> orderStatus = new HashMap<>();
        orderStatus.put("unrecv",cskaoyanMallOrderMapper.selectOrderListSizeByUserIdAndStatus(userId,301));
        orderStatus.put("uncomment",cskaoyanMallOrderMapper.selectOrderListSizeByUserIdAndStatus(userId,401)+cskaoyanMallOrderMapper.selectOrderListSizeByUserIdAndStatus(userId,402));
        orderStatus.put("unpaid",cskaoyanMallOrderMapper.selectOrderListSizeByUserIdAndStatus(userId,101));
        orderStatus.put("unship",cskaoyanMallOrderMapper.selectOrderListSizeByUserIdAndStatus(userId,201));

        HashMap<Object, Object> Data = new HashMap<>();
        Data.put("order",orderStatus);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(Data);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }
}
