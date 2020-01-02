package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallTopic;
import com.baidu.mall.bean.wx.BaseWxComment;
import com.baidu.mall.bean.wx.BaseWxCommentListVo;
import com.baidu.mall.service.WxTopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("wx")
@RestController
public class WXTopicController {

    @Autowired
    WxTopicService wxTopicService;

    //专题列表
    @RequestMapping("topic/list")
    public BaseRespVo BrandList(@RequestParam("page") Integer page,
                                @RequestParam("size") Integer size) {

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        PageHelper.startPage(page, size);
        List<CskaoyanMallTopic> topicList = wxTopicService.selectAllTopic();
        int count = wxTopicService.selectTotalNumber();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("data", topicList);
        hashMap.put("count", count);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    //专题详情
    @RequestMapping("topic/detail")
    public BaseRespVo BrandDetail(@RequestParam("id") Integer id) {

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        CskaoyanMallTopic topic = wxTopicService.selectByPrimaryKey(id);
        /*List<CskaoyanMallGoods> cskaoyanMallGoods = wxTopicService.selectGoodsById(id);*/
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("goods", new ArrayList<>());
        hashMap.put("topic", topic);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    //专题相关
    @RequestMapping("topic/related")
    public BaseRespVo BrandRelated(@RequestParam("id") Integer id) {

        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        CskaoyanMallTopic topic0 = wxTopicService.selectByPrimaryKey(264);
        CskaoyanMallTopic topic1 = wxTopicService.selectByPrimaryKey(266);
        CskaoyanMallTopic topic2 = wxTopicService.selectByPrimaryKey(268);
        CskaoyanMallTopic topic3 = wxTopicService.selectByPrimaryKey(271);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("0", topic0);
        hashMap.put("1", topic1);
        hashMap.put("2", topic2);
        hashMap.put("3", topic3);
        baseRespVo.setData(hashMap);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 显示相关专题的部分评论
     * @param
     * @return
     */
    @RequestMapping("comment/list")
    public BaseRespVo getTopicComment(BaseWxCommentListVo commentList) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Map map = wxTopicService.queryCommentByValueId(commentList);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 微信添加专题里面的comment
     * @param
     * @return
     */
    @RequestMapping("comment/post")
    public BaseRespVo addComment(@RequestBody BaseWxComment comment) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        /*Subject subject = SecurityUtils.getSubject();
        int userId = (int) subject.getSession().getAttribute("userId");*/
        comment.setUserId(1);
        BaseWxComment result = wxTopicService.insertComment(comment);
        baseRespVo.setData(0);
        baseRespVo.setData(result);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 查询专题comment的总数和有图片的总数
     * @param
     * @return
     */
    @RequestMapping("comment/count")
    public BaseRespVo getTopicCommentCount(@PathParam("valueId") int valueId, @PathParam("type") int type) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Map map = wxTopicService.getCommentCountAndHasPicCount(valueId, type);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
