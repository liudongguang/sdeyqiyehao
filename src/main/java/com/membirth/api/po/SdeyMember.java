package com.membirth.api.po;

import javax.persistence.*;

@Table(name = "sdey_member")
public class SdeyMember {
    private Integer uid;

    /**
     * 工号
     */
    private String gonghao;

    /**
     * 姓名
     */
    private String xingming;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 生日 月
     */
    private Integer month;

    /**
     * 生日 日
     */
    private Integer day;

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
     * 获取工号
     *
     * @return gonghao - 工号
     */
    public String getGonghao() {
        return gonghao;
    }

    /**
     * 设置工号
     *
     * @param gonghao 工号
     */
    public void setGonghao(String gonghao) {
        this.gonghao = gonghao;
    }

    /**
     * 获取姓名
     *
     * @return xingming - 姓名
     */
    public String getXingming() {
        return xingming;
    }

    /**
     * 设置姓名
     *
     * @param xingming 姓名
     */
    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    /**
     * 获取身份证
     *
     * @return idcard - 身份证
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证
     *
     * @param idcard 身份证
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取生日 月
     *
     * @return month - 生日 月
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 设置生日 月
     *
     * @param month 生日 月
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 获取生日 日
     *
     * @return day - 生日 日
     */
    public Integer getDay() {
        return day;
    }

    /**
     * 设置生日 日
     *
     * @param day 生日 日
     */
    public void setDay(Integer day) {
        this.day = day;
    }
}