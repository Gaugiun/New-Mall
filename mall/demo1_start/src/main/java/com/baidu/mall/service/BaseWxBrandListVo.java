package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallBrand;
import lombok.Data;

import java.util.List;

@Data
public class BaseWxBrandListVo {
    private List<CskaoyanMallBrand> brandList;
    private Integer totalPages;
}
