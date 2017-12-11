package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.HzxxIndex;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;

import java.text.ParseException;
import java.util.Map;

public interface YZCXHuiZhenSearchService {

    HzxxIndex getIndexHuiZhenForDay(YZCXSearchParam param) throws ParseException;

    Map<String,Object> getIndexChart(YZCXSearchParam param) throws ParseException;


    Map<String,Object> getMonthChart(YZCXSearchParam param);
}
