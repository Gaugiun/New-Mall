package com.baidu.mall.bean;

import com.baidu.mall.mapper.CskaoyanMallGoodsProductMapper;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BaseWxGoodsVo {
    private List<Map> specificationList;
    private List<CskaoyanMallGroupon> groupon;
    private List<CskaoyanMallIssue> issue;
    private Integer userHasCollect;
    private String shareImage;
    private Map comment;
    private List<CskaoyanMallGoodsAttribute> attribute;
    private CskaoyanMallBrand brand;
    private List<CskaoyanMallGoodsProduct> productList;
    private CskaoyanMallGoods info;

}
