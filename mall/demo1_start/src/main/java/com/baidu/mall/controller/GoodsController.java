package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.goodsbean.GoodsBrand;
import com.baidu.mall.bean.goodsbean.GoodsCategory;
import com.baidu.mall.bean.goodsbean.GoodsComment;
import com.baidu.mall.bean.goodsbean.GoodsFirstBean;
import com.baidu.mall.service.GoodsCommentReplyService;
import com.baidu.mall.service.GoodsCreateService;
import com.baidu.mall.service.GoodsDetailService;
import com.baidu.mall.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsCreateService goodsCreateService;

    @Autowired
    GoodsDetailService goodsDetailService;

    @Autowired
    GoodsCommentReplyService goodsCommentReplyService;

    /**
     * 显示商品
     *
     * @param page
     * @param limit
     * @param goodsSn
     * @param name
     * @return
     */
    @RequestMapping("goods/list")
    public BaseRespVo goodsList(int page, int limit, Integer goodsSn, String name) {
        PageHelper.startPage(page, limit);
        List<GoodsFirstBean> goodList = goodsService.queryGoods(goodsSn, name);
        PageInfo<GoodsFirstBean> goodsPageInfo = new PageInfo<>(goodList);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("total", goodsPageInfo.getTotal());
        map1.put("items", goodList);
        BaseRespVo resp = new BaseRespVo();
        resp.setData(map1);
        resp.setErrno(0);
        resp.setErrmsg("成功");
        return resp;
    }

    @RequestMapping("goods/catAndBrand")
    public BaseRespVo catAndBrand ( ){
        List<GoodsBrand> brandList = goodsService.catBrand();
        List<GoodsCategory> categoriesList = goodsService.queryCatery();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("categoryList",categoriesList);
        map1.put("brandList",brandList);
        BaseRespVo resp = new BaseRespVo();
        resp.setData(map1);
        resp.setErrno(0);
        resp.setErrmsg("成功");
        return resp;
    }



    @RequestMapping("goods/create")
    public BaseRespVo<Object> createGood(@RequestBody Map<String, Object> json) {
        goodsCreateService.addGood(json);

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    @RequestMapping("goods/detail")
    public BaseRespVo<Object> showGoodDetail(Integer id) {
        Map<String, Object> map = goodsDetailService.showGoodDetail(id);

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        return baseRespVo;
    }

    @RequestMapping("goods/update")
    public BaseRespVo<Object> updateGoods(@RequestBody Map<String, Object> json) {
        goodsCreateService.updateGoods(json);

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    @RequestMapping("comment/list")
    public BaseRespVo goodsComment (Integer page , Integer limit , Integer userId, Integer valueId ) {
        PageHelper.startPage(page, limit);
        List<GoodsComment> commentList = goodsService.queryGoodscomment(userId, valueId);
        Map<String, Object> map1 = new LinkedHashMap<>();
        PageInfo<GoodsComment> goodsPageInfo = new PageInfo<>(commentList);
        map1.put("total", goodsPageInfo.getTotal());
        map1.put("items", commentList);
        BaseRespVo resp = new BaseRespVo();
        resp.setData(map1);
        resp.setErrno(0);
        resp.setErrmsg("成功");
        return resp;
    }

    @RequestMapping("order/reply")
    public BaseRespVo<Object> replyComment(@RequestBody Map<String, Object> json) {
        Integer commentId = (Integer)json.get("commentId");
        String content = (String)json.get("content");

        Boolean flag = goodsCommentReplyService.isEmptyType(commentId);

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        if (flag == false) {
            goodsCommentReplyService.replyComment(commentId, content);
            baseRespVo.setErrmsg("成功");
            baseRespVo.setErrno(0);
            return baseRespVo;
        }
        baseRespVo.setErrmsg("订单商品已回复");
        baseRespVo.setErrno(622);
        return baseRespVo;
    }

    @RequestMapping("comment/delete")
    public BaseRespVo<Object> deleteComment(@RequestBody Map<String, Object> json) {
        Integer id = (Integer) json.get("id");
        goodsService.deleteComment(id);

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
}
