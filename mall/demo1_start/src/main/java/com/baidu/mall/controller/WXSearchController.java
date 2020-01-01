package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.service.WXSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信小程序 搜索界面 wx/search
 */
@RestController
@RequestMapping("wx")
public class WXSearchController {

    @Autowired
    WXSearchService wxSearchService;

    /**
     * 搜索界面 wx/search/index
     */
    @RequestMapping("search/index")
    public BaseRespVo searchIndex() {
        BaseRespVo baseRespVo = new BaseRespVo();
        LinkedHashMap linkedHashMap = wxSearchService.searchIndex();
        baseRespVo.setErrno(0);
        baseRespVo.setData(linkedHashMap);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 搜索帮助 wx/search/helper
     */
    @RequestMapping("search/helper")
    public BaseRespVo searchHelper(String keyword) {
        BaseRespVo baseRespVo = new BaseRespVo();
        List<String> keywords =  wxSearchService.searchHelper(keyword);
        String[] keywordsArray = new String[keywords.size()];
        for (int i = 0; i < keywords.size(); i++) {
            keywordsArray[i] = keywords.get(i);
        }
        baseRespVo.setErrno(0);
        baseRespVo.setData(keywordsArray);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 清除搜索记录 wx/search/clearhistory
     */
    @RequestMapping("search/clearhistory")
    public BaseRespVo clearHistory(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        wxSearchService.clearhistory("wx");
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
