package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallAdmin;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface SystemAdminService {

    List<CskaoyanMallAdmin> listAllAdmin(String username);

    CskaoyanMallAdmin addAdmin(CskaoyanMallAdmin cskaoyanMallAdmin);

    void deleteAdmin(Integer id);
}
