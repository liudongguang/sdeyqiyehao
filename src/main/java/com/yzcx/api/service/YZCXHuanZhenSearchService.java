package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.FeiYongHuiZong;

import java.text.ParseException;
import java.util.Map;

public interface YZCXHuanZhenSearchService {
    /**
     * 获取index患者
     * @param param
     * @return
     */
    Map<String,Object> getIndexChart(YZCXSearchParam param);

}
