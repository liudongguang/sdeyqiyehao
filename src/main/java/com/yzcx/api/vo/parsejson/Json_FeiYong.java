package com.yzcx.api.vo.parsejson;

import com.yzcx.api.vo.FYXXModle;
import com.yzcx.api.vo.MenZhenLiang;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class Json_FeiYong {
    private FYXXModle data;

    public FYXXModle getData() {
        return data;
    }

    public void setData(FYXXModle data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_FeiYong{" +
                "data=" + data +
                '}';
    }
}
