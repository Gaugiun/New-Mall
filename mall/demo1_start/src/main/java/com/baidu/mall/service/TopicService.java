package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallTopic;

import java.util.List;

public interface TopicService {

    List<CskaoyanMallTopic> selectFuzzy(String title, String subtitle);

    boolean deleteTopic(CskaoyanMallTopic cskaoyanMallTopic);

    CskaoyanMallTopic updateTopic(CskaoyanMallTopic cskaoyanMallTopic);

    CskaoyanMallTopic createTopic(CskaoyanMallTopic cskaoyanMallTopic);
}
