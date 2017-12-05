package com.yzcx.api.vo;

import java.util.List;

public class YIJIModle {
    private List<YiJiInfo> mzyiji;
    private List<YiJiInfo> zyyiji;
    private List<YJHLInfo> yjhl;

    public List<YiJiInfo> getMzyiji() {
        return mzyiji;
    }

    public void setMzyiji(List<YiJiInfo> mzyiji) {
        this.mzyiji = mzyiji;
    }

    public List<YiJiInfo> getZyyiji() {
        return zyyiji;
    }

    public void setZyyiji(List<YiJiInfo> zyyiji) {
        this.zyyiji = zyyiji;
    }

    public List<YJHLInfo> getYjhl() {
        return yjhl;
    }

    public void setYjhl(List<YJHLInfo> yjhl) {
        this.yjhl = yjhl;
    }

    @Override
    public String toString() {
        return "YIJIModle{" +
                "mzyiji=" + mzyiji +
                ", zyyiji=" + zyyiji +
                ", yjhl=" + yjhl +
                '}';
    }
}
