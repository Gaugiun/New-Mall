package com.baidu.mall.mapper;

import com.baidu.mall.bean.CskaoyanMallIssue;

import java.util.List;

public interface CskaoyanMallIssueMapper {
    int deleteByPrimaryKey(Integer id);

    CskaoyanMallIssue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CskaoyanMallIssue record);

    int updateByPrimaryKey(CskaoyanMallIssue record);

    List<CskaoyanMallIssue> selectAllIssue();

    List<CskaoyanMallIssue> selectByQuestion(String question);

    int insert(String question, String answer);
}
