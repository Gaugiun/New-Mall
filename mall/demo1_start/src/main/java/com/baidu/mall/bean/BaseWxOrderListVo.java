package com.baidu.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseWxOrderListVo {
    private List<BaseWxOrderVo> data;
    private Integer count;
    private Integer totalPages;

}
