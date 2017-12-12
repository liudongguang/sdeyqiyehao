package com.yzcx.api.vo.parsejson;

import com.yzcx.api.vo.YIJIHuLiModle;

public class Json_Yijihuli {
    private YIJIHuLiModle data;

    public YIJIHuLiModle getData() {
        return data;
    }

    public void setData(YIJIHuLiModle data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_Yijihuli{" +
                "data=" + data +
                '}';
    }
}
