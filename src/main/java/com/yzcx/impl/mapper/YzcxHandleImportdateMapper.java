package com.yzcx.impl.mapper;

import com.yzcx.api.po.YzcxHandleImportdate;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface YzcxHandleImportdateMapper extends Mapper<YzcxHandleImportdate> {

    void batchInsert(List<Date> dateByBetween);
}