package com.yzcx.api.vo.parsejson;

import com.yzcx.api.vo.SSXXDisplayModle;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class Json_ShouShuPage {
    private SSXXDisplayModle data;

    public SSXXDisplayModle getData() {
        return data;
    }

    public void setData(SSXXDisplayModle data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_ShouShuPage{" +
                "data=" + data +
                '}';
    }
}
