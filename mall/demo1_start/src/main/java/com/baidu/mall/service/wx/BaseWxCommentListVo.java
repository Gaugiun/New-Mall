package com.baidu.mall.service.wx;

import lombok.Data;

@Data
public class BaseWxCommentListVo {
    Integer valueId;
    Byte type;
    Integer showType;
    Integer page;
    Integer size;
}
