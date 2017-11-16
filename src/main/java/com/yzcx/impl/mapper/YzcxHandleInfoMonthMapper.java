package com.yzcx.impl.mapper;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHandleInfoDay;
import com.yzcx.api.po.YzcxHandleInfoMonth;
import com.yzcx.api.vo.YZCXSearchParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface YzcxHandleInfoMonthMapper extends Mapper<YzcxHandleInfoMonth> {
    void batchInsert(List<YzcxHandleInfo> menzhen);

    int selectImportState(YZCXSearchParam param);

    List<YzcxHandleInfoMonth> selectByDateAndType(YZCXSearchParam param);
}