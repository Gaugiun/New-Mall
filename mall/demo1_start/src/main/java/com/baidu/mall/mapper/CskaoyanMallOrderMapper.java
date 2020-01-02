package com.baidu.mall.mapper;

import com.baidu.mall.bean.BaseWxOrderVo;
import com.baidu.mall.bean.CskaoyanMallOrder;
import com.baidu.mall.bean.StatisticsOrderRow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallOrderMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallOrder record);

    int updateByPrimaryKey(CskaoyanMallOrder record);

    List<CskaoyanMallOrder> selectByUserIdOrderIdOrderStatus(@Param("userId") Integer userId, @Param("id") Integer id, @Param("orderStatus") Integer[] orderStatus);

    int updateOrderStatusById(Integer id);

    List<CskaoyanMallOrder> selectByUserIdOrderId(Integer userId, Integer id);

    List<CskaoyanMallOrder> selectById(Integer id);

    CskaoyanMallOrder selectByOrderId(Integer orderId);

    List<StatisticsOrderRow> selectAll();

    List<BaseWxOrderVo> selectOrderListByType(Integer showType);

    String selectStatusText(int id);

    void cancelOrderById(Integer orderId);

    void prepayOrderById(Integer orderId);

    void refundOrderById(Integer orderId);
}
