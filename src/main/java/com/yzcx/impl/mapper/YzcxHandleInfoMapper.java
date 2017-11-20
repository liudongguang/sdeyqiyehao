package com.yzcx.impl.mapper;

import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.vo.YZCXSearchParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface YzcxHandleInfoMapper extends Mapper<YzcxHandleInfo> {
    void batchInsert(List<YzcxHandleInfo> list);
    List<YzcxHandleInfo> montho_mzinfo(YZCXSearchParam param);

    /**
     * 根据日期，类型获取信息
     * @param yzcxSearchParam
     * @return
     */
    List<YzcxHandleInfo> selectByDateAndType(YZCXSearchParam yzcxSearchParam);
}