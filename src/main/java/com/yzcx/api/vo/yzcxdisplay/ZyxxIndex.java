package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.vo.YZCXSearchParam;

public class ZyxxIndex {
    private Double ruyuan;
    private Double chuyuan;
    private Double weizhong;
    private Double zhuanru;
    private Double zhuanchu;
    private Double siwang;
    ///
    private Double shizhan;
    private Double kaifang;
    private Double cwshiyonglv;//床位使用率

    //////
    private YZCXSearchParam param;


    public YZCXSearchParam getParam() {
        return param;
    }

    public void setParam(YZCXSearchParam param) {
        this.param = param;
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

    public Double getRuyuan() {
        return ruyuan;
    }

    public void setRuyuan(Double ruyuan) {
        this.ruyuan = ruyuan;
    }

    public Double getChuyuan() {
        return chuyuan;
    }

    public void setChuyuan(Double chuyuan) {
        this.chuyuan = chuyuan;
    }

    public Double getWeizhong() {
        return weizhong;
    }

    public void setWeizhong(Double weizhong) {
        this.weizhong = weizhong;
    }

    public Double getZhuanru() {
        return zhuanru;
    }

    public void setZhuanru(Double zhuanru) {
        this.zhuanru = zhuanru;
    }

    public Double getZhuanchu() {
        return zhuanchu;
    }

    public void setZhuanchu(Double zhuanchu) {
        this.zhuanchu = zhuanchu;
    }

    public Double getSiwang() {
        return siwang;
    }

    public void setSiwang(Double siwang) {
        this.siwang = siwang;
    }
}
