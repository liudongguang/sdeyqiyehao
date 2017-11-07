package com.yzcx.api.vo.parsejson;

import com.ldg.api.vo.ResultMsg2;
import com.yzcx.api.vo.MenZhenLiang;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/11/6.
 */
public class Json_Menzhen{
    private List<MenZhenLiang> data;

    public List<MenZhenLiang> getData() {
        return data;
    }

    public void setData(List<MenZhenLiang> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_Menzhen{" +
                "data=" + data +
                '}';
    }
}
