package com.baidu.mall.convert;

import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * 将String[]转换为String
 */

public class StringArray2StringConvert implements Converter<String[], String> {


    @Override
    public String convert(String[] strings) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < strings.length; i++){
            //遍历出来的数据，用逗号隔开
            sb. append(strings[i]+",");
        }
        sb.deleteCharAt(sb.length() - 1); //去除最后一个逗号
        String str = sb.toString();
        return str;
    }
}
