package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallLog;
import com.baidu.mall.mapper.CskaoyanMallLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CskaoyanMallLogServiceImp implements CskaoyanMallLogService {

    @Autowired
    CskaoyanMallLogMapper cskaoyanMallLogMapper;

    @Override
    public List<CskaoyanMallLog> listLog(Integer page, Integer limit, String name, String sort, String order) {
        List<CskaoyanMallLog> cskaoyanMallLogList = cskaoyanMallLogMapper.selectAllByName(name);
        return cskaoyanMallLogList;
    }
}
