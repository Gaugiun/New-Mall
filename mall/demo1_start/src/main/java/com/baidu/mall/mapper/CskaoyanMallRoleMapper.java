package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallRoleMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallRole record);

    int updateByPrimaryKey(CskaoyanMallRole record);

    List<CskaoyanMallRole> selectRoles(String name);

    void addRoles(@Param("name")String name, @Param("desc")String desc);

    CskaoyanMallRole  queryRoleByName(String name);
}