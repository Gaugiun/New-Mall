package com.baidu.mall.bean.goodsbean;

import lombok.Data;

import java.util.List;

@Data
public class GoodsCategory {
    private  Integer value ;
    private  String label;
    List<Children> children;
}
