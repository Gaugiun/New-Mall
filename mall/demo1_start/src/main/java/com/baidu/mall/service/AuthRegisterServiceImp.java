package com.baidu.mall.service;

import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import com.baidu.mall.utils.md5.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthRegisterServiceImp implements AuthRegisterService {

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Override
    public void registerUser(String username, String olePassword, String mobile, String lastLoginIp) {
        String password = Md5Util.getMd5(olePassword);
        cskaoyanMallUserMapper.addUser(username, password, mobile, lastLoginIp);
    }
}
