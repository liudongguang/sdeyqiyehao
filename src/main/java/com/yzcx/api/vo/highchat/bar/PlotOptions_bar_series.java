package com.yzcx.api.vo.highchat.bar;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotOptions_bar_series {
    private String stacking;//normal   这个值会让图表条状重叠

    public String getStacking() {
        return stacking;
    }

    public void setStacking(String stacking) {
        this.stacking = stacking;
    }
}