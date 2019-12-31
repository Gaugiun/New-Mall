package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallStorage;
import com.baidu.mall.mapper.CskaoyanMallStorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemStorageServiceImpl implements SystemStorageService {

    @Autowired
    CskaoyanMallStorageMapper cskaoyanMallStorageMapper;

    @Override
    public List<CskaoyanMallStorage> selectStorage(String key, String name) {
        return cskaoyanMallStorageMapper.selectStorage(key,name);
    }

    @Override
    public void insertStorage(CskaoyanMallStorage storage) {
        cskaoyanMallStorageMapper.insertStorage(storage);
    }

    @Override
    public CskaoyanMallStorage selectStorageByKey(String key) {
        return cskaoyanMallStorageMapper.selectStorageByKey(key);
    }

}
