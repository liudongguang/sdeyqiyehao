package com.yzcx.api.vo;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiuDongguang on 2017/11/3.
 */
@Table(name = "hisrun.v_yzcx_zybr")
public class ZybrInfo {
    private String zyh;
    private String zyhm;
    private String brxm;
    private String brxb;
    private Date csny;
    private String hkdz;
    private String brqk;
    private String brks;
    private String brbq;
    private Date ryrq;
    private Date cyrq;
    private String cyfs;

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
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

    public Date getCsny() {
        return csny;
    }

    public void setCsny(Date csny) {
        this.csny = csny;
    }

    public String getHkdz() {
        return hkdz;
    }

    public void setHkdz(String hkdz) {
        this.hkdz = hkdz;
    }

    public String getBrqk() {
        return brqk;
    }

    public void setBrqk(String brqk) {
        this.brqk = brqk;
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

    public String getCyfs() {
        return cyfs;
    }

    public void setCyfs(String cyfs) {
        this.cyfs = cyfs;
    }
}
