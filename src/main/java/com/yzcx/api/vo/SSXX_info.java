package com.yzcx.api.vo;

import java.util.Date;

public class SSXX_info {
    private Date ssrq;
    private String sqks;

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

    public String getSqks() {
        return sqks;
    }

    public void setSqks(String sqks) {
        this.sqks = sqks;
    }

    @Override
    public String toString() {
        return "SSXX_info{" +
                "ssrq=" + ssrq +
                ", sqks='" + sqks + '\'' +
                '}';
    }
}
