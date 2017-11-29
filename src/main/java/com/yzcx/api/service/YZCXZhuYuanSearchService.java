package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;

import java.util.Map;

public interface YZCXZhuYuanSearchService {
    /**
     * 根据日期获取当天的数据
     * @param param
     * @return
     */
    ZyxxIndex getIndexZhuYuanForDay(YZCXSearchParam param);

    /**
     * 住院视图
     * @param param
     * @return
     */
    Map<String,Object> getIndexChart(YZCXSearchParam param);
}
