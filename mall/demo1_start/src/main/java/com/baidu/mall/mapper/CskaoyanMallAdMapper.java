package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAd;
import com.baidu.mall.bean.CskaoyanMallCoupon;
import org.apache.ibatis.annotations.Mapper;
import com.baidu.mall.bean.CskaoyanMallAdExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.baidu.mall.bean.CskaoyanMallAdExample;
import org.apache.ibatis.annotations.Param;
import com.baidu.mall.bean.CskaoyanMallCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.List;

@Mapper
public interface CskaoyanMallAdMapper {
    long countByExample(CskaoyanMallAdExample example);

    int deleteByExample(CskaoyanMallAdExample example);

    int insert(CskaoyanMallAd record);

    int insertSelective(CskaoyanMallAd record);

    List<CskaoyanMallAd> selectByExample(CskaoyanMallAdExample example);

    int updateByExampleSelective(@Param("record") CskaoyanMallAd record, @Param("example") CskaoyanMallAdExample example);

    int updateByExample(@Param("record") CskaoyanMallAd record, @Param("example") CskaoyanMallAdExample example);


    List<CskaoyanMallAd> selectAd();
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallAd record);

    int updateByPrimaryKey(CskaoyanMallAd record);
}
