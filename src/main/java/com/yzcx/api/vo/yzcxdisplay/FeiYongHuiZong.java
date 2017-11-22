package com.yzcx.api.vo.yzcxdisplay;

public class FeiYongHuiZong {
    private Double zhuyuanzong;
    private Double menzhenzong;

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
