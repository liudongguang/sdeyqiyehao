package com.yzcx.api.util;

import com.yzcx.api.vo.highchat.XAxis;
import com.yzcx.api.vo.highchat.YAxis;
import com.yzcx.api.vo.highchat.bar.HighchartsConfig_bar;
import com.yzcx.api.vo.highchat.bar.Series_bar;

import java.util.List;

public class HighChartBuilder {
    public final static HighchartsConfig_bar builderHighchartsConfig_bar(List<String> categories,String yTitle,List<Series_bar> barData){
        HighchartsConfig_bar barChart=new HighchartsConfig_bar();
        XAxis xAxis = barChart.getxAxis();
        xAxis.setCategories(categories);
        YAxis yAxis = barChart.getyAxis();
        yAxis.getTitle().setText(yTitle);
        List<Series_bar> series = barChart.getSeries();
        barData.forEach(Series_bar->{
            series.add(Series_bar);
        });
        return barChart;
    }
    public final static Series_bar builderSeries_bar(String name,List<Number> number){
        Series_bar sbar=new Series_bar();
        sbar.setName(name);
        final List<? super Number> data = sbar.getData();
        number.forEach(num->{
            data.add(num);
        });
        return sbar;
    }
}
