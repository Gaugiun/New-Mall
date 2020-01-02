package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsDetailServiceImp implements GoodsDetailService {
    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;

    @Autowired
    CskaoyanMallGoodsSpecificationMapper cskaoyanMallGoodsSpecificationMapper;

    @Autowired
    CskaoyanMallGoodsProductMapper cskaoyanMallGoodsProductMapper;

    @Autowired
    CskaoyanMallGoodsAttributeMapper cskaoyanMallGoodsAttributeMapper;

    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;


    @Override
    public Map<String, Object> showGoodDetail(Integer id) {
        HashMap<String, Object> map = new HashMap<>();

        CskaoyanMallGoods cskaoyanMallGoods = cskaoyanMallGoodsMapper.selectByPrimaryKey(id);
        map.put("goods", cskaoyanMallGoods);
        Integer categoryId = cskaoyanMallGoods.getCategoryId();

        Integer pid = cskaoyanMallCategoryMapper.selectByPrimaryKey(categoryId).getPid();
        Integer[] category = {categoryId, pid};
        map.put("categoryIds", category);

        List<CskaoyanMallGoodsSpecification> cskaoyanMallGoodsSpecificationList = cskaoyanMallGoodsSpecificationMapper.selectGoodsSpecificationByGoodsId(id);
        map.put("specifications", cskaoyanMallGoodsSpecificationList);

        List<CskaoyanMallGoodsAttribute> cskaoyanMallGoodsAttributeList = cskaoyanMallGoodsAttributeMapper.selectAttributeByGoodsId(id);
        map.put("attributes", cskaoyanMallGoodsAttributeList);

        List<CskaoyanMallGoodsProduct> cskaoyanMallGoodsProductList = cskaoyanMallGoodsProductMapper.selectsByGoodsId(id);
        map.put("products", cskaoyanMallGoodsProductList);


        return map;
    }
}
