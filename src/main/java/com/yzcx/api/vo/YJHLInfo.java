package com.yzcx.api.vo;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
@Table(name = "hisrun.v_yzcx_yjhl")
public class YJHLInfo {
    private Date rq;
    private String zyhm;
    private String brxm;
    private String brxb;
    private String brks;
    private String brbq;
    private Date kssj;
    private Date tzsj;
    private String yzmc;

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getBrxb() {
        return brxb;
    }

    public void setBrxb(String brxb) {
        this.brxb = brxb;
    }

    public String getBrks() {
        return brks;
    }

    public void setBrks(String brks) {
        this.brks = brks;
    }

    public String getBrbq() {
        return brbq;
    }

    public void setBrbq(String brbq) {
        this.brbq = brbq;
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getTzsj() {
        return tzsj;
    }

    public void setTzsj(Date tzsj) {
        this.tzsj = tzsj;
    }

    public String getYzmc() {
        return yzmc;
    }

    public void setYzmc(String yzmc) {
        this.yzmc = yzmc;
    }

    @Override
    public String toString() {
        return "YJHLInfo{" +
                "rq=" + rq +
                ", zyhm='" + zyhm + '\'' +
                ", brxm='" + brxm + '\'' +
                ", brxb='" + brxb + '\'' +
                ", brks='" + brks + '\'' +
                ", brbq='" + brbq + '\'' +
                ", kssj=" + kssj +
                ", tzsj=" + tzsj +
                ", yzmc='" + yzmc + '\'' +
                '}';
    }
}
