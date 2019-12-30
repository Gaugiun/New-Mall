package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CskaoyanMallAddressMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallAddress record);

    int updateByPrimaryKey(CskaoyanMallAddress record);

    List<CskaoyanMallAddress> selectAddress(String name, Integer userId);

    List<CskaoyanMallAddress> select();

    Integer insert(@Param("cskaoyanMallAddress") CskaoyanMallAddress cskaoyanMallAddress);

    int updateDeleteById(@Param("id") Integer id);
}
