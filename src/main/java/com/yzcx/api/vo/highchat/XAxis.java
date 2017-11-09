package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class XAxis {
    private String type;
    private List<String> categories;
    private Axis_labels labels;
    private List<PlotBands> plotBands;
    private DateTimeLabelFormats dateTimeLabelFormats=new  DateTimeLabelFormats();


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PlotBands> getPlotBands() {
        return plotBands;
    }

    public void setPlotBands(List<PlotBands> plotBands) {
        this.plotBands = plotBands;
    }

    public Axis_labels getLabels() {
        return labels;
    }

    public void setLabels(Axis_labels labels) {
        this.labels = labels;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public DateTimeLabelFormats getDateTimeLabelFormats() {
        return dateTimeLabelFormats;
    }

    public void setDateTimeLabelFormats(DateTimeLabelFormats dateTimeLabelFormats) {
        this.dateTimeLabelFormats = dateTimeLabelFormats;
    }
}
