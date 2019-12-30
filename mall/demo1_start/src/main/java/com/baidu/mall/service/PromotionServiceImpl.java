package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallAd;
import com.baidu.mall.bean.CskaoyanMallAdExample;
import com.baidu.mall.bean.CskaoyanMallStorage;
import com.baidu.mall.bean.CskaoyanMallStorageExample;
import com.baidu.mall.mapper.CskaoyanMallAdMapper;
import com.baidu.mall.mapper.CskaoyanMallStorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 *这是推广管理模块的service层
 * @author KING
 */
@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class PromotionServiceImpl implements PromotionService {

    /**
     * 这是查询所有广告的API
     */
    @Autowired
    CskaoyanMallAdMapper cskaoyanMallAdMapper;
    @Autowired
    CskaoyanMallStorageMapper cskaoyanMallStorageMapper;


    @Override
    public List<CskaoyanMallAd> selectFuzzy(String name, String content){
        CskaoyanMallAdExample cskaoyanMallAdExample = new CskaoyanMallAdExample();
        if (name != null && content != null) {
            name = "%" + name + "%";
            content = "%" + content + "%";
            cskaoyanMallAdExample.createCriteria().andContentLike(content).andNameLike(name);
        }else if ((name == null || "".equals(name)) && content != null){
            content = "%" + content + "%";
            cskaoyanMallAdExample.createCriteria().andContentLike(content);
        }else if ((content == null || "".equals(content)) && name != null){
            name = "%" + name + "%";
            cskaoyanMallAdExample.createCriteria().andNameLike(name);
        }else {
            cskaoyanMallAdExample.createCriteria();
        }
        return cskaoyanMallAdMapper.selectByExample(cskaoyanMallAdExample);
    }

    /**
     * 编辑广告的API的前一部分
     * @param cskaoyanMallAd
     * @return
     */
    @Override
    public boolean updateAd(CskaoyanMallAd cskaoyanMallAd) {
        int i = cskaoyanMallAdMapper.updateByPrimaryKey(cskaoyanMallAd);
        if (i > 0){
            return true;
        }
        return false;
    }

    /**
     * 编辑广告的后一部分，查询该编辑后的广告
     * @param id
     * @return
     */
    @Override
    public CskaoyanMallAd selectById(Integer id) {
        CskaoyanMallAd cskaoyanMallAd = cskaoyanMallAdMapper.selectByPrimaryKey(id);
        return cskaoyanMallAd;
    }

    /**
     * 创建一个广告
     * @param cskaoyanMallAd
     * @return
     */
    @Override
    public CskaoyanMallAd createAd(CskaoyanMallAd cskaoyanMallAd) {
        long currentTimeMillis = System.currentTimeMillis();
        Date timestamp = new Date(currentTimeMillis);
        cskaoyanMallAd.setId(null);
        cskaoyanMallAd.setAddTime(timestamp);
        cskaoyanMallAd.setUpdateTime(timestamp);
        cskaoyanMallAd.setDeleted(false);
        int insert = cskaoyanMallAdMapper.insert(cskaoyanMallAd);
        if (insert > 0){
            return cskaoyanMallAd;
        }
        return null;
    }

    @Override
    public boolean deleteAd(CskaoyanMallAd cskaoyanMallAd) {
        int i = cskaoyanMallAdMapper.deleteByPrimaryKey(cskaoyanMallAd.getId());
        return i > 0;
    }
}
