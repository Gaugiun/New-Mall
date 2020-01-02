package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.BaseWxGoodsListVo;
import com.baidu.mall.bean.BaseWxGoodsVo;
import com.baidu.mall.bean.CskaoyanMallGoods;
import com.baidu.mall.service.WXCatalogServiceImpl;

import com.baidu.mall.bean.*;

import com.baidu.mall.service.WxGoodsServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequestMapping("wx")
@RestController
public class WXGoodsController {

    @Autowired
    WxGoodsServiceImpl wxGoodsService;
    @Autowired
    WXCatalogServiceImpl wxCatalogService;

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
    public BaseRespVo GoodsList(String keyword, Integer categoryId, Integer brandId,
                                Integer page, Integer size){
        BaseWxGoodsListVo baseWxGoodsListVo = new BaseWxGoodsListVo();
        BaseWxGoodsList baseWxGoodsList = new BaseWxGoodsList();
        BaseRespVo resp = new BaseRespVo();
        PageHelper.startPage(page, size);

        if (brandId!=null){
            List<CskaoyanMallGoods> cskaoyanMallGoods = wxGoodsService.selectGoodsListByBrandId(brandId);
            baseWxGoodsListVo.setGoodsList(cskaoyanMallGoods);
            baseWxGoodsListVo.setCount(cskaoyanMallGoods.size());
            baseWxGoodsListVo.setFilterCategoryList(wxGoodsService.selectFilterCategoryListByCategoryId(categoryId));
            resp.setData(baseWxGoodsListVo);
            resp.setErrno(0);
            resp.setErrmsg("成功");
            return resp;
        }
        if (categoryId!=null && keyword == null){
            List<CskaoyanMallGoods> cskaoyanMallGoods = wxGoodsService.selectGoodsListByCategoryId(categoryId);
            baseWxGoodsListVo.setGoodsList(cskaoyanMallGoods);
            baseWxGoodsListVo.setCount(cskaoyanMallGoods.size());
            baseWxGoodsListVo.setFilterCategoryList(wxGoodsService.selectFilterCategoryListByCategoryId(categoryId));
            resp.setData(baseWxGoodsListVo);
            resp.setErrno(0);
            resp.setErrmsg("成功");

            return resp;
        } else if (keyword != null &&  categoryId != null) {
            List<BaseWxGoods> cskaoyanMallGoodList = wxGoodsService.selectGoodsListByKeywords(keyword);
            baseWxGoodsList.setGoodsList(cskaoyanMallGoodList);
            baseWxGoodsList.setCount(cskaoyanMallGoodList.size());
            baseWxGoodsList.setFilterCategoryList(wxGoodsService.selectFilterCategoryListByCategoryId(categoryId));
            resp.setData(baseWxGoodsListVo);
            resp.setErrno(0);
            resp.setErrmsg("成功");

            return resp;
        }
        return null;
    }


/*@RequestMapping("goods/list")
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
    }*/

/*    *//**
     * 点击二级目录后显示该目录及其兄弟目录
     * @param id
     * @return
     *//*
    @RequestMapping("goods/category")
    public BaseRespVo categoryIndex(Integer id){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap hashMap = wxCatalogService.categoryIndex(id);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }*/
}

