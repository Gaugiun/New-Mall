package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallStorage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CskaoyanMallStorageMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallStorage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallStorage record);

    int updateByPrimaryKey(CskaoyanMallStorage record);

    List<CskaoyanMallStorage> selectStorage(String key, String name);

    void insertStorage(CskaoyanMallStorage storage);

    CskaoyanMallStorage selectStorageByKey(String key);


}
