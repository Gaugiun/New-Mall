package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallCoupon;
import com.baidu.mall.bean.CskaoyanMallCouponExample;
import com.baidu.mall.mapper.CskaoyanMallCouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class CouponServiceImpl implements CouponService {
    @Autowired
    CskaoyanMallCouponMapper cskaoyanMallCouponMapper;
    @Override
    public List<CskaoyanMallCoupon> selectFuzzy(String name, Short type, Short status) {
        CskaoyanMallCouponExample cskaoyanMallCouponExample = new CskaoyanMallCouponExample();

        if (name != null && type != null && status != null){
            name = "%" + name +"%";
            cskaoyanMallCouponExample.createCriteria().andNameEqualTo(name).andTypeEqualTo(type).andStatusEqualTo(status);
        }else if (name != null && (type == null || "".equals(type)) && (status == null || "".equals(status))){
            name = "%" + name +"%";
            cskaoyanMallCouponExample.createCriteria().andNameEqualTo(name);
        }else if ((name == null || "".equals(name)) && type != null && status != null){
            cskaoyanMallCouponExample.createCriteria().andTypeEqualTo(type).andStatusEqualTo(status);
        }else if ((name == null || "".equals(name)) && (type == null || "".equals(type)) && status != null){
            cskaoyanMallCouponExample.createCriteria().andStatusEqualTo(status);
        }else if ((name == null || "".equals(name)) && type != null && (status == null || "".equals(status))){
            cskaoyanMallCouponExample.createCriteria().andTypeEqualTo(type);
        }else if (name != null && type != null && (status == null || "".equals(status))){
            cskaoyanMallCouponExample.createCriteria().andNameEqualTo(name).andTypeEqualTo(type);
        }else if (name != null && (type == null || "".equals(type)) && status != null){
            cskaoyanMallCouponExample.createCriteria().andNameEqualTo(name).andStatusEqualTo(status);
        }
        List<CskaoyanMallCoupon> cskaoyanMallCoupons = cskaoyanMallCouponMapper.selectByExample(cskaoyanMallCouponExample);
        return cskaoyanMallCoupons;
    }

    @Override
    public CskaoyanMallCoupon updateCoupon(CskaoyanMallCoupon cskaoyanMallCoupon) {
        int i = cskaoyanMallCouponMapper.updateByPrimaryKey(cskaoyanMallCoupon);
        long currentTimeMillis = System.currentTimeMillis();
        Date timestamp = new Date(currentTimeMillis);
        cskaoyanMallCoupon.setUpdateTime(timestamp);
        return cskaoyanMallCouponMapper.selectByPrimaryKey(cskaoyanMallCoupon.getId());
    }

    @Override
    public CskaoyanMallCoupon readCoupon(Integer id) {
        return cskaoyanMallCouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public HashMap listUser(Integer couponId) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        hashMap.put("total", 0);
        hashMap.put("items", arrayList);
        return hashMap;
    }

    @Override
    public boolean deleteCoupon(CskaoyanMallCoupon cskaoyanMallCoupon) {
        int i = cskaoyanMallCouponMapper.deleteByPrimaryKey(cskaoyanMallCoupon.getId());
        return i > 0;
    }

    @Override
    public CskaoyanMallCoupon createCoupon(Map<String, Object> map) {
        CskaoyanMallCoupon cskaoyanMallCoupon = new CskaoyanMallCoupon();
        cskaoyanMallCoupon.setStartTime((java.util.Date) map.get("startTime"));
        cskaoyanMallCoupon.setName((String) map.get("name"));
        cskaoyanMallCoupon.setDesc((String) map.get("desc"));
        cskaoyanMallCoupon.setTag((String) map.get("tag"));
        cskaoyanMallCoupon.setTotal(Integer.parseInt((String) map.get("total")));
        cskaoyanMallCoupon.setDiscount(BigDecimal.valueOf((Long.parseLong((String) map.get("discount")))));
        cskaoyanMallCoupon.setMin(BigDecimal.valueOf((Long.parseLong((String) map.get("min")))));
        cskaoyanMallCoupon.setLimit(Short.parseShort((String) map.get("limit")));
        cskaoyanMallCoupon.setType(Short.parseShort(String.valueOf(map.get("type"))));
        cskaoyanMallCoupon.setStatus(Short.parseShort(String.valueOf(map.get("status"))));
        cskaoyanMallCoupon.setGoodsType(Short.parseShort(String.valueOf(map.get("goodsType"))));
        cskaoyanMallCoupon.setGoodsValue(map.get("goodsValue").toString());
        cskaoyanMallCoupon.setTimeType(Short.parseShort(String.valueOf(map.get("timeType"))));
        cskaoyanMallCoupon.setDays(Short.parseShort(String.valueOf(map.get("days"))));
        cskaoyanMallCoupon.setStartTime((Date) map.get("startTime"));
        cskaoyanMallCoupon.setEndTime((Date) map.get("endTime"));
        cskaoyanMallCoupon.setId(null);
        int insert = cskaoyanMallCouponMapper.insert(cskaoyanMallCoupon);

        CskaoyanMallCouponExample cskaoyanMallCouponExample = new CskaoyanMallCouponExample();
        cskaoyanMallCouponExample.createCriteria().andNameEqualTo(cskaoyanMallCoupon.getName());
        List<CskaoyanMallCoupon> cskaoyanMallCoupons = cskaoyanMallCouponMapper.selectByExample(cskaoyanMallCouponExample);
        return cskaoyanMallCoupons.remove(0);
    }
}
