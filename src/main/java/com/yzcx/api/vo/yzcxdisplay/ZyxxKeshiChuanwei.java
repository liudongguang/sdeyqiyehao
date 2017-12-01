package com.yzcx.api.vo.yzcxdisplay;

public class ZyxxKeshiChuanwei {
    ///
    private String ksname;
    private Double shizhan;
    private Double kaifang;
    private Double cwshiyonglv;//床位使用率

    public String getKsname() {
        return ksname;
    }

    public void setKsname(String ksname) {
        this.ksname = ksname;
    }

    public Double getShizhan() {
        return shizhan;
    }

    public void setShizhan(Double shizhan) {
        this.shizhan = shizhan;
    }

    public Double getKaifang() {
        return kaifang;
    }

    public void setKaifang(Double kaifang) {
        this.kaifang = kaifang;
    }

    public Double getCwshiyonglv() {
        return cwshiyonglv;
    }

    public void setCwshiyonglv(Double cwshiyonglv) {
        this.cwshiyonglv = cwshiyonglv;
    }

    @Override
    public String toString() {
        return "ZyxxKeshiChuanwei{" +
                "ksname='" + ksname + '\'' +
                ", shizhan=" + shizhan +
                ", kaifang=" + kaifang +
                ", cwshiyonglv=" + cwshiyonglv +
                '}';
    }
}
