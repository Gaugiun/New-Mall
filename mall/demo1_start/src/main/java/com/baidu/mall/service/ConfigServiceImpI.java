package com.baidu.mall.service;

import com.baidu.mall.bean.configbean.ConfigExpressVo;
import com.baidu.mall.bean.configbean.ConfigMallVo;
import com.baidu.mall.bean.configbean.ConfigOrderVo;
import com.baidu.mall.bean.configbean.ConfigWxVo;
import com.baidu.mall.mapper.CskaoyanMallSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpI implements ConfigService {

    @Autowired
    CskaoyanMallSystemMapper cskaoyanMallSystemMapper;

    /**
     * 获得商场配置信息
     * @return
     */
    @Override
    public ConfigMallVo queryByMall() {
        int nameId = 6;
        int addressId = 14;
        int phoneId = 12;
        int qqId = 8;
        String name = cskaoyanMallSystemMapper.selectByName(nameId);
        String address = cskaoyanMallSystemMapper.selectByAddress(addressId);
        String phone = cskaoyanMallSystemMapper.selectByPhone(phoneId);
        String qq = cskaoyanMallSystemMapper.selectByQQ(qqId);
        ConfigMallVo configMallVo = new ConfigMallVo(name,address,phone,qq);
        return configMallVo;
    }

    /**
     * 更改商城配置信息
     * @return
     */
    @Override
    public void updateMallConfig(ConfigMallVo configMallVo) {
        int nameId = 6;
        int addressId = 14;
        int phoneId = 12;
        int qqId = 8;
        cskaoyanMallSystemMapper.updateName(nameId, configMallVo);
        cskaoyanMallSystemMapper.updateAddress(addressId, configMallVo);
        cskaoyanMallSystemMapper.updatePhone(phoneId, configMallVo);
        cskaoyanMallSystemMapper.updateQQ(qqId, configMallVo);
    }

    /**
     * 显示运费配置信息
     * @return
     */
    @Override
    public ConfigExpressVo queryByExpress() {
        int minId = 5;
        int valueId = 7;
        String min = cskaoyanMallSystemMapper.selectExpressMinById(minId);
        String value = cskaoyanMallSystemMapper.selectExpressValueById(valueId);
        ConfigExpressVo configExpressVo = new ConfigExpressVo(min, value);
        return configExpressVo;
    }

    /**
     * 更改运费配置信息
     * @return
     */
    @Override
    public void updateExpressConfig(ConfigExpressVo configExpressVo) {
        int minId = 5;
        int valueId =7;
        cskaoyanMallSystemMapper.updateExpressMin(minId,configExpressVo);
        cskaoyanMallSystemMapper.updateExpressValue(valueId,configExpressVo);
    }

    /**
     * 显示订单配置信息
     * @return
     */
    @Override
    public ConfigOrderVo queryByOrder() {
        int unpaidId = 1;
        int unconfirmId = 3;
        int commentId = 10;
        String unpaid = cskaoyanMallSystemMapper.selectOrderUnpaidById(unpaidId);
        String unconfirm = cskaoyanMallSystemMapper.selectOrderUnconfirmById(unconfirmId);
        String comment = cskaoyanMallSystemMapper.selectOrderCommentById(commentId);
        ConfigOrderVo configOrderVo = new ConfigOrderVo(unpaid, unconfirm, comment);
        return configOrderVo;
    }

    /**
     * 更改订单配置信息
     * @return
     */
    @Override
    public void updateOrderConfig(ConfigOrderVo configOrderVo) {
        int unpaidId = 1;
        int unconfirmId = 3;
        int commentId = 10;
        cskaoyanMallSystemMapper.updateOrderUnpaid(unpaidId,configOrderVo);
        cskaoyanMallSystemMapper.updateOrderUnconfirm(unconfirmId,configOrderVo);
        cskaoyanMallSystemMapper.updateOrderComment(commentId,configOrderVo);
    }

    /**
     * 显示小程序配置信息
     * @return
     */
    @Override
    public ConfigWxVo queryByWx() {
        int indexNewId = 2;
        int indexHotId = 9;
        int indexBrandId = 15;
        int indexTopicId = 16;
        int catlogListId = 13;
        int catlogGoodsId = 11;
        int shareId = 4;
        String indexNew = cskaoyanMallSystemMapper.selectWxIndexNewById(indexNewId);
        String indexHot = cskaoyanMallSystemMapper.selectWxIndexHotById(indexHotId);
        String indexBrand = cskaoyanMallSystemMapper.selectWxIndexBrandById(indexBrandId);
        String indexTopic = cskaoyanMallSystemMapper.selectWxIndexTopicById(indexTopicId);
        String catlogList = cskaoyanMallSystemMapper.selectWxCatlogListById(catlogListId);
        String catlogGoods = cskaoyanMallSystemMapper.selectWxCatlogGoodsById(catlogGoodsId);
        String share = cskaoyanMallSystemMapper.selectWxShareById(shareId);
        ConfigWxVo configWxVo = new ConfigWxVo(indexNew, indexHot, indexBrand, indexTopic, catlogList, catlogGoods, share);
        return configWxVo;
    }

    /**
     * 更改小程序配置信息
     * @return
     */
    @Override
    public void updateWxConfig(ConfigWxVo configWxVo) {
        int indexNewId = 2;
        int indexHotId = 9;
        int indexBrandId = 15;
        int indexTopicId = 16;
        int catlogListId = 13;
        int catlogGoodsId = 11;
        int shareId = 4;
        cskaoyanMallSystemMapper.updateWxIndexNew(indexNewId,configWxVo);
        cskaoyanMallSystemMapper.updateWxIndexHot(indexHotId,configWxVo);
        cskaoyanMallSystemMapper.updateWxIndexBrand(indexBrandId,configWxVo);
        cskaoyanMallSystemMapper.updateWxIndexTopic(indexTopicId,configWxVo);
        cskaoyanMallSystemMapper.updateWxCatlogList(catlogListId,configWxVo);
        cskaoyanMallSystemMapper.updateWxCatlogGoods(catlogGoodsId,configWxVo);
        cskaoyanMallSystemMapper.updateWxShare(shareId,configWxVo);
    }

}
