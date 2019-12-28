package com.baidu.mall;

import com.baidu.mall.bean.CskaoyanMallRegion;
import com.baidu.mall.bean.CskaoyanMallStorage;
import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallStorageMapper;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import com.baidu.mall.service.MallService;
import com.baidu.mall.service.MallServiceImpl;
import com.baidu.mall.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {

    @Autowired
    CskaoyanMallStorageMapper cskaoyanMallStorageMapper;

    @Test
    public void mytest(){
        String s = "dc39f340685e4dfc8d162beaeb510540.jpg";
        CskaoyanMallStorage storage = cskaoyanMallStorageMapper.selectStorageByKey(s);
        if (storage==null){
            System.out.println("null!");
            return;
        }
        System.out.println(storage.toString());
    }
}
