package com.yzcx.api.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzcx_handle_info")
public class YzcxHandleInfo {
    @Id
    private Integer uid;

    /**
     * 处理的日期
     */
    private Date handledate;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 1.门诊  2.预约 3.疾病诊断
     */
    private Integer handletype;

    private String name;

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
     * 获取处理的日期
     *
     * @return handledate - 处理的日期
     */
    public Date getHandledate() {
        return handledate;
    }

    /**
     * 设置处理的日期
     *
     * @param handledate 处理的日期
     */
    public void setHandledate(Date handledate) {
        this.handledate = handledate;
    }

    /**
     * 获取数量
     *
     * @return count - 数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置数量
     *
     * @param count 数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取1.门诊  2.预约 3.疾病诊断
     *
     * @return handletype - 1.门诊  2.预约 3.疾病诊断
     */
    public Integer getHandletype() {
        return handletype;
    }

    /**
     * 设置1.门诊  2.预约 3.疾病诊断
     *
     * @param handletype 1.门诊  2.预约 3.疾病诊断
     */
    public void setHandletype(Integer handletype) {
        this.handletype = handletype;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}