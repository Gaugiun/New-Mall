package com.baidu.mall.bean;

import com.baidu.mall.utils.md5.Md5Util;

import java.util.ArrayList;
import java.util.Date;

public class CskaoyanMallAdmin {
    private Integer id;

    private String username;

    private String password;

    private String lastLoginIp;

    private Date lastLoginTime;

    private String avatar;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private ArrayList<Integer> roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {

        String newPassword = password == null ? null : password.trim();
        if (newPassword != null) {
            newPassword = Md5Util.getMd5(newPassword);
        }
        this.password = newPassword;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ArrayList<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(ArrayList<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}