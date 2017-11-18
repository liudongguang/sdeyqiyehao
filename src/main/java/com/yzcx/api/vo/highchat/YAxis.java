package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YAxis {
    private Title title=new Title();
    private Integer min=0;
    private List<YAxis_PlotLine> plotLines=new ArrayList<>();
    private Axis_labels labels=new Axis_labels();


    public Axis_labels getLabels() {
        return labels;
    }

    public void setLabels(Axis_labels labels) {
        this.labels = labels;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public List<YAxis_PlotLine> getPlotLines() {
        return plotLines;
    }

    public void setPlotLines(List<YAxis_PlotLine> plotLines) {
        this.plotLines = plotLines;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    ///标示线是用来标记坐标轴上特殊值的一条直线，在绘图区内绘制一条自定义的线。标示线是个数组，即可以配置多个标示线。
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class YAxis_PlotLine{
        private Integer value;
        private Integer width;
        private String color;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
