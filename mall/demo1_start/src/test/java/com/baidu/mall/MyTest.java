
 package com.baidu.mall;

import com.baidu.mall.bean.*;
import com.baidu.mall.controller.WXOrderController;
import com.baidu.mall.mapper.*;
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
    WXOrderController wxOrderController;
    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;

    @Test
    public void mytest(){
        //cskaoyanMallOrderMapper.selectOrderListByType(0);
       /* String s = cskaoyanMallOrderMapper.selectStatusText(103);
        System.out.println(s);*/
        BaseWxHandleOptionVo baseWxHandleOptionVo = new BaseWxHandleOptionVo();
        baseWxHandleOptionVo.setCancel(true);
        String s = baseWxHandleOptionVo.toString();
        System.out.println(s);


        //BaseRespVo baseRespVo = wxOrderController.UserList(0, 1, 10);
        //System.out.println(baseRespVo.toString());
       // Object data = baseRespVo.getData();
        //System.out.println(data.toString());
    }
}
