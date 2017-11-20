package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.vo.YZCXSearchParam;

public class Menzhen_Month_Yuyue {
    private Double zongmenzhen;//门诊急诊之和
    private Double yuyue; //预约数
    private Double menzhenGuaHao;//门诊挂号 = zongmenzhen -yuyue
    //////
    private YZCXSearchParam param;

    public Double getZongmenzhen() {
        return zongmenzhen;
    }

    public void setZongmenzhen(Double zongmenzhen) {
        this.zongmenzhen = zongmenzhen;
    }

    public Double getYuyue() {
        return yuyue;
    }

    public void setYuyue(Double yuyue) {
        this.yuyue = yuyue;
    }

    public Double getMenzhenGuaHao() {
        if (zongmenzhen != null && yuyue != null) {
            menzhenGuaHao = zongmenzhen - yuyue;
        }
        return menzhenGuaHao;
    }

    public void setMenzhenGuaHao(Double menzhenGuaHao) {
        this.menzhenGuaHao = menzhenGuaHao;
    }

    public YZCXSearchParam getParam() {
        return param;
    }

    public void setParam(YZCXSearchParam param) {
        this.param = param;
    }
}
