package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallAllauth;
import com.baidu.mall.bean.CskaoyanMallRole;
import com.baidu.mall.bean.SystemAuthChild;
import com.baidu.mall.bean.SystemCharacters4Options;

import java.util.HashMap;
import java.util.List;

public interface SystemCharacterService {
    public List<CskaoyanMallRole> queryCharacter(String name);

    public CskaoyanMallRole addRole(String name, String desc);

    public void updateRole(CskaoyanMallRole cskaoyanMallRole);

    public void deleteRole(Integer id);

    public List<SystemCharacters4Options> optionCharacter();

    public HashMap queryAuthorityById(Integer roleId);
}
