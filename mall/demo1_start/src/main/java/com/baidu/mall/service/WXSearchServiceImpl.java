package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallKeyword;
import com.baidu.mall.bean.CskaoyanMallUser;
import com.baidu.mall.mapper.CskaoyanMallKeywordMapper;
import com.baidu.mall.mapper.CskaoyanMallSearchHistoryMapper;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class WXSearchServiceImpl implements WXSearchService{

    @Autowired
    CskaoyanMallKeywordMapper cskaoyanMallKeywordMapper;

    @Autowired
    CskaoyanMallSearchHistoryMapper cskaoyanMallSearchHistoryMapper;

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Override
    public LinkedHashMap searchIndex() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        CskaoyanMallKeyword defaultKeyword = cskaoyanMallKeywordMapper.selectByPrimaryKey(8);
        List<CskaoyanMallKeyword> cskaoyanMallKeywords = cskaoyanMallKeywordMapper.selectByIsHot(true);
        List<String> historyKeywordList = cskaoyanMallSearchHistoryMapper.selectKeywords();
        linkedHashMap.put("defaultKeyword", defaultKeyword);
        linkedHashMap.put("hotKeywordList", cskaoyanMallKeywords);
        linkedHashMap.put("historyKeywordList", historyKeywordList);
        return linkedHashMap;
    }

    @Override
    public List<String> searchHelper(String keyword) {
        Integer userId = cskaoyanMallUserMapper.selectByUsername("wx").getId();
        cskaoyanMallSearchHistoryMapper.insertNewHistory(userId, keyword);
        return cskaoyanMallKeywordMapper.selectKeywordsByInput(keyword);
    }

    @Override
    public void clearhistory(String username) {
        CskaoyanMallUser cskaoyanMallUser = cskaoyanMallUserMapper.selectByUsername(username);
        Integer id = cskaoyanMallUser.getId();
        cskaoyanMallSearchHistoryMapper.deletByUserId(id);
    }
}
