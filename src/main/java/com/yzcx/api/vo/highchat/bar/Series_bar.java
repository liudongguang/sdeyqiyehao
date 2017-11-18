package com.yzcx.api.vo.highchat.bar;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series_bar {
    private String name;
    private List<? super Number> data=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? super Number> getData() {
        return data;
    }

    public void setData(List<? super Number> data) {
        this.data = data;
    }
}
