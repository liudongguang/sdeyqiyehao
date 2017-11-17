package com.yzcx.api.vo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiuDongguang on 2017/10/31.
 */
@Table(name = "hisrun.V_YZCX_MZXX_jbzb")
public class JBZDLiang {

    @Column(name = "RQ")
    private Date rq;
    private String rqStr;
    @Column(name = "JBMC")
    private String jbmc;



    public String getRqStr() {
        return rqStr;
    }

    public void setRqStr(String rqStr) {
        this.rqStr = rqStr;
    }

    public Date getRq() {
        return rq;
    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public String getJbmc() {
        return jbmc;
    }

    public void setJbmc(String jbmc) {
        this.jbmc = jbmc;
    }

    @Override
    public String toString() {
        return "JBZDLiang{" +
                "rq=" + rq +
                ", rqStr='" + rqStr + '\'' +
                ", jbmc='" + jbmc + '\'' +
                '}';
    }
}
