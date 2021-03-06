package com.yzcx.api.vo.highchat.pie;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tooltip_pie2 {
    private String valueSuffix;
    private String xDateFormat="%Y-%m-%d %H:%M";
    private String headerFormat="{series.name}<br>";
    private String pointFormat="{point.name}: <b>{point.y}";  //放在圆饼上的显示


    public String getxDateFormat() {
        return xDateFormat;
    }

    public void setxDateFormat(String xDateFormat) {
        this.xDateFormat = xDateFormat;
    }

    public String getHeaderFormat() {
        return headerFormat;
    }

    public void setHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
    }

    public String getPointFormat() {
        return pointFormat;
    }

    public void setPointFormat(String pointFormat) {
        this.pointFormat = pointFormat;
    }

    public String getValueSuffix() {
        return valueSuffix;
    }

    public void setValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
    }
}
