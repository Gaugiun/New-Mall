package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthLoginServiceImp implements AuthLoginService {

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Override
    public Map<String, String> loginUser(String username, String password) {
        Map<String, String> user = cskaoyanMallUserMapper.selectByUsername(username, password);
        return user;
    }

}
