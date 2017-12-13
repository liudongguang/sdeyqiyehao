package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.vo.YZCXSearchParam;

public class YiJiMonthData {
    private YZCXSearchParam param;
    private Integer menzhenRenci;
    private Integer zhuyuanRenci;
    private Double menzhenHeji;
    private Double zhuyuanHeji;
    private Double menzhenPingjun;
    private Double zhuyuanPingjun;
    private Double zongHejiPingjun;


    public void pingjun(){
        menzhenPingjun=menzhenHeji/menzhenRenci;
        zhuyuanPingjun=zhuyuanHeji/zhuyuanRenci;
        zongHejiPingjun=(menzhenHeji+zhuyuanHeji)/(menzhenRenci+zhuyuanRenci);
    }

    public Double getZongHejiPingjun() {
        return zongHejiPingjun;
    }

    public void setZongHejiPingjun(Double zongHejiPingjun) {
        this.zongHejiPingjun = zongHejiPingjun;
    }

    public YZCXSearchParam getParam() {
        return param;
    }

    public void setParam(YZCXSearchParam param) {
        this.param = param;
    }

    public Integer getMenzhenRenci() {
        return menzhenRenci;
    }

    public void setMenzhenRenci(Integer menzhenRenci) {
        this.menzhenRenci = menzhenRenci;
    }

    public Integer getZhuyuanRenci() {
        return zhuyuanRenci;
    }

    public void setZhuyuanRenci(Integer zhuyuanRenci) {
        this.zhuyuanRenci = zhuyuanRenci;
    }

    public Double getMenzhenHeji() {
        return menzhenHeji;
    }

    public void setMenzhenHeji(Double menzhenHeji) {
        this.menzhenHeji = menzhenHeji;
    }

    public Double getZhuyuanHeji() {
        return zhuyuanHeji;
    }

    public void setZhuyuanHeji(Double zhuyuanHeji) {
        this.zhuyuanHeji = zhuyuanHeji;
    }

    public Double getMenzhenPingjun() {
        return menzhenPingjun;
    }

    public void setMenzhenPingjun(Double menzhenPingjun) {
        this.menzhenPingjun = menzhenPingjun;
    }

    public Double getZhuyuanPingjun() {
        return zhuyuanPingjun;
    }

    public void setZhuyuanPingjun(Double zhuyuanPingjun) {
        this.zhuyuanPingjun = zhuyuanPingjun;
    }

    @Override
    public String toString() {
        return "YiJiMonthData{" +
                "param=" + param +
                ", menzhenRenci=" + menzhenRenci +
                ", zhuyuanRenci=" + zhuyuanRenci +
                ", menzhenHeji=" + menzhenHeji +
                ", zhuyuanHeji=" + zhuyuanHeji +
                ", menzhenPingjun=" + menzhenPingjun +
                ", zhuyuanPingjun=" + zhuyuanPingjun +
                ", zongHejiPingjun=" + zongHejiPingjun +
                '}';
    }
}
