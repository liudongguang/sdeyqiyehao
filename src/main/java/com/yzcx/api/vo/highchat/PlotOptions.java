package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/5/25.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotOptions {
    private PlotOptions_spline spline=new PlotOptions_spline();

    public PlotOptions_spline getSpline() {
        return spline;
    }

    public void setSpline(PlotOptions_spline spline) {
        this.spline = spline;
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
}
