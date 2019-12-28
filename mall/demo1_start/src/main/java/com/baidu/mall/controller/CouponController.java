package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallCoupon;
import com.baidu.mall.service.CouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是优惠券管理的controller
 */

@RestController
public class CouponController {

    /**
     * 模糊查询所有的优惠券
     */
    @Autowired
    CouponService couponService;

    @RequestMapping("admin/coupon/list")
    public BaseRespVo conponList(Integer page, Integer limit, String name, Short type, Short status){
        PageHelper.startPage(page, limit);
        List<CskaoyanMallCoupon> items = couponService.selectFuzzy(name, type, status);
        PageInfo<CskaoyanMallCoupon> total = new PageInfo<>(items);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("items", items);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 编辑优惠券的API
     * @param cskaoyanMallCoupon
     * @return
     */
    @RequestMapping("admin/coupon/update")
    public Object updateCoupon(@RequestBody CskaoyanMallCoupon cskaoyanMallCoupon){
        CskaoyanMallCoupon cskaoyanMallCoupon1 = couponService.updateCoupon(cskaoyanMallCoupon);

        return cskaoyanMallCoupon1;
    }

    /**
     * 查看优惠券详情
     * @param id
     * @return
     */
    @RequestMapping("admin/coupon/read")
    public BaseRespVo readCoupon(Integer id){
        CskaoyanMallCoupon cskaoyanMallCoupon = couponService.readCoupon(id);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(cskaoyanMallCoupon);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 显示用户领取优惠券
     * @param page
     * @param limit
     * @param couponId
     * @return
     */
    @RequestMapping("admin/coupon/listuser")
    public BaseRespVo listUser(Integer page, Integer limit, Integer couponId){
        HashMap hashMap = couponService.listUser(couponId);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 删除优惠券
     * @param cskaoyanMallCoupon
     * @return
     */
    @RequestMapping("admin/coupon/delete")
    public BaseRespVo deleteCoupon(@RequestBody CskaoyanMallCoupon cskaoyanMallCoupon){
        boolean b = couponService.deleteCoupon(cskaoyanMallCoupon);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 新建优惠券
     * @param cskaoyanMallCoupon
     * @return
     */
   /* @RequestMapping("admin/coupon/create")
    public BaseRespVo createCoupon(@RequestBody CskaoyanMallCoupon cskaoyanMallCoupon){
        CskaoyanMallCoupon cskaoyanMallCoupon1 = couponService.createCoupon(cskaoyanMallCoupon);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(cskaoyanMallCoupon1);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }*/
    @RequestMapping("admin/coupon/create")
    public BaseRespVo createCoupon(@RequestBody Map<String, Object> map){

        CskaoyanMallCoupon cskaoyanMallCoupon = couponService.createCoupon(map);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        //baseRespVo.setData(cskaoyanMallCoupon1);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
