package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallTopic;
import com.baidu.mall.mapper.CskaoyanMallTopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    CskaoyanMallTopicMapper cskaoyanMallTopicMapper;
    @Override
    public List<CskaoyanMallTopic> selectFuzzy(String title, String subtitle) {
        List<CskaoyanMallTopic> cskaoyanMallTopics =  cskaoyanMallTopicMapper.selectFuzzy(title, subtitle);
        return cskaoyanMallTopics;
    }

    @Override
    public boolean deleteTopic(CskaoyanMallTopic cskaoyanMallTopic) {
        int i = cskaoyanMallTopicMapper.deleteByPrimaryKey(cskaoyanMallTopic.getId());
        return i > 0;
    }

    @Override
    public CskaoyanMallTopic updateTopic(CskaoyanMallTopic cskaoyanMallTopic) {
        int i = cskaoyanMallTopicMapper.updateByPrimaryKey(cskaoyanMallTopic);
        return cskaoyanMallTopicMapper.selectByPrimaryKey(cskaoyanMallTopic.getId());
    }

    @Override
    public CskaoyanMallTopic createTopic(CskaoyanMallTopic cskaoyanMallTopic) {
        int i = cskaoyanMallTopicMapper.insert(cskaoyanMallTopic);
        return cskaoyanMallTopicMapper.selectByPrimaryKey(cskaoyanMallTopic.getId());
    }
}
