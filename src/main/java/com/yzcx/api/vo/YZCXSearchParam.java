package com.yzcx.api.vo;

import com.yzcx.api.util.LdgDateUtil;

import java.util.Date;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class YZCXSearchParam {
    private Date start;
    private Date end;
    private Integer handletype;

    public Integer getHandletype() {
        return handletype;
    }

    public void setHandletype(Integer handletype) {
        this.handletype = handletype;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "YZCXSearchParam{" +
                "start=" + start +
                ", end=" + end +
                ", handletype=" + handletype +
                '}';
    }
}
