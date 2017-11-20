package com.yzcx.api.vo;

import java.util.List;

public class FYXXModle {
    private List<FYXXmenzhenchufang> menzhenfycf;
    private List<FYXXmenzhenyiji> menzhenfyyj;
    private List<FYXXzhuyuan> zhuyuanfy;

    public List<FYXXmenzhenchufang> getMenzhenfycf() {
        return menzhenfycf;
    }

    public void setMenzhenfycf(List<FYXXmenzhenchufang> menzhenfycf) {
        this.menzhenfycf = menzhenfycf;
    }

    public List<FYXXmenzhenyiji> getMenzhenfyyj() {
        return menzhenfyyj;
    }

    public void setMenzhenfyyj(List<FYXXmenzhenyiji> menzhenfyyj) {
        this.menzhenfyyj = menzhenfyyj;
    }

    public List<FYXXzhuyuan> getZhuyuanfy() {
        return zhuyuanfy;
    }

    public void setZhuyuanfy(List<FYXXzhuyuan> zhuyuanfy) {
        this.zhuyuanfy = zhuyuanfy;
    }

    @Override
    public String toString() {
        return "FYXXModle{" +
                "menzhenfycf=" + menzhenfycf +
                ", menzhenfyyj=" + menzhenfyyj +
                ", zhuyuanfy=" + zhuyuanfy +
                '}';
    }
}
