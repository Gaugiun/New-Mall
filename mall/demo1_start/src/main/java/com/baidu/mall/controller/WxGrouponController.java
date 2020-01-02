package com.baidu.mall.controller;


import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.mapper.CskaoyanMallGrouponRulesMapper;
import com.baidu.mall.mapper.CskaoyanMallOrderMapper;
import com.baidu.mall.mapper.CskaoyanMallUserMapper;
import com.baidu.mall.service.WxGoodsServiceImpl;
import com.baidu.mall.service.WxGrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("wx")
@RestController
@Validated
public class WxGrouponController {

    @Autowired
    WxGrouponService wxGrouponService;

    @Autowired
    CskaoyanMallOrderMapper cskaoyanMallOrderMapper;

    @Autowired
    CskaoyanMallGrouponRulesMapper cskaoyanMallGrouponRulesMapper;

    @Autowired
    CskaoyanMallUserMapper cskaoyanMallUserMapper;

    @Autowired
    WxGoodsServiceImpl wxGoodsService;

    /**
     * 团购规则列表
     *
     * @param page 分页页数
     * @param size 分页大小
     * @return 团购规则列表
     */
    @GetMapping("groupon/list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size
                       )
    {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(wxGrouponService.queryGrouponList(page,size));
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

}
