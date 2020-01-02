package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseWxIndexTopic {

    private Integer id;

    private String title;

    private String subtitle;

    private BigDecimal price;

    private String readCount;

    private String picUrl;
}
