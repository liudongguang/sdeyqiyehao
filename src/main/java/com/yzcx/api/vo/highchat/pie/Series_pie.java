package com.yzcx.api.vo.highchat.pie;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series_pie {
    private String name=" ";
    private List<Series_pie_data> data=new ArrayList<>();
    public List<Series_pie_data> getData() {
        return data;
    }

    public void setData(List<Series_pie_data> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
