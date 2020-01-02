package com.baidu.mall.service;

import org.springframework.stereotype.Service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.CskaoyanMallAdminMapper;
import com.baidu.mall.mapper.CskaoyanMallAllauthMapper;
import com.baidu.mall.mapper.CskaoyanMallPermissionMapper;
import com.baidu.mall.mapper.CskaoyanMallRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    CskaoyanMallAdminMapper cskaoyanMallAdminMapper;
    @Autowired
    CskaoyanMallPermissionMapper cskaoyanMallPermissionMapper;
    @Autowired
    CskaoyanMallRoleMapper cskaoyanMallRoleMapper;
    @Autowired
    CskaoyanMallAllauthMapper cskaoyanMallAllauthMapper;
    @Override
    public AuthInfoBean selectByUserName(String userName) {
        AuthInfoBean bean = new AuthInfoBean();
        CskaoyanMallAdmin cskaoyanMallAdmin = cskaoyanMallAdminMapper.selectByName(userName);
        ArrayList<Integer> roleIds = cskaoyanMallAdmin.getRoleIds();
        Integer remove = roleIds.remove(0);
        List list = new ArrayList();
        List<CskaoyanMallPermission> cskaoyanMallPermissions = cskaoyanMallPermissionMapper.selectByRoleId(remove);


        CskaoyanMallAllauthExample cskaoyanMallAllauthExample = new CskaoyanMallAllauthExample();
        cskaoyanMallAllauthExample.createCriteria().andApiIsNotNull();
        List<CskaoyanMallAllauth> cskaoyanMallAllauths = cskaoyanMallAllauthMapper.selectByExample(cskaoyanMallAllauthExample);

        for (CskaoyanMallPermission cskaoyanMallPermission : cskaoyanMallPermissions) {
            if (cskaoyanMallPermission.getPermission().equals("*")){

                for (CskaoyanMallAllauth cskaoyanMallAllauth : cskaoyanMallAllauths) {
                    list.add(cskaoyanMallAllauth.getApi());
                }

            }else {
                for (CskaoyanMallAllauth cskaoyanMallAllauth : cskaoyanMallAllauths) {
                    if (cskaoyanMallPermission.getPermission().equals(cskaoyanMallAllauth.getId())){
                        list.add(cskaoyanMallAllauth.getApi());
                    }
                }

            }
        }
        CskaoyanMallRole cskaoyanMallRole = cskaoyanMallRoleMapper.selectByPrimaryKey(remove);
        List roles = new ArrayList();
        roles.add(cskaoyanMallRole.getName());
        bean.setName(cskaoyanMallAdmin.getUsername());
        bean.setAvatar(cskaoyanMallAdmin.getAvatar());
        bean.setRoles(roles);
        bean.setPerms(list);
        return bean;
    }
}
