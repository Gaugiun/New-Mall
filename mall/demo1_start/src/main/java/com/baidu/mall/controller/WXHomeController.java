package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.BaseWxIndexVo;
import com.baidu.mall.service.WxIndexService;
import com.baidu.mall.service.WxIndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

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

}
