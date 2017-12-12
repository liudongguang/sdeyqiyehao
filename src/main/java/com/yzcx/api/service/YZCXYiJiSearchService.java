package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;

import java.util.Map;

public interface YZCXYiJiSearchService {

    Map<String,Object> getIndexChart(YZCXSearchParam param);
}
