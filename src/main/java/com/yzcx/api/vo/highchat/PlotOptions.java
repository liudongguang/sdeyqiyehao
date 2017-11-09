package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/5/25.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotOptions {
    private PlotOptions_spline spline=new PlotOptions_spline();
    private PlotOptions_column column=new PlotOptions_column();

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

}
