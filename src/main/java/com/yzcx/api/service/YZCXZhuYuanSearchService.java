package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;

import java.text.ParseException;
import java.util.Map;

public interface YZCXZhuYuanSearchService {
    /**
     * 根据日期获取当天的数据
     * @param param
     * @return
     */
    ZyxxIndex getIndexZhuYuanForDay(YZCXSearchParam param) throws ParseException;

    /**
     * 住院视图
     * @param param
     * @return
     */
    Map<String,Object> getIndexChart(YZCXSearchParam param) throws ParseException;

    /**
     * 住院月
     * @param param
     * @return
     */
    ZyxxIndex getIndexZhuYuanForYue(YZCXSearchParam param) throws ParseException;

    /**
     * 住院月图表
     * @param yzcxSearchParam
     * @return
     */
    Map<String,Object> zhuyuan_yue_chart(YZCXSearchParam yzcxSearchParam) throws ParseException;
}
