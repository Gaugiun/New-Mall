package com.baidu.mall.bean;


import lombok.Data;

import java.util.List;

@Data
public class CskaoyanMallGoodsSpecificationToNameValueList {
    private String name;
    private List<CskaoyanMallGoodsSpecification> valueList;
}
