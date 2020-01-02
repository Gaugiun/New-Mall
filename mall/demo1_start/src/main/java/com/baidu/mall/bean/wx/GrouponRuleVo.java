package com.baidu.mall.bean.wx;

import java.math.BigDecimal;

public class GrouponRuleVo {


    private Integer id;
    private String name;
    private String brief;
    private String picUrl;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private Integer groupon_member;
    private BigDecimal groupon_price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getGroupon_member() {
        return groupon_member;
    }

    public void setGroupon_member(Integer groupon_member) {
        this.groupon_member = groupon_member;
    }

    public BigDecimal getGroupon_price() {
        return groupon_price;
    }

    public void setGroupon_price(BigDecimal groupon_price) {
        this.groupon_price = groupon_price;
    }
}
