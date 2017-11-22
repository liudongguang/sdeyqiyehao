package com.yzcx.api.bo;

public class YzcxHandleInfo_FeiYong {
    private String ksname;
    private double zhuyuanyiliaofei;
    private double zhuyuanyaofei;
    private double zhuyuanqitafei;
    private double menzhenyiliaofei;
    private double menzhenyaofei;
    private double menzhenqitafei;

    public double getZhuyuanZong() {
        return zhuyuanyiliaofei + zhuyuanyaofei + zhuyuanqitafei;
    }

    public double getMenzhenZong() {
        return menzhenyiliaofei + menzhenyaofei + menzhenqitafei;
    }

    public String getKsname() {
        return ksname;
    }

    public void setKsname(String ksname) {
        this.ksname = ksname;
    }

    public double getZhuyuanyiliaofei() {
        return zhuyuanyiliaofei;
    }

    public void setZhuyuanyiliaofei(double zhuyuanyiliaofei) {
        this.zhuyuanyiliaofei = zhuyuanyiliaofei;
    }

    public double getZhuyuanyaofei() {
        return zhuyuanyaofei;
    }

    public void setZhuyuanyaofei(double zhuyuanyaofei) {
        this.zhuyuanyaofei = zhuyuanyaofei;
    }

    public double getZhuyuanqitafei() {
        return zhuyuanqitafei;
    }

    public void setZhuyuanqitafei(double zhuyuanqitafei) {
        this.zhuyuanqitafei = zhuyuanqitafei;
    }

    public double getMenzhenyiliaofei() {
        return menzhenyiliaofei;
    }

    public void setMenzhenyiliaofei(double menzhenyiliaofei) {
        this.menzhenyiliaofei = menzhenyiliaofei;
    }

    public double getMenzhenyaofei() {
        return menzhenyaofei;
    }

    public void setMenzhenyaofei(double menzhenyaofei) {
        this.menzhenyaofei = menzhenyaofei;
    }

    public double getMenzhenqitafei() {
        return menzhenqitafei;
    }

    public void setMenzhenqitafei(double menzhenqitafei) {
        this.menzhenqitafei = menzhenqitafei;
    }

    @Override
    public String toString() {
        return "YzcxHandleInfo_FeiYong{" +
                "ksname='" + ksname + '\'' +
                ", zhuyuanyiliaofei=" + zhuyuanyiliaofei +
                ", zhuyuanyaofei=" + zhuyuanyaofei +
                ", zhuyuanqitafei=" + zhuyuanqitafei +
                ", menzhenyiliaofei=" + menzhenyiliaofei +
                ", menzhenyaofei=" + menzhenyaofei +
                ", menzhenqitafei=" + menzhenqitafei +
                '}';
    }
}
