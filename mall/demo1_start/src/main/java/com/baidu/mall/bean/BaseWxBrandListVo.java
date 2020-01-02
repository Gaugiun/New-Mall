package com.baidu.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseWxBrandListVo {
    private List<CskaoyanMallBrand> brandList;
    private Integer totalPages;
}
