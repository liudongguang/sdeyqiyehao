package com.yzcx.api.service;

import com.yzcx.api.bo.FbaiduParam;
import com.yzcx.api.po.YzcxHisoffice;

import java.util.List;

public interface YZCXHisOfficeService {
    void batchInsert(List<YzcxHisoffice> list);

    int deleteAll();

    List<YzcxHisoffice> searchKS(FbaiduParam fbaiduParam);
}
