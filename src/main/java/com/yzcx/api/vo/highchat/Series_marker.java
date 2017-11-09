package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/6/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series_marker {
    private String symbol;
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        StringBuilder sbd=new StringBuilder();
        this.symbol = sbd.append("url(").append(symbol).append(")").toString();
    }
}
