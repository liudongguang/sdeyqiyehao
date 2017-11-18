package com.yzcx.api.vo.highchat.pie;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/5/25.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotOptions_pie {
    private PlotOptions_pie_attr pie=new PlotOptions_pie_attr();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class PlotOptions_pie_attr{
        private boolean allowPointSelect=true;
        private String cursor="pointer";
        private PlotOptions_pie_attr_dataLabels dataLabels=new PlotOptions_pie_attr_dataLabels();

        public boolean isAllowPointSelect() {
            return allowPointSelect;
        }

        public void setAllowPointSelect(boolean allowPointSelect) {
            this.allowPointSelect = allowPointSelect;
        }

        public String getCursor() {
            return cursor;
        }

        public void setCursor(String cursor) {
            this.cursor = cursor;
        }

        public PlotOptions_pie_attr_dataLabels getDataLabels() {
            return dataLabels;
        }

        public void setDataLabels(PlotOptions_pie_attr_dataLabels dataLabels) {
            this.dataLabels = dataLabels;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class PlotOptions_pie_attr_dataLabels{
           private boolean enabled=true;
           private String format="<b>{point.name}</b>: {point.percentage:.1f} %"; //指出来的显示

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }
    }

    public PlotOptions_pie_attr getPie() {
        return pie;
    }

    public void setPie(PlotOptions_pie_attr pie) {
        this.pie = pie;
    }
}
