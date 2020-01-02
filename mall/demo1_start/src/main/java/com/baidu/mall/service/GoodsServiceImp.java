package com.baidu.mall.service;

import com.baidu.mall.bean.goodsbean.GoodsBrand;
import com.baidu.mall.bean.goodsbean.GoodsCategory;
import com.baidu.mall.bean.goodsbean.GoodsComment;
import com.baidu.mall.bean.goodsbean.GoodsFirstBean;
import com.baidu.mall.mapper.CskaoyanMallBrandMapper;
import com.baidu.mall.mapper.CskaoyanMallCategoryMapper;
import com.baidu.mall.mapper.CskaoyanMallCommentMapper;
import com.baidu.mall.mapper.CskaoyanMallGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {
    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;

    @Autowired
    CskaoyanMallBrandMapper mapperBrand;

    @Autowired
    CskaoyanMallCategoryMapper mapperCategory;

    @Autowired
    CskaoyanMallCommentMapper mapperComment;

    @Override
    public List<GoodsFirstBean> queryGoods(Integer goodsSn , String name) {
        List<GoodsFirstBean> goods = cskaoyanMallGoodsMapper.selectGoods(goodsSn,name);
        return goods;
    }

    @Override
    public List<GoodsBrand> catBrand() {
        List<GoodsBrand> grandList = mapperBrand.selectBrand2();
        return grandList;
    }

    @Override
    public List<GoodsCategory> queryCatery() {
        // List <GoodsCategory> goodsCategories ;
        List<GoodsCategory> categorieslevelList =mapperCategory.selectCatgryByTypeId("L1");
        for ( GoodsCategory categoriesList  : categorieslevelList){
            Integer pid = categoriesList.getValue();
            categoriesList.setChildren(mapperCategory.selectChildren(pid));
       /* List<Children> childrenList = mapperCategory.selectChildren(pid,"L2");
        categoriesList.setChildren(childrenList);*/

        }
        return  categorieslevelList;
    }

    @Override
    public List<GoodsComment> queryGoodscomment(Integer userId, Integer vlueId) {
        List<GoodsComment> commentsList  = mapperComment.selectGoodsComment(userId,vlueId);
        return  commentsList;
    }

    @Override
    public void deleteComment(Integer id) {
        mapperComment.deleteByPrimaryKey(id);
    }
}
