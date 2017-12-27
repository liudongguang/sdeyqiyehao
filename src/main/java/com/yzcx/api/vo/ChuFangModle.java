package com.yzcx.api.vo;

import java.util.List;

public class ChuFangModle {
    private List<FYXXmenzhenchufang> data;

    public List<FYXXmenzhenchufang> getData() {
        return data;
    }

    public void setData(List<FYXXmenzhenchufang> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChuFangModle{" +
                "data=" + data +
                '}';
    }
}
