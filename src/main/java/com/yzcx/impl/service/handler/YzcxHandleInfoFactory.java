package com.yzcx.impl.service.handler;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.util.LdgDateUtil;
import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.yzcxdisplay.YzcxHandleInfoExt;

import java.text.ParseException;
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
    public final static YzcxHandleInfoExt createYzcxHandleInfoExtForEveryDay(Date date,Double count,String name) {
        YzcxHandleInfoExt yzcxHandleInfoDayExt = new YzcxHandleInfoExt();
        try {
            yzcxHandleInfoDayExt.setHandledateStr(LdgDateUtil.getDayZhongwen(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        yzcxHandleInfoDayExt.setCount(count);
        yzcxHandleInfoDayExt.setName(name);
        return yzcxHandleInfoDayExt;
    }
}
