package com.yzcx.api.vo;

import java.util.Date;

public class ZYXXzhuanke {
    private String zhuangchukeshi;
    private String zhuangrukeshi;
    private Date zhuankeshijian;

    private String zhuankeshijianStr;

    public String getZhuankeshijianStr() {
        return zhuankeshijianStr;
    }

    public void setZhuankeshijianStr(String zhuankeshijianStr) {
        this.zhuankeshijianStr = zhuankeshijianStr;
    }

    public String getZhuangchukeshi() {
        return zhuangchukeshi;
    }

    public void setZhuangchukeshi(String zhuangchukeshi) {
        this.zhuangchukeshi = zhuangchukeshi;
    }

    public String getZhuangrukeshi() {
        return zhuangrukeshi;
    }

    public void setZhuangrukeshi(String zhuangrukeshi) {
        this.zhuangrukeshi = zhuangrukeshi;
    }

    public Date getZhuankeshijian() {
        return zhuankeshijian;
    }

    public void setZhuankeshijian(Date zhuankeshijian) {
        this.zhuankeshijian = zhuankeshijian;
    }

    @Override
    public String toString() {
        return "ZYXXzhuanke{" +
                "zhuangchukeshi='" + zhuangchukeshi + '\'' +
                ", zhuangrukeshi='" + zhuangrukeshi + '\'' +
                ", zhuankeshijian=" + zhuankeshijian +
                '}';
    }
}
