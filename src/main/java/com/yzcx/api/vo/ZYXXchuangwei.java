package com.yzcx.api.vo;

public class ZYXXchuangwei {
    private String rq;//日期
    private Integer shizhanshu;//实占床位数
    private Integer kaifangshu;//开放床位数
    private String ks;//科室
    private Integer kslb;// 1.科室 2.病区

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public Integer getShizhanshu() {
        return shizhanshu;
    }

    public void setShizhanshu(Integer shizhanshu) {
        this.shizhanshu = shizhanshu;
    }

    public Integer getKaifangshu() {
        return kaifangshu;
    }

    public void setKaifangshu(Integer kaifangshu) {
        this.kaifangshu = kaifangshu;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public Integer getKslb() {
        return kslb;
    }

    public void setKslb(Integer kslb) {
        this.kslb = kslb;
    }
}
