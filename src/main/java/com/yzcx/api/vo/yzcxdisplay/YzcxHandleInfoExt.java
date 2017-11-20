package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.po.YzcxHandleInfo;

public class YzcxHandleInfoExt extends YzcxHandleInfo {
    private String handledateStr;

    public String getHandledateStr() {
        return handledateStr;
    }

    public void setHandledateStr(String handledateStr) {
        this.handledateStr = handledateStr;
    }

    @Override
    public String toString() {
        return super.toString()+" YzcxHandleInfoDayExt{" +
                "handledateStr='" + handledateStr + '\'' +
                '}';
    }
}
