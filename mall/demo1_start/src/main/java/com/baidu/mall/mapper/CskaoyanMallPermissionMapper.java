package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CskaoyanMallPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallPermission record);

    int updateByPrimaryKey(CskaoyanMallPermission record);

    List<CskaoyanMallPermission> selectByRoleId(Integer roleId);

    List<String> selectPermissionsInRoleIds(@Param("roleIds") String roleIds);
}