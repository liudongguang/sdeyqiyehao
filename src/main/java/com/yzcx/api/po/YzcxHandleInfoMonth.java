package com.yzcx.api.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzcx_handle_info_month")
public class YzcxHandleInfoMonth {
    @Id
    private Integer uid;

    private Date handledate;

    private Integer count;

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
     * @return handledate
     */
    public Date getHandledate() {
        return handledate;
    }

    /**
     * @param handledate
     */
    public void setHandledate(Date handledate) {
        this.handledate = handledate;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return handletype
     */
    public Integer getHandletype() {
        return handletype;
    }

    /**
     * @param handletype
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