package com.baidu.mall.convert;

import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * 将LIst集合转换为String
 */

public class StringArray2StringConvert implements Converter<List, String> {

    @Override
    public String convert(List list) {
        String s = list.toString();
        return s;
    }
}
