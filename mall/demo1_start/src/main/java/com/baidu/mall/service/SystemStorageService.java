package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallStorage;

import java.util.List;

public interface SystemStorageService {
    List<CskaoyanMallStorage> selectStorage(String key, String name);

    void insertStorage(CskaoyanMallStorage storage);

    CskaoyanMallStorage selectStorageByKey(String key);
}
