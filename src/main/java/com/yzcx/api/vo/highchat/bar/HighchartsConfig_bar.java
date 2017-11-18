package com.yzcx.api.vo.highchat.bar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yzcx.api.vo.highchat.*;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HighchartsConfig_bar {
    private Chart chart = new Chart();
    private Credits credits = new Credits();//不显示版权
    private Exporting exporting = new Exporting();//不显示导出
    private Title title = new Title();
    private XAxis xAxis=new XAxis();
    private YAxis yAxis=new YAxis();
    private List<Series_bar> series = new ArrayList<>();
    private PlotOptions_bar plotOptions = new PlotOptions_bar();

    public HighchartsConfig_bar() {
        chart.setType("bar");
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



    public List<Series_bar> getSeries() {
        return series;
    }

    public void setSeries(List<Series_bar> series) {
        this.series = series;
    }

    public PlotOptions_bar getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(PlotOptions_bar plotOptions) {
        this.plotOptions = plotOptions;
    }
}
