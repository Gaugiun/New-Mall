package com.baidu.mall.service;

import com.baidu.mall.bean.*;

import java.util.List;
import java.util.Map;

public interface MallService {
    List<CskaoyanMallRegion> selectRegion();

    List<CskaoyanMallBrand> selectBrand(String name, Integer id);

    List<CskaoyanMallCategory> selectCategory();

    List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i);

    boolean deleteCategory(Integer id);

    void insertBrand(CskaoyanMallBrand brand);

    CskaoyanMallBrand selectBrandByName(String name);

    boolean deleteBrand(Integer id);

    boolean updateBrand(CskaoyanMallBrand cskaoyanMallBrand);

    List<CskaoyanMallBrand> selectAllBrand();

    void insertCatagory(CskaoyanMallCategory cskaoyanMallCategory);

    CskaoyanMallCategory selectCategoryByName(String name);

    void updateCategory(CskaoyanMallCategory cskaoyanMallCategory);

    List<CskaoyanMallOrder> selectByUserIdOrderIdOrderStatus(Integer userId, Integer id, String[] orderStatus);

    Map<String, Object> queryOrderMsg(Integer id);

    boolean refund(Integer orderId, Integer refundMoney);

    List<CskaoyanMallIssue> queryIssues(String question);

    CskaoyanMallIssue create(String question, String answer);

    boolean deleteIssueById(Integer id);

    void updateIssue(CskaoyanMallIssue cskaoyanMallIssue);

    List<CskaoyanMallKeyword> queryKeywords(String keyword, String url);

    CskaoyanMallKeyword createKeyword(String keyword, String url, Integer isHot, Integer isDefault);

    void updateKeyword(CskaoyanMallKeyword cskaoyanMallKeyword);

    void deleteKeyword(Integer id);
}
