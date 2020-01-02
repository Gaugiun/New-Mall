package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallAddress;
import com.baidu.mall.bean.CskaoyanMallUser;

import com.baidu.mall.bean.StatisticsUsersRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CskaoyanMallUserMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallUser record);

    int updateByPrimaryKey(CskaoyanMallUser record);

    List<CskaoyanMallUser> selectAllUser(String username, String mobile);

    CskaoyanMallUser selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<StatisticsUsersRow> selectDateNumble();

    CskaoyanMallUser selectByUsername(String username);

    void addUser(@Param("username") String username, @Param("password") String password, @Param("mobile") String mobile, @Param("lastLoginIp")String lastLoginIp);

    String  selectPasswordByUsername(@Param("username") String username);

}
