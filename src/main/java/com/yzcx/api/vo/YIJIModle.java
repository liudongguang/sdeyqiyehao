package com.yzcx.api.vo;

import java.util.List;

public class YIJIModle {
    private List<YiJiInfo> mzyiji;
    private List<YiJiInfo> zyyiji;


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

    @Override
    public String toString() {
        return "YIJIModle{" +
                "mzyiji=" + mzyiji +
                ", zyyiji=" + zyyiji +
                '}';
    }
}
