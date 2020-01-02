package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallBrandMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallBrand record);

    int updateByPrimaryKey(CskaoyanMallBrand record);

    List<CskaoyanMallBrand> selectBrand(String name, Integer id);

    void insertBrand(CskaoyanMallBrand brand);

    CskaoyanMallBrand selectBrandByName(String name);

    boolean deleteBrandById(Integer id);

    boolean updateBrand(CskaoyanMallBrand cskaoyanMallBrand);

    List<CskaoyanMallBrand> selectAllBrand();

    CskaoyanMallBrand selectBrandByGoodsId(Integer id);
}
