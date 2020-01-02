package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallCategoryByLevel;
import com.baidu.mall.bean.CskaoyanMallCategoryToIdNameIconUrl;
import com.baidu.mall.bean.goodsbean.Children;
import com.baidu.mall.bean.goodsbean.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CskaoyanMallCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallCategory record);

    int updateByPrimaryKey(CskaoyanMallCategory record);

    List<CskaoyanMallCategory> selectCategoryByPid(Integer pid);

    List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i);

    boolean deleteCategoryById(Integer id);

    void insertCategory(CskaoyanMallCategory cskaoyanMallCategory);

    CskaoyanMallCategory selectCategoryByName(String name);

    void updateCategory(CskaoyanMallCategory cskaoyanMallCategory);

    List<CskaoyanMallCategory> select();

    List<CskaoyanMallCategory> selectByPidAndLevel(@Param("pid") Integer pid);

    List<CskaoyanMallCategoryToIdNameIconUrl> selectCategoryLevel1ForIdNameIconUrl();

    List<CskaoyanMallCategory> selectFilterCategoryListByCategoryId(Integer categoryId);

    List<GoodsCategory> selectCatgryByTypeId(String l1);

    List<Children> selectChildren(Integer pid);
}
