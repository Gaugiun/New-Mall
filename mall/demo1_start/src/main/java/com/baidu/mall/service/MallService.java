package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallBrand;
import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallCategoryByLevel;
import com.baidu.mall.bean.CskaoyanMallRegion;

import java.util.List;

public interface MallService {
    List<CskaoyanMallRegion> selectRegion();

    List<CskaoyanMallBrand> selectBrand(String name, Integer id);

    List<CskaoyanMallCategory> selectCategory();

    List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i);

    boolean deleteCategory(Integer id);

    void insertBrand(CskaoyanMallBrand brand);

    CskaoyanMallBrand selectBrandByName(String name);

    boolean deleteBrand(Integer id);

    boolean updateBrand(CskaoyanMallBrand cskaoyanMallBrand);

    List<CskaoyanMallBrand> selectAllBrand();

    void insertCatagory(CskaoyanMallCategory cskaoyanMallCategory);

    CskaoyanMallCategory selectCategoryByName(String name);

    void updateCategory(CskaoyanMallCategory cskaoyanMallCategory);
}
