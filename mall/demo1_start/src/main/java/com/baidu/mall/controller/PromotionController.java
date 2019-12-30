package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallAd;
import com.baidu.mall.bean.CskaoyanMallStorage;
import com.baidu.mall.service.PromotionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 这是推广管理模块
 * @author KING
 */
@RestController
public class PromotionController {


    /**
     * 这是模糊查询广告的API，默认为查询所有的广告
     * 目前出现的问题：用注入的方式 多例实现失败
     * @return
     */
    @Autowired
    PromotionService promotionService;

    @RequestMapping("admin/ad/list")
    public BaseRespVo adList(Integer page, Integer limit, String name, String content){
        PageHelper.startPage(page, limit);

        List<CskaoyanMallAd> items = promotionService.selectFuzzy(name, content);

        PageInfo<CskaoyanMallAd> total = new PageInfo<>(items);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("items", items);

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 这是编辑广告的API
     * @param cskaoyanMallAd
     * @return
     */
    @RequestMapping("admin/ad/update")
    public BaseRespVo updateAd(@RequestBody CskaoyanMallAd cskaoyanMallAd){
        boolean b = promotionService.updateAd(cskaoyanMallAd);
        Integer id = cskaoyanMallAd.getId();
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        if (b){
            CskaoyanMallAd cskaoyanMallAd1 = promotionService.selectById(id);
            baseRespVo.setData(cskaoyanMallAd1);
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
        }
        return baseRespVo;
    }

    /**
     * 这是上传广告时上传广告图片的API
     * @param file
     * @return
     * @throws IOException
     @RequestMapping("admin/storage/create")
     public BaseRespVo createPic(MultipartFile file, HttpServletRequest request) throws IOException {
     CskaoyanMallStorage pic = promotionService.createPic(file, request);
     BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
     baseRespVo.setData(pic);
     baseRespVo.setErrno(0);
     baseRespVo.setErrmsg("成功");
     return baseRespVo;
     }*/

    /**
     * 这是添加广告的API
     * @param cskaoyanMallAd
     * @return
     */
    @RequestMapping("admin/ad/create")
    public BaseRespVo createAd(@RequestBody CskaoyanMallAd cskaoyanMallAd){
        CskaoyanMallAd addAd = promotionService.createAd(cskaoyanMallAd);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(addAd);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 这是删除广告的API
     * @param cskaoyanMallAd
     * @return
     */
    @RequestMapping("admin/ad/delete")
    public BaseRespVo deleteAd(@RequestBody CskaoyanMallAd cskaoyanMallAd){
        boolean b = promotionService.deleteAd(cskaoyanMallAd);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        if (b){
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        baseRespVo.setErrno(1);
        baseRespVo.setErrmsg("失败");
        return baseRespVo;
    }


}
