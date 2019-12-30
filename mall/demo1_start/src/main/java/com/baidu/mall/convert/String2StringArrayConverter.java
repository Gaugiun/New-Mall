package com.baidu.mall.convert;

import org.springframework.core.convert.converter.Converter;

public class String2StringArrayConverter implements Converter<String, String[]> {
    @Override
    public String[] convert(String s) {
        String[] split = s.split(",");
        return split;
    }
}
