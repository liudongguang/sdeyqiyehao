package com.yzcx.api.vo;

import javax.persistence.Table;
import java.util.Date;

/**
 * 住院费用
 */
@Table(name = "hisrun.v_yzcx_fymx")
public class FYXXzhuyuan {
    private String brks;//病人科室
    private Date ryrq;//入院日期
    private Date cyrq;//出院日期
    private String zxks;//执行科室
    private String fymc; //费用名称
    private double zjje;//住院金额
    private String fyxm;//费用类别
    private Date jfrq;//计费日期

    public String getBrks() {
        return brks;
    }

    public void setBrks(String brks) {
        this.brks = brks;
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

    public String getZxks() {
        return zxks;
    }

    public void setZxks(String zxks) {
        this.zxks = zxks;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public double getZjje() {
        return zjje;
    }

    public void setZjje(double zjje) {
        this.zjje = zjje;
    }

    public String getFyxm() {
        return fyxm;
    }

    public void setFyxm(String fyxm) {
        this.fyxm = fyxm;
    }

    public Date getJfrq() {
        return jfrq;
    }

    public void setJfrq(Date jfrq) {
        this.jfrq = jfrq;
    }

    @Override
    public String toString() {
        return "FYXXzhuyuan{" +
                "brks='" + brks + '\'' +
                ", ryrq=" + ryrq +
                ", cyrq=" + cyrq +
                ", zxks='" + zxks + '\'' +
                ", fymc='" + fymc + '\'' +
                ", zjje=" + zjje +
                ", fyxm='" + fyxm + '\'' +
                ", jfrq=" + jfrq +
                '}';
    }
}
