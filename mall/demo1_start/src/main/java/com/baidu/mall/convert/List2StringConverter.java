package com.baidu.mall.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class List2StringConverter implements Converter<List, String> {

    @Override
    public String convert(List list) {
        return list.toString();
    }
}
