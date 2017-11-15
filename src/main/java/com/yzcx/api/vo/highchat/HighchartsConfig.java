package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HighchartsConfig{
    private final static HighchartsConfig nullcig=new HighchartsConfig();
    static {
        nullcig.getTitle().setText("mytitle");
    }
    public static HighchartsConfig getNullcig() {
        return nullcig;
    }
    private Chart chart=new Chart();
    private Credits credits=new Credits();
    private Title title=new Title();
    private Subtitle subtitle=new Subtitle();
    private Exporting exporting=new Exporting();
    private XAxis xAxis=new XAxis();
    private YAxis yAxis=new YAxis();
    private Tooltip tooltip=new Tooltip();
    private Legend legend=new Legend();
    private List<Series> series=new ArrayList<>();
    private PlotOptions plotOptions;


    public Exporting getExporting() {
        return exporting;
    }

    public void setExporting(Exporting exporting) {
        this.exporting = exporting;
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public PlotOptions getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(PlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public YAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}
