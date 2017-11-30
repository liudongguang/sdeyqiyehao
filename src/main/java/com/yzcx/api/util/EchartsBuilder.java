package com.yzcx.api.util;

import com.github.abel533.echarts.Grid;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EchartsBuilder {
     final static  String[] color={"#7cb5ec"};
     final static String baise="#ffffff";
    /**
     *
     * @param title
     * @param subtext
     * @param category
     * @param nameAndData
     * @return
     */
    public static GsonOption buildEchartOption_line(String title,String subtext,List<String> category,Map<String,List<Number>> nameAndData){
        GsonOption option=new GsonOption();
        option.title().text(title).subtext(subtext);
        option.tooltip().trigger(Trigger.axis);
        option.backgroundColor(baise);
        //option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        List<String> legend=new ArrayList<>();
        List<Series> series=new ArrayList<>();
        ItemStyle itemStyle=new ItemStyle();
        itemStyle.normal().setColor(color[0]);
        nameAndData.forEach((barname,barDataList)->{
            legend.add(barname);
            Line line = new Line(barname);
            line.data(barDataList.toArray());
            line.itemStyle(itemStyle);
            series.add(line);
        });
        option.legend(legend);
        option.series(series);
        option.yAxis(new ValueAxis());
        //////////////////////////////////////////////
        List<Axis> axes = option.xAxis();
        Axis xAxis=new CategoryAxis();
        axes.add(xAxis);
        xAxis.data(category.toArray());
        return option;
    }
    /**
     *
     * @param title
     * @param subtext
     * @param category
     * @param nameAndData
     * @param hengshu  true  竖着显示   false横着显示
     * @return
     */
    public static GsonOption buildEchartOption_bar(String title,String subtext,List<String> category,Map<String,List<Number>> nameAndData,boolean hengshu){
        GsonOption option=new GsonOption();
        option.title().text(title).subtext(subtext);
        option.tooltip().trigger(Trigger.axis);
        option.backgroundColor(baise);
        //option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        List<String> legend=new ArrayList<>();
        List<Series> series=new ArrayList<>();
        ItemStyle itemStyle=new ItemStyle();
        itemStyle.normal().setColor(color[0]);
        nameAndData.forEach((barname,barDataList)->{
            legend.add(barname);
            Bar bar = new Bar(barname);
            bar.data(barDataList.toArray());
            bar.itemStyle(itemStyle);
            series.add(bar);
        });
        option.legend(legend);
        option.series(series);
        if (hengshu){
            option.xAxis(new CategoryAxis().data(category.toArray()));
            option.yAxis(new ValueAxis());
        }else{
            option.xAxis(new ValueAxis());
            option.yAxis(new CategoryAxis().data(category.toArray()).inverse(true));//inverse反转
            Grid grid=new Grid();
            grid.setLeft(80);
            option.grid(grid);
        }
        return option;
    }

    public static GsonOption getEchartOption(){
        GsonOption option=new GsonOption();
        option.title().text("某地区蒸发量和降水量").subtext("纯属虚构");
        option.tooltip().trigger(Trigger.axis);
        option.legend("蒸发量", "降水量");
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar).show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        option.xAxis(new CategoryAxis().data("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"));
        option.yAxis(new ValueAxis());

        Bar bar = new Bar("蒸发量");
        bar.data(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3);
        bar.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
        bar.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        Bar bar2 = new Bar("降水量");
        List<Double> list = Arrays.asList(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3);
        bar2.setData(list);
        bar2.markPoint().data(new PointData("年最高", 182.2).xAxis(7).yAxis(183).symbolSize(18), new PointData("年最低", 2.3).xAxis(11).yAxis(3));
        bar2.markLine().data(new PointData().type(MarkType.average).name("平均值"));

        option.series(bar, bar2);
        return option;
    }
}
