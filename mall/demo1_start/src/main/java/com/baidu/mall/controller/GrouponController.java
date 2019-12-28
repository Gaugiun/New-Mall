package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallGrouponRules;
import com.baidu.mall.service.GrouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 团购规则的controller层
 */
@RestController
public class GrouponController {
    @Autowired
    GrouponService grouponService;

    /**
     * 模糊查询
     * @param page
     * @param limit
     * @param goodsId
     * @return
     */
    @RequestMapping("admin/groupon/list")
    public BaseRespVo grouponList(Integer page, Integer limit, Integer goodsId){
        PageHelper.startPage(page, limit);
        List<CskaoyanMallGrouponRules> items = grouponService.grouponList(goodsId);
        PageInfo<CskaoyanMallGrouponRules> total = new PageInfo<>(items);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("items", items);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 删除团购优惠
     * @param cskaoyanMallGrouponRules
     * @return
     */
    @RequestMapping("admin/groupon/delete")
    public BaseRespVo deleteGroupon(@RequestBody CskaoyanMallGrouponRules cskaoyanMallGrouponRules){
        boolean b = grouponService.deleteGroupon(cskaoyanMallGrouponRules);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 编辑优惠
     * @param cskaoyanMallGrouponRules
     * @return
     */
    @RequestMapping("admin/groupon/update")
    public BaseRespVo updateGroupon(@RequestBody CskaoyanMallGrouponRules cskaoyanMallGrouponRules){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String s = cskaoyanMallGrouponRules.getDiscount().toString();
        Integer discountMember = cskaoyanMallGrouponRules.getDiscountMember();
        if (s == null || discountMember == null){
            baseRespVo.setErrmsg("参数不对");
            baseRespVo.setErrno(402);
            return baseRespVo;
        }
        char[] chars = s.toCharArray();
        char[] chars1 = Character.toChars(discountMember);
        if (chars.length == 0 || chars1.length == 0){
            baseRespVo.setErrmsg("参数不对");
            baseRespVo.setErrno(402);
            return baseRespVo;
        }else {
            for (char aChar : chars) {
                int i = aChar;
                if (i < 48 || i > 57){
                    baseRespVo.setErrmsg("参数不对");
                    baseRespVo.setErrno(402);
                    return baseRespVo;
                }
            }
            for (char c : chars1) {
                int i = c;
                if (i < 48 || i > 57){
                    baseRespVo.setErrmsg("参数不对");
                    baseRespVo.setErrno(402);
                    return baseRespVo;
                }
            }
            boolean b = grouponService.updateGroupon(cskaoyanMallGrouponRules);
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }


    }

    @RequestMapping("admin/groupon/listRecord")
    public BaseRespVo listRecordGroupon(Integer page, Integer limit, Integer goodsId){
        PageHelper.startPage(page, limit);
        List<CskaoyanMallGrouponRules> items = grouponService.grouponList(goodsId);
        PageInfo<CskaoyanMallGrouponRules> total = new PageInfo<>(items);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("total", total);
        hashMap.put("items", items);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }


}
