package com.yzcx.api.vo;

import java.util.List;

public class ZYXXModle {
    private List<ZYXXchuangwei> chuangwei;
    private List<ZYXXzhuanke> zhuangke;
    private List<ZYXXzhuyuanbr> bingren;

    public List<ZYXXchuangwei> getChuangwei() {
        return chuangwei;
    }

    public void setChuangwei(List<ZYXXchuangwei> chuangwei) {
        this.chuangwei = chuangwei;
    }

    public List<ZYXXzhuanke> getZhuangke() {
        return zhuangke;
    }

    public void setZhuangke(List<ZYXXzhuanke> zhuangke) {
        this.zhuangke = zhuangke;
    }

    public List<ZYXXzhuyuanbr> getBingren() {
        return bingren;
    }

    public void setBingren(List<ZYXXzhuyuanbr> bingren) {
        this.bingren = bingren;
    }

    @Override
    public String toString() {
        return "ZYXXModle{" +
                "chuangwei=" + chuangwei +
                ", zhuangke=" + zhuangke +
                ", bingren=" + bingren +
                '}';
    }
}
