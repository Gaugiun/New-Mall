package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallUser;

import java.util.Map;

public interface AuthLoginService {
    public Map<String, String> loginUser(String username, String password);

}
