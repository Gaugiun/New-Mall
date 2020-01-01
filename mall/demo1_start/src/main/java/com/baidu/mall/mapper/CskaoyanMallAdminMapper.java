package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CskaoyanMallAdminMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallAdmin record);

    int updateByPrimaryKey(CskaoyanMallAdmin record);

    CskaoyanMallAdmin selectByName(String userName);

    String selectPermissionByUserName(String userName);

    List<CskaoyanMallAdmin> selectALL(String username);

    void addAdmin(CskaoyanMallAdmin cskaoyanMallAdmin);

    CskaoyanMallAdmin selectAdminByUsername(String username);

    String selectRoleIdsByUsername(String username);
}
