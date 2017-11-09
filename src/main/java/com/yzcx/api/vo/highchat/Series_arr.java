package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series_arr {
    private String name;
    private List<Series_Data> data;
    private String type;
    private String color;
    private int lineWidth=1;
    private Series_marker marker;
    private String dashStyle;
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

    public String getDashStyle() {
        return dashStyle;
    }

    public void setDashStyle(String dashStyle) {
        this.dashStyle = dashStyle;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public Series_marker getMarker() {
        return marker;
    }

    public void setMarker(Series_marker marker) {
        this.marker = marker;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public List<Series_Data> getData() {
        return data;
    }

    public void setData(List<Series_Data> data) {
        this.data = data;
    }
}
