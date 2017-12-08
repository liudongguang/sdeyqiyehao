package com.yzcx.api.vo;

import javax.persistence.Table;
import java.util.Date;

/**会诊信息
 * Created by LiuDongguang on 2017/11/3.
 */
@Table(name = "hisrun.v_yzcx_hzxx")
public class HzxxInfo {
    private String zyhm;
    private String brxm;
    private String brxb;
    private String brks;
    private Date sqsj;
    private String qrbz;
    private String sqks;
    private String qrgh;
    private Date qrsj;//确认的接受时间
    private String ks;//确认接受科室
    private String ygxm;//接受医生

    private String groupDateStr;

    public String getGroupDateStr() {
        return groupDateStr;
    }

    public void setGroupDateStr(String groupDateStr) {
        this.groupDateStr = groupDateStr;
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

    public Date getSqsj() {
        return sqsj;
    }

    public void setSqsj(Date sqsj) {
        this.sqsj = sqsj;
    }

    public String getQrbz() {
        return qrbz;
    }

    public void setQrbz(String qrbz) {
        this.qrbz = qrbz;
    }

    public String getSqks() {
        return sqks;
    }

    public void setSqks(String sqks) {
        this.sqks = sqks;
    }

    public String getQrgh() {
        return qrgh;
    }

    public void setQrgh(String qrgh) {
        this.qrgh = qrgh;
    }

    public Date getQrsj() {
        return qrsj;
    }

    public void setQrsj(Date qrsj) {
        this.qrsj = qrsj;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getYgxm() {
        return ygxm;
    }

    public void setYgxm(String ygxm) {
        this.ygxm = ygxm;
    }

    @Override
    public String toString() {
        return "HzxxInfo{" +
                "zyhm='" + zyhm + '\'' +
                ", brxm='" + brxm + '\'' +
                ", brxb='" + brxb + '\'' +
                ", brks='" + brks + '\'' +
                ", sqsj=" + sqsj +
                ", qrbz='" + qrbz + '\'' +
                ", sqks='" + sqks + '\'' +
                ", qrgh='" + qrgh + '\'' +
                ", qrsj=" + qrsj +
                ", ks='" + ks + '\'' +
                ", ygxm='" + ygxm + '\'' +
                '}';
    }
}
