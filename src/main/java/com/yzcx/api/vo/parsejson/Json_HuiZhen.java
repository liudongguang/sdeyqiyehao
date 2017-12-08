package com.yzcx.api.vo.parsejson;

import com.yzcx.api.vo.FYXXModle;
import com.yzcx.api.vo.HzxxModle;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class Json_HuiZhen {
    private HzxxModle data;

    public HzxxModle getData() {
        return data;
    }

    public void setData(HzxxModle data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_HuiZhen{" +
                "data=" + data +
                '}';
    }
}
