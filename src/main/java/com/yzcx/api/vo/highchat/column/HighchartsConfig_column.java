package com.yzcx.api.vo.highchat.column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yzcx.api.vo.highchat.*;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HighchartsConfig_column {
    private Chart chart = new Chart();
    private Credits credits = new Credits();//不显示版权
    private Exporting exporting = new Exporting();//不显示导出
    private Title title = new Title();
    private XAxis xAxis=new XAxis();
    private YAxis yAxis=new YAxis();
    private List<Series_column> series = new ArrayList<>();
    private PlotOptions_column plotOptions = new PlotOptions_column();

    public HighchartsConfig_column() {
        chart.setType("column");
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



    public List<Series_column> getSeries() {
        return series;
    }

    public void setSeries(List<Series_column> series) {
        this.series = series;
    }

    public PlotOptions_column getPlotOptions() {
        return plotOptions;
    }

    public void setPlotOptions(PlotOptions_column plotOptions) {
        this.plotOptions = plotOptions;
    }
}
