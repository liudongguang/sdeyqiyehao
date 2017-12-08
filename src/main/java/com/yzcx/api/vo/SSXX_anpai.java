package com.yzcx.api.vo;

import java.util.Date;

public class SSXX_anpai {
    private Date ssrq;//安排手术日期
    private String ssfj;//手术分级
    private String brks;//病人科室
    private String ssmc;//手术名称
    private String brxm;//病人姓名
    private String ssys;//手术医生
    private Integer brnl;//病人年龄
    private String brxb;//病人性别

    private String ssrqStr;

    public String getSsrqStr() {
        return ssrqStr;
    }

    public void setSsrqStr(String ssrqStr) {
        this.ssrqStr = ssrqStr;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getSsys() {
        return ssys;
    }

    public void setSsys(String ssys) {
        this.ssys = ssys;
    }

    public Integer getBrnl() {
        return brnl;
    }

    public void setBrnl(Integer brnl) {
        this.brnl = brnl;
    }

    public String getBrxb() {
        return brxb;
    }

    public void setBrxb(String brxb) {
        this.brxb = brxb;
    }

    public Date getSsrq() {
        return ssrq;
    }

    public void setSsrq(Date ssrq) {
        this.ssrq = ssrq;
    }

    public String getSsfj() {
        return ssfj;
    }

    public void setSsfj(String ssfj) {
        this.ssfj = ssfj;
    }

    public String getBrks() {
        return brks;
    }

    public void setBrks(String brks) {
        this.brks = brks;
    }
}
