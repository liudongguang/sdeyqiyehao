package com.yzcx.api.vo.highchat.pie;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yzcx.api.vo.highchat.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HighchartsConfig_pie {
    private Chart chart = new Chart();
    private Credits credits = new Credits();//不显示版权
    private Exporting exporting = new Exporting();//不显示导出
    private Title title = new Title();
    private Tooltip_pie tooltip = new Tooltip_pie();
    private List<Series_pie> series = new ArrayList<>();
    private PlotOptions_pie plotOptions = new PlotOptions_pie();

    public HighchartsConfig_pie() {
        chart.setType("pie");
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Exporting getExporting() {
        return exporting;
    }

    public void setExporting(Exporting exporting) {
        this.exporting = exporting;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Tooltip_pie getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip_pie tooltip) {
        this.tooltip = tooltip;
    }

    public List<Series_pie> getSeries() {
        return series;
    }

    public void setSeries(List<Series_pie> series) {
        this.series = series;
    }

    public PlotOptions_pie getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(PlotOptions_pie plotOptions) {
        this.plotOptions = plotOptions;
    }
}
