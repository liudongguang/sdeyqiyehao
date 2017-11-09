package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/6/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series_Data {
    private Long x;
    private Long y;
    private Series_marker marker;

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Series_marker getMarker() {
        return marker;
    }

    public void setMarker(Series_marker marker) {
        this.marker = marker;
    }
}
