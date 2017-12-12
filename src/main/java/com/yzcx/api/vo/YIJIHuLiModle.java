package com.yzcx.api.vo;

import java.util.List;

public class YIJIHuLiModle {
    private List<YJHLInfo> yjhl;

    public List<YJHLInfo> getYjhl() {
        return yjhl;
    }

    public void setYjhl(List<YJHLInfo> yjhl) {
        this.yjhl = yjhl;
    }

    @Override
    public String toString() {
        return "YIJIHuLiModle{" +
                "yjhl=" + yjhl +
                '}';
    }
}
