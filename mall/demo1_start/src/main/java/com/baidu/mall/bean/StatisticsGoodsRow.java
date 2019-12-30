package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsGoodsRow {
    private BigDecimal amount;

    private Integer orders;

    private String day;

    private Integer products;
}
