package com.yzcx.api.vo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by LiuDongguang on 2017/10/31.
 */
@Table(name = "hisrun.V_YZCX_MZXX_YYL")
public class YuYueLiang {
    @Column(name = "BRID")
    private BigDecimal brid;
    @Column(name = "YYRQ")
    private Date yyrq;
    private String yyrqStr;
    @Column(name = "KS")
    private String ks;
    @Column(name = "YS")
    private String ys;


    public String getYyrqStr() {
        return yyrqStr;
    }

    public void setYyrqStr(String yyrqStr) {
        this.yyrqStr = yyrqStr;
    }

    public BigDecimal getBrid() {
        return brid;
    }

    public void setBrid(BigDecimal brid) {
        this.brid = brid;
    }

    public Date getYyrq() {
        return yyrq;
    }

    public void setYyrq(Date yyrq) {
        this.yyrq = yyrq;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    @Override
    public String toString() {
        return "YuYueLiang{" +
                "brid=" + brid +
                ", yyrq=" + yyrq +
                ", ks='" + ks + '\'' +
                ", ys='" + ys + '\'' +
                '}';
    }
}
