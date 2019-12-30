package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.BaseWxGoodsListVo;
import com.baidu.mall.bean.BaseWxGoodsVo;
import com.baidu.mall.bean.CskaoyanMallGoods;
import com.baidu.mall.service.WxGoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("wx")
@RestController
public class WXGoodsController {

    @Autowired
    WxGoodsServiceImpl wxGoodsService;

    /**
     * 商品详情
     * */
    @RequestMapping("goods/detail")
    public BaseRespVo GoodsDetail(@RequestParam("id") Integer id){
        BaseWxGoodsVo baseWxGoodsVo = new BaseWxGoodsVo();

        baseWxGoodsVo = wxGoodsService.selectGoodsDetail(id,baseWxGoodsVo);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(baseWxGoodsVo);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("goods/list")
    public BaseRespVo GoodsList(@RequestParam("brandId") Integer brandId,@RequestParam("categoryId") Integer categoryId,
                                @RequestParam("page") Integer page,@RequestParam("size") Integer size){
        BaseWxGoodsListVo baseWxGoodsListVo = new BaseWxGoodsListVo();
        if (brandId!=null){
            //List<CskaoyanMallGoods> cskaoyanMallGoods = wxGoodsService.selectGoodsListByCategoryId(categoryId);
            //baseWxGoodsListVo.setGoodsList(cskaoyanMallGoods);
            //baseWxGoodsListVo.setCount(cskaoyanMallGoods.size());
            //baseWxGoodsListVo.setFilterCategoryList(wxGoodsService.selectFilterCategoryListByCategoryId(categoryId));
        }else if (categoryId!=null){
            List<CskaoyanMallGoods> cskaoyanMallGoods = wxGoodsService.selectGoodsListByCategoryId(categoryId);
            baseWxGoodsListVo.setGoodsList(cskaoyanMallGoods);
            baseWxGoodsListVo.setCount(cskaoyanMallGoods.size());
            baseWxGoodsListVo.setFilterCategoryList(wxGoodsService.selectFilterCategoryListByCategoryId(categoryId));
        }


        BaseRespVo resp = new BaseRespVo();
        resp.setData(baseWxGoodsListVo);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }
}
