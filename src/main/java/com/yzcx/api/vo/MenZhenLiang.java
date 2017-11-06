package com.yzcx.api.vo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by LiuDongguang on 2017/10/31.
 */
@Table(name = "hisrun.V_YZCX_MZXX_mzl")
public class MenZhenLiang {
    @Column(name = "BRID")
    private BigDecimal brid;
    @Column(name = "GHRQ")
    private Date ghrq;
    @Column(name = "KSMC")
    private String ksmc;
    @Column(name = "YSMC")
    private String ysmc;
    @Column(name = "SFJZ")
    private Integer sfjz;

    public Date getGhrq() {
        return ghrq;
    }

    public void setGhrq(Date ghrq) {
        this.ghrq = ghrq;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public String getYsmc() {
        return ysmc;
    }

    public void setYsmc(String ysmc) {
        this.ysmc = ysmc;
    }

    public Integer getSfjz() {
        return sfjz;
    }

    public void setSfjz(Integer sfjz) {
        this.sfjz = sfjz;
    }

    public BigDecimal getBrid() {

        return brid;
    }

    public void setBrid(BigDecimal brid) {
        this.brid = brid;
    }

    @Override
    public String toString() {
        return "MenZhenLiang{" +
                "brid=" + brid +
                ", ghrq=" + ghrq +
                ", ksmc='" + ksmc + '\'' +
                ", ysmc='" + ysmc + '\'' +
                ", sfjz=" + sfjz +
                '}';
    }
}
