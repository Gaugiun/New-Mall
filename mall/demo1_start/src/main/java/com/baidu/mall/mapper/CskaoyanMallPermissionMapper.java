package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallPermission;

import java.util.List;

public interface CskaoyanMallPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallPermission record);

    int updateByPrimaryKey(CskaoyanMallPermission record);

    List<CskaoyanMallPermission> selectByRoleId(Integer roleId);
}