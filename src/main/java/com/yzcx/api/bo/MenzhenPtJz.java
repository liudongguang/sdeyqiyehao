package com.yzcx.api.bo;

public class MenzhenPtJz {
    private Integer menzhennum;
    private Integer jizhennum;
    private String ksName;

    public Integer getZongnum(){
        return menzhennum+jizhennum;
    }

    public Integer getMenzhennum() {
        return menzhennum;
    }

    public void setMenzhennum(Integer menzhennum) {
        this.menzhennum = menzhennum;
    }

    public Integer getJizhennum() {
        return jizhennum;
    }

    public void setJizhennum(Integer jizhennum) {
        this.jizhennum = jizhennum;
    }

    public String getKsName() {
        return ksName;
    }

    public void setKsName(String ksName) {
        this.ksName = ksName;
    }

    @Override
    public String toString() {
        return "MenzhenPtJz{" +
                "menzhennum=" + menzhennum +
                ", jizhennum=" + jizhennum +
                ", ksName='" + ksName + '\'' +
                '}';
    }
}
