package com.yzcx.api.vo;

import java.util.List;

public class HzxxModle {
    private List<HzxxInfo> shenqing;
    private List<HzxxInfo> jieshou;

    public List<HzxxInfo> getShenqing() {
        return shenqing;
    }

    public void setShenqing(List<HzxxInfo> shenqing) {
        this.shenqing = shenqing;
    }

    public List<HzxxInfo> getJieshou() {
        return jieshou;
    }

    public void setJieshou(List<HzxxInfo> jieshou) {
        this.jieshou = jieshou;
    }

    @Override
    public String toString() {
        return "HzxxModle{" +
                "shenqing=" + shenqing +
                ", jieshou=" + jieshou +
                '}';
    }
}
