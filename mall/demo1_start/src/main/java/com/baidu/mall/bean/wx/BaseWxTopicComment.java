package com.baidu.mall.bean.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class BaseWxTopicComment {

    private BaseWxTopicCommentUserInfo userInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private String[] picList;

    private String content;


}
