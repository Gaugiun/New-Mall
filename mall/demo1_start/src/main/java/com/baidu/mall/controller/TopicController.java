package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallTopic;
import com.baidu.mall.service.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 这是专题管理的controller
 */
@RestController
public class TopicController {

    @Autowired
    TopicService topicService;

    /**
     * 模糊查询专题管理，默认为查询所有
     * @param page
     * @param limit
     * @param title
     * @param subtitle
     * @return
     */
    @RequestMapping("admin/topic/list")
    public BaseRespVo topicList(Integer page, Integer limit, String title, String subtitle){
        PageHelper.startPage(page, limit);
        List<CskaoyanMallTopic> items = topicService.selectFuzzy(title, subtitle);
        PageInfo<CskaoyanMallTopic> total = new PageInfo<>(items);
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
     * 删除该专题
     * @param cskaoyanMallTopic
     * @return
     */
    @RequestMapping("admin/topic/delete")
    public BaseRespVo deleteTopic(@RequestBody CskaoyanMallTopic cskaoyanMallTopic){
        boolean b = topicService.deleteTopic(cskaoyanMallTopic);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        if (b) {
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
        return baseRespVo;
    }

    /**
     * 更新专题
     * @param cskaoyanMallTopic
     * @return
     */
    @RequestMapping("admin/topic/update")
    public BaseRespVo updateTopic(@RequestBody CskaoyanMallTopic cskaoyanMallTopic) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String s = cskaoyanMallTopic.getPrice().toString();
        if (s == null){
            baseRespVo.setErrmsg("参数不对");
            baseRespVo.setErrno(402);
            return baseRespVo;
        }
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            baseRespVo.setErrmsg("参数不对");
            baseRespVo.setErrno(402);
            return baseRespVo;
        } else {
            for (char aChar : chars) {
                int i = aChar;
                if (i < 48 || i > 57) {
                    baseRespVo.setErrmsg("参数不对");
                    baseRespVo.setErrno(402);
                    return baseRespVo;
                }
            }
            CskaoyanMallTopic topic = topicService.updateTopic(cskaoyanMallTopic);
            baseRespVo.setData(topic);
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
    }

    /**
     * 新建专题
     * @param cskaoyanMallTopic
     * @return
     */
    @RequestMapping("admin/topic/create")
    public BaseRespVo createTopic(@RequestBody CskaoyanMallTopic cskaoyanMallTopic){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String s = cskaoyanMallTopic.getPrice().toString();
        String readCount = cskaoyanMallTopic.getReadCount();
        if (s == null || readCount == null){
            baseRespVo.setErrmsg("参数不对");
            baseRespVo.setErrno(402);
            return baseRespVo;
        }
        char[] chars = s.toCharArray();
        char[] chars1 = readCount.toCharArray();
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
            CskaoyanMallTopic topic = topicService.createTopic(cskaoyanMallTopic);
            baseRespVo.setData(topic);
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
            return baseRespVo;
        }
    }
}
