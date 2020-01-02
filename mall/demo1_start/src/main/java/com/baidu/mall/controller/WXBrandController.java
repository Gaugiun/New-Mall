package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.BaseWxBrandListVo;
import com.baidu.mall.bean.CskaoyanMallBrand;
import com.baidu.mall.mapper.CskaoyanMallBrandMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("wx")
@RestController
public class WXBrandController {

    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;


    @RequestMapping("brand/detail")
    public BaseRespVo UserList(@RequestParam("id") Integer id){
        CskaoyanMallBrand cskaoyanMallBrand = cskaoyanMallBrandMapper.selectByPrimaryKey(id);

        Map<Object, Object> map = new HashMap<>();
        map.put("brand",cskaoyanMallBrand);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(map);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("brand/list")
    public BaseRespVo BrandList(Integer brandId,
                                @RequestParam("page") Integer page,
                                @RequestParam("size") Integer size) {
        PageHelper.startPage(page, size);
        BaseWxBrandListVo baseWxBrandListVo = new BaseWxBrandListVo();
        List<CskaoyanMallBrand> cskaoyanMallBrand = cskaoyanMallBrandMapper.selectAllBrand();
        baseWxBrandListVo.setBrandList(cskaoyanMallBrand);
        baseWxBrandListVo.setTotalPages(7);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(baseWxBrandListVo);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
