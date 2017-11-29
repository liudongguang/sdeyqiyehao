package com.yzcx.api.vo;

import java.util.Date;

public class ZYXXzhuyuanbr {
    private String brxb;//病人性别
    private Date csny;//生日
    private String hkdz;//户口地址
    private String brqk;//病人情况
    private String brks;//病人科室
    private String brbq;//病人病区
    private Date ryrq;//入院日期
    private Date cyrq;//出院日期
    private String cyfs;//出院方式

    private String ryrqStr;

    public String getRyrqStr() {
        return ryrqStr;
    }

    public void setRyrqStr(String ryrqStr) {
        this.ryrqStr = ryrqStr;
    }

    public String getBrxb() {
        return brxb;
    }

    public void setBrxb(String brxb) {
        this.brxb = brxb;
    }

    public Date getCsny() {
        return csny;
    }

    public void setCsny(Date csny) {
        this.csny = csny;
    }

    public String getHkdz() {
        return hkdz;
    }

    public void setHkdz(String hkdz) {
        this.hkdz = hkdz;
    }

    public String getBrqk() {
        return brqk;
    }

    public void setBrqk(String brqk) {
        this.brqk = brqk;
    }

    public String getBrks() {
        return brks;
    }

    public void setBrks(String brks) {
        this.brks = brks;
    }

    public String getBrbq() {
        return brbq;
    }

    public void setBrbq(String brbq) {
        this.brbq = brbq;
    }

    public Date getRyrq() {
        return ryrq;
    }

    public void setRyrq(Date ryrq) {
        this.ryrq = ryrq;
    }

    public Date getCyrq() {
        return cyrq;
    }

    public void setCyrq(Date cyrq) {
        this.cyrq = cyrq;
    }

    public String getCyfs() {
        return cyfs;
    }

    public void setCyfs(String cyfs) {
        this.cyfs = cyfs;
    }

    @Override
    public String toString() {
        return "ZYXXzhuyuanbr{" +
                "brxb='" + brxb + '\'' +
                ", csny=" + csny +
                ", hkdz='" + hkdz + '\'' +
                ", brqk='" + brqk + '\'' +
                ", brks='" + brks + '\'' +
                ", brbq='" + brbq + '\'' +
                ", ryrq=" + ryrq +
                ", cyrq=" + cyrq +
                ", cyfs='" + cyfs + '\'' +
                '}';
    }
}
