package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.CskaoyanMallAllauthMapper;
import com.baidu.mall.mapper.CskaoyanMallPermissionMapper;
import com.baidu.mall.mapper.CskaoyanMallRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public List<SystemCharacters4Options> optionCharacter() {
        List<SystemCharacters4Options> systemCharacters4Options = cskaoyanMallRoleMapper.showRoles();
        return systemCharacters4Options;
    }

    @Autowired
    CskaoyanMallAllauthMapper cskaoyanMallAllauthMapper;

    @Autowired
    CskaoyanMallPermissionMapper cskaoyanMallPermissionMapper;

    @Override
    public HashMap queryAuthorityById(Integer roleId) {
        CskaoyanMallAllauthExample cskaoyanMallAllauthExample = new CskaoyanMallAllauthExample();
        CskaoyanMallAllauthExample.Criteria criteria = cskaoyanMallAllauthExample.createCriteria();
        criteria.andPidEqualTo(0);
        List<CskaoyanMallAllauth> cskaoyanMallAllauthList = selectAllAuthor(cskaoyanMallAllauthExample);

        HashMap<String, Object> map = new HashMap<>();
        map.put("systemPermissions", cskaoyanMallAllauthList);
        List<CskaoyanMallPermission> cskaoyanMallPermissionList =cskaoyanMallPermissionMapper.selectByRoleId(roleId);
        map.put("assignedPermissions", cskaoyanMallPermissionList);
        return map;
    }

    public List<CskaoyanMallAllauth> selectAllAuthor(CskaoyanMallAllauthExample cskaoyanMallAllauthExample) {
        List<CskaoyanMallAllauth> systemAuthChildrenList = cskaoyanMallAllauthMapper.selectByExample(cskaoyanMallAllauthExample);
        for (CskaoyanMallAllauth cskaoyanMallAllauth : systemAuthChildrenList) {
            Integer pid = cskaoyanMallAllauth.getPrimaryId();
            cskaoyanMallAllauthExample.clear();
            cskaoyanMallAllauthExample.createCriteria().andPidEqualTo(pid);
            cskaoyanMallAllauth.setChildren(selectAllAuthor(cskaoyanMallAllauthExample));
            if (pid == 37) {
                return systemAuthChildrenList;
            }
        }
        return systemAuthChildrenList;
    }
}
