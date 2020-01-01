package com.baidu.mall.config;

import com.baidu.mall.convert.List2StringConverter;
import com.baidu.mall.convert.String2DateConverter;
import com.baidu.mall.convert.String2StringArrayConverter;
import com.baidu.mall.convert.StringArray2StringConvert;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*
    * 自定义参数转换器，将前端不同数据类型的数据转换成后端所需类型数据
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new String2DateConverter());
        registry.addConverter(new String2StringArrayConverter());
        registry.addConverter(new StringArray2StringConvert());
        registry.addConverter(new List2StringConverter());
    }
}
