package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.po.YzcxHandleInfoDay;

public class YzcxHandleInfoDayExt extends YzcxHandleInfoDay {
    private String handledateStr;

    public String getHandledateStr() {
        return handledateStr;
    }

    public void setHandledateStr(String handledateStr) {
        this.handledateStr = handledateStr;
    }

    @Override
    public String toString() {
        return "YzcxHandleInfoDayExt{" +
                "handledateStr='" + handledateStr + '\'' +
                '}';
    }
}
