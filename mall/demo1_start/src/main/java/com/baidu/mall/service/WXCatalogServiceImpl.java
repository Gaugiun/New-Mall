package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WXCatalogServiceImpl implements WXCatalogService {
    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;
    @Autowired
    CskaoyanMallGoodsMapper cskaoyanMallGoodsMapper;
    @Autowired
    CskaoyanMallCouponMapper cskaoyanMallCouponMapper;
    @Autowired
    CskaoyanMallCouponUserMapper cskaoyanMallCouponUserMapper;
    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;
    @Autowired
    CskaoyanMallAddressMapper cskaoyanMallAddressMapper;
    @Autowired
    CskaoyanMallRegionMapper cskaoyanMallRegionMapper;
    @Autowired
    CskaoyanMallFeedbackMapper cskaoyanMallFeedbackMapper;

    //表示数据库中的总共的商品数量  李雅雯已写 等会可以删除
    private Integer goodsCount;

    @Override
    public HashMap catalogIndex(){
        HashMap hashMap = new HashMap();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List<CskaoyanMallCategory> cskaoyanMallCategories = cskaoyanMallCategoryMapper.select();

        goodsCount = cskaoyanMallCategories.size();

        CskaoyanMallCategory mallCategory = cskaoyanMallCategories.get(0);
        for (CskaoyanMallCategory cskaoyanMallCategory : cskaoyanMallCategories) {
            if ("L1".equals(cskaoyanMallCategory.getLevel())){
                list1.add(cskaoyanMallCategory);
            }
            if ("L2".equals(cskaoyanMallCategory.getLevel()) && mallCategory.getId().equals(cskaoyanMallCategory.getPid())){
                list2.add(cskaoyanMallCategory);
            }
        }
        hashMap.put("currentCategory", mallCategory);
        hashMap.put("categoryList", list1);
        hashMap.put("currentSubCategory", list2);
        return hashMap;
    }

    @Override
    public Integer count(){
        return goodsCount;
    }

    @Override
    public HashMap currenIndex(Integer id) {
        HashMap hashMap = new HashMap();
        CskaoyanMallCategory mallCategory = cskaoyanMallCategoryMapper.selectByPrimaryKey(id);
        List<CskaoyanMallCategory> cskaoyanMallCategories = cskaoyanMallCategoryMapper.selectCategoryByPid(id);
        hashMap.put("currentCategory", mallCategory);
        hashMap.put("currentSubCategory", cskaoyanMallCategories);
        return hashMap;
    }

    @Override
    public HashMap categoryIndex(Integer id) {
        HashMap hashMap = new HashMap();
        CskaoyanMallCategory mallCategory = cskaoyanMallCategoryMapper.selectByPrimaryKey(id);
        Integer parentCategoryId = mallCategory.getPid();
        CskaoyanMallCategory cskaoyanMallCategory = cskaoyanMallCategoryMapper.selectByPrimaryKey(parentCategoryId);
        List<CskaoyanMallCategory> cskaoyanMallCategories = cskaoyanMallCategoryMapper.selectByPidAndLevel(parentCategoryId);
        hashMap.put("currentCategory", mallCategory);
        hashMap.put("brotherCategory", cskaoyanMallCategories);
        hashMap.put("parentCategory", cskaoyanMallCategory);
        return hashMap;
    }

    @Override
    public List<CskaoyanMallGoods> goodsList(Integer categoryId) {
        List<CskaoyanMallGoods> cskaoyanMallGoods = cskaoyanMallGoodsMapper.selectByCategoryId(categoryId);
        return cskaoyanMallGoods;
    }

    @Override
    public CskaoyanMallUser authLogin(String username, String password) {
        CskaoyanMallUser cskaoyanMallUser = cskaoyanMallUserMapper.selectByUsernameAndPassword(username, password);
        return cskaoyanMallUser;
    }

    @Override
    public boolean couponReceive(Integer userId, Integer couponId) {
        CskaoyanMallCouponUserExample cskaoyanMallCouponUserExample = new CskaoyanMallCouponUserExample();
        cskaoyanMallCouponUserExample.createCriteria().andUserIdEqualTo(userId).andCouponIdEqualTo(couponId);
        List<CskaoyanMallCouponUser> cskaoyanMallCouponUsers = cskaoyanMallCouponUserMapper.selectByExample(cskaoyanMallCouponUserExample);
        int size = cskaoyanMallCouponUsers.size();
        if (size == 0){
            CskaoyanMallCouponUser couponUser = new CskaoyanMallCouponUser();
            couponUser.setId(null);
            couponUser.setUserId(userId);
            couponUser.setCouponId(couponId);
            couponUser.setStatus((short) 0);
            couponUser.setUsedTime(null);
            CskaoyanMallCoupon cskaoyanMallCoupon = cskaoyanMallCouponMapper.selectByPrimaryKey(couponId);
            couponUser.setStartTime(cskaoyanMallCoupon.getStartTime());
            couponUser.setEndTime(cskaoyanMallCoupon.getEndTime());
            couponUser.setOrderId(null);
            long l = System.currentTimeMillis();
            Date date = new Date(l);
            couponUser.setAddTime(date);
            couponUser.setUpdateTime(date);
            couponUser.setDeleted(false);
            int insert = cskaoyanMallCouponUserMapper.insert(couponUser);
            return insert > 0;
        }
        return false;
    }

    @Override
    public List<CskaoyanMallCouponUser> couponMylist(Integer userId, Short status) {
        CskaoyanMallCouponUserExample cskaoyanMallCouponUserExample = new CskaoyanMallCouponUserExample();
        cskaoyanMallCouponUserExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status);
        List<CskaoyanMallCouponUser> couponUserList = cskaoyanMallCouponUserMapper.selectByExample(cskaoyanMallCouponUserExample);
        return couponUserList;
    }

    @Override
    public List<CskaoyanMallCoupon> couponExchange(String code) {
        CskaoyanMallCouponExample cskaoyanMallCouponExample = new CskaoyanMallCouponExample();
        cskaoyanMallCouponExample.createCriteria().andCodeEqualTo(code);
        List<CskaoyanMallCoupon> cskaoyanMallCoupons = cskaoyanMallCouponMapper.selectByExample(cskaoyanMallCouponExample);
        return cskaoyanMallCoupons;
    }

    @Override
    public List<CskaoyanMallAddress> addressList() {
        return cskaoyanMallAddressMapper.select();
    }

    @Override
    public Integer addressSave(CskaoyanMallAddress cskaoyanMallAddress) {
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        cskaoyanMallAddress.setId(null);
        cskaoyanMallAddress.setAddTime(date);
        cskaoyanMallAddress.setUpdateTime(date);
        cskaoyanMallAddress.setDeleted(false);
        Integer i = cskaoyanMallAddressMapper.insert(cskaoyanMallAddress);
        Integer id = cskaoyanMallAddress.getId();
        return id;
    }

    @Override
    public CskaoyanMallAddress addressDetail(Integer id) {
        return cskaoyanMallAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean addressDelete(Integer id) {
        //int i = cskaoyanMallAddressMapper.deleteByPrimaryKey(id);
        int i = cskaoyanMallAddressMapper.updateDeleteById(id);
        return i > 0;
    }

    @Override
    public List<CskaoyanMallRegion> regionList(Integer pid) {
        List<CskaoyanMallRegion> cskaoyanMallRegions = cskaoyanMallRegionMapper.selectByPid(pid);
        return cskaoyanMallRegions;
    }

    @Override
    public boolean feedbackSubmit(Integer userId, CskaoyanMallFeedback cskaoyanMallFeedback) {
        CskaoyanMallUser cskaoyanMallUser = cskaoyanMallUserMapper.selectByPrimaryKey(userId);
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        cskaoyanMallFeedback.setAddTime(date);
        cskaoyanMallFeedback.setUpdateTime(date);
        cskaoyanMallFeedback.setUserId(userId);
        cskaoyanMallFeedback.setUsername(cskaoyanMallUser.getUsername());
        cskaoyanMallFeedback.setId(null);
        cskaoyanMallFeedback.setStatus(0);
        cskaoyanMallFeedback.setDeleted(false);
        if (cskaoyanMallFeedback.getHasPicture() == false){
            cskaoyanMallFeedback.setPicUrls(null);
        }
        boolean b =  cskaoyanMallFeedbackMapper.insert(cskaoyanMallFeedback);
        return false;
    }
}
