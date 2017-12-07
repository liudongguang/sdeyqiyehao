package com.yzcx.api.vo;

import java.util.Date;

public class SSXX_anpai {
    private Date ssrq;//安排手术日期
    private String ssfj;//手术分级
    private String brks;//病人科室

    private String ssrqStr;

    public String getSsrqStr() {
        return ssrqStr;
    }

    public void setSsrqStr(String ssrqStr) {
        this.ssrqStr = ssrqStr;
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

    @Override
    public String toString() {
        return "SSXX_anpai{" +
                "ssrq=" + ssrq +
                ", ssfj='" + ssfj + '\'' +
                ", brks='" + brks + '\'' +
                '}';
    }
}
