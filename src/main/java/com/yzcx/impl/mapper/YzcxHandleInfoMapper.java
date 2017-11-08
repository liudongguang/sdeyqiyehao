package com.yzcx.impl.mapper;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.vo.YZCXSearchParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface YzcxHandleInfoMapper extends Mapper<YzcxHandleInfo> {
    void batchInsert(List<YzcxHandleInfo> list);
    List<YzcxHandleInfo> montho_mzinfo(YZCXSearchParam param);
}