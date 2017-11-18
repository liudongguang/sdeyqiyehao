package com.yzcx.api.vo.highchat.bar;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotOptions_bar {
    private PlotOptions_bar_series series;
    public PlotOptions_bar_series getSeries() {
        return series;
    }

    public void setSeries(PlotOptions_bar_series series) {
        this.series = series;
    }

}
