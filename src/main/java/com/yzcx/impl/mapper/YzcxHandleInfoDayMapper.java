package com.yzcx.impl.mapper;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface YzcxHandleInfoDayMapper extends Mapper<YzcxHandleInfoDay> {
    void batchInsert(List<YzcxHandleInfo> yzcxHandleInfos);
}