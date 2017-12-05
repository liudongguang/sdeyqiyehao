package com.yzcx.api.vo;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by LiuDongguang on 2017/10/31.
 */
@Table(name = "hisrun.V_YZCX_yjxx")
public class YiJiInfo {
   private String brbh;
   private String brxm;
   private String kdks;
   private String ysdm;
   private String zxks;
   private Date kdrq;
   private Date zxrq;
   private String fyxh;
   private String fymc;
   private String fygb;
   private BigDecimal hjje;

    public String getBrbh() {
        return brbh;
    }

    public void setBrbh(String brbh) {
        this.brbh = brbh;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

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

    public String getFyxh() {
        return fyxh;
    }

    public void setFyxh(String fyxh) {
        this.fyxh = fyxh;
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

    public BigDecimal getHjje() {
        return hjje;
    }

    public void setHjje(BigDecimal hjje) {
        this.hjje = hjje;
    }

    @Override
    public String toString() {
        return "YiJiInfo{" +
                "brbh='" + brbh + '\'' +
                ", brxm='" + brxm + '\'' +
                ", kdks='" + kdks + '\'' +
                ", ysdm='" + ysdm + '\'' +
                ", zxks='" + zxks + '\'' +
                ", kdrq=" + kdrq +
                ", zxrq=" + zxrq +
                ", fyxh='" + fyxh + '\'' +
                ", fymc='" + fymc + '\'' +
                ", fygb='" + fygb + '\'' +
                ", hjje=" + hjje +
                '}';
    }
}
