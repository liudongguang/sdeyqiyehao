package com.yzcx.impl.mapper;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.vo.YZCXSearchParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface YzcxHandleInfoDayMapper extends Mapper<YzcxHandleInfoDay> {
    void batchInsert(List<YzcxHandleInfo> yzcxHandleInfos);

    int getMZDayCount(YZCXSearchParam param);

    int deleteByTime(YZCXSearchParam param);
}