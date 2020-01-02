package com.baidu.mall.service;

import com.baidu.mall.mapper.CskaoyanMallGoodsAttributeMapper;
import com.baidu.mall.mapper.CskaoyanMallGoodsMapper;
import com.baidu.mall.mapper.CskaoyanMallGoodsProductMapper;
import com.baidu.mall.mapper.CskaoyanMallGoodsSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class GoodsCreateServiceImp implements GoodsCreateService {
    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;

    @Autowired
    CskaoyanMallGoodsSpecificationMapper cskaoyanMallGoodsSpecificationMapper;

    @Autowired
    CskaoyanMallGoodsProductMapper cskaoyanMallGoodsProductMapper;

    @Autowired
    CskaoyanMallGoodsAttributeMapper cskaoyanMallGoodsAttributeMapper;

    @Override
    public void addGood(Map<String, Object> json) {
        LinkedHashMap<String, Object> goods = (LinkedHashMap<String, Object>) json.get("goods");
        cskaoyanMallGoodsMapper.addGood(goods);
//        Integer goodsId = (Integer) goods.get("id");
        Integer goodsId = cskaoyanMallGoodsMapper.selectLastInsertId();


        ArrayList<LinkedHashMap<String, Object>> specificationsList = (ArrayList<LinkedHashMap<String, Object>>) json.get("specifications");
        for (int i=0; i<specificationsList.size(); i++) {
            specificationsList.get(i).put("goodsId", goodsId);
            cskaoyanMallGoodsSpecificationMapper.addSpecification(specificationsList.get(i));
        }

        ArrayList<LinkedHashMap<String, Object>> productsList = (ArrayList<LinkedHashMap<String, Object>>) json.get("products");
        for (int i=0; i<productsList.size(); i++) {
            productsList.get(i).put("goodsId", goodsId);
            cskaoyanMallGoodsProductMapper.addProduct(productsList.get(i));
        }

        ArrayList<LinkedHashMap<String, Object>> attributesList = (ArrayList<LinkedHashMap<String, Object>>) json.get("attributes");
        for (int i=0; i<attributesList.size(); i++) {
            attributesList.get(i).put("goodsId", goodsId);
            cskaoyanMallGoodsAttributeMapper.addGoodsAttribute(attributesList.get(i));
        }
    }


    @Override
    public void updateGoods(Map<String, Object> json) {
        LinkedHashMap<String, Object> goods = (LinkedHashMap<String, Object>) json.get("goods");
        cskaoyanMallGoodsMapper.updateGoodByPrimaryKey(goods);

        ArrayList<LinkedHashMap<String, Object>> specificationsList = (ArrayList<LinkedHashMap<String, Object>>) json.get("specifications");
        for (int i = 0; i < specificationsList.size(); i++) {
            cskaoyanMallGoodsSpecificationMapper.updateSpecificationByPrimaryKey(specificationsList.get(i));
        }

        ArrayList<LinkedHashMap<String, Object>> productsList = (ArrayList<LinkedHashMap<String, Object>>) json.get("products");
        for (int i = 0; i < productsList.size(); i++) {
            cskaoyanMallGoodsProductMapper.updateProductByPrimaryId(productsList.get(i));
        }

        ArrayList<LinkedHashMap<String, Object>> attributesList = (ArrayList<LinkedHashMap<String, Object>>) json.get("attributes");
        for (int i = 0; i < attributesList.size(); i++) {
            cskaoyanMallGoodsAttributeMapper.updateAttributeByPrimary(attributesList.get(i));
        }
    }
}
