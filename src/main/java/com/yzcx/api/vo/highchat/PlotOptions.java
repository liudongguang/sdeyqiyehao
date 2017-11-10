package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/5/25.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotOptions {
    private PlotOptions_spline spline=new PlotOptions_spline();
    private PlotOptions_column column=new PlotOptions_column();
    private PlotOptions_bar bar=new PlotOptions_bar();

    public PlotOptions_bar getBar() {
        return bar;
    }

    public void setBar(PlotOptions_bar bar) {
        this.bar = bar;
    }

    public PlotOptions_spline getSpline() {
        return spline;
    }

    public void setSpline(PlotOptions_spline spline) {
        this.spline = spline;
    }

    public PlotOptions_column getColumn() {
        return column;
    }

    public void setColumn(PlotOptions_column column) {
        this.column = column;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class PlotOptions_spline {
          private PlotOptions_marker marker=new PlotOptions_marker();

        public PlotOptions_marker getMarker() {
            return marker;
        }

        public void setMarker(PlotOptions_marker marker) {
            this.marker = marker;
        }
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class PlotOptions_marker{
        private boolean enabled=true;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class PlotOptions_column{
        private String stacking="normal";

        public String getStacking() {
            return stacking;
        }

        public void setStacking(String stacking) {
            this.stacking = stacking;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class PlotOptions_bar{
        private PlotOptions_bar_dataLabels dataLabels=new PlotOptions_bar_dataLabels();

        public PlotOptions_bar_dataLabels getDataLabels() {
            return dataLabels;
        }

        public void setDataLabels(PlotOptions_bar_dataLabels dataLabels) {
            this.dataLabels = dataLabels;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class PlotOptions_bar_dataLabels{
        private Boolean enabled=true;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

}
