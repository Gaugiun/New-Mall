package com.baidu.mall.service;

import java.util.LinkedHashMap;
import java.util.List;

public interface WXSearchService {

    LinkedHashMap searchIndex();

    List<String> searchHelper(String keyword);

    void clearhistory(String wx);
}
