package com.yzcx.api.service;

import com.yzcx.api.po.YzcxHandleInfoMonth;
import com.yzcx.api.vo.YZCXSearchParam;

import java.util.List;

public interface YZCXCommonService {
    List<YzcxHandleInfoMonth> getMonthDataByParam(YZCXSearchParam yzcxSearchParam);
}
