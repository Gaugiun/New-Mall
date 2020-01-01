package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxGoodsServiceImpl implements WxGoodsService {
    @Autowired
    CskaoyanMallGoodsSpecificationMapper cskaoyanMallGoodsSpecificationMapper;
    @Autowired
    CskaoyanMallIssueMapper cskaoyanMallIssueMapper;
    @Autowired
    CskaoyanMallGoodsAttributeMapper cskaoyanMallGoodsAttributeMapper;
    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;
    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;
    @Autowired
    CskaoyanMallGoodsProductMapper cskaoyanMallGoodsProductMapper;
    @Autowired
    CskaoyanMallCommentMapper commentMap;
    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;


    @Override
    public BaseWxGoodsVo selectGoodsDetail(Integer id, BaseWxGoodsVo baseWxGoodsVo) {
/*        private List<CskaoyanMallGoodsSpecificationToNameValueList> specificationList;  CskaoyanMallGoodsSpecificationToNameValueList -- name / list
        private List<CskaoyanMallGroupon> groupon;
        private List<CskaoyanMallIssue> issue;
        private Integer userHasCollect;
        private String shareImage;
        private Map comment;
        private List<CskaoyanMallGoodsAttribute> attribute;
        private CskaoyanMallBrand brand;
        private List<CskaoyanMallGoodsProduct> productList;
        private CskaoyanMallGoods info;*/

        List<String> specList = cskaoyanMallGoodsSpecificationMapper.selectGoodsSpecificationGroupByGoodsId(id);
        int size = specList.size();
        List<Map> mapList = new ArrayList<>();
        Map specMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            specMap.put("name",specList.get(i));
            specMap.put("valueList",cskaoyanMallGoodsSpecificationMapper.selectGoodsSpecificationByGoodsIdAndSpec(id,specList.get(i)));
            mapList.add(specMap);
        }

        List<CskaoyanMallComment> comments = commentMap.selectByValueId(id);
        Map commentMap = new HashMap<>();
        commentMap.put("data",comments);
        commentMap.put("count",comments.size());

        baseWxGoodsVo.setSpecificationList(mapList);
        //baseWxGoodsVo.setGroupon();
        baseWxGoodsVo.setIssue(cskaoyanMallIssueMapper.selectAllIssue());
        //baseWxGoodsVo.setUserHasCollect();
        // goods.shareUrl???
        baseWxGoodsVo.setShareImage(cskaoyanMallGoodsMapper.selectShareUrlById(id));
        baseWxGoodsVo.setComment(commentMap);
        baseWxGoodsVo.setAttribute(cskaoyanMallGoodsAttributeMapper.selectAttributeByGoodsId(id));
        baseWxGoodsVo.setBrand(cskaoyanMallBrandMapper.selectBrandByGoodsId(id));
        baseWxGoodsVo.setProductList(cskaoyanMallGoodsProductMapper.selectById(id));
        baseWxGoodsVo.setInfo(cskaoyanMallGoodsMapper.selectByPrimaryKey(id));

        return baseWxGoodsVo;
    }

    @Override
    public List<CskaoyanMallGoods> selectGoodsListByCategoryId(Integer categoryId) {
        return cskaoyanMallGoodsMapper.selectGoodsListByCategoryId(categoryId);
    }

    @Override
    public List<CskaoyanMallCategory> selectFilterCategoryListByCategoryId(Integer categoryId) {
        return cskaoyanMallCategoryMapper.selectFilterCategoryListByCategoryId(categoryId);
    }

    @Override
    public List<BaseWxGoods> selectGoodsListByKeywords(String keyword) {
        return cskaoyanMallGoodsMapper.selectGoodsListByKeywords(keyword);
    }
}
