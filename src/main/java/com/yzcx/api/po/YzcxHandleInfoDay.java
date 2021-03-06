package com.yzcx.api.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzcx_handle_info_day")
public class YzcxHandleInfoDay {
    @Id
    private Integer uid;

    private Date handledate;

    private Double count;

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
    public Double getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Double count) {
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

    @Override
    public String toString() {
        return "YzcxHandleInfoDay{" +
                "uid=" + uid +
                ", handledate=" + handledate +
                ", count=" + count +
                ", handletype=" + handletype +
                ", name='" + name + '\'' +
                '}';
    }
}