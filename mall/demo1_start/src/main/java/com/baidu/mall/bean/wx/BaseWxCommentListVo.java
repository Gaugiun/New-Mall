package com.baidu.mall.bean.wx;

import lombok.Data;

@Data
public class BaseWxCommentListVo {
    Integer valueId;
    Byte type;
    Integer showType;
    Integer page;
    Integer size;
}
