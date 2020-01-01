package com.baidu.mall.controller;

import com.baidu.mall.bean.*;
import com.baidu.mall.service.WXCatalogService;
import com.baidu.mall.utils.md5.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 微信小程序 分类 页面
 */
@RestController
@RequestMapping("wx")
public class WXCatalogController {
    //这里定义一个 userId 用于API
    private Integer userId;

/*    @Autowired
    HttpServletRequest request;*/

    @Autowired
    WXCatalogService wxCatalogService;

    /**
     * 显示分类页面的商品
     * @return
     */
    @RequestMapping("catalog/index")
    public BaseRespVo catalogIndex(){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap hashMap = wxCatalogService.catalogIndex();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

/*    *//**
     * 搜索框默认显示一共多少商品
     * @return
     */
/*    @RequestMapping("goods/count")
=======*/
    /*@RequestMapping("goods/count")
     *//*
    @RequestMapping("goods/count")
    public BaseRespVo count(){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Integer goodsCount = wxCatalogService.count();
        HashMap hashMap = new HashMap();
        hashMap.put("goodsCount", goodsCount);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }*/


    /**
     * 点击分类页面左侧的一级目录出现的二级目录
     * @param id
     * @return
     */
    @RequestMapping("catalog/current")
    public BaseRespVo currentIndex(Integer id){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap hashMap = wxCatalogService.currenIndex(id);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 点击二级目录后显示该目录及其兄弟目录
     * @param id
     * @return
     */
    @RequestMapping("goods/category")
    public BaseRespVo categoryIndex(Integer id){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        HashMap hashMap = wxCatalogService.categoryIndex(id);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

/*    *//**
    /**
     * 显示该二级目录（商品分类）下的所有商品
     * @param categoryId
     * @param page
     * @param size
     * @return
     */
/*    @RequestMapping("goods/list")
=======*/
    /*@RequestMapping("goods/list")
>>>>>>> Stashed changes
     *//*
    @RequestMapping("goods/list")
    public BaseRespVo goodsList(Integer categoryId, Integer page, Integer size){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        PageHelper.startPage(page, size);
        List<CskaoyanMallGoods> goodsList = wxCatalogService.goodsList(categoryId);
        PageInfo<CskaoyanMallGoods> count = new PageInfo<>(goodsList);
        HashMap<Object, Object> hashMap = new HashMap<>();
        int size1 = count.getSize();
        hashMap.put("goodsList", goodsList);
        hashMap.put("count", size1);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }*/




    /**
     * 登录
     * @param hashMap
     * @return
     */
    @RequestMapping("auth/login")
    public BaseRespVo authLogin(@RequestBody HashMap<String, String> hashMap){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String username = hashMap.get("username");
        String oldPassword = hashMap.get("password");
        String password = Md5Util.getMd5(oldPassword);
        CskaoyanMallUser cskaoyanMallUser = wxCatalogService.authLogin(username, password);

        if (cskaoyanMallUser != null) {
            userId = cskaoyanMallUser.getId();
        }

        HashMap data = new HashMap();
        HashMap userInfo = new HashMap();
        userInfo.put("nickName", cskaoyanMallUser.getNickname());
        userInfo.put("avatarUrl", cskaoyanMallUser.getAvatar());
        data.put("userInfo", userInfo);
        data.put("tokenExpire", "2019-12-31T04:18:41.598");
        data.put("token", "0xitqrh84nfhy2gfnxhk6mx8k8wxmfz9");
        if (cskaoyanMallUser != null) {
            baseRespVo.setErrno(0);
            baseRespVo.setData(data);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }else {
            baseRespVo.setErrno(402);
            baseRespVo.setErrmsg("参数值不对");
            return baseRespVo;
        }
    }

    /**
     * 领取优惠券
     * @param hashMap
     * @return
     */
    @RequestMapping("coupon/receive")
    public BaseRespVo couponReceive(@RequestBody HashMap<String, Integer> hashMap){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Integer couponId = hashMap.get("couponId");
        boolean b = wxCatalogService.couponReceive(userId, couponId);
        if (b) {
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }else {
            baseRespVo.setErrno(740);
            baseRespVo.setErrmsg("优惠券已经领取过");
            return baseRespVo;
        }
    }

    /**
     * 个人中心的 核心服务 的优惠券页面
     * @param status
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("coupon/mylist")
    public BaseRespVo couponMylist(Short status, Integer page, Integer size){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        PageHelper.startPage(page, size);
        List<CskaoyanMallCoupon> goodsList = wxCatalogService.couponMylist(userId, status);
        //貌似用不到PageInfo 等会试试
        //PageInfo<CskaoyanMallCoupon> count = new PageInfo<>(goodsList);
        HashMap data = new HashMap();
        data.put("data", goodsList);
        data.put("count", goodsList.size());
        baseRespVo.setData(data);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 兑换优惠券
     * @param hashMap
     * @return
     */
    @RequestMapping("coupon/exchange")
    public BaseRespVo couponExchange(@RequestBody HashMap<String, String> hashMap){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String code = hashMap.get("code");
        List<CskaoyanMallCoupon> couponList = wxCatalogService.couponExchange(code);
        if (couponList.size() > 0) {
            CskaoyanMallCoupon cskaoyanMallCoupon = couponList.remove(0);
            boolean b = wxCatalogService.couponReceive(userId, cskaoyanMallCoupon.getId());
            if (b){
                baseRespVo.setErrno(0);
                baseRespVo.setErrmsg("成功");
                return baseRespVo;
            }
        }
        baseRespVo.setErrno(742);
        baseRespVo.setErrmsg("优惠券不正确");
        return baseRespVo;
    }

    /**
     * 收货地址列表
     * @return
     */
    @RequestMapping("address/list")
    public BaseRespVo addressList(){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<CskaoyanMallAddress> cskaoyanMallAddresses = wxCatalogService.addressList();
        baseRespVo.setData(cskaoyanMallAddresses);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 新建收货地址页面的 国家 省 市
     * @param pid
     * @return
     */
    @RequestMapping("region/list")
    public BaseRespVo regionList(Integer pid){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
       List<CskaoyanMallRegion> cskaoyanMallAddresses = wxCatalogService.regionList(pid);
        baseRespVo.setData(cskaoyanMallAddresses);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 新建收货地址
     * @param cskaoyanMallAddress
     * @return
     */
    @RequestMapping("address/save")
    public BaseRespVo addressSave(@RequestBody CskaoyanMallAddress cskaoyanMallAddress){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        cskaoyanMallAddress.setUserId(userId);
        Integer id = wxCatalogService.addressSave(cskaoyanMallAddress);
        baseRespVo.setData(id);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 收货地址详情页
     * @param id
     * @return
     */
    @RequestMapping("address/detail")
    public BaseRespVo addressDetail(Integer id){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        CskaoyanMallAddress cskaoyanMallAddress= wxCatalogService.addressDetail(id);
        baseRespVo.setData(cskaoyanMallAddress);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 用户删除收货地址 逻辑删除
     * @param hashMap
     * @return
     */
    @RequestMapping("address/delete")
    public BaseRespVo addressDelete(@RequestBody HashMap<String, Integer> hashMap){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Integer id = hashMap.get("id");
        boolean b = wxCatalogService.addressDelete(id);
        if (b){
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        return baseRespVo;
    }

    @RequestMapping("feedback/submit")
    public BaseRespVo feedbackSubmit(@RequestBody CskaoyanMallFeedback cskaoyanMallFeedback){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        boolean b = wxCatalogService.feedbackSubmit(userId, cskaoyanMallFeedback);
        if (b){
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        return baseRespVo;
    }

    /**
     * 显示浏览足迹
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("footprint/list")
    public BaseRespVo footprintList(Integer page, Integer size){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        PageHelper.startPage(page, size);
        List<FootPrintBean> footprintList = wxCatalogService.footprintList();
        //貌似用不到PageInfo 等会试试
       // PageInfo<FootPrintBean> totalPages = new PageInfo<>(footprintList);
        double a = footprintList.size();
        double b = size;
        int totalPages = (int) Math.ceil(a / b);
        HashMap data = new HashMap();
        data.put("footprintList", footprintList);
        data.put("totalPages", totalPages);
        baseRespVo.setData(data);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("coupon/selectlist")
    public BaseRespVo couponSelectlist(Integer cartId, Integer grouponRulesId){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();

        List<CskaoyanMallCoupon> list = wxCatalogService.couponSelectlist(userId);

        baseRespVo.setData(list);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


}
