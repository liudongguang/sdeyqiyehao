package com.yzcx.api.po;

import javax.persistence.*;

@Table(name = "yzcx_hisoffice")
public class YzcxHisoffice {
    private Integer uid;

    private String kspinyin;

    private String ksname;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return kspinyin
     */
    public String getKspinyin() {
        return kspinyin;
    }

    /**
     * @param kspinyin
     */
    public void setKspinyin(String kspinyin) {
        this.kspinyin = kspinyin;
    }

    /**
     * @return ksname
     */
    public String getKsname() {
        return ksname;
    }

    /**
     * @param ksname
     */
    public void setKsname(String ksname) {
        this.ksname = ksname;
    }

    @Override
    public String toString() {
        return "YzcxHisoffice{" +
                "uid=" + uid +
                ", kspinyin='" + kspinyin + '\'' +
                ", ksname='" + ksname + '\'' +
                '}';
    }
}