package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallCollect;
import com.baidu.mall.bean.WxCollectGoods;
import com.baidu.mall.service.WXCollectService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("wx")
public class WXCollectController {

    @Autowired
    WXCollectService wxCollectService;

    /**
     * 显示收藏列表 wx/collect/list
     */
    @RequestMapping("collect/list")
    public BaseRespVo collectList(Byte type, Integer page, Integer size) {
        BaseRespVo baseRespVo = new BaseRespVo();
        PageHelper.startPage(page, size);
        List<WxCollectGoods> cskaoyanMallCollects = wxCollectService.collectList(type);
        Map map = new LinkedHashMap();
        map.put("totalPages", (cskaoyanMallCollects.size() / size));
        map.put("collectList", cskaoyanMallCollects);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 添加或取消收藏 wx/collect/addordelete
     */
    @RequestMapping("collect/addordelete")
    public BaseRespVo addOrDeleteCollect(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer type = (Integer) map.get("type");
        Integer valueId = (Integer) map.get("valueId");
        Map map1 = new LinkedHashMap();
        String changeType = wxCollectService.addOrDeleteCollect(Byte.parseByte(type.toString()), valueId);
        map1.put("type", changeType);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map1);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
