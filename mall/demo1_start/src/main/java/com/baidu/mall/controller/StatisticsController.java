package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.StatisticsGoodsRow;
import com.baidu.mall.bean.StatisticsOrderRow;
import com.baidu.mall.bean.StatisticsUsersRow;
import com.baidu.mall.service.StatisticsGoodService;
import com.baidu.mall.service.StatisticsOrderService;
import com.baidu.mall.service.StatisticsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class StatisticsController {

    @Autowired
    StatisticsUserService statisticsUserService;

    @Autowired
    StatisticsOrderService statisticsOrderService;

    @Autowired
    StatisticsGoodService statisticsGoodService;

    /**
     * @return BaseRespVo<Object>
     * 为了返回正确的json数据，使用了map来放置data，colums直接用string数组了
     */
    @RequestMapping("stat/user")
    public BaseRespVo<Object> userStatics() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<StatisticsUsersRow> statisticsUsersRowList = statisticsUserService.querryDateNumble();
        baseRespVo.setErrno(0);
        Map map = new HashMap<String, Object>();
        String[] columns = {"day", "users"};
        map.put("columns", columns);
        map.put("rows", statisticsUsersRowList);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("stat/order")
    public BaseRespVo<Object> orderStatics() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<StatisticsOrderRow> statisticsOrderRows = statisticsOrderService.querryDateNumble();
        baseRespVo.setErrno(0);
        Map map = new HashMap<String, Object>();
        String[] columns = {"day", "orders", "customers", "amount", "pcr"};
        map.put("columns", columns);
        map.put("rows", statisticsOrderRows);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("stat/goods")
    public BaseRespVo goodsStatics() {
        BaseRespVo baseRespVo = new BaseRespVo();
        List<StatisticsGoodsRow> statisticsGoodsRows = statisticsGoodService.querryDateNumble();
        baseRespVo.setErrno(0);
        Map map = new HashMap<String, Object>();
        String[] columns = {"day", "orders", "products", "amount"};
        map.put("columns", columns);
        map.put("rows", statisticsGoodsRows);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}