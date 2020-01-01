package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallCartMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCart record);

    int updateByPrimaryKey(CskaoyanMallCart record);

    List<CskaoyanMallCart> selectByChack(@Param("b") boolean b);
}
