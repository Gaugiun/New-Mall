package com.baidu.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class SystemAuthChild {
    private Integer id;

    private String label;

    private List<SystemAuthChild> children;

    private String api;
}
