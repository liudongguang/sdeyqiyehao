package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.YiJiMonthData;

import java.util.Map;

public interface YZCXYiJiSearchService {

    Map<String,Object> getIndexChart(YZCXSearchParam param);

    YiJiMonthData getMonthChartData(YZCXSearchParam yzcxSearchParam);

    Map<String,Object> monthChartPage_chart(YZCXSearchParam yzcxSearchParam);
}
