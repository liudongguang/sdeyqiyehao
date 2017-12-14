package com.yzcx.impl.mapper;

import com.yzcx.api.bo.FbaiduParam;
import com.yzcx.api.po.YzcxHandleInfo;
import com.yzcx.api.po.YzcxHisoffice;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface YzcxHisofficeMapper extends Mapper<YzcxHisoffice> {
    void batchInsert(List<YzcxHisoffice> offices);

    int deleteAll();

    List<YzcxHisoffice> searchKS(FbaiduParam fbaiduParam);
}