package com.yzcx.api.vo.parsejson;



import com.yzcx.api.vo.YiJiInfo;

import java.util.List;


public class Json_HisOffice {
    private List<YiJiInfo> data;

    public List<YiJiInfo> getData() {
        return data;
    }

    public void setData(List<YiJiInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json_HisOffice{" +
                "data=" + data +
                '}';
    }
}
