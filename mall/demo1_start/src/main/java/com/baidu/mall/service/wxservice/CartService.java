package com.baidu.mall.service.wxservice;

import com.baidu.mall.bean.CskaoyanMallCart;

import java.util.LinkedHashMap;
import java.util.List;

public interface CartService {

    List<CskaoyanMallCart> queryCart();

    List<CskaoyanMallCart> checked(Integer productId, Integer isChecked);

    Short add2Cart(Integer goodsId, Short number, Integer productId);

    boolean updateCart(Integer goodsId, Integer id, Short number, Integer productId);

    void delete(Integer productId);

    LinkedHashMap checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId);

    Integer getGoodsCount();

    Short fastAdd(Integer goodsId, Short number, Integer productId);
}
