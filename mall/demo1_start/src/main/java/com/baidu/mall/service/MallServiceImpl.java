package com.baidu.mall.service;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MallServiceImpl implements MallService{
    @Autowired
    CskaoyanMallRegionMapper cskaoyanMallRegionMapper;

    @Transactional
    @Override
    public List<CskaoyanMallRegion> selectRegion() {
        List<CskaoyanMallRegion> regions = selectRegionByPId(0);
        return regions;
    }

    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;

    @Override
    public List<CskaoyanMallBrand> selectBrand(String name, Integer id) {
        return cskaoyanMallBrandMapper.selectBrand(name,id);
    }

    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;

    @Override
    public List<CskaoyanMallCategory> selectCategory() {
        List<CskaoyanMallCategory> cskaoyanMallCategories = cskaoyanMallCategoryMapper.selectCategoryByPid(0);
        for (CskaoyanMallCategory category : cskaoyanMallCategories){
            Integer pid = category.getId();
            category.setChildren(cskaoyanMallCategoryMapper.selectCategoryByPid(pid));
        }
        return cskaoyanMallCategories;
    }

    @Override
    public List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i) {
        return cskaoyanMallCategoryMapper.selectCategoryByLevel(i);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        return cskaoyanMallCategoryMapper.deleteCategoryById(id);
    }

    @Override
    public void insertBrand(CskaoyanMallBrand brand) {
        cskaoyanMallBrandMapper.insertBrand(brand);
    }

    @Override
    public CskaoyanMallBrand selectBrandByName(String name) {
        return cskaoyanMallBrandMapper.selectBrandByName(name);
    }

    @Override
    public boolean deleteBrand(Integer id) {
        return cskaoyanMallBrandMapper.deleteBrandById(id);
    }

    @Override
    public boolean updateBrand(CskaoyanMallBrand cskaoyanMallBrand) {
        return cskaoyanMallBrandMapper.updateBrand(cskaoyanMallBrand);
    }

    @Override
    public List<CskaoyanMallBrand> selectAllBrand() {
        return cskaoyanMallBrandMapper.selectAllBrand();
    }

    @Override
    public void insertCatagory(CskaoyanMallCategory cskaoyanMallCategory) {
        cskaoyanMallCategoryMapper.insertCategory(cskaoyanMallCategory);
    }

    @Override
    public CskaoyanMallCategory selectCategoryByName(String name) {
        return cskaoyanMallCategoryMapper.selectCategoryByName(name);
    }

    @Override
    public void updateCategory(CskaoyanMallCategory cskaoyanMallCategory) {
        cskaoyanMallCategoryMapper.updateCategory(cskaoyanMallCategory);
    }

    public List<CskaoyanMallRegion> selectRegionByPId(Integer Pid) {
        List<CskaoyanMallRegion> regions = cskaoyanMallRegionMapper.selectRegionByPId(Pid);
        for(CskaoyanMallRegion region : regions){
            if (region.getType()==3){
                return regions;
            }
            Integer id = region.getId();
            region.setChildren(selectRegionByPId(id));
        }
        return regions;
    }

    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Autowired
    CskaoyanMallOrderGoodsMapper cskaoyanMallOrderGoodsMapper;

    @Autowired
    CskaoyanMallUserFormidMapper cskaoyanMallUserFormidMapper;

    @Autowired
    CskaoyanMallIssueMapper cskaoyanMallIssueMapper;

    @Autowired
    CskaoyanMallKeywordMapper cskaoyanMallKeywordMapper;

    @Override
    public List<CskaoyanMallOrder> selectByUserIdOrderIdOrderStatus(Integer userId, Integer id, String[] orderStatus) {
        if (orderStatus != null) {
            Integer[] statuses = new Integer[orderStatus.length];
            for (int i = 0; i < orderStatus.length; i++) {
                Integer integer = Integer.valueOf(orderStatus[i]);
                statuses[i] = integer;
            }
            return cskaoyanMallOrderMapper.selectByUserIdOrderIdOrderStatus(userId, id, statuses);
        }
        return cskaoyanMallOrderMapper.selectByUserIdOrderId(userId, id);

    }

    @Override
    public Map<String, Object> queryOrderMsg(Integer id) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<CskaoyanMallOrderGoods> cskaoyanMallOrderGoods = cskaoyanMallOrderGoodsMapper.selectById(id);
        Integer orderId = cskaoyanMallOrderGoods.get(0).getOrderId();

        CskaoyanMallOrder cskaoyanMallOrder = cskaoyanMallOrderMapper.selectByOrderId(orderId);
        Integer userId = cskaoyanMallOrder.getUserId();
        CskaoyanMallUser cskaoyanMallUser = cskaoyanMallUserMapper.selectByPrimaryKey(userId);
        map.put("orderGoods", cskaoyanMallOrderGoods);
        map.put("user", cskaoyanMallUser);
        map.put("order", cskaoyanMallOrder);
        return map;
    }

    @Override
    @Transactional
    public boolean refund(Integer orderId, Integer refundMoney) {
        CskaoyanMallOrder cskaoyanMallOrder = cskaoyanMallOrderMapper.selectByPrimaryKey(orderId);
        Integer userFormidId = cskaoyanMallOrder.getUserId();
        int i = cskaoyanMallUserFormidMapper.updateUseAmountById(userFormidId, refundMoney);
        if (i > 0) {
            cskaoyanMallOrderMapper.updateOrderStatusById(orderId);
            return true;
        }
        return false;
    }

    @Override
    public List<CskaoyanMallIssue> queryIssues(String question) {
        return cskaoyanMallIssueMapper.selectByQuestion(question);
    }

    @Override
    @Transactional
    public CskaoyanMallIssue create(String question, String answer) {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        cskaoyanMallIssueMapper.insert(question, answer);
        List<CskaoyanMallIssue> issueList = cskaoyanMallIssueMapper.selectByQuestion(question);
        return issueList.get(0);
    }

    @Override
    @Transactional
    public boolean deleteIssueById(Integer id) {
        int delete = cskaoyanMallIssueMapper.deleteByPrimaryKey(id);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateIssue(CskaoyanMallIssue cskaoyanMallIssue) {
        cskaoyanMallIssueMapper.updateByPrimaryKeySelective(cskaoyanMallIssue);
    }

    @Override
    public List<CskaoyanMallKeyword> queryKeywords(String keyword, String url) {
        return cskaoyanMallKeywordMapper.selectByKeywordUrl(keyword, url);
    }

    @Override
    public CskaoyanMallKeyword createKeyword(String keyword, String url, Integer isHot, Integer isDefault) {
        cskaoyanMallKeywordMapper.insert(keyword, url, isHot, isDefault);
        return cskaoyanMallKeywordMapper.selectByKeywordUrl(keyword , url).get(0);
    }

    @Override
    public void updateKeyword(CskaoyanMallKeyword cskaoyanMallKeyword) {
        cskaoyanMallKeywordMapper.updateByPrimaryKeySelective(cskaoyanMallKeyword);
    }

    @Override
    public void deleteKeyword(Integer id) {
        cskaoyanMallKeywordMapper.deleteByPrimaryKey(id);
    }
}
