package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallLog;

import java.util.List;

public interface CskaoyanMallLogService {

    List<CskaoyanMallLog> listLog(Integer page, Integer limit, String name, String sort, String order);
}
