package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallCategoryByLevel;
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
}
