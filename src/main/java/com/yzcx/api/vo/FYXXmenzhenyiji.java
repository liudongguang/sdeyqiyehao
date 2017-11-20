package com.yzcx.api.vo;

import javax.persistence.Table;
import java.util.Date;

/**
 * 门诊医技费用
 */
@Table(name = "hisrun.V_YZCX_yjxx")
public class FYXXmenzhenyiji {
    private String kdks;//开单科室
    private String ysdm;//医生代码
    private String zxks;//执行科室
    private Date kdrq;//开单日期
    private Date zxrq;//执行日期
    private String fymc;//费用名称
    private String fygb;//费用类别
    private Double hjje;//合计金额

    public String getKdks() {
        return kdks;
    }

    public void setKdks(String kdks) {
        this.kdks = kdks;
    }

    public String getYsdm() {
        return ysdm;
    }

    public void setYsdm(String ysdm) {
        this.ysdm = ysdm;
    }

    public String getZxks() {
        return zxks;
    }

    public void setZxks(String zxks) {
        this.zxks = zxks;
    }

    public Date getKdrq() {
        return kdrq;
    }

    public void setKdrq(Date kdrq) {
        this.kdrq = kdrq;
    }

    public Date getZxrq() {
        return zxrq;
    }

    public void setZxrq(Date zxrq) {
        this.zxrq = zxrq;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getFygb() {
        return fygb;
    }

    public void setFygb(String fygb) {
        this.fygb = fygb;
    }

    public Double getHjje() {
        return hjje;
    }

    public void setHjje(Double hjje) {
        this.hjje = hjje;
    }

    @Override
    public String toString() {
        return "FYXXmenzhenyiji{" +
                "kdks='" + kdks + '\'' +
                ", ysdm='" + ysdm + '\'' +
                ", zxks='" + zxks + '\'' +
                ", kdrq=" + kdrq +
                ", zxrq=" + zxrq +
                ", fymc='" + fymc + '\'' +
                ", fygb='" + fygb + '\'' +
                ", hjje=" + hjje +
                '}';
    }
}
