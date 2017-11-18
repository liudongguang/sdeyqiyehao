package com.yzcx.api.vo.highchat.column;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotOptions_column {
    private PlotOptions_column_series series;
    public PlotOptions_column_series getSeries() {
        return series;
    }

    public void setSeries(PlotOptions_column_series series) {
        this.series = series;
    }

}
