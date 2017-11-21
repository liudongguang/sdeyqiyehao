package com.yzcx.api.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzcx_handle_importdate")
public class YzcxHandleImportdate {
    @Id
    private Integer uid;

    private Date importdate;

    @Column(name = "importType")
    private Integer importtype;

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
     * @return importdate
     */
    public Date getImportdate() {
        return importdate;
    }

    /**
     * @param importdate
     */
    public void setImportdate(Date importdate) {
        this.importdate = importdate;
    }

    /**
     * @return importType
     */
    public Integer getImporttype() {
        return importtype;
    }

    /**
     * @param importtype
     */
    public void setImporttype(Integer importtype) {
        this.importtype = importtype;
    }
}