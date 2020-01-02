package com.baidu.mall.service;

import com.baidu.mall.bean.AuthInfoBean;
import org.springframework.stereotype.Service;

public interface AdminService {
    AuthInfoBean selectByUserName(String userName);
}
