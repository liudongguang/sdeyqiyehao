package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.vo.YZCXSearchParam;

public class FeiYongHuiZong {
    private Double zhuyuanzong;
    private Double menzhenzong;
    private double qianribaifenbi;//前日的费用增长率

    private YZCXSearchParam param;

    public YZCXSearchParam getParam() {
        return param;
    }

    public void setParam(YZCXSearchParam param) {
        this.param = param;
    }

    public double getQianribaifenbi() {
        return qianribaifenbi;
    }

    public void setQianribaifenbi(double qianribaifenbi) {
        this.qianribaifenbi = qianribaifenbi;
    }

    public Double getZhuyuanzong() {
        return zhuyuanzong;
    }

    public void setZhuyuanzong(Double zhuyuanzong) {
        this.zhuyuanzong = zhuyuanzong;
    }

    public Double getMenzhenzong() {
        return menzhenzong;
    }

    public void setMenzhenzong(Double menzhenzong) {
        this.menzhenzong = menzhenzong;
    }

    @Override
    public String toString() {
        return "FeiYongHuiZong{" +
                "zhuyuanzong=" + zhuyuanzong +
                ", menzhenzong=" + menzhenzong +
                '}';
    }
}
