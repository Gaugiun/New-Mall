package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallAd;
import com.baidu.mall.bean.CskaoyanMallStorage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface PromotionService {
    List<CskaoyanMallAd> selectFuzzy(String name, String content);
    boolean updateAd(CskaoyanMallAd cskaoyanMallAd);

    CskaoyanMallAd selectById(Integer id);
   // CskaoyanMallStorage createPic(MultipartFile file, HttpServletRequest request) throws IOException;

    CskaoyanMallAd createAd(CskaoyanMallAd cskaoyanMallAd);

    boolean deleteAd(CskaoyanMallAd cskaoyanMallAd);
}
