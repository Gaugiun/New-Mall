package com.baidu.mall.controller.wxcontroller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CartTotal;
import com.baidu.mall.bean.CskaoyanMallCart;
import com.baidu.mall.service.wxservice.CartService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxCartController {

    @Autowired
    CartService cartService;

    /**
     * 显示购物车界面信息 wx/cart/index
     */
    @RequestMapping("cart/index")
    public BaseRespVo cartIndex() {
        BaseRespVo baseRespVo = new BaseRespVo();
        List<CskaoyanMallCart> cskaoyanMallCartList = cartService.queryCart();
        CartTotal cartTotal = new CartTotal();
        Integer goodsCount = null;
        Integer checkedGoodsCount = null;
        BigDecimal goodsAmount = null;
        BigDecimal checkedGoodsAmount = null;
        for (CskaoyanMallCart cskaoyanMallCart : cskaoyanMallCartList) {
            //true表示该件商品被选中， false表示取消选中或未被选中
            if (cskaoyanMallCart.getChecked()) {
                checkedGoodsCount++;
                checkedGoodsAmount = checkedGoodsAmount.add(cskaoyanMallCart.getPrice().multiply(new BigDecimal(cskaoyanMallCart.getNumber())));
            }
            else {
                goodsCount++;
                goodsAmount = goodsAmount.add(cskaoyanMallCart.getPrice().multiply(new BigDecimal(cskaoyanMallCart.getNumber())));
            }
        }
        cartTotal.setGoodsCount(goodsCount);
        cartTotal.setCheckedGoodsCount(checkedGoodsCount);
        cartTotal.setGoodsAmount(goodsAmount);
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount);
        Map map = new LinkedHashMap();
        map.put("cartTotal", cartTotal);
        map.put("cartList", cskaoyanMallCartList);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 选中商品或者取消选中 wx/cart/checked
     */
    @RequestMapping("cart/checked")
    public BaseRespVo checked(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer[] productIds = (Integer[]) map.get("productIds");
        Integer productId = productIds[0];
        Integer isChecked = (Integer) map.get("isChecked");
        List<CskaoyanMallCart> cskaoyanMallCarts = cartService.checked(productId, isChecked);
        CartTotal cartTotal = new CartTotal();
        Integer goodsCount = null;
        Integer checkedGoodsCount = null;
        BigDecimal goodsAmount = null;
        BigDecimal checkedGoodsAmount = null;
        for (CskaoyanMallCart cskaoyanMallCart : cskaoyanMallCarts) {
            //true表示该件商品被选中， false表示取消选中或未被选中
            if (cskaoyanMallCart.getChecked()) {
                checkedGoodsCount++;
                checkedGoodsAmount = checkedGoodsAmount.add(cskaoyanMallCart.getPrice().multiply(new BigDecimal(cskaoyanMallCart.getNumber())));
            }
            else {
                goodsCount++;
                goodsAmount = goodsAmount.add(cskaoyanMallCart.getPrice().multiply(new BigDecimal(cskaoyanMallCart.getNumber())));
            }
        }
        cartTotal.setGoodsCount(goodsCount);
        cartTotal.setCheckedGoodsCount(checkedGoodsCount);
        cartTotal.setGoodsAmount(goodsAmount);
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount);
        Map map1 = new LinkedHashMap();
        map1.put("cartTotal", cartTotal);
        map1.put("cartList", cskaoyanMallCarts);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map1);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 添加商品至购物车 wx/cart/add
     */
    @RequestMapping("cart/add")
    public BaseRespVo add(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer goodsId = (Integer) map.get("goodsId");
        Short number = (Short) map.get("number");
        Integer productId = (Integer) map.get("productId");
        Short goodsNumber = cartService.add2Cart(goodsId, number, productId);
        baseRespVo.setErrno(0);
        baseRespVo.setData(goodsNumber);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 更新购物车信息 wx/cart/update
     */
    @RequestMapping("cart/update")
    public BaseRespVo updateCart(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer goodsId = (Integer) map.get("goodsId");
        Integer id = (Integer) map.get("id");
        Short number = (Short) map.get("number");
        Integer productId = (Integer) map.get("productId");
        boolean flag = cartService.updateCart(goodsId, id, number, productId);
        if (flag) {
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        baseRespVo.setErrmsg("fail");
        return baseRespVo;
    }

    /**
     * 删除购物车商品 wx/cart/delete
     */
    @RequestMapping("cart/delete")
    public BaseRespVo delete(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer[] productIds = (Integer[]) map.get("productIds");
        cartService.delete(productIds[0]);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 下单页面显示优惠券、运费减免和订单信息等情况 wx/cart/checkout?cartId=0&addressId=0&couponId=0&grouponRulesId=0
     */
    @RequestMapping("cart/checkout")
    public BaseRespVo checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        BaseRespVo baseRespVo = new BaseRespVo();
        LinkedHashMap map = cartService.checkout(cartId, addressId, couponId, grouponRulesId);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 获取购物车商品总数 wx/cart/goodscount
     */
    @RequestMapping("cart/goodscount")
    public BaseRespVo getGoodsCount() {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer goodsCount = cartService.getGoodsCount();
        baseRespVo.setErrno(0);
        baseRespVo.setData(goodsCount);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 快速购买商品 wx/cart/fastadd
     */
    @RequestMapping("cart/fastadd")
    public BaseRespVo fastAdd(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer goodsId = (Integer) map.get("goodsId");
        Short number = (Short) map.get("number");
        Integer productId = (Integer) map.get("productId");
        Short goodsNumber = cartService.fastAdd(goodsId, number, productId);
        baseRespVo.setErrno(0);
        baseRespVo.setData(goodsNumber);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
