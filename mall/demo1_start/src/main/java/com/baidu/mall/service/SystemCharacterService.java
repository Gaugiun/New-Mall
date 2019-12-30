package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallRole;

import java.util.List;

public interface SystemCharacterService {
    public List<CskaoyanMallRole> queryCharacter(String name);

    public CskaoyanMallRole addRole(String name, String desc);

    public void updateRole(CskaoyanMallRole cskaoyanMallRole);

    public void deleteRole(Integer id);
}
