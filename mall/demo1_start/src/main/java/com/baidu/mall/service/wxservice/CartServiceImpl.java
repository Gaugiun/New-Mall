package com.baidu.mall.service.wxservice;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CskaoyanMallCartMapper cskaoyanMallCartMapper;

    @Autowired
    CskaoyanMallGoodsProductMapper cskaoyanMallGoodsProductMapper;

    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Autowired
    CskaoyanMallAddressMapper cskaoyanMallAddressMapper;

    @Autowired
    CskaoyanMallCouponMapper cskaoyanMallCouponMapper;

    @Autowired
    CskaoyanMallCouponUserMapper cskaoyanMallCouponUserMapper;

    @Autowired
    CskaoyanMallGrouponMapper cskaoyanMallGrouponMapper;

    @Autowired
    CskaoyanMallGrouponRulesMapper cskaoyanMallGrouponRulesMapper;

    @Override
    public List<CskaoyanMallCart> queryCart() {
        return cskaoyanMallCartMapper.selectCarts();
    }

    @Override
    public List<CskaoyanMallCart> checked(Integer productId, Integer isChecked) {
        cskaoyanMallCartMapper.updateByProductId(productId);
        List<CskaoyanMallCart> cskaoyanMallCarts = cskaoyanMallCartMapper.selectCarts();
        return cskaoyanMallCarts;
    }

    @Override
    public Short add2Cart(Integer goodsId, Short number, Integer productId) {
        CskaoyanMallGoodsProduct cskaoyanMallGoodsProduct = cskaoyanMallGoodsProductMapper.selectByGoodsId(goodsId);
        CskaoyanMallCart cskaoyanMallCart = new CskaoyanMallCart();
        CskaoyanMallGoods cskaoyanMallGoods = cskaoyanMallGoodsMapper.selectByPrimaryKey(goodsId);
        cskaoyanMallCart.setGoodsId(cskaoyanMallGoodsProduct.getGoodsId());
        cskaoyanMallCart.setGoodsName(cskaoyanMallGoods.getName());
        cskaoyanMallCart.setGoodsSn(cskaoyanMallGoods.getGoodsSn());
        cskaoyanMallCart.setNumber(Short.parseShort(cskaoyanMallGoodsProduct.getNumber().toString()));
        cskaoyanMallCart.setAddTime(new Date(System.currentTimeMillis()));
        cskaoyanMallCart.setUpdateTime(new Date(System.currentTimeMillis()));
        cskaoyanMallCart.setChecked(false);
        cskaoyanMallCart.setDeleted(false);
        cskaoyanMallCart.setPicUrl(cskaoyanMallGoods.getPicUrl());
        cskaoyanMallCart.setPrice(cskaoyanMallGoods.getRetailPrice());
        cskaoyanMallCart.setProductId(productId);
        cskaoyanMallCart.setSpecifications(cskaoyanMallGoodsProduct.getSpecifications());
        cskaoyanMallCart.setUserId(123);
        cskaoyanMallCartMapper.insert(cskaoyanMallCart);
        List<CskaoyanMallCart> cskaoyanMallCarts = cskaoyanMallCartMapper.selectCarts();
        //购物车里商品总数
        int goodsNumber = 0;
        for (CskaoyanMallCart cskaoyanMallCart1 : cskaoyanMallCarts) {
            goodsNumber = cskaoyanMallCart1.getNumber() + goodsNumber;
        }
        return (short) goodsNumber;
    }

    @Override
    public boolean updateCart(Integer goodsId, Integer id, Short number, Integer productId) {
        return (cskaoyanMallCartMapper.updateByIdGoodsIdNumberProductId(goodsId, id, number, productId) > 0) ? true : false;
    }

    @Override
    public void delete(Integer productId) {
        CskaoyanMallUser cskaoyanMallUser = cskaoyanMallUserMapper.selectByUsername("wx");
        cskaoyanMallCartMapper.deleteByProductId(productId, cskaoyanMallUser.getId());
    }

    @Override
    public LinkedHashMap checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        List<CskaoyanMallCart> cskaoyanMallCarts = cskaoyanMallCartMapper.selectByChecked();
        CskaoyanMallAddress cskaoyanMallAddress = cskaoyanMallAddressMapper.selectByPrimaryKey(addressId);
        CskaoyanMallCoupon cskaoyanMallCoupon = cskaoyanMallCouponMapper.selectByPrimaryKey(couponId);
        CskaoyanMallGrouponRules cskaoyanMallGrouponRules = cskaoyanMallGrouponRulesMapper.selectByPrimaryKey(grouponRulesId);

        //遍历被选中的所有商品，计算商品的总价格以及扣除优惠、团购折扣等之后的商品总价格
        BigDecimal orderTotalPrice = null;
        BigDecimal actualPrice = null;
        //商品总价格
        for (CskaoyanMallCart cskaoyanMallCart : cskaoyanMallCarts) {
            orderTotalPrice = orderTotalPrice.add(cskaoyanMallCart.getPrice().multiply(new BigDecimal(cskaoyanMallCart.getNumber())));
        }
        //扣除团购折扣和优惠券折扣
        if (couponId != 0 && grouponRulesId != 0) {
            actualPrice = orderTotalPrice.subtract(cskaoyanMallGrouponRules.getDiscount()).subtract(cskaoyanMallCoupon.getDiscount());
        }
        else if (couponId != 0 && grouponRulesId == 0) {
            actualPrice = orderTotalPrice.subtract(cskaoyanMallCoupon.getDiscount());
        }
        else if (couponId == 0 && grouponRulesId != 0) {
            actualPrice = orderTotalPrice.subtract(cskaoyanMallGrouponRules.getDiscount());
        }
        LinkedHashMap map = new LinkedHashMap();
        map.put("grouponPrice", cskaoyanMallGrouponRules.getDiscount());
        map.put("grouponRulesId", grouponRulesId);
        map.put("checkedAddress", cskaoyanMallAddress);
        map.put("actualPrice", actualPrice);
        map.put("orderTotalPrice", orderTotalPrice);
        map.put("couponPrice", cskaoyanMallCoupon.getDiscount());
        map.put("availableCouponLength", cskaoyanMallCoupon.getLimit()*cskaoyanMallCoupon.getGoodsType());
        map.put("couponId", couponId);
        if (actualPrice.compareTo(new BigDecimal(88)) >= 0) {
            map.put("freightPrice", 0);
        }
        else {
            map.put("freightPrice", new BigDecimal(16));
        }
        map.put("checkedGoodsList", cskaoyanMallCarts);
        map.put("goodsTotalPrice", actualPrice.add(new BigDecimal(16)));
        map.put("addressId", addressId);
        return map;
    }

    @Override
    public Integer getGoodsCount() {
        List<CskaoyanMallCart> cskaoyanMallCarts = cskaoyanMallCartMapper.selectCarts();
        int goodsCount = 0;
        for (CskaoyanMallCart cskaoyanMallCart : cskaoyanMallCarts) {
            goodsCount += cskaoyanMallCart.getNumber();
        }
        return goodsCount;
    }

    @Override
    public Short fastAdd(Integer goodsId, Short number, Integer productId) {
        return add2Cart(goodsId, number, productId);
    }
}
