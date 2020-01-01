package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallAdmin;
import com.baidu.mall.mapper.CskaoyanMallAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAdminServiceImp implements SystemAdminService {

    @Autowired
    CskaoyanMallAdminMapper cskaoyanMallAdminMapper;

    @Override
    public List<CskaoyanMallAdmin> listAllAdmin(String username) {
        List<CskaoyanMallAdmin> cskaoyanMallAdminList = cskaoyanMallAdminMapper.selectALL(username);
        return cskaoyanMallAdminList;
    }

    @Override
    public CskaoyanMallAdmin addAdmin(CskaoyanMallAdmin cskaoyanMallAdmin) {
        cskaoyanMallAdminMapper.addAdmin(cskaoyanMallAdmin);
        CskaoyanMallAdmin cskaoyanMallAdmin1 = cskaoyanMallAdminMapper.selectByName(cskaoyanMallAdmin.getUsername());
        return cskaoyanMallAdmin;
    }

    @Override
    public void deleteAdmin(Integer id) {
        cskaoyanMallAdminMapper.deleteByPrimaryKey(id);
    }
}
