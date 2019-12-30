package com.baidu.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsOrderRow {
    private String day;

    private String orders;

    private String customers;

    private BigDecimal amount;

    private  BigDecimal pcr;


}
