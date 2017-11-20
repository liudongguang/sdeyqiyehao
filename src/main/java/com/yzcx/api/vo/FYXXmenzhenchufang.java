package com.yzcx.api.vo;

import javax.persistence.Table;
import java.util.Date;

/**
 * 门诊处方费用
 */
@Table(name = "hisrun.v_yzcx_mzcf")
public class FYXXmenzhenchufang {
    private Date kfrq;//开方日期
    private Date fyrq;//发药日期
    private String  ks;//科室
    private Double hjje;//合计金额

    public Date getKfrq() {
        return kfrq;
    }

    public void setKfrq(Date kfrq) {
        this.kfrq = kfrq;
    }

    public Date getFyrq() {
        return fyrq;
    }

    public void setFyrq(Date fyrq) {
        this.fyrq = fyrq;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public Double getHjje() {
        return hjje;
    }

    public void setHjje(Double hjje) {
        this.hjje = hjje;
    }

    @Override
    public String toString() {
        return "FYXXmenzhenchufang{" +
                "kfrq=" + kfrq +
                ", fyrq=" + fyrq +
                ", ks='" + ks + '\'' +
                ", hjje=" + hjje +
                '}';
    }
}
