package com.baidu.mall.bean;

import com.baidu.mall.mapper.CskaoyanMallOrderGoodsMapper;
import com.baidu.mall.mapper.CskaoyanMallOrderMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class BaseWxOrderVo {

/*    "orderStatusText":"已取消(系统)",
    "isGroupin":false,
    "orderSn":"20191230927093",
    "actualPrice":503,
    "goodsList":Array[1],
    "id":30,
    "handleOption":Object{...}*/

    private String orderStatusText;
    private int orderStatus;
    private boolean isGroupin;
    private String orderSn;
    private Integer actualPrice;
    private List<CskaoyanMallOrderGoods> goodsList;
    private Integer id;
    private BaseWxHandleOptionVo handleOption;

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isGroupin() {
        return isGroupin;
    }

    public void setGroupin(boolean groupin) {
        isGroupin = groupin;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public List<CskaoyanMallOrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CskaoyanMallOrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BaseWxHandleOptionVo getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(BaseWxHandleOptionVo handleOption) {
        this.handleOption = handleOption;
    }
}
