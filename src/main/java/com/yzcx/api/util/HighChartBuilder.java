package com.yzcx.api.util;

import com.ldg.api.sms.LdgMD5Util;
import com.yzcx.api.vo.highchat.XAxis;
import com.yzcx.api.vo.highchat.YAxis;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.bar.PlotOptions_bar_series;
import com.yzcx.api.vo.highchat.bar.Series_bar;
import com.yzcx.api.vo.highchat.pie.*;

import java.util.List;
import java.util.Map;

public class HighChartBuilder {
    public final static HighchartsConfig_bar builderHighchartsConfig_bar(List<String> categories, String yTitle, Map<String,List<Number>> nameAndData, boolean isDieJia) {
        HighchartsConfig_bar barChart = new HighchartsConfig_bar();
        XAxis xAxis = barChart.getxAxis();
        xAxis.setCategories(categories);
        YAxis yAxis = barChart.getyAxis();
        yAxis.getTitle().setText(yTitle);
        if (isDieJia) {
            PlotOptions_bar_series plotOptions_bar_series = new PlotOptions_bar_series();
            plotOptions_bar_series.setStacking("normal"); //叠加
            barChart.getPlotOptions().setSeries(plotOptions_bar_series);
        }
        List<Series_bar> series = barChart.getSeries();
        nameAndData.forEach((Series_barName,Data)->{
            Series_bar series_bar=new Series_bar();
            series_bar.setName(Series_barName);
            series_bar.setData(Data);
            series.add(series_bar);
        });
        return barChart;

    }

    public final static HighchartsConfig_bar builderHighchartsConfig_bar(List<String> categories, String yTitle, Map<String,List<Number>> nameAndData) {
        return builderHighchartsConfig_bar(categories,yTitle,nameAndData,false);
    }
    public final static Series_bar builderSeries_bar(String name, List<Number> number) {
        Series_bar sbar = new Series_bar();
        sbar.setName(name);
        final List<? super Number> data = sbar.getData();
        number.forEach(num -> {
            data.add(num);
        });
        return sbar;
    }

    public static HighchartsConfig_pie2 builderHighchartsConfig_pie(String Title, Map<String,Map<String,Double>> nameAndData) {
        HighchartsConfig_pie2 hcfg = new HighchartsConfig_pie2();
        hcfg.getTitle().setText(Title);
        List<Series_pie> series = hcfg.getSeries();
        nameAndData.forEach((name,data)->{
            Series_pie series1 = new Series_pie();
            series1.setName(name);
            data.forEach((dataName,num)->{
                Series_pie_data sdata = new Series_pie_data();
                sdata.setName(dataName);
                sdata.setY(num);
                series1.getData().add(sdata);
            });
            series.add(series1);
        });
        return hcfg;
    }
}
