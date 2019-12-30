package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallRole;
import com.baidu.mall.mapper.CskaoyanMallRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemCharacterServiceImp implements SystemCharacterService {

    @Autowired
    CskaoyanMallRoleMapper cskaoyanMallRoleMapper;

    @Override
    public List<CskaoyanMallRole> queryCharacter(String name) {
        List<CskaoyanMallRole> cskaoyanMallRoleList = cskaoyanMallRoleMapper.selectRoles(name);
        return cskaoyanMallRoleList;
    }

    @Override
    public CskaoyanMallRole addRole(String name, String desc) {
        cskaoyanMallRoleMapper.addRoles(name, desc);
        CskaoyanMallRole cskaoyanMallRole = cskaoyanMallRoleMapper.queryRoleByName(name);
        return cskaoyanMallRole;
    }

    @Override
    public void updateRole(CskaoyanMallRole cskaoyanMallRole) {
        cskaoyanMallRoleMapper.updateByPrimaryKey(cskaoyanMallRole);
    }

    @Override
    public void deleteRole(Integer id) {
        cskaoyanMallRoleMapper.deleteByPrimaryKey(id);
    }
}
