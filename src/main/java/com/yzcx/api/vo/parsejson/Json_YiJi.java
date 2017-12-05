package com.yzcx.api.vo.parsejson;

import com.yzcx.api.vo.YIJIModle;
import com.yzcx.api.vo.ZYXXModle;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class Json_YiJi {
    private YIJIModle data;

    public YIJIModle getData() {
        return data;
    }

    public void setData(YIJIModle data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_YiJi{" +
                "data=" + data +
                '}';
    }
}
