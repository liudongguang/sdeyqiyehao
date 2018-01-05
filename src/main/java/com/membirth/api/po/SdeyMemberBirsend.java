package com.membirth.api.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sdey_member_birsend")
public class SdeyMemberBirsend {
    private Integer uid;

    /**
     * 生日祝福发送日期
     */
    private Date senddate;

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
     * 获取生日祝福发送日期
     *
     * @return senddate - 生日祝福发送日期
     */
    public Date getSenddate() {
        return senddate;
    }

    /**
     * 设置生日祝福发送日期
     *
     * @param senddate 生日祝福发送日期
     */
    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }
}