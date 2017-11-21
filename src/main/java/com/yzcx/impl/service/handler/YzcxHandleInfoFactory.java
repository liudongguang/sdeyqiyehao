package com.yzcx.impl.service.handler;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.util.YZCXConstant;

import java.util.Date;

public class YzcxHandleInfoFactory {
    public final static YzcxHandleInfo createYzcxHandleInfo(String name, int HandlerType, Date handleDate, Double num) {
        YzcxHandleInfo yzcxHandleInfo = new YzcxHandleInfo();
        yzcxHandleInfo.setCount(num);
        yzcxHandleInfo.setHandledate(handleDate);
        yzcxHandleInfo.setHandletype(HandlerType);
        yzcxHandleInfo.setName(name);
        return yzcxHandleInfo;
    }
}
