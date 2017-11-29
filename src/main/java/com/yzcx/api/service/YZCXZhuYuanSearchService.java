package com.yzcx.api.service;

import com.yzcx.api.vo.YZCXSearchParam;
import com.yzcx.api.vo.yzcxdisplay.ZyxxIndex;

public interface YZCXZhuYuanSearchService {
    /**
     * 根据日期获取当天的数据
     * @param param
     * @return
     */
    ZyxxIndex getIndexZhuYuanForDay(YZCXSearchParam param);
}
