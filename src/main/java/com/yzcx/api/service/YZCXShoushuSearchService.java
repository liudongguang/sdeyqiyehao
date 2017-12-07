package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.SsxxIndex;

import java.util.Map;

public interface YZCXShoushuSearchService {

    SsxxIndex getIndexData(YZCXSearchParam param);

    Map<String,Object> getIndexChart(YZCXSearchParam param);
}
