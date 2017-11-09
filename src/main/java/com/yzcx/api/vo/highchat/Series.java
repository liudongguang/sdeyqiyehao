package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series {
    private String name;
    private List<? extends Number> data;
    private String type;
    private Long pointStart;
    private Long pointInterval;

    public Long getPointStart() {
        return pointStart;
    }

    public void setPointStart(Long pointStart) {
        this.pointStart = pointStart;
    }

    public Long getPointInterval() {
        return pointInterval;
    }

    public void setPointInterval(Long pointInterval) {
        this.pointInterval = pointInterval;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? extends Number> getData() {
        return data;
    }

    public void setData(List<? extends Number> data) {
        this.data = data;
    }
}
