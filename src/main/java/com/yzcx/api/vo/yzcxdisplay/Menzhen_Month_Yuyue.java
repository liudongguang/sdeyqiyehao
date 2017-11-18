package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.vo.YZCXSearchParam;

public class Menzhen_Month_Yuyue {
    private Double menzhen;
    private Double yuyue;
    //////
    private YZCXSearchParam param;

    public Double getMenzhen() {
        return menzhen;
    }

    public void setMenzhen(Double menzhen) {
        this.menzhen = menzhen;
    }

    public Double getYuyue() {
        return yuyue;
    }

    public void setYuyue(Double yuyue) {
        this.yuyue = yuyue;
    }

    public YZCXSearchParam getParam() {
        return param;
    }

    public void setParam(YZCXSearchParam param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Menzhen_Month_Yuyue{" +
                "menzhen=" + menzhen +
                ", yuyue=" + yuyue +
                ", param=" + param +
                '}';
    }
}
