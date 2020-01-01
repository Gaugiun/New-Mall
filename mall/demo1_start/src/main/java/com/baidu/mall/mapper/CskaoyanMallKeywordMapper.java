package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallKeyword;

import java.util.List;

public interface CskaoyanMallKeywordMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallKeyword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallKeyword record);

    int updateByPrimaryKey(CskaoyanMallKeyword record);

    List<CskaoyanMallKeyword> selectByKeywordUrl(String keyword, String url);

    int insert(String keyword, String url, Integer isHot, Integer isDefault);

    List<CskaoyanMallKeyword> selectByIsHot(Boolean isHot);

    List<String> selectKeywordsByInput(String keyword);
}
