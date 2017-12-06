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
    private String ghrqStr;
    @Column(name = "KSMC")
    private String ksmc;
    @Column(name = "YSMC")
    private String ysmc;
    @Column(name = "SFJZ")
    private Integer sfjz;//0  普通  1 急诊
    private String sfjzStr;

    private Date csny;//出生年月
    private String brxb;//病人性别

    private int ageNum;
    private String ageString;

    public int getAgeNum() {
        return ageNum;
    }

    public void setAgeNum(int ageNum) {
        this.ageNum = ageNum;
    }

    public String getAgeString() {
        return ageString;
    }

    public void setAgeString(String ageString) {
        this.ageString = ageString;
    }

    public Date getCsny() {
        return csny;
    }

    public void setCsny(Date csny) {
        this.csny = csny;
    }

    public String getBrxb() {
        return brxb;
    }

    public void setBrxb(String brxb) {
        this.brxb = brxb;
    }

    public String getSfjzStr() {
        return sfjzStr;
    }

    public void setSfjzStr(String sfjzStr) {
        this.sfjzStr = sfjzStr;
    }

    public String getGhrqStr() {
        return ghrqStr;
    }

    public void setGhrqStr(String ghrqStr) {
        this.ghrqStr = ghrqStr;
    }

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
