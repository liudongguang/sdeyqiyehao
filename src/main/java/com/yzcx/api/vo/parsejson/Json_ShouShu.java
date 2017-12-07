package com.yzcx.api.vo.parsejson;

import com.yzcx.api.vo.SSXXModle;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class Json_ShouShu {
    private SSXXModle data;

    public SSXXModle getData() {
        return data;
    }

    public void setData(SSXXModle data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_ShouShu{" +
                "data=" + data +
                '}';
    }
}
